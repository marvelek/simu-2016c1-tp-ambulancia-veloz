package servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        return ThreadLocalRandom.current().nextInt(2, 10 + 1); //Implementar posta
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

        if (tiempoComprometidoMinimo > tiempoActual + tiempoDemoraAdmitida) {
            cantidadPedidosPerdidos++;
            return;
        }

        if (tiempoComprometidoMinimo <= tiempoActual) {
            sumatoriaTiempoOcioso += tiempoActual - tiempoComprometidoMinimo;
            tiempoATrabajar = tiempoActual;
        } else {
            tiempoATrabajar = tiempoComprometidoMinimo;
        }
        setTiempoComprometido(indiceTiempoComprometidoMinimo, tiempoATrabajar + getTiempoViaje() + tiempoAtencion);

    }

    public void calcularPorcentajeTiempoOcioso(Integer tiempoActual) {
        System.out.println(sumatoriaTiempoOcioso/tiempoActual * 100);
    }

    public void calcularPorcentajePedidosPerdidos(Integer cantidadPedidos) {
        System.out.println(cantidadPedidosPerdidos/cantidadPedidos * 100);
    }
}
