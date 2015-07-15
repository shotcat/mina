package codec;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/***
 * 编码服务端用
 * @author gaoyuandong
 *
 */
public class ImageResponseEncoder extends ProtocolEncoderAdapter {

	/**
	 *   0000 00000000  0000  00000000
	 */
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		
		ImageResponse imageResponse = (ImageResponse) message;
		byte[] b1 = getBytes(imageResponse.getImage1());
		byte[] b2 = getBytes(imageResponse.getImage2());
		int capacity = b1.length + b2.length + 8;
		IoBuffer buffer = IoBuffer.allocate(capacity, false);
		buffer.setAutoExpand(true);
		buffer.putInt(b1.length);
		buffer.put(b1);
		buffer.putInt(b2.length);
		buffer.put(b2);
		buffer.flip();
		out.write(buffer);
		
	}
	 private byte[] getBytes(BufferedImage image) throws IOException {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "PNG", baos);
	        return baos.toByteArray();
	    }
}
