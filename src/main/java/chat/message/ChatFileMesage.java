package chat.message;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import chat.util.FileUtil;

/**
 * 
 * @author gaoyuandong
 *
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
	 
	

	
}
