package pago;

public interface TransferenciaBancariaAPI {
    boolean validarCbuAlias(String cbuCvu, String alias);
    boolean ejecutarTransferencia(double monto);
}
