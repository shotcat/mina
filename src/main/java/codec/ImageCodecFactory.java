package codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ImageCodecFactory implements ProtocolCodecFactory {

	private ProtocolDecoder decoder;
	private ProtocolEncoder encoder;
	
	public ImageCodecFactory(boolean client) {
		
		if (client) {
			encoder = new ImageRequestEncoder();
			decoder = new ImageRequestDecoder();
		} else {
			encoder = new ImageResponseEncoder();
			decoder = new ImageResponseDecoder();
		}
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return null;
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return null;
	}

}
