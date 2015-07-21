package chat.message;

import chat.constant.Constant;

/***
 * 心跳机制请求消息实体类
 * @author gaoyuandong
 * @date   2015年7月20日 上午8:02:07
 * @mail   466862016@qq.com
 */
public class ChatHeartRequestMessage extends AbstractChatMessage {

	private String  heartRequest = "0XAA";
	
	@Override
	public int getMessageType() {
		super.setMessageType(Constant.MESSAGE_TYPE_HEART_REQUEST);
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
