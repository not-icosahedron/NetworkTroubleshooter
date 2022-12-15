import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;

public class NetworkTroubleshooter {
    public static void main(String[] args) {
        System.out.println("\r\n  _ _                            _      \r\n (_) |                          | |     \r\n  _| | _____  ___  __ _  ___  __| |_ __ \r\n | | |/ / _ \\/ __|/ _` |/ _ \\/ _` | '__|\r\n | |   < (_) \\__ \\ (_| |  __/ (_| | |   \r\n |_|_|\\_\\___/|___/\\__,_|\\___|\\__,_|_|   \r\n                                        \r\n                                        \r\n");
        System.out.println("Script created by ikosaedr \n https://github.com/not-icosahedron \n");

        try {

            System.out.println("--------Queste sono le interfacce presenti sul sistema, con relativi indirizzi IPv4 e IPv6");

            // Controlla l'ip delle interfacce del sistema
            Process proc = Runtime.getRuntime().exec("ipconfig");
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            String interfaceName = "";
            while ((line = reader.readLine()) != null) {
                if (line.contains("Ethernet") || line.contains("Scheda")) {
                    interfaceName = line.split(":")[0].trim();
                }
                if (line.contains("IPv4")) {
                    System.out.println(interfaceName + ":\n " + line);
                }
                if (line.contains("IPv6")) {
                    System.out.println("\n " + line +"\n");
                }
            }

            System.out.println("\n\n");


            String ip = Inet4Address.getLocalHost().getHostAddress();
            System.out.println("--------Trovato IPv4 locale: " +ip +" senza connessione outbound.");



            try (final DatagramSocket socket = new DatagramSocket()) {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                ip = socket.getLocalAddress().getHostAddress();
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }

            System.out.println("--------Trovato IPv4: " +ip +" con connessione outbound.");

            // traceroute to 1.1.1.1

            System.out.println("\n--------TraceRoute verso il DNS quad9, per testare la connettività verso l'esterno");
            proc = Runtime.getRuntime().exec("tracert 9.9.9.9");
            reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("\n--------TraceRoute verso www.google.com, per testare la connettività verso l'esterno, su un sito web");
            proc = Runtime.getRuntime().exec("tracert www.google.com");
            reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
