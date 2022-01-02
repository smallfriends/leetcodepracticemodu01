package zhiming.javaliuyi.test1010.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpPractice01 {
    public static void main(String[] args) throws IOException {
        //创建客户端的Socket对象（Socket）
        Socket s = new Socket("192.168.0.106", 10005);
        //获取输出流，写数据
        OutputStream os = s.getOutputStream();
        os.write("hello, tcp, 我来了".getBytes(StandardCharsets.UTF_8));

        //接收服务器反馈
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String(bys, 0, len);
        System.out.println("客户端：" + data);

        s.close();
    }
}
