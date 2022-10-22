package org.wu.rpc.data;

import lombok.AllArgsConstructor;

/**
 * 枚举类
 * @author Wu
 * @date 2022年10月22日 20:04
 */

@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(0, "成功"),
    FAIL(1, "失败");
    private Integer code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
