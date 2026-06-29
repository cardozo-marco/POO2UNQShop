package reportes;

import catalogo.Paquete;
import catalogo.Producto;

public interface FormateadorReporte {

	void visitarProducto(Producto producto);
	
	void visitarPaquete(Paquete paquete);
	
	String obtenerReporte();

}
