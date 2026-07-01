package entidades;

public class ItemFactura {
    private String detalle;
    private double monto;

    public ItemFactura(String detalle, double monto) {
        this.detalle = detalle;
        this.monto = monto;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public double getMonto() {
        return this.monto;
    }
}
