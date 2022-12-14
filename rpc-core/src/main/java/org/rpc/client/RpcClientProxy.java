package org.rpc.client;

import lombok.extern.slf4j.Slf4j;
import org.rpc.client.socketImpl.SocketClient;
import org.rpc.dataTransfer.RpcRequest;
import org.rpc.dataTransfer.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * RPC客户端动态代理
 *
 * @author Wu
 * @date 2022年10月22日 22:05
 */
@Slf4j
public class RpcClientProxy implements InvocationHandler {
    private final String host;
    private final int port;

    public RpcClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @SuppressWarnings("unchecked") //忽略警告
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        //构建请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();
        log.info("构建请求成功{}", rpcRequest);
        //生成客户端对象
        SocketClient socketClient = new SocketClient(host, port);
        return ((RpcResponse<?>) socketClient.sendRequest(rpcRequest)).getData();
    }
}
