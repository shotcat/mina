
package time;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.err.println("sessionOpened");
		session.write("xxxxx");
	}
	
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.err.println("messageReceived");
		System.err.println(message);
	}
}
