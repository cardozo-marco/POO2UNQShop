package reportes;


import catalogo.Paquete;
import catalogo.Producto;

public class FormateadorHTML implements FormateadorReporte {
    private StringBuilder contenidoHtml;

    public FormateadorHTML() {
        this.contenidoHtml = new StringBuilder();
    }

    @Override
    public void visitarProducto(Producto p, int cantidadVentas) {
        contenidoHtml.append("<tr>\n");
        contenidoHtml.append("  <td>").append(p.getNombre()).append("</td>\n");
        contenidoHtml.append("  <td>").append(cantidadVentas).append("</td>\n");
        contenidoHtml.append("</tr>\n");
    }

    @Override
    public void visitarPaquete(Paquete paquete, int cantidadVentas) {
        contenidoHtml.append("<tr>\n");
        // Le agrego el texto "(Paquete)" para distinguirlo visualmente 
        contenidoHtml.append("  <td>").append(paquete.getNombre()).append(" (Paquete)</td>\n");
        contenidoHtml.append("  <td>").append(cantidadVentas).append("</td>\n");
        contenidoHtml.append("</tr>\n");
    }

    @Override
    public String obtenerReporte() {
        return contenidoHtml.toString();
    }
}



