package org.wu.rpc.client;

import org.wu.rpc.entity.HelloObject;
import org.wu.rpc.service.HelloService;

public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("localhost", 9000);
        //获取动态代理
        HelloService helloService = proxy.getProxy(HelloService.class);
        //初始化消息
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
