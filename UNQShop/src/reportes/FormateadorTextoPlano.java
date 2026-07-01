package reportes;

import catalogo.Paquete;
import catalogo.Producto;
import java.util.Locale;

public class FormateadorTextoPlano implements FormateadorReporte {
    private StringBuilder contenidoTexto;

    public FormateadorTextoPlano() {
        this.contenidoTexto = new StringBuilder();
        // Agrego un  título 
        this.contenidoTexto.append("--- REPORTE DE PRODUCTOS MÁS VENDIDOS ---\n");
    }

    @Override
    public void visitarProducto(Producto producto, int cantidad, double precioPromedio) {
        contenidoTexto.append("- Producto: ").append(producto.getNombre())
                      .append(" | Unidades vendidas: ").append(cantidad)
                      .append(" | Precio Promedio: $").append(String.format(Locale.US, "%.2f", precioPromedio))
                      .append("\n");
    }

    @Override
    public void visitarPaquete(Paquete paquete, int cantidad, double precioPromedio) {
        contenidoTexto.append("- Paquete: ").append(paquete.getNombre())
                      .append(" | Unidades vendidas: ").append(cantidad)
                      .append(" | Precio Promedio: $").append(String.format(Locale.US, "%.2f", precioPromedio))
                      .append("\n");
    }

    @Override
    public String obtenerReporte() {
        return contenidoTexto.toString();
    }
}