package chat.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import chat.codec.ChatMessageProtocolCodecFactory;
import chat.message.DefaultChatMessage;

public class Client   extends IoHandlerAdapter{

	
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
		
	   new Client().start();
		
	}
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		DefaultChatMessage msg = new DefaultChatMessage();
		msg.setMessageType(1);
		msg.setText("xxxx");
		session.write(msg);
	}
	
	
}
