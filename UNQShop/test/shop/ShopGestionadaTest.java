package shop;

import catalogo.Catalogo;
import entidades.Cliente;
import entidades.Pedido;
import envio.MetodoEnvio;
import pago.MetodoPago;
import reportes.Reporte;
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
        
        String resultado = shop.generarReporteMasVendidos(formateadorMock);
        
        assertEquals("Reporte de Más Vendidos", resultado);
        verify(formateadorMock, times(1)).obtenerReporte();
    }
}
