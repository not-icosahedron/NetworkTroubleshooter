import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.logging.ErrorManager;


public class NetDiag {

    private static final ErrorManager LOGGER = null;

    public NetDiag() {
    }
     private final String os = System.getProperty("os.name").toLowerCase();

    public String traceRoute(String address){
        String route = "";
        try {
            Process traceRt;
            if (os.contains("win")) traceRt = Runtime.getRuntime().exec("tracert " + address);
            else traceRt = Runtime.getRuntime().exec("traceroute " + address);

            // read the output from the command
            route = String.valueOf(traceRt.getInputStream());
            route = convertStreamToString(traceRt.getInputStream());

            // read any errors from the attempted command
            String errors = convertStreamToString(traceRt.getErrorStream());
        }
        catch (Exception e) {
            e.printStackTrace();
            }
        return route;
    }

    public String convertStreamToString(InputStream is) {
        //String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        Scanner s = new Scanner(is).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        return result;
    }
}
