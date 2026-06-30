package pedido;

import catalogo.ItemCatalogo;

public class EstadoConfirmado implements EstadoPedido {
	
	public EstadoConfirmado(Pedido pedido) {
		for(ItemCatalogo i: pedido.getItems()) {
			i.reducirStock();
		}
	}

	@Override
	public void agregarItem(Pedido pedido, ItemCatalogo item) {
		throw new OperacionInvalidaException("No se puede agregar items si el pedido esta confirmado");
	}

	@Override
	public void quitarItem(Pedido pedido, ItemCatalogo item) {
		throw new OperacionInvalidaException("No se puede quitar items si el pedido esta confirmado");
	}

	@Override
	public void confirmar(Pedido pedido) {
		throw new OperacionInvalidaException("El pedido ya fue confirmado, no se puede volver a confirmar el pedido");
	}

	@Override
	public void preparar(Pedido pedido) {
		pedido.setEstado(new EstadoEnPreparacion());
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
		for(ItemCatalogo i: pedido.getItems()) {
			i.reponerStock(); // incremento el stock si se cancela
		}
		double monto = pedido.calcularTotal();
		NotaDeCredito nota = new NotaDeCredito(pedido, monto);
		
		pedido.setEstado(new EstadoCancelado());
		return nota; // como ya fue pagado, tambien le genero la nota de credito.
	}
	
}
