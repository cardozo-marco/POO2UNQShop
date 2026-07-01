package reportes;

import catalogo.Paquete;
import catalogo.Producto;

public interface FormateadorReporte {

	void visitarProducto(Producto producto, int cantidadVentas);
	
	void visitarPaquete(Paquete paquete, int cantidadVentas);
	
	String obtenerReporte();

}
