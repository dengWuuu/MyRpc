package org.wu.rpc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * 测试对象
 * @author Wu
 * @date 2022年10月22日 19:57
 */
@Data
@AllArgsConstructor
public class HelloObject implements Serializable {
    private Integer id;
    private String message;
}