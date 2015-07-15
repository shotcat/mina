package codec;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/***
 * 服务端
 * @author gaoyuandong
 *
 */
public class ImageServer {

	public ImageServer() throws IOException {
	
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ImageCodecFactory(false)));
		acceptor.setHandler(new ImageServerHandler());
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.bind(new InetSocketAddress("localhost", 6666));
		System.err.println("服务已经启动.....");
	}

	
	
	public static void main(String[] args) throws IOException {
		new ImageServer();
	}
}
