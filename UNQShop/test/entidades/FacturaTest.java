package entidades;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import pedido.Pedido;

class FacturaTest {

    private Pedido mockPedido;
    private ItemCatalogo mockItem1;
    private ItemCatalogo mockItem2;

    @BeforeEach
    void setUp() {
        mockPedido = mock(Pedido.class);
        mockItem1 = mock(ItemCatalogo.class);
        mockItem2 = mock(ItemCatalogo.class);

        when(mockItem1.getNombre()).thenReturn("Mouse");
        when(mockItem1.getPrecioFinal()).thenReturn(50.0);
        
        when(mockItem2.getNombre()).thenReturn("Teclado");
        when(mockItem2.getPrecioFinal()).thenReturn(100.0);

        when(mockPedido.getItems()).thenReturn(Arrays.asList(mockItem1, mockItem2));
        when(mockPedido.calcularCostoEnvio()).thenReturn(20.0);
        when(mockPedido.calcularSubtotal()).thenReturn(150.0);
        when(mockPedido.calcularTotal()).thenReturn(170.0);
    }

    @Test
    void testFacturaGeneracionDesdePedido() {
        Factura factura = new Factura(mockPedido);

        assertNotNull(factura.getIdFactura());
        assertEquals(LocalDate.now(), factura.getFechaEmision());
        assertEquals(mockPedido, factura.getPedidoFacturado());
        assertEquals(20.0, factura.getMontoEnvio());
        assertEquals(150.0, factura.getSubtotal());
        assertEquals(170.0, factura.getMontoTotal());

        assertEquals(2, factura.getItems().size());
        
        ItemFactura item1 = factura.getItems().get(0);
        assertEquals("Mouse", item1.getDetalle());
        assertEquals(50.0, item1.getMonto());

        ItemFactura item2 = factura.getItems().get(1);
        assertEquals("Teclado", item2.getDetalle());
        assertEquals(100.0, item2.getMonto());
    }
}
