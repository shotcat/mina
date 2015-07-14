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

