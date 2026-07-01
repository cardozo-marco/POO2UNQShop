package envios;
import pedido.Pedido;

public class EnvioExpress implements MetodoEnvio {
    
    private float cargoBase;
    private float porcentajeCargoTotal;

    public EnvioExpress(float cargoBase, float porcentajeCargoTotal) {
        this.cargoBase = cargoBase;
        this.porcentajeCargoTotal = porcentajeCargoTotal;
    }

    @Override
    public float calcularCosto(Pedido pedido) {
        float precio = pedido.getValorTotal();
        return cargoBase + (precio * (porcentajeCargoTotal / 100f));
    }

    @Override
    public int estimarDiasEntrega(Pedido pedido) {
        return 1; 
    }
}