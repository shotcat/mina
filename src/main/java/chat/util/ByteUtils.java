package chat.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/***
 * 字节数组与对象之间的转换工具类
 * 
 * @author gaoyuandong
 *
 */
public class ByteUtils {

	
	/***
	 * 对象转换成字节数组
	 * @param object 要转换成字节数组的对象
	 * @return 转成成的字节数组
	 */
	public static byte[] object2Bytes(Object object) {
		try {
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			arrayOutputStream.writeTo(arrayOutputStream);
			byte[] bytes = arrayOutputStream.toByteArray();
			objectOutputStream.close();
			arrayOutputStream.close();
			return bytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/****
	 * 将字节数组转换成对象
	 * @param <T> 要转换的对象类型
	 * @param bytes 要转换的字节数组
	 * @return 转换成的对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T  bytes2Object(byte[] bytes, Class<T> clazz) {
		try {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			return (T)objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	


	public static byte[] int2ByteArray(int i) {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		DataOutputStream dos= new DataOutputStream(buf);
		try {
			dos.writeInt(i);
			byte[] b = buf.toByteArray();
			dos.close();
			buf.close();
			return b;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public static int ByteArray2Int(byte b[])  {
		
		ByteArrayInputStream buf = new ByteArrayInputStream(b);
		DataInputStream dis= new DataInputStream (buf);
		try {
			return dis.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
