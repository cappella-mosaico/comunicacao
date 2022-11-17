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

    public int sendPush(String strJsonBody) {
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

            byte[] sendBytes = strJsonBody.getBytes(StandardCharsets.UTF_8);
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();

            Scanner scanner = new Scanner(httpResponse >= HttpURLConnection.HTTP_OK && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST 
                                            ? con.getInputStream() 
                                            : con.getErrorStream(), 
                                          StandardCharsets.UTF_8);            ;
            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();
            return httpResponse;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return 0;
    }

}
