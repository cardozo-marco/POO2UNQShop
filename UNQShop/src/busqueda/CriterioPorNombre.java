package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorNombre implements CriterioBusqueda {
	
	private String nombreBuscado;
	
	public CriterioPorNombre(String nombreBuscado) {
		this.nombreBuscado = nombreBuscado; 
	}
	@Override
	public boolean satisface(ItemCatalogo item) {
		return item.getNombre().toLowerCase().contains(nombreBuscado.toLowerCase());
	}
	
}
