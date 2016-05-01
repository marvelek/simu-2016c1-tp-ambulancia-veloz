import clientes.Cliente;
import clientes.ClinicaPrivada;
import clientes.HospitalPublico;
import servicios.Servicio;

/**
 * Created by marcos on 01/05/16.
 */
public class Simulacion {

    /**
     * Constantes, expresadas en minutos
     */
    private final Integer T_FINAL = 1000000;
    private final Integer T_DEMORA_ADMITIDA_ALTA_COMPLEJIDAD = 30;
    private final Integer T_DEMORA_ADMITIDA_BAJA_COMPLEJIDAD = 180;
    private final Integer T_DEMORA_ADMITIDA_MOTO = 10;
    private final Integer T_SERVICIO_ALTA_COMPLEJIDAD = 45;
    private final Integer T_SERVICIO_BAJA_COMPLEJIDAD = 30;
    private final Integer T_SERVICIO_MOTO = 14;

    /**
     * Variables de tiempo
     */
    private Integer tiempoActual = 0;

    /**
     * Variables auxiliares para cálculos de resultados
     */
    private Integer cantidadPedidos = 0;

    /**
     * Servicios
     */
    Servicio servicioAltaComplejidad;
    Servicio servicioBajaComplejidad;
    Servicio servicioMoto;

    /**
     * Clientes
     */
    Cliente hospitalPublico = new HospitalPublico();
    Cliente clinicaPrivada = new ClinicaPrivada();

    public Simulacion(Integer ambulanciasAlta, Integer ambulanciasBaja, Integer motos) {
        servicioAltaComplejidad = new Servicio(T_DEMORA_ADMITIDA_ALTA_COMPLEJIDAD, T_SERVICIO_ALTA_COMPLEJIDAD, ambulanciasAlta);
        servicioBajaComplejidad = new Servicio(T_DEMORA_ADMITIDA_BAJA_COMPLEJIDAD, T_SERVICIO_BAJA_COMPLEJIDAD, ambulanciasBaja);
        servicioMoto = new Servicio(T_DEMORA_ADMITIDA_MOTO, T_SERVICIO_MOTO, motos);
    }

    private void procesarPedido() {
        Cliente clienteATrabajar;
        if (clinicaPrivada.getTiempoProximoPedido() <= hospitalPublico.getTiempoProximoPedido()) {
            clienteATrabajar = clinicaPrivada;
        } else {
            clienteATrabajar = hospitalPublico;
        }

        tiempoActual = clienteATrabajar.getTiempoProximoPedido();
        clienteATrabajar.actualizarTiempoProximoPedido();
    }

    private void actualizarTiempoComprometido() {
        double random = Math.random();
        Servicio servicioATrabajar;

        if (random <= 0.6) {
            servicioATrabajar = servicioBajaComplejidad;
        } else if(random <= 0.9) {
            servicioATrabajar = servicioAltaComplejidad;
        } else {
            servicioATrabajar = servicioMoto;
        }

        servicioATrabajar.actualizarTiempoComprometido(tiempoActual);

    }

    private void calcularEImprimirResultados() {
        System.out.println("Ambulancias de Alta Complejidad");
        System.out.print("Porcentaje de Tiempo Ocioso: ");
        servicioAltaComplejidad.calcularPorcentajeTiempoOcioso(tiempoActual);
        System.out.print("Porcentaje de Pedidos perdidos: ");
        servicioAltaComplejidad.calcularPorcentajePedidosPerdidos(cantidadPedidos);

        System.out.println("Ambulancias de Baja Complejidad");
        System.out.print("Porcentaje de Tiempo Ocioso: ");
        servicioBajaComplejidad.calcularPorcentajeTiempoOcioso(tiempoActual);
        System.out.print("Porcentaje de Pedidos perdidos: ");
        servicioBajaComplejidad.calcularPorcentajePedidosPerdidos(cantidadPedidos);

        System.out.println("Médico en Moto");
        System.out.print("Porcentaje de Tiempo Ocioso: ");
        servicioMoto.calcularPorcentajeTiempoOcioso(tiempoActual);
        System.out.print("Porcentaje de Pedidos perdidos: ");
        servicioMoto.calcularPorcentajePedidosPerdidos(cantidadPedidos);

    }

    public void iniciarSimulacion() {

        while (tiempoActual < T_FINAL) {
            procesarPedido();
            cantidadPedidos++;
            actualizarTiempoComprometido();
        }

        calcularEImprimirResultados();

    }
}
