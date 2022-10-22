package org.wu.rpc.client;

import lombok.extern.slf4j.Slf4j;
import org.wu.rpc.data.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * RPC客户端
 *
 * @author Wu
 * @date 2022年10月22日 22:05
 */
@Slf4j
public class RpcClient {

    /**
     * 模拟客户端发送请求
     *
     * @param rpcRequest request
     * @param host       host
     * @param port       port
     */
    public Object sendRequest(RpcRequest rpcRequest, String host, int port) {
        //创建socket连接跟服务端通信
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("客户端发送远程请求时候发生报错{}", e.toString());
            return null;
        }
    }
}
