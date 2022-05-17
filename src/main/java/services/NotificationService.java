package services;

import entities.Pastoral;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NotificationService {

    private static final String APP_ID = System.getenv("ONE_SIGNAL_APP_ID");
    private static final String APP_KEY = System.getenv("ONE_SIGNAL_APP_KEY");

    public int sendPush(Pastoral pastoral, String heading) {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic " + APP_KEY);
            con.setRequestMethod("POST");

            String strJsonBody = "{"
                    + "\"app_id\":\"" + APP_ID + "\","
                    + "\"included_segments\": [\"Subscribed Users\"],"
                    + "\"headings\": {\"en\": \"" + heading +": " + pastoral.getTitulo() + "\"},"
                    + "\"contents\": {\"en\": \"por: " + pastoral.getAutor() + "\"},"
                    + "\"web_url\": \"https://cappella.meteorapp.com/\"}";

            byte[] sendBytes = strJsonBody.getBytes(StandardCharsets.UTF_8);
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), StandardCharsets.UTF_8);
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), StandardCharsets.UTF_8);
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);
            return httpResponse;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return 0;
    }

}
