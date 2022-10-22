package org.rpc.server.socketImpl;

import lombok.extern.slf4j.Slf4j;
import org.rpc.dataTransfer.RpcRequest;
import org.rpc.dataTransfer.RpcResponse;
import org.rpc.registry.ServiceRegistry;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class RequestHandlerThread implements Runnable {
    private final Socket socket;
    private final RequestHandler requestHandler;
    private final ServiceRegistry serviceRegistry;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceRegistry serviceRegistry) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //获取方法-通过发现在注册表中是否有接口名称去获取方法
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getService(interfaceName);
            Object result = requestHandler.handle(rpcRequest, service);
            objectOutputStream.writeObject(RpcResponse.success(result));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            log.error("调用或发送时有错误发生：", e);
        }
    }
}
