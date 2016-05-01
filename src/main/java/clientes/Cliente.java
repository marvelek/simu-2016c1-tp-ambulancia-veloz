package clientes;

/**
 * Created by marcos on 01/05/16.
 */
public abstract class Cliente {

    private Integer tiempoProximoPedido = 0;

    abstract public Integer getIntervaloProximoPedido();

    public Integer getTiempoProximoPedido() {
        return tiempoProximoPedido;
    }

    public void actualizarTiempoProximoPedido() {
        tiempoProximoPedido += getIntervaloProximoPedido();
    }

}
