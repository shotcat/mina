package chat.client;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import chat.codec.ChatMessageProtocolCodecFactory;
import chat.constant.Constant;
import chat.message.DefaultChatMessage;
/***
 * 客户端-发送纯文本消息
 * @author gaoyuandong
 * @date   2015年7月20日 上午7:44:12
 * @mail   466862016@qq.com
 */
public class ClientText   extends IoHandlerAdapter{

	private static Logger logger = Logger.getLogger(ClientText.class);
	
	public void start() {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ChatMessageProtocolCodecFactory()));
		connector.setHandler(this);
		ConnectFuture connect = connector.connect(new InetSocketAddress("192.168.1.144", 6666));
		connect.awaitUninterruptibly();
		while(true) {
			
		}
	}
	public static void main(String[] args) {
		
	   new ClientText().start();
		
	}
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	   // byte[] bytes = FileUtil.File2byte("E:/sts/java-nio/src/main/java/text.txt");
		DefaultChatMessage msg = new DefaultChatMessage();
		msg.setMessageType(Constant.MESSAGE_TYPE_FULL_TXT);
		msg.setText("冬天欢迎你....");;
		session.write(msg);
	}
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		logger.info("接收到消息了...." + message);
	}
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("发送消息了...." + message);
	}
	
	
}
