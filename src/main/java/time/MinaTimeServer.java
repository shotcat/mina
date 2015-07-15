package time;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @author gaoyuandong
 *
 */
public class MinaTimeServer {

	
	public static void main(String[] args) throws Exception {
		
		IoAcceptor  acceptor = new NioSocketAcceptor();
		//添加日志过滤器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		acceptor.setHandler(new TimeServerHandler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		//设置session空闲时候指定调用的时间间隔
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		//接收器 绑定到对应的地址端口
		acceptor.bind(new InetSocketAddress(6666));
				
	}
	
}
