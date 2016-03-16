package servlet;

import java.io.IOException;

/**
 * 在servlet接口的五个方法中，init,service,destroy 是servlet的声明周期方法。
 */
public interface Servlet {
	/**
	 * 在servlet 初始化之后，init方法会被servlet容器所调用，servlet只调用一次，以表明servlet已被加载进服务中。
	 * init方法必须在可以接受请求之前执行完毕。程序员可以通过覆盖这个方法来写那些仅仅需要运行一次的方法。如加载数据库驱动，值初始化等等。
	 * 在其他情况下，这个方法通常是空的。
	 */
	public void init();

	/**
	 * servlet为调用他的容器提供service方法。servlet容器传递一个ServletRequest对象和ServletResponse对象
	 * ， ServletRequest对象包括客户端的HTTP请求信息，而ServletResponse对象封装servlet的相应。
	 * 在servlet声明周期中，service方法会给调用多次。
	 * 
	 * @throws IOException
	 */
	public void service(ServletRequest request, ServletResponse response)
			throws IOException;

	/**
	 * 当从服务中移除一个servlet实例的时候，servlet容器调用destroy方法，
	 * 这通常发生在servlet容器被关闭或者servlet容器需要一些空闲内存的时候
	 * ，仅仅在所有的servlet线程的service方法已经退出或者超时淘汰的时候
	 * ，这个方法才被调用。在servlet容器已经调用完destroy方法之后，当前这个servlet容器不会再调用service方法。
	 * destroy方法提供了一个方法来清理任何已经被占用的资源
	 * 。如内存、文件句柄、和线程。并确保任何处于持久状态和servlet的内存当前状态是同步的
	 */
	public void destory();

	/**
	 * servlet的配置文件
	 */
	public void getServletConfig();

	public String getServletInfo();
}
