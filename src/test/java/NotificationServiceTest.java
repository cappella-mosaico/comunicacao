import entities.Pastoral;
import org.junit.jupiter.api.Test;
import services.NotificationService;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationServiceTest {

    private static final String APP_ID = System.getenv("ONE_SIGNAL_APP_ID");
    private NotificationService service = new NotificationService();

    @Test
    public void testSendPush() {
        Pastoral pastoral = new Pastoral();
        pastoral.setTitulo("Titulo de Teste");
        pastoral.setAutor("Autor de Teste " + Math.random());
        String strJsonBody = "{"
            + "\"app_id\":\"" + APP_ID + "\","
            + "\"included_segments\": [\"Subscribed Users\"],"
            + "\"headings\": {\"en\": \"Pastoral de Hoje: " + pastoral.getTitulo() + "\"},"
            + "\"contents\": {\"en\": \"por: " + pastoral.getAutor() + "\"},"
            + "\"web_url\": \"https://cappella.meteorapp.com/\"}";

        assertEquals(HttpURLConnection.HTTP_OK, service.sendPush(strJsonBody));
    }

}
