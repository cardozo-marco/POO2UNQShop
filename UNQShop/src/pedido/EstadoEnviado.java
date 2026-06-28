package pedido;

import catalogo.ItemCatalogo;

public class EstadoEnviado implements EstadoPedido {

	@Override
	public void agregarItem(Pedido pedido, ItemCatalogo item) {
		throw new OperacionInvalidaException("No se puede agregar un item cuando el pedido fue enviado");
	}

	@Override
	public void quitarItem(Pedido pedido, ItemCatalogo item) {
		throw new OperacionInvalidaException("No se puede quitar un item cuando el pedido fue enviado");
	}

	@Override
	public void confirmar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede confirmar el pedido cuando fue enviado");
	}

	@Override
	public void preparar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede preparar el pedido cuando fue enviado");
	}

	@Override
	public void enviar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede enviar el pedido cuando ya fue enviado");
	}

	@Override
	public void entregar(Pedido pedido) {
		pedido.setEstado(new EstadoEntregado());
	}

	@Override
	public NotaDeCredito cancelar(Pedido pedido) {
		double monto = pedido.calcularSubtotal() ;
		NotaDeCredito nota = new NotaDeCredito(pedido,monto);
		
		pedido.setEstado(new EstadoCancelado());
		return nota;
	}
	
}
