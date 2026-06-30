package catalogo;

import reportes.FormateadorReporte;

public interface ItemCatalogo {
    String getNombre();
    String getDescripcion();
    double getPrecioBase();
    double getPrecioFinal();
    double getPeso();
    void reducirStock();
    void reponerStock();
	void aceptar(FormateadorReporte visitante);
}
