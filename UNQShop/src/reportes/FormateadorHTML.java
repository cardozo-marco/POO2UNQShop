package reportes;

import java.util.Map;

import catalogo.ItemCatalogo;
import catalogo.Paquete;
import catalogo.Producto;

public class FormateadorHTML implements FormateadorReporte {
    
    private StringBuilder contenidoHtml;
    // Agrego la variable para guardar el map de ventas
    private Map<ItemCatalogo, Integer> mapVentas;

    // Modifico el constructor para recibir el map
    public FormateadorHTML(Map<ItemCatalogo, Integer> mapVentas) {
        this.contenidoHtml = new StringBuilder();
        this.mapVentas = mapVentas;
    }

    @Override
    public void visitarProducto(Producto p) {
        // Extraigo la cantidad del map. 
        // Uso getOrDefault por si un producto nunca se vendió, para que devuelva 0 en vez de null.
        int cantidad = this.mapVentas.getOrDefault(p, 0);

        contenidoHtml.append("<tr>\n");
        contenidoHtml.append("  <td>").append(p.getNombre()).append("</td>\n");
        contenidoHtml.append("  <td>").append(cantidad).append("</td>\n");
        contenidoHtml.append("</tr>\n");
    }

    @Override
    public void visitarPaquete(Paquete paquete) {
        
        int cantidad = this.mapVentas.getOrDefault(paquete, 0);

        contenidoHtml.append("<tr>\n");
        // Le agrego el texto "(Paquete)" para distinguirlo visualmente 
        contenidoHtml.append("  <td>").append(paquete.getNombre()).append(" (Paquete)</td>\n");
        contenidoHtml.append("  <td>").append(cantidad).append("</td>\n");
        contenidoHtml.append("</tr>\n");
    }

    @Override
    public String obtenerReporte() {
        return contenidoHtml.toString();
    }
}



