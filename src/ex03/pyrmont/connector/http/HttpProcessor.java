package ex03.pyrmont.connector.http;

import java.net.Socket;

/*this class used to be called HttpServer*/
/**
 * HttpProcessor 处理器，在connector中被创建，接受前来的HTTP请求套接字，做下面的事情： 1、创建一个HttpRequest对象。
 * 2、创建一个HttpResponse对象。 3、解析HttpRequest请求的第一行和头部，并放到HttpRequest对象。
 * 4、解析HttpRequest和HttpResponse对象到一个ServletProcessor或者StaticResourceProcessor
 * 。想第二章中说的，ServletProcessor调用被请求的servlet
 * 的service方法，而StaticResourceProcessor发送一个静态资源的内容。
 * 
 * */
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
