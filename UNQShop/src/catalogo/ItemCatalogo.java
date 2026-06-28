package catalogo;

import reportes.FormateadorReporte;

public interface ItemCatalogo {
    String getNombre();
    String getDescripcion();
    double getPrecioBase();
    double getPrecioFinal();
    double getPeso();
	void aceptar(FormateadorReporte visitante);
}
