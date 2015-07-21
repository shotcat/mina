package chat.client;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.session.IoSession;

import chat.listener.SessionStatusListener;
import chat.message.ChatHeartRequestMessage;

public class KeepAliveHandler {
	private IoSession ioSession;
	public KeepAliveHandler(IoSession session) {
		super();
		this.ioSession = session;
	}
	
	
	public void runAlive() {
		
		 ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		 scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				if(ioSession != null) {
					System.err.println("send alive ....");
					ChatHeartRequestMessage chatHeartRequestMessage = new ChatHeartRequestMessage();
					ioSession.write(chatHeartRequestMessage);
				}
			}
		}, 15, 15, TimeUnit.SECONDS);
	}
}
