package chat.message;

import chat.constant.Constant;

/***
 * 消息类型
 * @author gaoyuandong
 *
 */
public class ChatHeartRequestMessage extends AbstractChatMessage {

	private String  heartRequest = "0XAA";
	
	@Override
	public int getMessageType() {
		return Constant.MESSAGE_TYPE_HEART_REQUEST;
	}
	
	@Override
	public void bytes2Body(byte[] bytes) {
		
		this.heartRequest = new String(bytes);
	}

	@Override
	public byte[] body2Bytes() {
		return heartRequest.getBytes();
	}

}
