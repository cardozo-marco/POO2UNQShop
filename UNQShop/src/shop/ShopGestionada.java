package shop;

import catalogo.Catalogo;
import catalogo.ItemCatalogo;
import entidades.Cliente;
import pedido.Pedido;
import pedido.NotaDeCredito;
import envio.MetodoEnvio;
import pago.MetodoPago;
import busqueda.CriterioBusqueda;
import reportes.FormateadorReporte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public String generarReporteMasVendidos(FormateadorReporte formateador) {
    	Map<ItemCatalogo, Integer> contadorVentas = new HashMap<>();
        for (Pedido pedido : this.pedidos) {
            for (ItemCatalogo item : pedido.getItems()) {
                contadorVentas.put(item, contadorVentas.getOrDefault(item, 0) + 1);
            }
        }
        
        List<ItemCatalogo> masVendidos = contadorVentas.entrySet().stream()
            .sorted(Map.Entry.<ItemCatalogo, Integer>comparingByValue().reversed())
            .limit(10)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        for(ItemCatalogo item : masVendidos) {
            item.aceptar(formateador);
        }
        
        return formateador.obtenerReporte();
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
