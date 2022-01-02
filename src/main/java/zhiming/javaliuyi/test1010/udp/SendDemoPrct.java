package zhiming.javaliuyi.test1010.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.nio.charset.StandardCharsets;


public class SendDemoPrct {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        //自己封装键盘录入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while((line = br.readLine()) != null) {
            if("886".equals(line)) {
                break;
            }
            byte[] bys = line.getBytes(StandardCharsets.UTF_8);
            DatagramPacket dp = new DatagramPacket(bys, bys.length, Inet4Address.getByName("10.18.45.157"), 12345);
            ds.send(dp);
        }
        ds.close();
    }
}
