package servlet;

import java.io.IOException;

import demo1.Request;
import demo1.Response;

/**
 * 该类用来提供静态方法的请求
 */
public class StaticResourceProcessor {
	/**
	 * 该方法的process方法，用来提供静态请求的方法
	 * @param request
	 * @param response
	 */
	public void process(Request request, Response response) {
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
