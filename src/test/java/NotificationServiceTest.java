import org.junit.jupiter.api.Test;
import services.NotificationService;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationServiceTest {

    private NotificationService service = new NotificationService();

    @Test
    public void testSendPush() {
        assertEquals(HttpURLConnection.HTTP_OK,
                service.sendPush("Testing " + Math.random()));
    }

}
