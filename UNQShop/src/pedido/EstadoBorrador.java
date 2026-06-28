package pedido;

import catalogo.*;

public class EstadoBorrador implements EstadoPedido {
	
	@Override
	public void agregarItem(Pedido pedido, ItemCatalogo item) {
		pedido.getItems().add(item);
	}
	
	@Override
	public void quitarItem(Pedido pedido, ItemCatalogo item) {
		pedido.getItems().remove(item);
	}

	@Override
	public void confirmar(Pedido pedido) {
		pedido.procesarPago();
		pedido.setEstado(new EstadoConfirmado(pedido));
	}

	@Override
	public void preparar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede preparar el pedido si no esta confirmado");
	}

	@Override
	public void enviar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede enviar el pedido si no esta preparado");
	}

	@Override
	public void entregar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede entregar el pedido si no fue enviado");
	}

	@Override
	public NotaDeCredito cancelar(Pedido pedido) {
		pedido.setEstado(new EstadoCancelado());
		return null;
	}
}
