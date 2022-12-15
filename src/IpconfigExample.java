import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpconfigExample {
    public static void main(String[] args) {
        try {
            Process p = Runtime.getRuntime().exec("ipconfig");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            Pattern pattern = Pattern.compile("adapter:");
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println("Interface: " + matcher.group(1));
                    System.out.println("IP address: " + matcher.group(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
