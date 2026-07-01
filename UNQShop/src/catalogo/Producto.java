package catalogo;

import java.util.HashMap;
import java.util.Map;

import reportes.FormateadorReporte;

public class Producto implements ItemCatalogo {
    private String sku;
    private String nombre;
    private String marca;
    private String categoria;
    private double precioBase;
    private double descuentoPromocional;
    private double peso;
    private int stock;
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

    public String getMarca() {
        return this.marca;
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
        // 1. Validar los atributos fijos obligatorios
        boolean fijosValidos = this.sku != null && !this.sku.trim().isEmpty() && 
                               this.nombre != null && !this.nombre.trim().isEmpty();
                               
        if (!fijosValidos) {
            return false;
        }

        // 2. Validar que todos los atributos dinámicos tengan un valor asignado
        for (Object valor : this.atributosDinamicos.values()) {
            if (valor == null) {
                return false;
            }
        }
        
        return true;
    }

	@Override
	public void aceptar(FormateadorReporte visitante, int cantidadVentas) {
		visitante.visitarProducto(this, cantidadVentas);
	}

    public void reducirStock() {
        this.stock--;
    }

    public void reponerStock() {
        this.stock++;
    }
    
    @Override
    public String getCategoria() {
        return this.categoria;
    }

    @Override
    public int getStock() {
        return this.stock;
    }

}
