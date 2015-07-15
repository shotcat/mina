package time;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class TimeServerHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		  String str = message.toString();
	        if( str.trim().equalsIgnoreCase("quit") ) {
	            session.close(true);
	            return;
	        }
	        System.err.println(str);
	        Date date = new Date();
	        session.write( date.toString() );
	        System.out.println("Message written...");
	}
	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		
		System.err.println(session.getIdleCount(status));
	}
	
	
	
	
	
}
