package chat.keepalive;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import chat.constant.Constant;
import chat.message.ChatHeartRequestMessage;
import chat.message.ChatHeartResponseMessage;

/***
 * 心跳
 * @author gaoyuandong
 *
 */
public class ChatMessageKeepAliveMessageFactory implements KeepAliveMessageFactory {

	
	public boolean isRequest(IoSession session, Object message) {
		
		
		if (message instanceof ChatHeartRequestMessage) {
			System.err.println("请求心跳包信息 "+ message);
			return true;
		}
		return false;
	}

	public boolean isResponse(IoSession session, Object message) {
		if (message instanceof ChatHeartResponseMessage) {
			System.err.println("响应心跳包信息" + message);
			
			return true;
		}
		return false;
	}

	public Object getRequest(IoSession session) {
		System.err.println("getRequest");
		ChatHeartRequestMessage chatHeartRequestMessage = new ChatHeartRequestMessage();
		return chatHeartRequestMessage;
	}

	public Object getResponse(IoSession session, Object request) {
		System.err.println("getResponse");
		ChatHeartResponseMessage chatHeartResponseMessage = new ChatHeartResponseMessage();
		return chatHeartResponseMessage;
	}

}
