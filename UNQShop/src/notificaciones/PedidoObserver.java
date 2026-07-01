package notificaciones;

import pedido.EstadoPedido;
import pedido.Pedido;

public interface PedidoObserver {
    void actualizar(Pedido pedido, EstadoPedido estadoAnterior, EstadoPedido nuevoEstado);
}
