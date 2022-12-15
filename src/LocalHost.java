import java.net.*;

public class LocalHost {


    public LocalHost() {
    }
    private String localIP = null;

    public String getLocalIP() throws UnknownHostException {
        String ip = Inet4Address.getLocalHost().getHostAddress();
        System.out.println("Trovato ip: " +ip +" senza connessione outbound.");



        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Trovato ip: " +ip +" con connessione outbound.");

        return ip;
    }
}
