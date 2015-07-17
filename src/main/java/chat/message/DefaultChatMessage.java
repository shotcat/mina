package chat.message;

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

}
