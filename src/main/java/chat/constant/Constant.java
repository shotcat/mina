package chat.constant;

/***
 * 常量类
 * @author gaoyuandong
 * @date   2015年7月20日 上午7:57:30
 * @mail   466862016@qq.com
 */
public class Constant {

	public final static int MESSAGE_TYPE_COUNT = 4;
	public final static int MESSAGE_HEAD_COUNT = 4;
	
	
	//心跳包数据
	public final static int MESSAGE_TYPE_HEART_REQUEST = 1;
	public final static int MESSAGE_TYPE_HEART_RESPONSE = 2;
	
	//纯文本消息
	public final static int MESSAGE_TYPE_FULL_TXT = 3;
	//带文件上传的消息
	public final static int MESSAGE_TYPE_FILE = 4;
	
	
}
