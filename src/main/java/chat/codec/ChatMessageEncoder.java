package chat.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import chat.message.AbstractChatMessage;
import chat.message.DefaultChatMessage;
/***
 * 消息编码
 * @author gaoyuandong
 *
 */
public class ChatMessageEncoder extends ProtocolEncoderAdapter {

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		
		if(message instanceof DefaultChatMessage) {
			
			DefaultChatMessage chatMessage = (DefaultChatMessage) message;
				byte[] by = chatMessage.toBytes();
				IoBuffer buffer = IoBuffer.allocate(by.length +AbstractChatMessage.MESSAGE_HEAD_COUNT);
				buffer.setAutoExpand(true);
				buffer.putInt(by.length);
				buffer.put(by);
				buffer.flip();
				out.write(buffer);
			
		}else {
			out.write(message);
		}
		
	}

}
