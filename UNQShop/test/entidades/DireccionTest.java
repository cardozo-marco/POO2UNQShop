package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DireccionTest {

    private Direccion direccion;

    @BeforeEach
    void setUp() {
        direccion = new Direccion("Calle Falsa", 123, "Springfield");
    }

    @Test
    void testGetCalle() {
        assertEquals("Calle Falsa", direccion.getCalle());
    }

    @Test
    void testGetNumero() {
        assertEquals(123, direccion.getNumero());
    }

    @Test
    void testGetLocalidad() {
        assertEquals("Springfield", direccion.getLocalidad());
    }
}
