package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pago.MetodoPago;
import catalogo.ItemCatalogo;
import entidades.Cliente;
import envios.MetodoEnvio;
import notificaciones.PedidoObserver;
import entidades.*;

class PedidoTest {
	private Pedido pedido;
    private Cliente clienteMock;
    private MetodoEnvio envioMock;
    private MetodoPago pagoMock;
    private PedidoObserver observadorMock;

    @BeforeEach
    public void setUp() {
        clienteMock = mock(Cliente.class);
        envioMock = mock(MetodoEnvio.class);
        pagoMock = mock(MetodoPago.class);
        observadorMock = mock(PedidoObserver.class);
        
        pedido = new Pedido(clienteMock, envioMock, pagoMock);
        pedido.agregarObservador(observadorMock);
    }

    @Test
    public void testCalcularTotalDelegaEnEnvioYSubtotal() {
        // Configuramos un ítem con precio final 100
        ItemCatalogo item = mock(ItemCatalogo.class);
        when(item.getPrecioFinal()).thenReturn(100.0);
        pedido.agregarItem(item);
        
        // El envío con costo 50
        when(envioMock.calcularCosto(pedido)).thenReturn(50.0f);

        assertEquals(150.0, pedido.calcularTotal(), "El total debe ser subtotal + envio");
    }
    
    @Test
    public void testGetPesoTotal_SumaPesosDeItems() {
        ItemCatalogo item1 = mock(ItemCatalogo.class);
        ItemCatalogo item2 = mock(ItemCatalogo.class);
        when(item1.getPeso()).thenReturn(1.5);
        when(item2.getPeso()).thenReturn(2.0);
        
        pedido.agregarItem(item1);
        pedido.agregarItem(item2);

        assertEquals(3.5, pedido.getPesoTotal(), "Debe sumar correctamente el peso de todos los ítems");
    }
    
    @Test
    public void testGetValorTotal_RetornaSubtotalEnFloat() {
        ItemCatalogo item = mock(ItemCatalogo.class);
        when(item.getPrecioFinal()).thenReturn(120.50);
        pedido.agregarItem(item);

        assertEquals(120.50f, pedido.getValorTotal(), "Debe retornar el subtotal como float para integración");
    }

    @Test
    public void testGetDireccionEnvio_DelegaAlCliente() {
        Direccion dirMock = mock(Direccion.class);
        when(clienteMock.getDireccion()).thenReturn(dirMock);

        assertEquals(dirMock, pedido.getDireccionEnvio());
    }
    
    @Test
    public void testSetEstadoNotificaCambioCorrectamente() {
        EstadoPedido nuevoEstado = mock(EstadoPedido.class);
        EstadoPedido estadoAnterior = pedido.getEstado();
        
        pedido.setEstado(nuevoEstado);

        // Verificamos si se envía el mensaje actualizar
        verify(observadorMock).actualizar(pedido, estadoAnterior, nuevoEstado);
        assertEquals(nuevoEstado, pedido.getEstado());
    }

    @Test
    public void testQuitarObservador_DejaDeRecibirNotificaciones() {
        pedido.quitarObservador(observadorMock);
        pedido.setEstado(mock(EstadoPedido.class));

        // Verificamos que NO se llamo al metodo actualizar despues de quitarlo
        verify(observadorMock, times(0)).actualizar(any(), any(), any());
    }   
    
    // --- TESTS DE TRANSACCIONES Y PAGO
    @Test
    public void testRegistrarCupon_SeteaCuponYGeneraCodigo() {
        pedido.registrarCupon("PROMO-2024");
        
        assertEquals("PROMO-2024", pedido.getCuponPago());
        assertNotNull(pedido.getCodigoTransaccion());
        assertTrue(pedido.getCodigoTransaccion().contains("TX-TARJETA-"));
    }

    @Test
    public void testRegistrarComprobante_SeteaCodigoTransaccion() {
        pedido.registrarComprobante("TICKET-999");
        assertEquals("TICKET-999", pedido.getCodigoTransaccion());
    }

    @Test
    public void testSetCodigoTransaccion_LógicaSimple() {
        pedido.setCodigoTransaccion("ID-CUSTOM");
        assertEquals("ID-CUSTOM", pedido.getCodigoTransaccion());
    }

    // --- TESTS DE DELEGACIÓN A ESTADOS

    @Test
    public void testQuitarItem_DelegaAlEstadoActual() {
        // En Borrador (estado inicial), el item debe removerse de la lista
        ItemCatalogo item = mock(ItemCatalogo.class);
        pedido.agregarItem(item);
        pedido.quitarItem(item);
        
        assertTrue(pedido.getItems().isEmpty());
    }

    @Test
    public void testEnviarYEntregar_DeleganAlEstado() {
        // Usamos un mock para el estado para verificar que Pedido simplemente delega el mensaje
        EstadoPedido estadoMock = mock(EstadoPedido.class);
        pedido.setEstado(estadoMock);

        pedido.enviar();
        verify(estadoMock).enviar(pedido);

        pedido.entregar();
        verify(estadoMock).entregar(pedido);
    }

    // --- GETTERS RESTANTES

    @Test
    public void testGettersSimples_RetornanValoresCorrectos() {
        assertEquals(clienteMock, pedido.getCliente());
        assertEquals(LocalDate.now(), pedido.getFecha());
    }

    // --- TESTS DE EXCEPCIONES

    @Test
    public void testNoSePuedeEntregarUnPedidoQueAunEstaEnBorrador() {
        // El pedido nace en Borrador, lanzar Entregar debe fallar
        assertThrows(OperacionInvalidaException.class, () -> pedido.entregar());
    }
    
    @Test
    public void testCancelarPedidoEnPreparacionGeneraYRegistraNotaDeCredito() {   
        pedido.confirmar(); // Pasa a Confirmado
        pedido.preparar();  // Pasa a EnPreparacion
        
        double totalEsperado = pedido.calcularTotal();
        NotaDeCredito nc = pedido.cancelar();

        assertNotNull(nc);
        assertEquals(nc, pedido.getNotaDeCredito());
        assertEquals(totalEsperado, nc.getMontoReembolso());
    }
    
}
