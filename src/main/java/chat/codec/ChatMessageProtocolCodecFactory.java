package chat.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author gaoyuandong
 *
 */
public class ChatMessageProtocolCodecFactory implements ProtocolCodecFactory {

	
	private ChatMessageDecoder chatMessageDecoder;
	private ChatMessageEncoder chatMessageEncoder;
	
	public ChatMessageProtocolCodecFactory() {
		
		chatMessageEncoder = new ChatMessageEncoder();
		chatMessageDecoder = new ChatMessageDecoder();
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return chatMessageEncoder;
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return chatMessageDecoder;
	}

}
