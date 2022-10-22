package org.rpc;


import org.rpc.registry.ServiceRegistry;
import org.rpc.registry.impl.DefaultServiceRegistry;
import org.rpc.server.socketImpl.SocketServer;
import org.rpc.service.HelloService;


public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.start(9000);
    }
}

