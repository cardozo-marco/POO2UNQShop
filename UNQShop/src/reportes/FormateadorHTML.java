package reportes;


import catalogo.Paquete;
import catalogo.Producto;
import java.util.Locale;

public class FormateadorHTML implements FormateadorReporte {
    private StringBuilder contenidoHtml;

    public FormateadorHTML() {
        this.contenidoHtml = new StringBuilder();
        this.contenidoHtml.append("<table>\n");
        this.contenidoHtml.append("<tr><th>Item</th><th>Tipo</th><th>Cantidad</th><th>Precio Prom.</th></tr>\n");
    }

    @Override
    public void visitarProducto(Producto producto, int cantidad, double precioPromedio) {
        contenidoHtml.append("<tr>")
          .append("<td>").append(producto.getNombre()).append("</td>")
          .append("<td>Producto</td>")
          .append("<td>").append(cantidad).append("</td>")
          .append("<td>$").append(String.format(Locale.US, "%.2f", precioPromedio)).append("</td>")
          .append("</tr>\n");
    }

    @Override
    public void visitarPaquete(Paquete paquete, int cantidad, double precioPromedio) {
        contenidoHtml.append("<tr>")
          .append("<td>").append(paquete.getNombre()).append(" (Paquete)</td>")
          .append("<td>").append(cantidad).append("</td>")
          .append("<td>$").append(String.format(Locale.US, "%.2f", precioPromedio)).append("</td>")
          .append("</tr>\n");
    }

    @Override
    public String obtenerReporte() {
        return contenidoHtml.toString();
    }
}



