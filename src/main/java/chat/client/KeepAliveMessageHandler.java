package chat.client;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import chat.listener.SessionStatusListener;
import chat.message.ChatHeartRequestMessage;

public class KeepAliveMessageHandler {
	private SessionStatusListener sessionStatusListener;
	public KeepAliveMessageHandler(SessionStatusListener sessionStatusListener) {
		super();
		this.sessionStatusListener = sessionStatusListener;
	}
	
	
	public void runAlive() {
		
		 ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		 scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				if(sessionStatusListener.isConnectioned) {
					System.err.println("send alive ....");
					ChatHeartRequestMessage chatHeartRequestMessage = new ChatHeartRequestMessage();
					sessionStatusListener.ioSession.write(chatHeartRequestMessage);
				}
			}
		}, 15, 15, TimeUnit.SECONDS);
	}
}
