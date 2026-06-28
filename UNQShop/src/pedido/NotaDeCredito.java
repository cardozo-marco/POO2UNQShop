package pedido;

import java.time.LocalDate;

public class NotaDeCredito {
	
	private String idNotaCredito;
	private Pedido pedido;
	private double montoReembolso;
	private LocalDate fechaEmision;
	
	public NotaDeCredito(Pedido pedido,double montoReembolso) {
		this.pedido         = pedido;
		this.montoReembolso = montoReembolso;
		this.fechaEmision = LocalDate.now();
		this.idNotaCredito  = "NC-" + this.fechaEmision.toString() + "-" + pedido.hashCode();
	}
	
	public String getIdNotaCredito() {
		return this.idNotaCredito; 
	}
	
	public double getMontoReembolso() {
		return this.montoReembolso;
	}
	
	public Pedido getPedidoAsociado() {
		return this.pedido;
	}
	
	public LocalDate getFechaEmision() {
		return this.fechaEmision;
	}
}
