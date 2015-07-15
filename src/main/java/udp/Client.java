package udp;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

/***
 * UDP Client
 * @author gaoyuandong
 *
 */
public class Client extends IoHandlerAdapter {
	private IoSession session = null;
	public Client() {
		
		NioDatagramConnector connector = new NioDatagramConnector();
		connector.setHandler(this);
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		ConnectFuture future = connector.connect(new InetSocketAddress("localhost", 6666));
		System.err.println("wait for....");
		future.awaitUninterruptibly();
		
		future.addListener(new IoFutureListener<ConnectFuture>() {

			public void operationComplete(ConnectFuture future) {
				
				if (future.isConnected()) {
					session = future.getSession();
					sendData();
				} else {
					System.err.println("no connected .... exit!");
				}
			}

			private void sendData() {
				for (int i = 0; i < 100; i++) {
					session.write("dongtian");
					
				}
			}
		});
	}
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.err.println("sessionCreated");
	}
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("sessionOpened");
	}
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("sessionClosed");
	}
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("sessionIdle");
	}
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("exceptionCaught");
	}
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("messageReceived");
	}
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.err.println("messageSent");
	}

	
	public static void main(String[] args) {
		
		new Client();
	}
	
}
