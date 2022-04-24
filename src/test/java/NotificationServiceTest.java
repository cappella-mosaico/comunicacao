import entities.Pastoral;
import org.junit.jupiter.api.Test;
import services.NotificationService;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationServiceTest {

    private NotificationService service = new NotificationService();

    @Test
    public void testSendPush() {
        Pastoral pastoral = new Pastoral();
        pastoral.setTitulo("Titulo de Teste");
        pastoral.setAutor("Autor de Teste " + Math.random());
        assertEquals(HttpURLConnection.HTTP_OK,
                service.sendPush(pastoral, "Nova Pastoral"));
    }

}
