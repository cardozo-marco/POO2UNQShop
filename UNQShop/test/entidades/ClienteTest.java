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
}
