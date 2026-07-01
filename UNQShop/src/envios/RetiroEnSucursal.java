package envios;
import pedido.Pedido;

public class RetiroEnSucursal implements MetodoEnvio {
    
    private Sucursal sucursal;

    public RetiroEnSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public float calcularCosto(Pedido pedido) {
        return 0f; 
    }

    @Override
    public int estimarDiasEntrega(Pedido pedido) {
        if (sucursal.tieneStock(pedido)) {
            return 0;
        } else {
            return 3;
        }
    }
}