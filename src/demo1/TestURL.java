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
 * ��Ҫ������url����������������ֱ��� url.openStream(); url.openConnection(); url.getContent();
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

	/**
	 * url.openConnection()
	 * 
	 * @throws IOException
	 */
	public static void test3() throws IOException {
		URL url = new URL("http://www.baidu.com");
		// ����һ�� URLConnection��������ʾ��URL�����õ�Զ�̶�������ӡ�
		URLConnection uc = url.openConnection();
		// �򿪵����Ӷ�ȡ����������
		char[] cbuf = new char[1024];
		InputStream in = uc.getInputStream();
		InputStreamReader isr = new InputStreamReader(in);
		while (isr.read(cbuf) != -1) {
			System.out.println(cbuf);
		}
		in.close();
	}
	
	/**
	 * ��ȡURLָ������ҳ���� ���� url.openStream()
	 */
	public static void test2() throws IOException {
		URL url = new URL("http://www.baidu.com");
		// �򿪵��� URL �����Ӳ�����һ�����ڴӸ����Ӷ����InputStream
		Reader reader = new InputStreamReader(new BufferedInputStream(
				url.openStream()));
		int c;
		while ((c = reader.read()) != -1) {
			System.out.print((char) c);
		}
		reader.close();
	}

	/**
	 * ��ȡURL��������������� ������Ҫ˵���� url.openStream() ������
	 */
	public static void test1() throws IOException {
		URL url = new URL("http://www.baidu.com");
		// �򿪵��� URL �����Ӳ�����һ�����ڴӸ����Ӷ����inputstream
		InputStream in = url.openStream();
		int c;
		while ((c = in.read()) != -1) {
			System.out.println(c);
		}
		in.close();
	}

}
