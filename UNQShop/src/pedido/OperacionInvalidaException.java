package pedido;

public class OperacionInvalidaException extends RuntimeException  {
	
	public OperacionInvalidaException(String mensaje) {
		super(mensaje); // mensaje personalizado
	}
}
