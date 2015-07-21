package chat.client;

import java.io.File;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import chat.codec.ChatMessageProtocolCodecFactory;
import chat.constant.Constant;
import chat.message.ChatFileMesage;
/***
 * 客户端-发送带文件的消息
 * @author gaoyuandong
 * @date   2015年7月20日 上午7:43:34
 * @mail   466862016@qq.com
 */
public class ClientFile   extends IoHandlerAdapter{

	private static Logger logger = Logger.getLogger(ClientFile.class);
	private KeepAliveHandler keepAliveHandler;
	
	
	public void start() {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ChatMessageProtocolCodecFactory()));
		connector.setHandler(this);
		ConnectFuture connect = connector.connect(new InetSocketAddress("192.168.1.144", 6666));
	
		connect.addListener(new IoFutureListener<IoFuture>() {

			public void operationComplete(IoFuture future) {
				logger.info("已经成功建立连接");
//				IoSession session = future.getSession();
//				if (keepAliveHandler == null) {
//					keepAliveHandler = new KeepAliveHandler();
//					keepAliveHandler.runAlive();
//				}
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
		
	   new ClientFile().start();
		
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
		
		logger.info("接收到消息了...." + message);
	}
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("发送数据...." + message );
	}
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("sessionClosed");
	}
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.info("exceptionCaught" + cause.getMessage());
	}
	
	
}
