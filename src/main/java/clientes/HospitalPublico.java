package clientes;

import java.util.Random;

/**
 * Created by marcos on 01/05/16.
 */
public class HospitalPublico extends Cliente {

    public Integer getIntervaloProximoPedido() {
        Random random = new Random();
        return (int) Math.abs(Math.round(random.nextGaussian() + 5));
    }
}
