package chat.adapter;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import chat.listener.SessionStatusListener;

/***
 * 断开重连适配器
 * @author gaoyuandong
 * @date   2015年7月21日 下午1:41:26
 * @mail   466862016@qq.com
 */
public class ReConnectionFilterAdapter extends IoFilterAdapter {

	private static Logger logger = Logger.getLogger(ReConnectionFilterAdapter.class);
	private NioSocketConnector socketConnector;
	private boolean isConectioned;
	private SessionStatusListener sessionStatusListener;
	
	public boolean isConectioned() {
		return isConectioned;
	}

	public ReConnectionFilterAdapter(NioSocketConnector socketConnector,SessionStatusListener sessionStatusListener) {
		this.socketConnector = socketConnector;
		this.sessionStatusListener = sessionStatusListener;
	}
	
	
	

	@Override
	public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception {
		sessionStatusListener.ioSession = session;
		nextFilter.sessionOpened(session);
		
	}
//
//
	@Override
	public void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception {
		sessionStatusListener.ioSession = session;
		sessionStatusListener.isConnectioned = true;
		nextFilter.sessionCreated(session);
	}

	@Override
	public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {
		sessionStatusListener.ioSession = session;
		nextFilter.sessionIdle(session, status);
	}
//
	@Override
	public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
		sessionStatusListener.ioSession = session;
		nextFilter.sessionClosed(session);
		sessionStatusListener.isConnectioned = false;
		logger.info(session.getRemoteAddress() +"断开连接准备重连.....");
		for(;;) {
			
			try {
				Thread.sleep(3000);
				ConnectFuture connect = socketConnector.connect();
				connect.awaitUninterruptibly();
				IoSession sc = connect.getSession();
				if(sc.isConnected()) {
					sessionStatusListener.isConnectioned = true;
					logger.info("[重连 "+ socketConnector.getDefaultRemoteAddress().getHostName() +":" +socketConnector.getDefaultRemoteAddress().getPort()+"连接成功!]");
					break;
				}
			} catch (Exception e) {
				logger.info("重新连接失败,3秒后再次尝试连接一次:" + e.getMessage());
			}
			
		}
	}

	
	
}
