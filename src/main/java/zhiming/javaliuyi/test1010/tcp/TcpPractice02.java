package zhiming.javaliuyi.test1010.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpPractice02 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10005);
        Socket s = ss.accept();
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String(bys, 0, len);
        System.out.println("服务器："+ data);

        OutputStream os = s.getOutputStream();
        os.write("数据已收到".getBytes(StandardCharsets.UTF_8));

        ss.close();

    }
}
