package entidades;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ItemFacturaTest {

    @Test
    void testItemFactura() {
        ItemFactura item = new ItemFactura("Combo Gamer", 1500.50);
        assertEquals("Combo Gamer", item.getDetalle());
        assertEquals(1500.50, item.getMonto());
    }
}
