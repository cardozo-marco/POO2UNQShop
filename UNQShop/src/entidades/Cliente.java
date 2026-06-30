package entidades;

public class Cliente {
    private String nombre;
    private String email;
    private Direccion direccion;
    
    public Cliente(String nombre, String email, Direccion direccion) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
    }
    
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public Direccion getDireccion() { return direccion; }
}
