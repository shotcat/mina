package codec;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
/***
 * 解码客户端用
 * @author gaoyuandong
 *
 */
public class ImageResponseDecoder extends CumulativeProtocolDecoder {
	
	private static final String DECODER_STATE_KEY = ImageResponseDecoder.class.getName() + ".STATE";
	
	 public static final int MAX_IMAGE_SIZE = 5 * 1024 * 1024;

	    private static class DecoderState {
	        BufferedImage image1;
	    }
	    
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		
		DecoderState decoderState = (DecoderState) session.getAttribute(DECODER_STATE_KEY);
		if (decoderState == null) {
			decoderState = new DecoderState();
			session.setAttribute(DECODER_STATE_KEY, decoderState);
		}
		
		if(decoderState.image1 == null) { //解码第一张图片
			
			if(in.prefixedDataAvailable(4,MAX_IMAGE_SIZE)) {
				decoderState.image1 = readImage(in);
			} else {
				return false;
			}
		} else if(decoderState.image1 != null) { //解码第二张图片信息
			
			if(in.prefixedDataAvailable(4,MAX_IMAGE_SIZE)) {
				BufferedImage image2 = readImage(in);
				ImageResponse imageResponse = new ImageResponse(decoderState.image1, image2);
				out.write(imageResponse);
				decoderState.image1 = null;
				session.removeAttribute(DECODER_STATE_KEY);
				return true;
			} else {
				 return false;
			}
		}
		return false;
	}
	 private BufferedImage readImage(IoBuffer in) throws IOException {
	        int length = in.getInt();
	        byte[] bytes = new byte[length];
	        in.get(bytes);
	        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	        return ImageIO.read(bais);
	    }
	
}
