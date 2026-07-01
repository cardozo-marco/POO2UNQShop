package pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import catalogo.*;
import notificaciones.PedidoObserver;

import entidades.Cliente;
import envios.MetodoEnvio;
import pago.*;
import entidades.Direccion;

public class Pedido {
	
	private LocalDate fecha;
	private Cliente cliente;
	private MetodoEnvio metodoEnvio;
	private MetodoPago metodoPago;
	private List<ItemCatalogo> items;
	private String codigoTransaccion;
	private List<PedidoObserver> observadores;
	private EstadoPedido estadoActual;
	private String cuponPago;
	private NotaDeCredito notaDeCredito; 
	
	public Pedido(Cliente cliente, MetodoEnvio metodoEnvio, MetodoPago metodoPago) {
		this.fecha        = LocalDate.now();
		this.cliente      = cliente;
		this.metodoEnvio  = metodoEnvio;
		this.metodoPago   = metodoPago;
		this.items        = new ArrayList<ItemCatalogo>();
		this.codigoTransaccion = null;
		this.observadores = new ArrayList<PedidoObserver>();
		this.estadoActual = new EstadoBorrador();
		this.cuponPago    = null;
	}
	
	public void agregarItem(ItemCatalogo item) {
		estadoActual.agregarItem(this, item);
	}
	
	public void quitarItem(ItemCatalogo item) {
		estadoActual.quitarItem(this, item);
	}
	
	public void confirmar() {
		estadoActual.confirmar(this);
	}
	
	public void preparar() {
		estadoActual.preparar(this);
	}
	
	public void enviar() {
		estadoActual.enviar(this);
	}
	
	public void entregar() {
		estadoActual.entregar(this);
	}
	
	public NotaDeCredito cancelar() {
		this.notaDeCredito = estadoActual.cancelar(this);
        
        return this.notaDeCredito;
	}
	
	public List<ItemCatalogo> getItems() {
		return this.items;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public EstadoPedido getEstado() {
		return this.estadoActual;
	}
	
	public void setEstado(EstadoPedido estado) {
		EstadoPedido estadoAnterior = this.estadoActual; 
		this.estadoActual = estado;
		this.notificar(estadoAnterior, estado);
	}
	
	public double calcularTotal() {
		return this.calcularCostoEnvio() + this.calcularSubtotal();
	}
	
	public double calcularSubtotal(){
		return items.stream().mapToDouble(i -> i.getPrecioFinal()).sum();
	}
	
	public double calcularCostoEnvio() {
		return metodoEnvio.calcularCosto(this);
	}
	
	public String getCodigoTransaccion() {
		return this.codigoTransaccion;
	}
	
	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}
	
	public void registrarComprobante(String comprobante) {
	    this.setCodigoTransaccion(comprobante);
	}
	
	public void registrarCupon(String cupon) {
	    this.cuponPago = cupon;
	    this.setCodigoTransaccion("TX-TARJETA-" + cupon.hashCode());
	}

	public String getCuponPago() {
		return this.cuponPago;
	}
	
	public void procesarPago() {
		metodoPago.procesarPago(this);
	}
	
	public NotaDeCredito getNotaDeCredito() {
		return this.notaDeCredito; // en caso de que se quiera consultar la nota
	}

    // OBSERVER 
    public void agregarObservador(PedidoObserver pedidoObserver) {
        this.observadores.add(pedidoObserver);
    }

    public void quitarObservador(PedidoObserver pedidoObserver) {
        this.observadores.remove(pedidoObserver);
    }

    protected void notificar(EstadoPedido estadoAnterior, EstadoPedido nuevoEstado) {
        // Se le avisa a cada observador suscrito
        for (PedidoObserver pedidoObserver : this.observadores) {
        	pedidoObserver.actualizar(this, estadoAnterior, nuevoEstado);
        }
    }
    
    // METODOS DE INTEGRACIÓN ENVÍOS
    
	public float getPesoTotal() {
		return (float) items.stream().mapToDouble(i -> i.getPeso()).sum();
	}

	public Direccion getDireccionEnvio() {
		return this.cliente.getDireccion();
	}

	public float getValorTotal() {
		return (float) this.calcularSubtotal();
	}
}