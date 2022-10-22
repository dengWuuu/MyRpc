package org.wu.rpc.server;

import org.wu.rpc.registry.ServiceRegistry;
import org.wu.rpc.registry.impl.DefaultServiceRegistry;
import org.wu.rpc.service.HelloService;
import org.wu.rpc.service.impl.HelloServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}

