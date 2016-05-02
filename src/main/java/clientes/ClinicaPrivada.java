package clientes;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by marcos on 01/05/16.
 */
public class ClinicaPrivada extends Cliente{

    public Integer getIntervaloProximoPedido() {
        Random random = new Random();
        return (int) Math.abs(Math.round(random.nextGaussian()*2 + 10));
    }
}
