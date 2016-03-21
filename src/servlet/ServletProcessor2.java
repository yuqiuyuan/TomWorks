package servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import pub.Constants;
import demo1.Request;
import demo1.Response;
import ex02.pyrmont.RequestFacade;
import ex02.pyrmont.ResponseFacade;

/**
 * Servlet处理器
 */
public class ServletProcessor2 {
	public void process(Request request, Response response) {
		String uri = request.geturi();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;
		try {
			// create a URLclassLoader
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			String repository = (new URL("file", null,
					classPath.getAbsolutePath() + File.separator)).toString();
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		@SuppressWarnings("rawtypes")
		Class myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
		Servlet servlet = null;
		// 第一个应用解决安全性问题的方法
		RequestFacade requestFacade = new RequestFacade(request);
		ResponseFacade responsefacade = new ResponseFacade(response);
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service((RequestFacade) requestFacade,
					(ResponseFacade) responsefacade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
