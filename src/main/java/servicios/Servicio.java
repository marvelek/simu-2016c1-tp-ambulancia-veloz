package servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by marcos on 01/05/16.
 */
public class Servicio {

    private Integer tiempoDemoraAdmitida;
    private Integer tiempoAtencion;

    /**
     * Variables auxiliares para cálculos de resultados
     */
    private Integer sumatoriaTiempoOcioso = 0;
    private Integer cantidadPedidosPerdidos = 0;

    /**
     * Variables de estado
     */
    private List<Integer> tiempoComprometido;


    /**
     * Constructor
     */
    public Servicio(Integer tiempoDemoraAdmitida, Integer tiempoAtencion, Integer cantidadVehiculos) {
        this.tiempoDemoraAdmitida = tiempoDemoraAdmitida;
        this.tiempoAtencion = tiempoAtencion;
        this.tiempoComprometido = new ArrayList<>(Collections.nCopies(cantidadVehiculos, 0));
    }

    private Integer getTiempoViaje() {
        double random = Math.random();
        return (int )Math.floor((15/35 + random)*35);
    }

    private Integer getIndiceTiempoComprometidoMinimo() {
        return tiempoComprometido.indexOf(Collections.min(tiempoComprometido));
    }

    private void setTiempoComprometido(int index, Integer valor) {
        tiempoComprometido.set(index, valor);
    }


    public void actualizarTiempoComprometido(Integer tiempoActual) {
        int indiceTiempoComprometidoMinimo = getIndiceTiempoComprometidoMinimo();
        Integer tiempoComprometidoMinimo = tiempoComprometido.get(indiceTiempoComprometidoMinimo);
        Integer tiempoATrabajar;

        if (tiempoComprometidoMinimo > (tiempoActual + tiempoDemoraAdmitida)) {
            cantidadPedidosPerdidos++;
            return;
        }

        if (tiempoComprometidoMinimo <= tiempoActual) {
            sumatoriaTiempoOcioso = sumatoriaTiempoOcioso + (tiempoActual - tiempoComprometidoMinimo);
            tiempoATrabajar = tiempoActual;
        } else {
            tiempoATrabajar = tiempoComprometidoMinimo;
        }
        setTiempoComprometido(indiceTiempoComprometidoMinimo, tiempoATrabajar + getTiempoViaje() + tiempoAtencion);

    }

    public void calcularPorcentajeTiempoOcioso(Integer tiempoActual) {
        System.out.println(Math.round((double) sumatoriaTiempoOcioso / tiempoComprometido.size() / tiempoActual * 100d * 100d) / 100d);
    }

    public void calcularPorcentajePedidosPerdidos(Integer cantidadPedidos) {
        System.out.println(Math.round((double) cantidadPedidosPerdidos / cantidadPedidos * 100d * 100d) / 100d);
    }
}
