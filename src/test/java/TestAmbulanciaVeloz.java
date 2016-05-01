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
        simulacion = new Simulacion(10, 10, 5);
        simulacion.iniciarSimulacion();
    }

}
