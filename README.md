# java-nio
java-nio
  通道表示到实体，如硬件设备、文件、网络套接字或可以执行一个或多个不同 I/O 操作（如读取或写入）的程序组件的开放的连接。
       在以往的java的IO操作的过程中都是面向字节流操作，并且读写操作是单向的操作，而在java1.4添加nio包新的io不再像以前的标准的io操作一样面向流操作，而NIO是面向块的操作，并且有通道、缓冲区等重要的部分组成。读写操作都是通过缓冲区在对应的通道进行读取和写入数据。
    通道的所有已知实现类： 
AbstractInterruptibleChannel, AbstractSelectableChannel, DatagramChannel, FileChannel, Pipe.SinkChannel, Pipe.SourceChannel, SelectableChannel, ServerSocketChannel, SocketChannel 

一般我们用的最多的是:   
DatagramChannel:从UDP中读写网络数据

FileChannel:从文件中读写数据

ServerSocketChannel:针对面向流的侦听套接字的可选择通道,监听新进来的TCP连接，对每个新进的连接都会创建一个新的SocketChannel.

SocketChannel:从TCP中读写网络中的数据

此代码是自己在看 Apache Mina的时候写的例子

一、包含time echo 案例

二、发送图片并返回案例

三、自定义消息编码的案例

四、apache mina 心跳包机制的使用案例

五、apache UDP使用案例

六、综合案例-简单的纯文本发送消息和带文件的发送消息案例（待完善）

七、多人在线视频聊天系统（IOS、apache Mina  待定）

 其他相关项目:

一、未集成cas单点的权限管理系统

https://github.com/dongtian3240/sys

二、集成cas单点登录的企业系统管理

https://github.com/dongtian3240/pro

三、采用jasig耶鲁大学的cas server cas 单点登录项目

https://github.com/dongtian3240/cas

四、基于springsecurity oauth2认证方式的实现，token数据库存储，提供app后台服务端项目

https://github.com/dongtian3240/oauth2

五、网络应用框架Apache Mina 相关学习案例(综合案例-简单的纯文本发送消息和带文件的发送消息案例 以及多人在线聊天系统服务端)

https://github.com/dongtian3240/mina