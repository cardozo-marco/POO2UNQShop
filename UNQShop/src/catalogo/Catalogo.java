package catalogo;

import busqueda.CriterioBusqueda;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Catalogo {
    private List<ItemCatalogo> items;

    public Catalogo() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemCatalogo item) {
        this.items.add(item);
    }

    public List<ItemCatalogo> buscar(CriterioBusqueda criterio) {
        return this.items.stream()
                   .filter(item -> criterio.satisface(item))
                   .collect(Collectors.toList());
    }
}
