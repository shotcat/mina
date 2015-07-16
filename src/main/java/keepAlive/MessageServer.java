package keepAlive;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MessageServer {

	private String host;
	private int port;
	private NioSocketAcceptor acceptor;
	public MessageServer(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		
		
	}
	
	public void start() throws Exception {
		
		//创建一个套接字接收器
		acceptor = new NioSocketAcceptor();
		//添加日志过滤器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		//添加消息编码解码
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		//创建心跳消息工厂
		MessageKeepAliveMessageFactory messageKeepAliveMessageFactory = new MessageKeepAliveMessageFactory();
		//创建心跳过滤器 在读通道空闲时候,指定时间间隔的
        KeepAliveFilter keepAliveFilter = new KeepAliveFilter(messageKeepAliveMessageFactory,IdleStatus.READER_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
		//在读通道空闲时候，心跳包请求时间间隔
        keepAliveFilter.setRequestInterval(10);
        //心跳包请求后等待反馈超时时间
        keepAliveFilter.setRequestTimeout(10);
        //继续调用IOhandlerAdapter 中的sessionidle时间
        keepAliveFilter.setForwardEvent(true);
        acceptor.getFilterChain().addLast("keepAlive", keepAliveFilter);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.setHandler(new MessageHandler(acceptor));
		acceptor.bind(new InetSocketAddress(host, port));
		System.err.println("服务器已经启动....");
		
	}
	
	
	
	public static void main(String[] args) {
		
		try {
			new MessageServer("localhost", 6666).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
