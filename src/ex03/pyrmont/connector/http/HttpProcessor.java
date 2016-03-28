package ex03.pyrmont.connector.http;

import java.io.OutputStream;
import java.net.Socket;

/*this class used to be called HttpServer*/
/**
 * 
 * */
public class HttpProcessor {

	private HttpConnector connector = null;
	private HttpRequest request;
	public HttpProcessor(HttpConnector connector) {
		this.connector = connector;
	}

	/**
	 * The HttpConnector with which this processor is associated.
	 */
	public void process(Socket socket) {
		SocketInputStream input;
		OutputStream output = null;
		try {
			input = new SocketInputStream(socket.getInputStream(), 2048);
			output = socket.getOutputStream();
			// create HttpRequest object and parse
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
