package entidades;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {

    private Cliente cliente;
    private Direccion direccionMock;

    @BeforeEach
    void setUp() {
        direccionMock = mock(Direccion.class);
        cliente = new Cliente("Juan Perez", "juan@test.com", direccionMock);
    }

    @Test
    void testGetNombre() {
        assertEquals("Juan Perez", cliente.getNombre());
    }

    @Test
    void testGetEmail() {
        assertEquals("juan@test.com", cliente.getEmail());
    }

    @Test
    void testGetDireccion() {
        assertEquals(direccionMock, cliente.getDireccion());
    }

    @Test
    void testNotificacionesRecibidas() {
        assertTrue(cliente.getNotificaciones().isEmpty());
        cliente.notificar("Mensaje de prueba");
        assertEquals(1, cliente.getNotificaciones().size());
        assertTrue(cliente.getNotificaciones().contains("Mensaje de prueba"));
    }

    @Test
    void testFacturas() {
        assertTrue(cliente.getFacturas().isEmpty());
        Factura facturaMock = mock(Factura.class);
        cliente.recibirFactura(facturaMock);
        assertEquals(1, cliente.getFacturas().size());
        assertTrue(cliente.getFacturas().contains(facturaMock));
    }
}
