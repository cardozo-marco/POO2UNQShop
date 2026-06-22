package pago;

import entidades.Pedido;

public abstract class MetodoPago {
    
    // Template Method
    public void procesarPago(Pedido pedido) {
        validarDatos(pedido);
        reservarFondos(pedido);
        ejecutarTransaccion(pedido);
        notificarResultado(pedido);
    }

    // Operaciones primitivas abstractas
    protected abstract void validarDatos(Pedido pedido);
    protected abstract void reservarFondos(Pedido pedido);
    protected abstract void ejecutarTransaccion(Pedido pedido);
    
    // Método Gancho (Hook) con implementación por defecto
    protected void notificarResultado(Pedido pedido) {
        // En un caso genérico, solo se setearía el código de transacción en el pedido
        pedido.setCodigoTransaccion("TX-GENERICO");
    }
}
