package busqueda;

import catalogo.ItemCatalogo;

public interface CriterioBusqueda {
	
	public boolean satisface(ItemCatalogo item);
}
