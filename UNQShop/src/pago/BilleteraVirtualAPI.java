package pago;

public interface BilleteraVirtualAPI {
    boolean validarSaldo(String email, double monto);
    boolean bloquearSaldo(String email, double monto);
    boolean acreditar(String email, double monto);
    void enviarNotificacionPush(String email, String mensaje);
}
