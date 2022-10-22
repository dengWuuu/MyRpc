import java.io.*;
import java.net.Socket;

/**
 * @author Wu
 * @date 2022年10月22日 20:35
 */
public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8848;
        try {
            System.out.println("连接到主机：" + host + " ，端口号：" + port);
            Socket client = new Socket(host, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
