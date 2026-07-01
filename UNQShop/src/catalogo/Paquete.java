package catalogo;

import java.util.ArrayList;
import java.util.List;

import reportes.FormateadorReporte;

public class Paquete implements ItemCatalogo {
    private String nombre;
    private String descripcion;
    private double descuento;
    private List<ItemCatalogo> items;

    public Paquete(String nombre, String descripcion, double descuento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemCatalogo item) {
        this.items.add(item);
    }

    public void quitarItem(ItemCatalogo item) {
        this.items.remove(item);
    }

    public List<ItemCatalogo> getItems() {
        return this.items;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }

    @Override
    public double getPrecioBase() {
        return this.items.stream()
                   .mapToDouble(ItemCatalogo::getPrecioBase)
                   .sum();
    }

    @Override
    public double getPrecioFinal() {
        double precioSuma = this.items.stream()
                                .mapToDouble(ItemCatalogo::getPrecioFinal)
                                .sum();
        // El precio final se calcula aplicando el descuento sobre la suma de los precios finales de los items
        return precioSuma * (1 - this.descuento);
    }

    
    @Override
    public double getPeso() {
        return this.items.stream()
                   .mapToDouble(ItemCatalogo::getPeso)
                   .sum();
    }
    
    
    @Override
    public void aceptar(FormateadorReporte visitante, int cantidadVentas, double precioPromedio) {
        visitante.visitarPaquete(this, cantidadVentas, precioPromedio);
    }

    @Override
    public void reducirStock() {
        for (ItemCatalogo item : this.items) {
            item.reducirStock();
        }
    }

    @Override
    public void reponerStock() {
        for (ItemCatalogo item : this.items) {
            item.reponerStock();
        }
    }
    
    @Override
    public String getCategoria() {
        return "Paquete";
    }
    
    @Override
    public int getStock() {
        return this.items.stream()
                   .mapToInt(ItemCatalogo::getStock)
                   .min()
                   .orElse(0);
    }

}