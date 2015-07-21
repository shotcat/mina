package chat.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 
 * @author gaoyuandong
 *
 */
public class ChatMessageHandler extends IoHandlerAdapter {

	
	private static Logger logger = Logger.getLogger(ChatMessageHandler.class);
	
	
	private NioSocketAcceptor acceptor;

	public ChatMessageHandler(NioSocketAcceptor acceptor) {
		this.acceptor = acceptor;
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info(session.getRemoteAddress() + "sessionCreated");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info(session.getRemoteAddress() + "sessionOpened");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("sessionClosed");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.info("sessionIdle");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
		logger.info(session.getRemoteAddress() + "exceptionCaught");
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.info(session.getRemoteAddress() + "接收的数据为:" + message);
		session.write(message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("messageSent");
	}
	
	
}
