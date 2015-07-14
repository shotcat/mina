package channel;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

	
	public static void main(String[] args) throws Exception {
		
		URL url = ChannelTest.class.getClassLoader().getResource("read.txt");
		System.err.println(url);
		RandomAccessFile randomAccessFile = new RandomAccessFile(new File(url.getPath()), "r");
		//获取一个通道
		FileChannel channel = randomAccessFile.getChannel();
		//创建一个读缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(10);
		//准备读将通道的数据放入缓冲区
		while (channel.read(byteBuffer) != -1) {
			//准备读
			byteBuffer.flip();
			while (byteBuffer.hasRemaining() == true) {
				System.err.println((char)byteBuffer.get());
			}
			//清空缓冲区
			// byteBuffer.compact(); 只会清除已经读的数据
			//清空缓冲区
			byteBuffer.clear();
			
			
		}
		//关闭
		randomAccessFile.close();
	}
}
