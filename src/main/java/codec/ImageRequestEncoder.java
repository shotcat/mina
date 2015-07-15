package codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/***
 * 编码客户端用的
 * @author gaoyuandong
 *
 */
public class ImageRequestEncoder extends ProtocolEncoderAdapter {

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		ImageRequest imageRequest = (ImageRequest) message;
		IoBuffer buffer = IoBuffer.allocate(12,false);
		buffer.putInt(imageRequest.getWidth());
		buffer.putInt(imageRequest.getHeight());
		buffer.putInt(imageRequest.getNumberOfCharacters());
		buffer.flip();
		out.write(buffer);
	}

}
