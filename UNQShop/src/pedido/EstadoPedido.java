package pedido;

import catalogo.*;

public interface EstadoPedido {

	public void agregarItem(Pedido pedido, ItemCatalogo item);
	public void quitarItem(Pedido pedido, ItemCatalogo item);
	
	public void confirmar(Pedido pedido);
	public void preparar(Pedido pedido);
	public void enviar(Pedido pedido);
	public void entregar(Pedido pedido); 
	public NotaDeCredito cancelar(Pedido pedido);
}
