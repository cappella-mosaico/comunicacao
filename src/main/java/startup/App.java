package startup;

import kafka.LembretePastoralConsumer;
import kafka.NewPastoralConsumer;

public class App {

    public static void main(String[] args) {
        new NewPastoralConsumer().start();
        new LembretePastoralConsumer().start();
    }

}