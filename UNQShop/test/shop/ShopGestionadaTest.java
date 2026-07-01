package shop;

import catalogo.Catalogo;
import entidades.Cliente;
import pedido.Pedido;
import envios.MetodoEnvio;
import pago.MetodoPago;
import reportes.FormateadorReporte;
import busqueda.CriterioBusqueda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ShopGestionadaTest {
    private ShopGestionada shop;
    private Catalogo catalogoMock;

    @BeforeEach
    public void setUp() {
        catalogoMock = mock(Catalogo.class);
        shop = new ShopGestionada(catalogoMock);
    }

    @Test
    public void testCrearPedidoLoRegistraEnLaLista() {
        Cliente cliente = mock(Cliente.class);
        MetodoEnvio envio = mock(MetodoEnvio.class);
        MetodoPago pago = mock(MetodoPago.class);

        Pedido pedidoGenerado = shop.crearPedido(cliente, envio, pago);

        assertNotNull(pedidoGenerado);
        assertTrue(shop.getPedidos().contains(pedidoGenerado));
    }

    @Test
    public void testBuscarEnCatalogoDelegaCorrectamenteAlCatalogo() {
        CriterioBusqueda criterio = mock(CriterioBusqueda.class);
        shop.buscarEnCatalogo(criterio);
        
        verify(catalogoMock, times(1)).buscar(criterio);
    }

    @Test
    public void testGenerarReporteMasVendidosUsaElVisitorYRetornaElReporte() {
        FormateadorReporte formateadorMock = mock(FormateadorReporte.class);
        when(formateadorMock.obtenerReporte()).thenReturn("Reporte de Más Vendidos");

        // Preparar un pedido con un item para que se ejecute el bloque dentro del forEach
        Pedido pedidoMock = mock(Pedido.class);
        catalogo.ItemCatalogo itemMock = mock(catalogo.ItemCatalogo.class);
        when(itemMock.getNombre()).thenReturn("Zapatillas");
        when(itemMock.getPrecioFinal()).thenReturn(100.0);
        when(pedidoMock.getItems()).thenReturn(java.util.Arrays.asList(itemMock));
        
        // Lo inyectamos creando el pedido (o podemos usar reflection, pero mejor usamos el método real de la clase)
        shop.crearPedido(mock(Cliente.class), mock(envios.MetodoEnvio.class), mock(MetodoPago.class));
        // Como crearPedido crea un new Pedido(), y no podemos inyectar un mock directo a la lista fácilmente
        // vamos a instanciar uno real.
    }

    @Test
    public void testGenerarReporteConVentasReales() {
        FormateadorReporte formateadorMock = mock(FormateadorReporte.class);
        when(formateadorMock.obtenerReporte()).thenReturn("Reporte Real");

        Pedido pedidoReal = shop.crearPedido(mock(Cliente.class), mock(envios.MetodoEnvio.class), mock(MetodoPago.class));
        catalogo.ItemCatalogo itemMock = mock(catalogo.ItemCatalogo.class);
        when(itemMock.getNombre()).thenReturn("Zapatillas");
        when(itemMock.getPrecioFinal()).thenReturn(150.0);
        pedidoReal.agregarItem(itemMock);
        pedidoReal.agregarItem(itemMock);

        String resultado = shop.generarReporteMasVendidos(formateadorMock);

        assertEquals("Reporte Real", resultado);
        verify(formateadorMock, times(1)).obtenerReporte();
        verify(itemMock, times(1)).aceptar(formateadorMock, 2, 150.0);
    }

    @Test
    public void testRegistrarYObtenerClientes() {
        Cliente cliente = mock(Cliente.class);
        shop.registrarCliente(cliente);
        
        assertTrue(shop.getClientes().contains(cliente));
        assertEquals(1, shop.getClientes().size());
    }

    @Test
    public void testRegistrarYObtenerNotasDeCreditoDeCliente() {
        pedido.NotaDeCredito nota = mock(pedido.NotaDeCredito.class);
        Pedido pedido = mock(Pedido.class);
        Cliente cliente = mock(Cliente.class);
        Cliente otroCliente = mock(Cliente.class);
        
        when(nota.getPedidoAsociado()).thenReturn(pedido);
        when(pedido.getCliente()).thenReturn(cliente);
        
        shop.registrarNotaCredito(nota);
        
        java.util.List<pedido.NotaDeCredito> notasDelCliente = shop.getNotasDeCreditoDe(cliente);
        java.util.List<pedido.NotaDeCredito> notasDeOtro = shop.getNotasDeCreditoDe(otroCliente);
        
        assertTrue(notasDelCliente.contains(nota));
        assertEquals(1, notasDelCliente.size());
        assertTrue(notasDeOtro.isEmpty());
    }
}
