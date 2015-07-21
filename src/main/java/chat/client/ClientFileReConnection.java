package chat.client;

import java.io.File;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import chat.adapter.ReConnectionFilterAdapter;
import chat.codec.ChatMessageProtocolCodecFactory;
import chat.constant.Constant;
import chat.listener.SessionStatusListener;
import chat.message.ChatFileMesage;
/***
 * 客户端-发送带文件的消息
 * 心跳机制并且断线重连功能
 * @author gaoyuandong
 * @date   2015年7月20日 上午7:43:34
 * @mail   466862016@qq.com
 */
public class ClientFileReConnection   extends IoHandlerAdapter{

	private SessionStatusListener sessionStatusListener;
	private KeepAliveMessageHandler keepAliveHandler;
	public void start() {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ChatMessageProtocolCodecFactory()));
		sessionStatusListener = new SessionStatusListener();
		connector.getFilterChain().addFirst("reConnection", new ReConnectionFilterAdapter(connector,sessionStatusListener));
		connector.setHandler(this);
		connector.setDefaultRemoteAddress(new InetSocketAddress("192.168.1.144", 6666));
		ConnectFuture connect = connector.connect();
		connect.addListener(new IoFutureListener<IoFuture>() {
			public void operationComplete(IoFuture future) {
				IoSession session = future.getSession();
				if (keepAliveHandler == null) {
					keepAliveHandler = new KeepAliveMessageHandler(sessionStatusListener);
					keepAliveHandler.runAlive();
				}
			}
		});
		connect.awaitUninterruptibly();
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		
	   new ClientFileReConnection().start();
		
	}
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	   // byte[] bytes = FileUtil.File2byte("E:/sts/java-nio/src/main/java/text.txt");
		ChatFileMesage msg = new ChatFileMesage();
		msg.setMessageType(Constant.MESSAGE_TYPE_FILE);
		msg.setFile(new File("E:\\sts\\java-nio\\src\\main\\java\\text.txt"));
		session.write(msg);
	}
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		System.err.println("接收到消息了...." + message);
	}
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.err.println("发送数据...." + message );
	}
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.err.println("sessionClosed");
	}
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
		System.err.println("exceptionCaught" + cause);
	}
	
	
}
