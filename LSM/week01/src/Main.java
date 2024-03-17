import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    static final String USER_AGENT = ""; // Mozilla/5.0
    static final String DATA = "test data";
    public static void main(String[] args) {

        try {
            // URL 객체 -> Serializable 인터페이스 구현 -> 직렬화를 위한 조건
            // url 객체 생성 - MalformedURLException
            // MalformedURLException -> url 이 null 이거나 프로토콜을 알 수 없을 때 발생
            URL url = new URL("https://www.google.com");

            // URL 객체 연결 담당 URLConnection 객체
            // URLConnection conn = url.openConnection();
            // MalformedURLException -> IOException(상속)
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // GET 요청 등록. GET 이 default value
            conn.setRequestMethod("GET");

            // POST 요청 - OutputStream 객체로 데이터 전송
            // conn.setRequestMethod("POST");
            // setDoOutput(boolean doOutput) 메소드를 통해 전송할 데이터가 있다는 옵션을 설정해야 함
            // checkConnected() 에서 연결 객체가 연결되어있는지 확인하고 이미 연결되어 있으면 IllegalStateException
            // getOutputStream() -> 프로토콜이 출력을 지원하지 않는다면 UnknownServiceException
            // 전송 데이터가 문자열이면 DataOutputStream 클래스의 writebytes() 메소드를 활용하여 쉽게 데이터 설정
            /*
              DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
              outputStream.writeBytes(DATA);
              outputStream.flush();
              outputStream.close();
             */

            // 헤더 설정 setRequestProperty()
            // User-Agent 헤더 - 애플리케이션 타입, 운영 체제, 소프트웨어 벤더 또는 소프트웨어 버전 식별
            conn.setRequestProperty("User-Agent", USER_AGENT);

            // 응답 코드 얻기
            int responseCode = conn.getResponseCode();

            // 응답 데이터 얻기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();

            String response = sb.toString();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}