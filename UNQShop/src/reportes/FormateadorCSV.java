package reportes;

import catalogo.Paquete;
import catalogo.Producto;

public class FormateadorCSV implements FormateadorReporte {
    private StringBuilder contenidoCsv;

    public FormateadorCSV() {
        this.contenidoCsv = new StringBuilder();
        // Agrego la cabecera del CSV
        this.contenidoCsv.append("Item,CantidadVendida\n");
    }

    @Override
    public void visitarProducto(Producto p, int cantidadVentas) {
        contenidoCsv.append(p.getNombre()).append(",").append(cantidadVentas).append("\n");
    }

    @Override
    public void visitarPaquete(Paquete paquete, int cantidadVentas) {
        // Le agrego un String al nombre para distinguir que es un paquete
        contenidoCsv.append(paquete.getNombre()).append(" (Paquete),").append(cantidadVentas).append("\n");
    }

    @Override
    public String obtenerReporte() {
        return contenidoCsv.toString();
    }
}