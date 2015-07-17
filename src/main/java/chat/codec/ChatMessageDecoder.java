package chat.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import chat.constant.Constant;
import chat.message.AbstractChatMessage;
import chat.message.DefaultChatMessage;
import chat.util.ByteUtils;

/***
 * 消息解码
 * @author gaoyuandong
 *
 */
public class ChatMessageDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		
		
		boolean has = in.prefixedDataAvailable(4,Integer.MAX_VALUE);
		if(has) {
			int length = in.getInt();
			byte[] bytes = new byte[length];
			in.get(bytes);
			byte[] messageType = new byte[Constant.MESSAGE_TYPE_COUNT];
			System.arraycopy(bytes, 0, messageType, 0, Constant.MESSAGE_TYPE_COUNT);
			int type = ByteUtils.ByteArray2Int(messageType);
			byte[] bodyByte =  new byte[length -Constant.MESSAGE_TYPE_COUNT ];
			System.arraycopy(bytes, Constant.MESSAGE_TYPE_COUNT, bodyByte, 0, length - Constant.MESSAGE_TYPE_COUNT);
				AbstractChatMessage chatMessage = new DefaultChatMessage();
				chatMessage.bytes2Body(bodyByte);
				chatMessage.setMessageType(type);
				out.write(chatMessage);
			return true;
		}
		return false;
	}

	
}
