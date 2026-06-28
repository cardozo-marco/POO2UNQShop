package pedido;

import catalogo.ItemCatalogo;

public class EstadoCancelado implements EstadoPedido {

	@Override
	public void agregarItem(Pedido pedido, ItemCatalogo item) {
		throw new OperacionInvalidaException("No se puede agregar un item cuando el pedido fue cancelado");
	}

	@Override
	public void quitarItem(Pedido pedido, ItemCatalogo item) {
		throw new OperacionInvalidaException("No se puede quitar un item cuando el pedido fue cancelado");
	}

	@Override
	public void confirmar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede confirmar el pedido cuando fue cancelado");
	}

	@Override
	public void preparar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede preparar el pedido cuando fue cancelado");
	}

	@Override
	public void enviar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede enviar el pedido cuando fue cancelado");
		
	}

	@Override
	public void entregar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede entregar el pedido cuando fue cancelado");
	}

	@Override
	public NotaDeCredito cancelar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede cancelar el pedido cuando ya fue cancelado");
	}

}
