package pago;

public interface TarjetaCreditoAPI {
    boolean validarTarjeta(String numero, String cvv, String vencimiento);
    boolean preAutorizar(double monto);
    boolean transferirFondos(double monto);
}
