package envios;
import pedido.Pedido;


public class EnvioEstandar implements MetodoEnvio {
    
    
    private CorreoArgentina correo;

    public EnvioEstandar(CorreoArgentina correo) {
        this.correo = correo;
    }

    @Override
    public float calcularCosto(Pedido pedido) {       
        return correo.estimarEnvio(pedido.getPesoTotal(), pedido.getDireccionEnvio());
    }

    @Override
    public int estimarDiasEntrega(Pedido pedido) {
        return 5; 
    }
}