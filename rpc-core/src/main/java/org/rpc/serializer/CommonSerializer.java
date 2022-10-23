package org.rpc.serializer;

import org.rpc.serializer.impl.JsonSerializer;




/**
 * @author Wu
 * @date 2022年10月23日 15:00
 */
public interface CommonSerializer {

    /**
     * 序列化接口
     * @param obj 序列化对象
     * @return 字节数组
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化接口
     * @param bytes 字节数组
     * @param clazz 反序列化的类
     * @return 结果
     */
    Object deserialize(byte[] bytes, Class<?> clazz);

    int getCode();

    static CommonSerializer getByCode(int code) {
        if (code == 1) {
            return new JsonSerializer();
        }
        return null;
    }
}
