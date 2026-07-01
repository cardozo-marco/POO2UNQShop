package pedido;

public class OperacionInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public OperacionInvalidaException(String mensaje) {
		super(mensaje); // mensaje personalizado
	}
}
