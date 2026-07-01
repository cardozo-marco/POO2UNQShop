package envios;

import pedido.Pedido;

public interface MetodoEnvio {
    
	float calcularCosto(Pedido pedido);
    
    int estimarDiasEntrega(Pedido pedido);
    
}