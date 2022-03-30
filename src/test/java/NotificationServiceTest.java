import org.junit.jupiter.api.Test;
import services.NotificationService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationServiceTest {

    private NotificationService service = new NotificationService();

    @Test
    public void testSendPush() {
        int HTTP_OK_RESPONSE = 200;
        assertEquals(HTTP_OK_RESPONSE, service.sendPush("Testing " + Math.random()));
    }

}
