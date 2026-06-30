package notificaciones;

import notificaciones.PedidoObserver;
import pedido.EstadoPedido;
import pedido.Pedido;

public class GeneradorFactura implements PedidoObserver {

    @Override
    public void actualizar(Pedido pedido, EstadoPedido estadoAnterior, EstadoPedido nuevoEstado) {
        if (nuevoEstado.getClass().getSimpleName().equals("EstadoEntregado")) {
            //Factura
            System.out.println("FACTURA GENERADA: El pedido fue entregado exitosamente.");
        }
    }
}