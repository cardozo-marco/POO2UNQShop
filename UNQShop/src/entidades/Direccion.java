package entidades;

public class Direccion {
    private String calle;
    private int numero;
    private String localidad;
    
    public Direccion(String calle, int numero, String localidad) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
    }
    
    public String getCalle() { return calle; }
    public int getNumero() { return numero; }
    public String getLocalidad() { return localidad; }
}
