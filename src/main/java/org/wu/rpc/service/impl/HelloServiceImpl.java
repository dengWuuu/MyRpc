package org.wu.rpc.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.wu.rpc.entity.HelloObject;
import org.wu.rpc.service.HelloService;

/**
 * 测试服务实现
 *
 * @author Wu
 * @date 2022年10月22日 19:57
 */
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(HelloObject object) {
        log.info("接收到：{}", object.getMessage());
        return "这是掉用的返回值，id=" + object.getId();
    }
}
