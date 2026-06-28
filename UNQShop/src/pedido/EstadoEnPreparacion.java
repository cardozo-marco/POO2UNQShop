package pedido;

import catalogo.*;

public class EstadoEnPreparacion implements EstadoPedido {
	
	@Override
	public void agregarItem(Pedido pedido, ItemCatalogo item) {
		throw new OperacionInvalidaException("No se puede agregar un item cuando el pedido esta en prepraracion");
	}
	
	@Override
	public void quitarItem(Pedido pedido, ItemCatalogo item) {
		throw new OperacionInvalidaException("No se puede quitar un item cuando el pedido esta en prepraracion");
	}

	@Override
	public void confirmar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede volver a confirmar un pedido cuando esta en preparacion");
	}

	@Override
	public void preparar(Pedido pedido) {
		throw new OperacionInvalidaException("El pedido ya esta en preparacion");
	}

	@Override
	public void enviar(Pedido pedido) {
		pedido.setEstado(new EstadoEnviado());
	}

	@Override
	public void entregar(Pedido pedido) {
		throw new OperacionInvalidaException("No se puede entregar un pedido que esta en preparacion");
	}

	@Override
	public NotaDeCredito cancelar(Pedido pedido) {
		for(ItemCatalogo i: pedido.getItems()) {
			i.reponerStock();
		}
		double monto = pedido.calcularTotal();
		NotaDeCredito nota = new NotaDeCredito(pedido, monto);
		pedido.setEstado(new EstadoCancelado());
		return nota;
	}
	
}