package ex03.pyrmont.connector.http;

/*this class used to be called HttpServer*/
/**
 * HttpProcessor ����������connector�б�����������ǰ����HTTP�����׽��֣�����������飺 1������һ��HttpRequest����
 * 2������һ��HttpResponse���� 3������HttpRequest����ĵ�һ�к�ͷ�������ŵ�HttpRequest����
 * 4������HttpRequest��HttpResponse����һ��ServletProcessor����StaticResourceProcessor
 * ����ڶ�����˵�ģ�ServletProcessor���ñ������servlet
 * ��service��������StaticResourceProcessor����һ����̬��Դ�����ݡ�
 * 
 * */

import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletException;

import org.apache.tomcat.util.res.StringManager;

import ex03.pyrmont.ServletProcessor;
import ex03.pyrmont.StaticResourceProcessor;

/*this class used to be called HttpServer*/
/**
 * it's used to created request and response.
 */
public class HttpProcessor {
	private HttpConnector connector = null;
	private HttpRequest request;
	private HttpResponse response;

	protected StringManager sm = StringManager.getManager("ex03.pyrmont.connector.http");

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
			request = new HttpRequest(input);
			// create HttpResponse object
			response = new HttpResponse(output);
			response.setRequest(request);
			response.setHeader("Server", "Pyrmont Servlet Container");
			// TODO
			parseRequest(input, output);
			// TODO
			parseHeaders(input);
			// check if this is a request for a servlet or a static resource
			// a request for a servlet begins with "/servlet/"
			if (request.getRequestURI().startsWith("/servlet/")) {
				ServletProcessor processor = new ServletProcessor();
				processor.process(request, response);
			} else {
				StaticResourceProcessor processor = new StaticResourceProcessor();
				processor.process(request, response);
			}
			// Close the socket
			socket.close();
			// no shutdown for this application
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is the simplified version of the similar method in
	 * org.apache.catalina.connector.http.HttpProcessor. However, this method
	 * only parse some "easy" headers ,such as "cookie","content-length",and
	 * "content-type",and ignore other headers.
	 * 
	 * @param input
	 * @throws ServletException
	 */
	private void parseHeaders(SocketInputStream input) throws ServletException {
		// TODO Auto-generated method stub
		while (true) {
			HttpHeader header = new HttpHeader();
			// reader the next header
			// input.readHeader(header);
			if (header.nameEnd == 0) {
				if (header.valueEnd == 0) {
					return;
				} else {
					throw new ServletException(sm.getString("httpProcessor.parseHeaders.colon"));
				}
			}
			String name = new String(header.name, 0, header.nameEnd);
			String value = new String(header.value, 0, header.valueEnd);

		}
	}

	private void parseRequest(SocketInputStream input, OutputStream output) {
		// TODO Auto-generated method stub
	}
}
