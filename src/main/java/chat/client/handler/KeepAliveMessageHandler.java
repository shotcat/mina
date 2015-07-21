package chat.client.handler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import chat.listener.SessionStatusListener;
import chat.message.ChatHeartRequestMessage;
/***
 * 每间隔15秒钟发送心跳
 * @author gaoyuandong
 * @date   2015年7月21日 下午4:25:35
 * @mail   466862016@qq.com
 */
public class KeepAliveMessageHandler {
	
	private static Logger logger = Logger.getLogger(KeepAliveMessageHandler.class);
	private SessionStatusListener sessionStatusListener;
	
	
	public KeepAliveMessageHandler(SessionStatusListener sessionStatusListener) {
		super();
		this.sessionStatusListener = sessionStatusListener;
	}
	
	
	public void runAlive() {
		
		 ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		 scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				if(sessionStatusListener.isConnectioned) {//是否已经成功连接到远程服务器
				logger.info(sessionStatusListener.ioSession.getLocalAddress() + "客户端: 发送心跳");
					ChatHeartRequestMessage chatHeartRequestMessage = new ChatHeartRequestMessage();
					sessionStatusListener.ioSession.write(chatHeartRequestMessage);
				}
			}
		}, 15, 15, TimeUnit.SECONDS);
	}
}
