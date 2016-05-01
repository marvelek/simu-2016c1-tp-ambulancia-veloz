package clientes;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by marcos on 01/05/16.
 */
public class HospitalPublico extends Cliente {

    public Integer getIntervaloProximoPedido() {
        return ThreadLocalRandom.current().nextInt(0, 10 + 1); //Implementar posta
    }
}
