package entidades;

import pedido.Pedido;
import catalogo.ItemCatalogo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Factura {
    private String idFactura;
    private Pedido pedidoFacturado;
    private LocalDate fechaEmision;
    private double montoEnvio;
    private double subtotal;
    private double montoTotal;
    private List<ItemFactura> items;

    public Factura(Pedido pedido) {
        this.idFactura = UUID.randomUUID().toString();
        this.pedidoFacturado = pedido;
        this.fechaEmision = LocalDate.now();
        this.montoEnvio = pedido.calcularCostoEnvio();
        this.subtotal = pedido.calcularSubtotal();
        this.montoTotal = pedido.calcularTotal();
        
        this.items = new ArrayList<>();
        for (ItemCatalogo item : pedido.getItems()) {
            this.items.add(new ItemFactura(item.getNombre(), item.getPrecioFinal()));
        }
    }

    public String getIdFactura() {
        return this.idFactura;
    }

    public LocalDate getFechaEmision() {
        return this.fechaEmision;
    }

    public double getMontoTotal() {
        return this.montoTotal;
    }

    public Pedido getPedidoFacturado() {
        return this.pedidoFacturado;
    }

    public List<ItemFactura> getItems() {
        return this.items;
    }
    
    public double getSubtotal() {
    	return this.subtotal;
    }
    
    public double getMontoEnvio() {
    	return this.montoEnvio;
    }
}
