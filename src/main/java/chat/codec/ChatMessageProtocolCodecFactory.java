package chat.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/***
 * 消息编解码工厂类
 * @author gaoyuandong
 * @date   2015年7月20日 上午7:56:40
 * @mail   466862016@qq.com
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
