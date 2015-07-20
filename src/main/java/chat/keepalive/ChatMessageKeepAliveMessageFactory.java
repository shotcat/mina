package chat.keepalive;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import chat.message.ChatHeartRequestMessage;
import chat.message.ChatHeartResponseMessage;

/***
 * 心跳机制
 * 为 @org.apache.mina.filter.keepalive.KeepAliveFilter 提供心跳消息工厂类
 * 接收心跳请求并进行响应
 * @author gaoyuandong
 * @date   2015年7月20日 上午7:57:56
 * @mail   466862016@qq.com
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
