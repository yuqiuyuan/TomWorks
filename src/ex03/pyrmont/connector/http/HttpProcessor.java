package ex03.pyrmont.connector.http;

import java.io.IOException;
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
import ex03.pyrmont.ServletProcessor;
import ex03.pyrmont.StaticResourceProcessor;
/*this class used to be called HttpServer*/
/**
 * it's used to created request and response.
 * */
public class HttpProcessor
{
	private HttpConnector	connector	= null;
	private HttpRequest		request;
	private HttpResponse	response;
	private HttpRequestLine	requestLine	= new HttpRequestLine();
	//	protected StringManager	sm			= StringManager.getManager("ex03.pyrmont.connector.http");

	public HttpProcessor(HttpConnector connector)
	{
		this.connector = connector;
	}

	public HttpProcessor()
	{
	}

	/**
	 * The HttpConnector with which this processor is associated.
	 */
	public void process(Socket socket)
	{
		SocketInputStream input;
		OutputStream output = null;
		try
		{
			input = new SocketInputStream(socket.getInputStream(), 2048);
			output = socket.getOutputStream();
			// create HttpRequest object and parse
			request = new HttpRequest(input);
			// create HttpResponse object
			response = new HttpResponse(output);
			response.setRequest(request);
			response.setHeader("Server", "Pyrmont Servlet Container");
			//	TODO
			parseRequest(input, output);
			//	TODO
			parseHeaders(input);
			// check if this is a request for a servlet or a static resource
			// a request for a servlet begins with "/servlet/"
			if (request.getRequestURI().startsWith("/servlet/"))
			{
				ServletProcessor processor = new ServletProcessor();
				processor.process(request, response);
			} else
			{
				StaticResourceProcessor processor = new StaticResourceProcessor();
				processor.process(request, response);
			}
			// Close the socket
			socket.close();
			// no shutdown for this application
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method is the simplified version of the similar method in org.apache.catalina.connector.http.HttpProcessor.
	 * However, this method only parse some "easy" headers ,such as 
	 * "cookie","content-length",and "content-type",and ignore other headers.
	 * @param input
	 * @throws ServletException 
	 */
	private void parseHeaders(SocketInputStream input) throws ServletException
	{
		// TODO Auto-generated method stub
		while (true)
		{
			HttpHeader header = new HttpHeader();
			//reader the next header
			//			input.readHeader(header);
			if (header.nameEnd == 0)
			{
				if (header.valueEnd == 0)
				{
					return;
				} else
				{
					//					throw new ServletException(sm.getString("httpProcessor.parseHeaders.colon"));
				}
			}
			String name = new String(header.name, 0, header.nameEnd);
			String value = new String(header.value, 0, header.valueEnd);
		}
	}

	private void parseRequest(SocketInputStream input, OutputStream output) throws IOException, ServletException
	{
		// Parse incoming request line
		input.readRequestLine(requestLine);
		String method = new String(requestLine.method, 0, requestLine.methodEnd);
		String uri = null;
		String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);
		//		Validate the incoming request line
		if (method.length() < 1)
		{
			throw new ServletException("Missing HTTP request method!");
		} else if (requestLine.uriEnd < 1)
		{
			throw new ServletException("Missing HTTP request URI!");
		}
		//	Parse any query parameters out of the request URI.
		int question = requestLine.indexOf("?");
		if (question >= 0)
		{
			request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question - 1));
			uri = new String(requestLine.uri, 0, question);
		} else
		{
			request.setQueryString(null);
			uri = new String(requestLine.uri, 0, requestLine.uriEnd);
		}
		//		Checking for an absolute URI (with HTTP protocol)
		if (!uri.startsWith("/"))
		{
			int pos = uri.indexOf("://");
			//	Parsing out protocol and host name
			if (pos != -1)
			{
				pos = uri.indexOf('/', pos + 3);
				if (pos == -1)
				{
					uri = "";
				} else
				{
					uri = uri.substring(pos);
				}
			}
		}
		//		Parsing any request session ID out of the request URI
		String match = ";jsessionid=";
		int semicolon = uri.indexOf(match);
		if (semicolon >= 0)
		{
			String rest = uri.substring(semicolon + match.length());
			int semicolon2 = rest.indexOf(";");
			if (semicolon2 > 0)
			{
				request.setRequestSessionID(rest.substring(0, semicolon2));
				rest = rest.substring(semicolon2);
			} else
			{
				request.setRequestSessionID(rest);
				rest = "";
			}
			request.setRequestSessionURL(true);
			uri = uri.substring(0, semicolon) + rest;
		} else
		{
			request.setRequestSessionID(null);
			request.setRequestSessionURL(false);
		}
		//		Normalize URI (using String operations at the moment)
		String normalizedUri = normalize(uri);
		//		Set the corresponding request properties
		//		request.setProtocol()
		if (normalizedUri != null)
		{
			request.setRequestURI(normalizedUri);
		} else
		{
			request.setRequestURI(uri);
		}
		if (normalizedUri == null)
		{
			throw new ServletException("Invalid URI" + uri + "'");
		}
	}

	private String normalize(String uri)
	{
		return uri;
	}
}
