package org.rpc.client;


import org.rpc.dataTransfer.RpcRequest;

public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);
}