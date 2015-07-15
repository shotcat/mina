package codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
/***
 * 解码服务端用
 * @author gaoyuandong
 *
 */
public class ImageRequestDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if (in.remaining() >= 12) {
			int width = in.getInt();
			int height = in.getInt();
			int numberOfCharacters = in.getInt();
			ImageRequest request = new ImageRequest(width, height, numberOfCharacters);
			out.write(request);
			return true;
		}
		return false;
	}

}
