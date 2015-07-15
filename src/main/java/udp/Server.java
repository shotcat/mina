package udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
/***
 * UDP Server
 * @author gaoyuandong
 *
 */
public class Server {

	 private static  ConcurrentHashMap<SocketAddress, String> clients;
	 
	public  Server() throws IOException {
		
		clients = new ConcurrentHashMap<SocketAddress, String>();
		
		NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		//设置为可再用地址
		acceptor.getSessionConfig().setReuseAddress(true);
		acceptor.setHandler(new MemoryMonitorHandler(this));
		acceptor.bind(new InetSocketAddress("localhost", 6666));
		 System.out.println("UDPServer listening on port " + 6666);
	}
	
	
	protected boolean containsClient(SocketAddress address) {
		
		return clients.contains(address);
	}
	protected void addClient(SocketAddress socketAddress) {
		
		if (!containsClient(socketAddress)) {
			clients.put(socketAddress, new Random().nextInt(1000) +"name");
			System.err.println("欢迎 "+ socketAddress.toString() +"进入....");
		}
		
	}
	
	protected void removeClient( SocketAddress socketAddress) {
		
		clients.remove(socketAddress);
	}
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
