package chat.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import chat.codec.ChatMessageProtocolCodecFactory;
import chat.handler.ChatMessageHandler;
import chat.keepalive.ChatMessageKeepAliveMessageFactory;

/***
 *  消息服务类
 * @author gaoyuandong
 * @date   2015年7月20日 上午8:03:48
 * @mail   466862016@qq.com
 */
public class ChatServer {

	private String host;
	private int port;
	private NioSocketAcceptor acceptor;
	
	public ChatServer(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	
	public void start() throws IOException {
		//创建一个套接字接收器
				acceptor = new NioSocketAcceptor();
				//添加日志过滤器
				acceptor.getFilterChain().addLast("logger", new LoggingFilter());
				acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ChatMessageProtocolCodecFactory()));
				//创建心跳消息工厂
				ChatMessageKeepAliveMessageFactory messageKeepAliveMessageFactory = new ChatMessageKeepAliveMessageFactory();
				//创建心跳过滤器 在读通道空闲时候,指定时间间隔的
		        KeepAliveFilter keepAliveFilter = new KeepAliveFilter(messageKeepAliveMessageFactory,IdleStatus.READER_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
				//在读通道空闲时候，心跳包请求时间间隔
		        keepAliveFilter.setRequestInterval(15);
		        //心跳包请求后等待反馈超时时间
		        keepAliveFilter.setRequestTimeout(15);
		        //继续调用IOhandlerAdapter 中的sessionidle时间
		        keepAliveFilter.setForwardEvent(true);
		        acceptor.getFilterChain().addLast("keepAlive", keepAliveFilter);
		      //添加消息编码解码
				acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,30);
				acceptor.getSessionConfig().setReadBufferSize(2048);
				acceptor.setHandler(new ChatMessageHandler(acceptor));
				acceptor.bind(new InetSocketAddress(host, port));
				System.err.println("服务器已经启动....");
	}
	
	
	public static void main(String[] args) {
		
		try {
			new ChatServer("192.168.1.144", 6666).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
