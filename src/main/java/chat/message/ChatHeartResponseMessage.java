package chat.message;

import chat.constant.Constant;

/***
 * 消息类型
 * @author gaoyuandong
 *
 */
public class ChatHeartResponseMessage extends AbstractChatMessage {

	private String  heartResponse = "0XFF";
	
	@Override
	public int getMessageType() {
		return Constant.MESSAGE_TYPE_HEART_RESPONSE;
	}

	

	@Override
	public void bytes2Body(byte[] bytes) {
		
		this.heartResponse = new String(bytes);
	}

	@Override
	public byte[] body2Bytes() {
		return heartResponse.getBytes();
	}

}
