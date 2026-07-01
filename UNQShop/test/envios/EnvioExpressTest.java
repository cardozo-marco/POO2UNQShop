package envios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

public class EnvioExpressTest {

    private EnvioExpress envioExpress;
    private Pedido mockPedido;

    @BeforeEach
    public void setUp() {
        // Inicializo con 500f de cargo base y 10% de recargo
        envioExpress = new EnvioExpress(500f, 10f);
        mockPedido = mock(Pedido.class);
    }

    @Test
    public void testCalcularCostoAplicaPorcentajeYCargoBase() {
        when(mockPedido.getValorTotal()).thenReturn(1000f);

        // EXERCISE: Costo esperado = 500 + (1000 * 10%) = 500 + 100 = 600f
        float costo = envioExpress.calcularCosto(mockPedido);

        // VERIFY
        assertEquals(600f, costo);
    }

    @Test
    public void testEstimarDiasEntregaEsUnDiaGarantizado() {
        int dias = envioExpress.estimarDiasEntrega(mockPedido);
        assertEquals(1, dias); 
    }
}
