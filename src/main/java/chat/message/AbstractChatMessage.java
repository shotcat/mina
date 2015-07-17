package chat.message;

import chat.util.ByteUtils;

public abstract class AbstractChatMessage  {

	public final static int MESSAGE_TYPE_COUNT = 4;
	public final static int MESSAGE_HEAD_COUNT = 4;
	private int messageType;
	
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
	//字节数组转换成消息体
	public abstract void bytes2Body(byte[] bytes);
	//消息内容转换成字节数组
	public abstract byte[] body2Bytes() ;
	/***
	 * 转字节数组
	 * @return
	 */
	public byte[] toBytes() {
		
		byte[] body = body2Bytes();
		int len = body.length + MESSAGE_TYPE_COUNT;
		byte[] by = new byte[len];
		System.arraycopy(ByteUtils.int2ByteArray(messageType), 0, by, 0,MESSAGE_TYPE_COUNT);
		System.arraycopy(body, 0, by, MESSAGE_TYPE_COUNT, body.length);
		return by;
		
	}
	
}
