package chat.message;

import chat.constant.Constant;
import chat.util.ByteUtils;
/***
 * 抽象消息实体类
 * @author gaoyuandong
 * @date   2015年7月20日 上午8:00:42
 * @mail   466862016@qq.com
 */
public abstract class AbstractChatMessage  {

	//消息类型
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
		int len = body.length + Constant.MESSAGE_TYPE_COUNT;
		byte[] by = new byte[len];
		System.arraycopy(ByteUtils.int2ByteArray(messageType), 0, by, 0,Constant.MESSAGE_TYPE_COUNT);
		System.arraycopy(body, 0, by, Constant.MESSAGE_TYPE_COUNT, body.length);
		return by;
		
	}
	
}
