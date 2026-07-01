package reportes;

import catalogo.Paquete;
import catalogo.Producto;

public class FormateadorTextoPlano implements FormateadorReporte {
    private StringBuilder contenidoTexto;

    public FormateadorTextoPlano() {
        this.contenidoTexto = new StringBuilder();
        // Agrego un  título 
        this.contenidoTexto.append("--- REPORTE DE PRODUCTOS MÁS VENDIDOS ---\n");
    }

    @Override
    public void visitarProducto(Producto p, int cantidadVentas) {
        contenidoTexto.append("- Producto: ").append(p.getNombre())
                      .append(" | Unidades vendidas: ").append(cantidadVentas).append("\n");
    }

    @Override
    public void visitarPaquete(Paquete paquete, int cantidadVentas) {
        contenidoTexto.append("- Paquete: ").append(paquete.getNombre())
                      .append(" | Unidades vendidas: ").append(cantidadVentas).append("\n");
    }

    @Override
    public String obtenerReporte() {
        return contenidoTexto.toString();
    }
}