package chat.client;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import chat.listener.SessionStatusListener;
import chat.message.ChatHeartRequestMessage;

public class KeepAliveHandler {
	
	
	private static Logger logger = Logger.getLogger(KeepAliveHandler.class);
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
					logger.info("发送心跳数据...");
					ChatHeartRequestMessage chatHeartRequestMessage = new ChatHeartRequestMessage();
					ioSession.write(chatHeartRequestMessage);
				}
			}
		}, 15, 15, TimeUnit.SECONDS);
	}
}
