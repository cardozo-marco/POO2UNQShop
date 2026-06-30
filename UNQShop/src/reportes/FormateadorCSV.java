package reportes;

import java.util.Map;
import catalogo.ItemCatalogo;
import catalogo.Paquete;
import catalogo.Producto;

public class FormateadorCSV implements FormateadorReporte {
    
    private StringBuilder contenidoCsv;
    private Map<ItemCatalogo, Integer> mapVentas;

    public FormateadorCSV(Map<ItemCatalogo, Integer> mapVentas) {
        this.contenidoCsv = new StringBuilder();
        // Agrego la cabecera del CSV
        this.contenidoCsv.append("Item,CantidadVendida\n");
        this.mapVentas = mapVentas;
    }

    @Override
    public void visitarProducto(Producto p) {
        int cantidad = this.mapVentas.getOrDefault(p, 0);
        contenidoCsv.append(p.getNombre()).append(",").append(cantidad).append("\n");
    }

    @Override
    public void visitarPaquete(Paquete paquete) {
        int cantidad = this.mapVentas.getOrDefault(paquete, 0);
        // Le agrego un String al nombre para distinguir que es un paquete
        contenidoCsv.append(paquete.getNombre()).append(" (Paquete),").append(cantidad).append("\n");
    }

    @Override
    public String obtenerReporte() {
        return contenidoCsv.toString();
    }
}