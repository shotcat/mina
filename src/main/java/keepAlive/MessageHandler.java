package keepAlive;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 
 * @author gaoyuandong
 *
 */
public class MessageHandler extends IoHandlerAdapter {

	private NioSocketAcceptor acceptor;

	public MessageHandler(NioSocketAcceptor acceptor) {
		super();
		this.acceptor = acceptor;
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.err.println("sessionCreated");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.err.println("sessionOpened");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.err.println("sessionClosed");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.err.println("sessionIdle");
		super.sessionIdle(session, status);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.err.println("exceptionCaught");
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.err.println("messageReceived");
		System.err.println(message);
		session.write(message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.err.println("messageSent");
	}
	
	
}
