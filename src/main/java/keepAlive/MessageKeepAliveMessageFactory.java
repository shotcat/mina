package keepAlive;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/***
 * 心跳
 * @author gaoyuandong
 *
 */
public class MessageKeepAliveMessageFactory implements KeepAliveMessageFactory {

	private final static String REQUEST_HANDLER = "0XAA";
	private final static String RESPONSE_HANDLER = "0XFF";
	public boolean isRequest(IoSession session, Object message) {
		
		
		if (message.equals(REQUEST_HANDLER)) {
			System.err.println("请求心跳包信息 "+ message);
			return true;
		}
		return false;
	}

	public boolean isResponse(IoSession session, Object message) {
		if (message.equals(RESPONSE_HANDLER)) {
			System.err.println("响应心跳包信息" + message);
			
			return true;
		}
		return false;
	}

	public Object getRequest(IoSession session) {
		System.err.println("getRequest");
		return REQUEST_HANDLER;
	}

	public Object getResponse(IoSession session, Object request) {
		System.err.println("getResponse");
		return RESPONSE_HANDLER;
	}

}
