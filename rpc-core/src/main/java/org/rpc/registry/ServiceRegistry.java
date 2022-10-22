package org.rpc.registry;



/**
 * RPC服务注册容器
 * @author Wu
 * @date 2022年10月22日 23:00
 */
public interface ServiceRegistry {
    <T> void register(T service);
    Object getService(String serviceName);
}
