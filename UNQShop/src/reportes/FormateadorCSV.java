package reportes;

import catalogo.Paquete;
import catalogo.Producto;
import java.util.Locale;

public class FormateadorCSV implements FormateadorReporte {
    private StringBuilder contenidoCsv;

    public FormateadorCSV() {
        this.contenidoCsv = new StringBuilder();
        // Agrego la cabecera del CSV
        this.contenidoCsv.append("Item,CantidadVendida,PrecioPromedio\n");
    }

    @Override
    public void visitarProducto(Producto producto, int cantidad, double precioPromedio) {
        contenidoCsv.append(producto.getNombre()).append(",")
          .append(cantidad).append(",")
          .append(String.format(Locale.US, "%.2f", precioPromedio)).append("\n");
    }

    @Override
    public void visitarPaquete(Paquete paquete, int cantidadVentas, double precioPromedio) {
        // Le agrego un String al nombre para distinguir que es un paquete
        contenidoCsv.append(paquete.getNombre()).append(" (Paquete),").append(cantidadVentas).append(",").append(String.format(Locale.US, "%.2f", precioPromedio)).append("\n");
    }

    @Override
    public String obtenerReporte() {
        return contenidoCsv.toString();
    }
}