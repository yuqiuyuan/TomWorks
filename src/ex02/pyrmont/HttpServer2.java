package ex02.pyrmont;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import servlet.ServletProcessor1;
import servlet.StaticResoureProcessor;
import demo1.Request;
import demo1.Response;


public class HttpServer2 {
	/**
	 * WEB_ROOT is the directory where our HTML and other files reside.For this
	 * package,WEB_ROOT is the "webroot" directory under the work directory.The
	 * working directory is the location in the file system from where the java
	 * command invoked
	 * 
	 * @param args
	 */
	public static final String WEB_ROOT = "" + System.getProperty("user.dir")
			+ File.separator + "webroot";
	// shutdown command
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	// the shutdown command received
	private boolean shutdown = false;
	public static void main(String[] args) {
		HttpServer2 httpServer = new HttpServer2();
		httpServer.await();
	}

	private void await() {
		// TODO Auto-generated method stub
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
				// create a request Object
				Request request = new Request(input);
				request.parse();
				// create a response Object
				Response response = new Response(output);
				response.setRequest(request);
				// check if this is a request for a servlet or a static resource
				// a request for a servlet begins with a "/servlet/"
				String uri = request.geturi();
				if (uri.startsWith("/servlet/")) {
					ServletProcessor1 processor = new ServletProcessor1();
					processor.process(request, response);
				} else {
					StaticResoureProcessor processor = new StaticResoureProcessor();
					processor.process(request, response);
				}
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
