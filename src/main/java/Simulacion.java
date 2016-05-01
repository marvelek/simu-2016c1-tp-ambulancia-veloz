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
        servicioAltaComplejidad = new Servicio(30, 45, ambulanciasAlta);
        servicioBajaComplejidad = new Servicio(180, 30, ambulanciasBaja);
        servicioMoto = new Servicio(0, 15, motos);
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
