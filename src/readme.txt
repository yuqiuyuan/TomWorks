java.net.Url URL类的学习 2016-03-17
http://blog.csdn.net/chenzheng_java/article/details/6248090
2、ClassLoader 类加载器
Java利用ClassLoader将类装入内存，并且在同一应用中，可以有多个ClassLoader，通过委派机制，把装载的任务传递给上级的装载器的，以次类推，直到启动类装载器(没有上级类装载器)。
如果启动类装载器能够装载这个类，那么他会首先装载。若果不能，则往下传递。当父类为null时，JVM内置的类(称为:bootstrap class loader)就会充当父类。想想眼下的越来越多的
使用XML做配置文件或者描述符、部署符。其实这些通过XML文档描述的配置信息最终都要变成java类，其实都是通过ClassLoader来完成的。URLClassLoader是ClassLoader的子类
它用于从指向JAR文件和目录的URL的搜索路径加载类和资源。也就是说，通过URLClassLoader就可以加载指定jar中的class到内存中。
