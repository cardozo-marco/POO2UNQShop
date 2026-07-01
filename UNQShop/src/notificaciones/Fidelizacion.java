package notificaciones;

import pedido.EstadoPedido;
import pedido.Pedido;

public class Fidelizacion implements PedidoObserver {

    @Override
    public void actualizar(Pedido pedido, EstadoPedido estadoAnterior, EstadoPedido nuevoEstado) {
        if (nuevoEstado.getClass().getSimpleName().equals("EstadoCancelado")) {
            String mensaje = "CUPÓN ENVIADO: Te regalamos 5% de descuento por tu pedido cancelado.";
            System.out.println(mensaje);
            pedido.getCliente().notificar(mensaje);
        }
    }
}