package envios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Direccion;
import pedido.Pedido;

public class EnvioEstandarTest {

    private EnvioEstandar envioEstandar;
    private CorreoArgentina mockCorreo;
    private Pedido mockPedido;
    private Direccion mockDireccion;

    @BeforeEach
    public void setUp() {
        // 1. SETUP
        mockCorreo = mock(CorreoArgentina.class);
        mockPedido = mock(Pedido.class);
        mockDireccion = mock(Direccion.class);
        
        envioEstandar = new EnvioEstandar(mockCorreo);
    }

    @Test
    public void testCalcularCostoDelegaAlCorreoArgentino() {
        when(mockPedido.getPesoTotal()).thenReturn(2.5f);
        when(mockPedido.getDireccionEnvio()).thenReturn(mockDireccion);
        when(mockCorreo.estimarEnvio(2.5f, mockDireccion)).thenReturn(1500.0f);

        // 2. EXERCISE
        float costo = envioEstandar.calcularCosto(mockPedido);

        // 3. VERIFY
        assertEquals(1500.0f, costo);
        verify(mockCorreo).estimarEnvio(2.5f, mockDireccion); 
    }

    @Test
    public void testEstimarDiasEntregaEsFijo() {
        // El TP pide que sea fijo entre 5 y 7 días ??????
        int dias = envioEstandar.estimarDiasEntrega(mockPedido);
        assertEquals(5, dias); 
    }
}