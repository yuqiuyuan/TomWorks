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
	 * �˽�URL���һ�����ʵķ���
	 */
	public static void test() {
		// ����һ������
		try {
			URL url = new URL("http://www.baidu.com/");
			// URL�����openStream()��������һ��InputStream������
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is);
			// ��ȡ����������ת��ΪbufferedReader,����BufferedReader��readLine()������������
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
	 * ��ȡURLָ������Դ
	 * 
	 * @throws IOException
	 */
	public static void test4() throws IOException {
		URL url = new URL(
				"http://lavasoft.blog.51cto.com/attachment/200811/200811271227767778082.jpg");
		// ��ô�URL������
		byte[] buffer = new byte[1024];
		InputStream is = url.openStream();
		while (is.read(buffer) != -1) {
			System.out.print(buffer + "	");
		}
		Object obj = url.getContent();
		System.out.println(obj.getClass().getName());
	}

}
