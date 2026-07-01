package notificaciones;

import pedido.EstadoPedido;
import pedido.Pedido;

public class NotificadorEmail implements PedidoObserver {

    private MailSender mailSender; //inyección de la dependencia

    public NotificadorEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void actualizar(Pedido pedido, EstadoPedido estadoAnterior, EstadoPedido nuevoEstado) {
        String nombreNuevoEstado = nuevoEstado.getClass().getSimpleName();
        
        if (nombreNuevoEstado.equals("EstadoConfirmado") || 
            nombreNuevoEstado.equals("EstadoEnviado") || 
            nombreNuevoEstado.equals("EstadoEntregado")) {
            
            String mensaje = "El pedido ha cambiado a " + nombreNuevoEstado;
            //MailSender
            this.mailSender.enviar(mensaje);
            pedido.getCliente().notificar(mensaje);
        }
    }
}