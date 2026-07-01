package reportes;

import catalogo.Paquete;
import catalogo.Producto;

public interface FormateadorReporte {

	void visitarProducto(Producto producto, int cantidadVentas, double precioPromedio);
	
	void visitarPaquete(Paquete paquete, int cantidadVentas, double precioPromedio);
	
	String obtenerReporte();

}
