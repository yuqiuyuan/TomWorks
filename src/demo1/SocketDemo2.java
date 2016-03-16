package demo1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;


public class SocketDemo2 {
	public static void main(String[] args) {
		String path = "blog.csdn.net";
		// try {
		// URL url = new URL(path);
		// URLConnection conn = url.openConnection();
		// conn.getInputStream();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		String constant = getHtml(path);
		System.out.println(constant);
	}

	public static String getHtml(String path) {
		String strServer = path;
		String strPage = "/";
		String sb = null;
		try {
			int port = 80;
			InetAddress addr = InetAddress.getByName(strServer);
			Socket socket = new Socket(addr, port);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			bw.write("GET:" + strPage + " HTTP/1.0/r/n");
			bw.write("HOST:" + strServer + "/r/n");
			bw.write("Connection: Close /r/n");
			bw.write("Accept: */*/r/n");
			bw.write("/r/n");
			bw.flush();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String line;
			boolean loop = true;
			while (loop) {
				while ((line = rd.readLine()) != null) {
					sb += line + "/n";
					loop = false;
				}
			}
			bw.close();
			rd.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb;
	}
}
