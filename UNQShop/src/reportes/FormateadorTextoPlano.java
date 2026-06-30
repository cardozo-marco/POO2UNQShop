package reportes;

import java.util.Map;
import catalogo.ItemCatalogo;
import catalogo.Paquete;
import catalogo.Producto;

public class FormateadorTextoPlano implements FormateadorReporte {
    
    private StringBuilder contenidoTexto;
    private Map<ItemCatalogo, Integer> mapVentas;

    public FormateadorTextoPlano(Map<ItemCatalogo, Integer> mapVentas) {
        this.contenidoTexto = new StringBuilder();
        // Agrego un  título 
        this.contenidoTexto.append("--- REPORTE DE PRODUCTOS MÁS VENDIDOS ---\n");
        this.mapVentas = mapVentas;
    }

    @Override
    public void visitarProducto(Producto p) {
        int cantidad = this.mapVentas.getOrDefault(p, 0);
        contenidoTexto.append("- Producto: ").append(p.getNombre())
                      .append(" | Unidades vendidas: ").append(cantidad).append("\n");
    }

    @Override
    public void visitarPaquete(Paquete paquete) {
        int cantidad = this.mapVentas.getOrDefault(paquete, 0);
        contenidoTexto.append("- Paquete: ").append(paquete.getNombre())
                      .append(" | Unidades vendidas: ").append(cantidad).append("\n");
    }

    @Override
    public String obtenerReporte() {
        return contenidoTexto.toString();
    }
}