import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by marcos on 01/05/16.
 */
public class TestAmbulanciaVeloz extends TestCase {

    Simulacion simulacion;

    @Before
    public void setUp() {
    }

    @Test
    public void testCasoActual() {
        simulacion = new Simulacion(5, 7, 1);
        simulacion.iniciarSimulacion();
    }

    @Test
    public void testPeorCaso() {
        simulacion = new Simulacion(1, 1, 1);
        simulacion.iniciarSimulacion();
    }

    @Test
    public void testMejorCaso() {
        simulacion = new Simulacion(7, 9, 2);
        simulacion.iniciarSimulacion();
    }


}
