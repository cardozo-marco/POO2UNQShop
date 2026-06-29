package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorPrecioMaximo implements CriterioBusqueda {
	 
	private double precioMaximo;
	
	public CriterioPorPrecioMaximo(double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}
	
	@Override
	public boolean satisface(ItemCatalogo item) {
		return item.getPrecioBase() <= precioMaximo;
	}	
}
