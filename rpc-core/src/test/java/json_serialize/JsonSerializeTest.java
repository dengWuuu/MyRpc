package json_serialize;

import org.junit.Test;
import org.rpc.dataTransfer.RpcRequest;
import org.rpc.serializer.CommonSerializer;
import org.rpc.serializer.impl.JsonSerializer;

import java.util.Arrays;

/**
 * @author Wu
 * @date 2022年10月23日 16:05
 */

public class JsonSerializeTest {

    @Test
    public void testSerialize() {
        CommonSerializer jsonSerializer = new JsonSerializer();


        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName("haha")
                .methodName("haha")
                .parameters(new Object[]{"ss"})
                .paramTypes(new Class[]{String.class})
                .build();
        byte[] serialize = jsonSerializer.serialize(rpcRequest);

        System.out.println(Arrays.toString(serialize));

        RpcRequest deserialize = (RpcRequest) jsonSerializer.deserialize(serialize, RpcRequest.class);
        System.out.println(deserialize);

    }
}
