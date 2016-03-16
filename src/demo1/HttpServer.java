package demo1;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	/**
	 * WEB_ROOT is the directory where our HTML and other files reside. For this
	 * package,WEB_ROOT is the "webroot" directory under the work directory. The
	 * working directory is the location in the file system from where the java
	 * command invoked
	 */
	public static final String WEB_ROOT = "" + System.getProperty("user.dir")
			+ File.separator + "webroot";
	// shutdown command
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	// the shutdown command received
	private boolean shutdown = false;

	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer();
		httpServer.await();
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1,
					InetAddress.getByName("127.0.0.1"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		// Looping wait a request
		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				// create request Object
				Request request = new Request(input);
				request.parse();
				// create response object
				Response response = new Response(output);
				response.setRequest(request);
				response.sendStaticResource();
				// Close the socket
				socket.close();
				// check if the privaous URI is a shutdown command
				shutdown = request.geturi().equals(SHUTDOWN_COMMAND);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
