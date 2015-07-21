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

import chat.client.handler.KeepAliveHandler;
import chat.codec.ChatMessageProtocolCodecFactory;
import chat.constant.Constant;
import chat.message.ChatFileMesage;
/***
 * 客户端-发送带文件的消息
 * @author gaoyuandong
 * @date   2015年7月20日 上午7:43:34
 * @mail   466862016@qq.com
 */
public class ClientFileAlive   extends IoHandlerAdapter{
	
	
	private static Logger logger = Logger.getLogger(ClientFileAlive.class);
	private KeepAliveHandler keepAliveHandler;
	
	public void start() {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ChatMessageProtocolCodecFactory()));
		connector.setHandler(this);
		ConnectFuture connect = connector.connect(new InetSocketAddress("192.168.1.144", 6666));
	
		connect.addListener(new IoFutureListener<IoFuture>() {

			public void operationComplete(IoFuture future) {
				IoSession session = future.getSession();
				if (keepAliveHandler == null) {
					logger.info("准备发送心跳数据....");
					keepAliveHandler = new KeepAliveHandler(session);
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
		
	   new ClientFileAlive().start();
		
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
		
		System.err.println(session.getLocalAddress() + "接收到消息了....");
	}
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.err.println(message +"发送数据....");
	}
	
	
}
