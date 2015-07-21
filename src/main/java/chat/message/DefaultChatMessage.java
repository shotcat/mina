package chat.message;
/***
 *  默认只要发送或者接收文字消息实体
 * @author gaoyuandong
 * @date   2015年7月20日 上午8:03:02
 * @mail   466862016@qq.com
 */
public class DefaultChatMessage extends AbstractChatMessage {

	private String text;
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void bytes2Body(byte[] bytes) {
		
		text = new String(bytes);
	}

	@Override
	public byte[] body2Bytes() {
		
		return text.getBytes();
	}

	@Override
	public String toString() {
		return "DefaultChatMessage [text=" + text + "]";
	}

}
