package pago;

import pedido.Pedido;

public class TransferenciaBancaria extends MetodoPago {
    private String cbuCvu;
    private String alias;
    private TransferenciaBancariaAPI api;

    public TransferenciaBancaria(String cbuCvu, String alias, TransferenciaBancariaAPI api) {
        this.cbuCvu = cbuCvu;
        this.alias = alias;
        this.api = api;
    }

    @Override
    protected void validarDatos(Pedido pedido) {
        if (!api.validarCbuAlias(cbuCvu, alias)) {
            throw new RuntimeException("CBU o Alias inválido");
        }
    }

    @Override
    protected void reservarFondos(Pedido pedido) {
        // En transferencias directas no hay reserva de fondos. El paso queda vacío.
    }

    @Override
    protected void ejecutarTransaccion(Pedido pedido) {
        if (!api.ejecutarTransferencia(pedido.calcularTotal())) {
            throw new RuntimeException("Error en la transferencia bancaria");
        }
    }

    @Override
    protected void notificarResultado(Pedido pedido) {
        String comprobante = "Comprobante CBU - Operación exitosa hacia " + cbuCvu;
        pedido.registrarComprobante(comprobante);
    }
}
