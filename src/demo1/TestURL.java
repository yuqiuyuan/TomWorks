package demo1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 主要讲述了url对象的三个方法，分别是 url.openStream(); url.openConnection(); url.getContent();
 */
public class TestURL {
	public static void main(String[] args) {
		try {
			test4();
			System.out.println("run test3 infomation:");
			test3();
			// System.out.println("run test2 infomation:");
			// test2();
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
				System.out.print(str);
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

	/**
	 * url.openConnection()
	 * 
	 * @throws IOException
	 */
	public static void test3() throws IOException {
		URL url = new URL("http://www.baidu.com");
		// 返回一个 URLConnection对象，他表示到URL所引用的远程对象的连接。
		URLConnection uc = url.openConnection();
		// 打开的连接读取的输入流。
		char[] cbuf = new char[1024];
		InputStream in = uc.getInputStream();
		InputStreamReader isr = new InputStreamReader(in);
		while (isr.read(cbuf) != -1) {
			System.out.println(cbuf);
		}
		in.close();
	}
	
	/**
	 * 读取URL指定的网页内容 这里 url.openStream()
	 */
	public static void test2() throws IOException {
		URL url = new URL("http://www.baidu.com");
		// 打开到此 URL 的连接并返回一个用于从该连接读入的InputStream
		Reader reader = new InputStreamReader(new BufferedInputStream(
				url.openStream()));
		int c;
		while ((c = reader.read()) != -1) {
			System.out.print((char) c);
		}
		reader.close();
	}

	/**
	 * 获取URL的输入流，并输出 这里主要说的是 url.openStream() 方法！
	 */
	public static void test1() throws IOException {
		URL url = new URL("http://www.baidu.com");
		// 打开到此 URL 的连接并返回一个用于从该连接读入的inputstream
		InputStream in = url.openStream();
		int c;
		while ((c = in.read()) != -1) {
			System.out.println(c);
		}
		in.close();
	}

}
