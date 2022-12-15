import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetTestMain {

    public static void main(String[] args) throws UnknownHostException {
        LocalHost localIP = new LocalHost();
        NetDiag diagnostic = new NetDiag();
      String localHost = localIP.getLocalIP();

        System.out.println("\nIP address selected: " +localHost);

        //String outAddress = "google.it";
        //System.out.println(diagnostic.traceRoute(outAddress));

    }
}
