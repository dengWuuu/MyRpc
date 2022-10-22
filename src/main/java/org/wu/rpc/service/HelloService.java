package org.wu.rpc.service;

import org.wu.rpc.entity.HelloObject;

/**
 * 测试服务
 * @author Wu
 * @date 2022年10月22日 19:57
 */
public interface HelloService {
    String hello(HelloObject object);
}
