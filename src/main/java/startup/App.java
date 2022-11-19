package startup;

import kafka.LembretePastoralConsumer;
import kafka.NewPastoralConsumer;
import kafka.NewRelatorioFinanceiroConsumer;

public class App {

    public static void main(String[] args) {
        new NewPastoralConsumer().start();
        new LembretePastoralConsumer().start();
        new NewRelatorioFinanceiroConsumer().start();
    }

}
