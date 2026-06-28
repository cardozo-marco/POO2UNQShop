package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorDisponibilidad implements CriterioBusqueda {

	@Override
	public boolean satisface(ItemCatalogo item) {
		return item.getStock() > 0;
	}
	
	
}
