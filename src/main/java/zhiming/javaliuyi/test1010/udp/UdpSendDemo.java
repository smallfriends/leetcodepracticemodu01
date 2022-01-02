package zhiming.javaliuyi.test1010.udp;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpSendDemo {
    public static void main(String[] args) throws IOException {

        DatagramSocket ds = new DatagramSocket();       //socket对象
        byte[] bys = "hello, udp, 我来了".getBytes(StandardCharsets.UTF_8);
        DatagramPacket dp = new DatagramPacket(bys, bys.length, Inet4Address.getByName("10.18.45.157"), 10086);

        ds.send(dp);
        ds.close();
    }

}
