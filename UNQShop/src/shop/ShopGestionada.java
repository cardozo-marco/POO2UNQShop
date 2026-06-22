package core;

import catalogo.Catalogo;
import catalogo.ItemCatalogo;
import entidades.Cliente;
import entidades.Pedido;
import entidades.NotaDeCredito;
import envio.MetodoEnvio;
import pago.MetodoPago;
import busqueda.CriterioBusqueda;
import reportes.Reporte;
import reportes.FormateadorReporte;

import java.util.ArrayList;
import java.util.List;

public class ShopGestionada {
    
    
    private Catalogo catalogo;
    private List<Cliente> clientesRegistrados;
    private List<Pedido> pedidos;
    private List<NotaDeCredito> notasDeCredito;

    public ShopGestionada(Catalogo catalogo) {
        this.catalogo = catalogo;
        this.clientesRegistrados = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.notasDeCredito = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) {
        this.clientesRegistrados.add(cliente);
    }

    public Pedido crearPedido(Cliente cliente, MetodoEnvio metodoEnvio, MetodoPago metodoPago) {
        Pedido nuevoPedido = new Pedido(cliente, metodoEnvio, metodoPago);
        this.pedidos.add(nuevoPedido);
        return nuevoPedido;
    }

    public List<ItemCatalogo> buscarEnCatalogo(CriterioBusqueda criterio) {
        return this.catalogo.buscar(criterio);
    }

    public String generarReporte(Reporte reporte, FormateadorReporte formateador) {
        return reporte.accept(formateador);
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(this.clientesRegistrados);
    }

    public List<Pedido> getPedidos() {
        return new ArrayList<>(this.pedidos);
    }

    public void registrarNotaCredito(NotaDeCredito nota) {
        this.notasDeCredito.add(nota);
    }

    public List<NotaDeCredito> getNotasDeCreditoDe(Cliente cliente) {
        List<NotaDeCredito> notasCliente = new ArrayList<>();
        for (NotaDeCredito nota : this.notasDeCredito) {
            if (nota.getPedidoAsociado().getCliente().equals(cliente)) {
                notasCliente.add(nota);
            }
        }
        return notasCliente;
    }
}
