package chat.message;

import java.io.File;
import java.io.FileOutputStream;

import chat.util.FileUtil;

/***
 * 带文件上传的消息实体类
 * @author gaoyuandong
 * @date   2015年7月20日 上午8:01:20
 * @mail   466862016@qq.com
 */
public class ChatFileMesage extends DefaultChatMessage {

	private File file;

	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void bytes2Body(byte[] bytes) {
		try {
			//TODO
			file = new File("1.txt");
			FileUtil.byte2File(bytes, "d:", "1.txt");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public byte[] body2Bytes() {
		byte [] fileBytes =  FileUtil.File2byte(file.getAbsolutePath());
		return fileBytes;
	}

	@Override
	public String toString() {
		return "ChatFileMesage [file=" + file + "]";
	}
	 
	

	
}
