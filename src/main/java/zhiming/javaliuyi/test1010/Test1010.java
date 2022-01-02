package zhiming.javaliuyi.test1010;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Test1010 {
    public static void main(String[] args) throws UnknownHostException {
        Inet4Address inet4Address = (Inet4Address) Inet4Address.getByName("10.18.45.157");
        String name = inet4Address.getHostName();
        String ip = inet4Address.getHostAddress();
        System.out.println("主机名：" + name);
        System.out.println("IP地址：" + ip);
    }
}
