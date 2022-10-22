package org.rpc.codeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举类
 * @author Wu
 * @date 2022年10月22日 20:04
 */

@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(200, "调用方法成功"),
    FAIL(500, "调用方法失败"),
    METHOD_NOT_FOUND(500, "未找到指定方法"),
    CLASS_NOT_FOUND(500, "未找到指定类");

    private final int code;
    private final String message;

}
