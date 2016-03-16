package servlet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import demo1.Request;
import demo1.Response;

public class HttpServer1 {
	/**
	 * WEB_ROOT is the directory where our HTML and other files reside. For this
	 * package,WEB_ROOT is the "webroot" directory under the working directory.
	 * The working directory is the location in the file system from where the
	 * java command was invoked.
	 */
	// shutdown command
	private final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	// the shutdown command received
	private boolean shutdown = false;

	public static void main(String[] args) {
		HttpServer1 server = new HttpServer1();
		server.await();
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 8081;
		try {
			serverSocket = new ServerSocket(port, 1,
					InetAddress.getByName("127.0.0.1"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		// Looping for request
		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				// create request object
				Request request = new Request(input);
				request.parse();
				// create response object
				Response response = new Response(output);
				response.setRequest(request);
				// check if this is a request for a servlet or a static
				// resource.
				// a request for a servlet begins with a "/servlet/"
				String uri = request.geturi();
				if (uri.startsWith("/servlet/")) {
					ServletProcessor1 processor = new ServletProcessor1();
					processor.process(request, response);
				} else {
					StaticResoureProcessor processor = new StaticResoureProcessor();
					processor.process(request, response);
				}
				// close the socket
				socket.close();
				// check if the privous uri is a shutdown command
				shutdown = request.geturi().equals(SHUTDOWN_COMMAND);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
