package envios;

import pedido.Pedido;

public interface MetodoEnvio {
    
    double calcularCosto(Pedido pedido);
    
    int estimarDiasEntrega(Pedido pedido);
    
}