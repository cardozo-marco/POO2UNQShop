package entidades;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String email;
    private Direccion direccion;
    private List<String> notificacionesRecibidas;
    private List<Factura> facturas;
    
    public Cliente(String nombre, String email, Direccion direccion) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.notificacionesRecibidas = new ArrayList<>();
        this.facturas = new ArrayList<>();
    }
    
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public Direccion getDireccion() { return direccion; }

    public void notificar(String mensaje) {
        this.notificacionesRecibidas.add(mensaje);
    }

    public void recibirFactura(Factura factura) {
        this.facturas.add(factura);
    }

    public List<String> getNotificaciones() {
        return new ArrayList<>(this.notificacionesRecibidas);
    }

    public List<Factura> getFacturas() {
        return new ArrayList<>(this.facturas);
    }
}
