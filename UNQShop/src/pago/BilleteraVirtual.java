package pago;

import entidades.Pedido;

public class BilleteraVirtual extends MetodoPago {
    private String emailUsuario;
    private BilleteraVirtualAPI api;

    public BilleteraVirtual(String emailUsuario, BilleteraVirtualAPI api) {
        this.emailUsuario = emailUsuario;
        this.api = api;
    }

    @Override
    protected void validarDatos(Pedido pedido) {
        if (!api.validarSaldo(emailUsuario, pedido.calcularTotal())) {
            throw new RuntimeException("Saldo insuficiente en billetera virtual");
        }
    }

    @Override
    protected void reservarFondos(Pedido pedido) {
        if (!api.bloquearSaldo(emailUsuario, pedido.calcularTotal())) {
            throw new RuntimeException("No se pudo bloquear el saldo de la billetera");
        }
    }

    @Override
    protected void ejecutarTransaccion(Pedido pedido) {
        if (!api.acreditar(emailUsuario, pedido.calcularTotal())) {
            throw new RuntimeException("Error al acreditar el pago con la billetera");
        }
    }

    @Override
    protected void notificarResultado(Pedido pedido) {
        // Personaliza el gancho enviando push notification
        api.enviarNotificacionPush(emailUsuario, "Su pago por el pedido ha sido procesado exitosamente.");
        pedido.setCodigoTransaccion("TX-BILLETERA-" + emailUsuario);
    }
}
