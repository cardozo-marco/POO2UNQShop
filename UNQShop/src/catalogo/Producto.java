package catalogo;

import java.util.HashMap;
import java.util.Map;

public class Producto implements ItemCatalogo {
    private String sku;
    private String nombre;
    private String marca;
    private String categoria;
    private double precioBase;
    private double descuentoPromocional;
    private double peso;
    private Map<String, Object> atributosDinamicos;

    public Producto(String sku, String nombre, double precioBase, double peso) {
        this.sku = sku;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.peso = peso;
        this.atributosDinamicos = new HashMap<>();
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getDescripcion() {
        return "Producto: " + this.nombre + " - SKU: " + this.sku;
    }

    @Override
    public double getPrecioBase() {
        return this.precioBase;
    }

    @Override
    public double getPrecioFinal() {
        return this.precioBase - this.descuentoPromocional;
    }

    @Override
    public double getPeso() {
        return this.peso;
    }

    public Map<String, Object> getAtributosDinamicos() {
        return this.atributosDinamicos;
    }

    public void agregarAtributoDinamico(String key, Object value) {
        this.atributosDinamicos.put(key, value);
    }

    public boolean validar() {
        return this.sku != null && !this.sku.isEmpty() && 
               this.nombre != null && !this.nombre.isEmpty();
    }
}
