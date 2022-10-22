package org.rpc.exception;

import org.rpc.codeEnum.RpcError;

/**
 * @author Wu
 * @date 2022年10月22日 22:03
 */
public class RpcException extends RuntimeException {

    public RpcException(RpcError error, String detail) {
        super(error.getMessage() + ": " + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcError error) {
        super(error.getMessage());
    }

}
