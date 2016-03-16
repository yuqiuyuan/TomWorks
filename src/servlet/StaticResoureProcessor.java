package servlet;

import demo1.Request;
import demo1.Response;

/**
 * 静态资源处理器
 */
public class StaticResoureProcessor {
	public void process(Request request, Response response) {
		// TODO
		try {
			response.sendStaticResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
