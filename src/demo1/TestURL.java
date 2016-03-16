package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TestURL {
	public static void main(String[] args) {
		try {
			test4();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 了解URL类的一个草率的方法
	 */
	public static void test() {
		// 创建一个对象
		try {
			URL url = new URL("http://www.baidu.com/");
			// URL对象的openStream()方法返回一个InputStream输入流
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is);
			// 读取输入流，并转换为bufferedReader,利用BufferedReader的readLine()方法读出内容
			BufferedReader bf = new BufferedReader(isr);
			String str;
			while ((str = bf.readLine()) != null) {
				System.out.println(str);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * 获取URL指定的资源
	 * 
	 * @throws IOException
	 */
	public static void test4() throws IOException {
		URL url = new URL(
				"http://lavasoft.blog.51cto.com/attachment/200811/200811271227767778082.jpg");
		// 获得此URL的内容
		byte[] buffer = new byte[1024];
		InputStream is = url.openStream();
		while (is.read(buffer) != -1) {
			System.out.print(buffer + "	");
		}
		Object obj = url.getContent();
		System.out.println(obj.getClass().getName());
	}

}
