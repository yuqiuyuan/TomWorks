package ex03.pyrmont.connector.http;

import java.net.Socket;

/*this class used to be called HttpServer*/
public class HttpProcessor {

	private HttpConnector connector = null;
	public HttpProcessor(HttpConnector connector) {
		this.connector = connector;
	}

	/**
	 * The HttpConnector with which this processor is associated.
	 */
	public void process(Socket socket) {
	}
}
