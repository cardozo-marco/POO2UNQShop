package shop;

import catalogo.Catalogo;
import catalogo.ItemCatalogo;
import entidades.Cliente;
import pedido.Pedido;
import pedido.NotaDeCredito;
import envios.MetodoEnvio;
import pago.MetodoPago;
import busqueda.CriterioBusqueda;
import reportes.FormateadorReporte;

import java.util.ArrayList;
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
        Map<String, List<ItemCatalogo>> ventasPorNombre = this.pedidos.stream()
            .flatMap(pedido -> pedido.getItems().stream())
            .collect(Collectors.groupingBy(ItemCatalogo::getNombre));

        ventasPorNombre.entrySet().stream()
            .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
            .limit(10)
            .forEach(entry -> {
                List<ItemCatalogo> items = entry.getValue();
                ItemCatalogo item = items.get(0);
                int cantidad = items.size();
                double precioPromedio = items.stream().mapToDouble(ItemCatalogo::getPrecioFinal).average().orElse(0.0);
                item.aceptar(formateador, cantidad, precioPromedio);
            });
            
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
