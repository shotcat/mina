package codec;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/***
 * 客户端
 * @author gaoyuandong
 *
 */
public class ImageClient  extends IoHandlerAdapter{

	private IoSession ioSession;
	
	public ImageClient() {
		NioSocketConnector ioConnector = new NioSocketConnector();
		ioConnector.getFilterChain().addLast("logger", new LoggingFilter());
		ioConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ImageCodecFactory(true)));
		ioConnector.setHandler(this);
		ioConnector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
		ioConnector.getSessionConfig().setBothIdleTime(30);
		ConnectFuture connectFuture = ioConnector.connect(new InetSocketAddress("localhost", 6666));
		
		
	}

	public static void main(String[] args) {
		new ImageClient();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionCreated(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.err.println("sessionOpened...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageReceived(session, message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
	}
	
	
}
