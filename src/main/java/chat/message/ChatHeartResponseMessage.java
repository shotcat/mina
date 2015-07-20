package chat.message;

import chat.constant.Constant;

/***
 * 心跳机制响应消息实体类
 * @author gaoyuandong
 * @date   2015年7月20日 上午8:02:48
 * @mail   466862016@qq.com
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
