package time;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Client {

	private static long CONNECT_TIMEOUT = 5000;
	
	public static void main(String[] args) {
		
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
		connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		connector.getFilterChain().addLast("logger",new LoggingFilter());
		connector.setHandler(new ClientHandler());
		IoSession session = null;
		for (;;) {
			
			try {
				
				ConnectFuture future = connector.connect( new InetSocketAddress("localhost", 6666));
				future.awaitUninterruptibly();
				session = future.getSession();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		if (session != null) {
			session.getCloseFuture().awaitUninterruptibly();
			connector.dispose();
		}
	}
}
