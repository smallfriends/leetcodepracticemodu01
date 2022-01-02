package zhiming.javaliuyi.test1010.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("10.18.45.157", 12000);
        OutputStream os = s.getOutputStream();
        os.write("hello, tcp, 我来了".getBytes(StandardCharsets.UTF_8));
        s.close();
    }
}
