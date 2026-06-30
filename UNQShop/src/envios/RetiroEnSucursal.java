package envios;
import pedido.Pedido;

public class RetiroEnSucursal implements MetodoEnvio {
    
    private Sucursal sucursal;

    public RetiroEnSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public double calcularCosto(Pedido pedido) {
        // El costo de envío es siempre cero
        return 0f; 
    }

    @Override
    public int estimarDiasEntrega(Pedido pedido) {
        // Inmediato (0) si hay stock, hasta 3 días si no.
        if (sucursal.tieneStock(pedido)) {
            return 0;
        } else {
            return 3;
        }
    }
}