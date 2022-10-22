package org.wu.rpc.server;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.wu.rpc.data.RpcRequest;
import org.wu.rpc.data.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author Wu
 * @date 2022年10月22日 20:30
 */
@Slf4j
@AllArgsConstructor
public class WorkerThread implements Runnable {
    private final Socket socket;

    private final Object service;


    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            //接受请求
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            log.info(rpcRequest.toString());
            //获取通过方法名字和类型获取唯一需要调用的接口的方法
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            log.info("method方法:{}", method);
            //执行方法
            Object returnObject = method.invoke(service, rpcRequest.getParameters());
            objectOutputStream.writeObject(RpcResponse.success(returnObject));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            log.error("worker调用或发送时有错误发生：", e);
        }
    }

}
