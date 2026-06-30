package pago;

import pedido.Pedido;

public class TarjetaCredito extends MetodoPago {
    private String numeroTarjeta;
    private String cvv;
    private String vencimiento;
    private TarjetaCreditoAPI api;

    public TarjetaCredito(String numeroTarjeta, String cvv, String vencimiento, TarjetaCreditoAPI api) {
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.vencimiento = vencimiento;
        this.api = api;
    }

    @Override
    protected void validarDatos(Pedido pedido) {
        if (!api.validarTarjeta(numeroTarjeta, cvv, vencimiento)) {
            throw new RuntimeException("Datos de tarjeta inválidos");
        }
    }

    @Override
    protected void reservarFondos(Pedido pedido) {
        if (!api.preAutorizar(pedido.calcularTotal())) {
            throw new RuntimeException("No se pudieron reservar los fondos");
        }
    }

    @Override
    protected void ejecutarTransaccion(Pedido pedido) {
        if (!api.transferirFondos(pedido.calcularTotal())) {
            throw new RuntimeException("Error al ejecutar la transacción");
        }
    }

    @Override
    protected void notificarResultado(Pedido pedido) {
        String cupon = "Cupón de Pago - Tarjeta terminada en " + numeroTarjeta.substring(numeroTarjeta.length() - 4);
        pedido.registrarCupon(cupon);
    }
}
