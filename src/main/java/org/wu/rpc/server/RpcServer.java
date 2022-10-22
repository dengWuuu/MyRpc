package org.wu.rpc.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


/**
 * RPC服务端
 * @author Wu
 * @date 2022年10月22日 23:05
 */
@Slf4j
public class RpcServer {
    private final ExecutorService threadPool;

    public RpcServer() {
        //构建线程池
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 60;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    /**
     * 运行指定方法
     * @param service
     * @param port
     */
    public void register(Object service, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("服务器启动...");
            Socket socket;
            //CAS等待连接到客户端
            while((socket = serverSocket.accept()) != null) {
                log.info("客户端连接！Ip为：" + socket.getInetAddress());
                threadPool.execute(new WorkerThread(socket, service));
            }
        } catch (IOException e) {
            log.error("注册时有错误发生：", e);
        }
    }


}
