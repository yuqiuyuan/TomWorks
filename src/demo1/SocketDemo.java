package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 创建一个套接字，和本地的HTTP服务器进行通信
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>创建日期：2016年3月1日 上午10:52:07</p>
 * <p>类全名：demo1.SocketDemo</p>
 * 作者：虞秋元
 */
public class SocketDemo {
	public static int hour = 0, min = 0, sends = 0;
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		try {
			Socket socket = new Socket("127.0.0.1", 8090);
			OutputStream os = socket.getOutputStream();
			boolean autoflush = true;
			PrintWriter out = new PrintWriter(os, autoflush);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// 发送一个http请求到web服务器
			out.print("Accept image/png,image/*;q=0.8,*/*;q=0.5");
			out.print("Accept-Encoding gzip, deflate");
			out.print("Accept-Language zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			out.print("Cache-Control max-age=0");
			out.print("Connection keep-alive");
			out.print("Host 127.0.0.1:8090");
			out.print("If-Modified-Since Mon, 02 Feb 2015 07:18:01 GMT");
			out.print("Referer http://127.0.0.1:8090/cbe/Login.html");
			out.print("User-Agent http://127.0.0.1:8090/cbe/Login.html");
			// 读取响应
			boolean loop = true;
			StringBuffer sb = new StringBuffer(8096);
			while (loop) {
				if (br.ready()) {
					int i = 0;
					while (i != -1) {
						i = br.read();
						sb.append((char) i);
					}
					loop = false;
				}
				Thread.currentThread().sleep(1000);
				System.out
						.println("等待时间:" + (hour + ":" + min + ":" + sends++));
				if (sends == 60) {
					sends = 0;
					min++;
				}
				if (min == 60) {
					min = 0;
					hour++;
				}
			}
			// 显示当前响应在控制台上
			System.out.println(sb.toString());
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
