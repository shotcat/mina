package chat.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import chat.codec.ChatMessageProtocolCodecFactory;
import chat.constant.Constant;
import chat.message.ChatFileMesage;
import chat.message.DefaultChatMessage;
import chat.util.FileUtil;

public class ClientFile   extends IoHandlerAdapter{

	
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
		
		System.err.println(message +"SSSSSSSSSSSSS");
		System.err.println("接收到消息了....");
	}
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.err.println(message +"发送数据....");
	}
	
	
}
