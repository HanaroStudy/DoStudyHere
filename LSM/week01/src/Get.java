import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Get {
    static final String URL = "https://www.google.com";
    static final String USER_AGENT = "Mozilla/5.0";
    static final String DATA = "get data";
    public static void main(String[] args) throws IOException {
        URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = conn.getResponseCode();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        br.close();

        String response = sb.toString();
        System.out.println(response);
    }
}
