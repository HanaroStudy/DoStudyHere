import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post {
    static final String URL = "https://www.google.com";
    static final String USER_AGENT = "Mozilla/5.0";
    static final String DATA = "post data";
    public static void main(String[] args) throws IOException {
        URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setDoOutput(true); // 전송할 데이터가 있다

        DataOutputStream output = new DataOutputStream(conn.getOutputStream());
        output.writeBytes(DATA);
        output.flush();
        output.close();

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
