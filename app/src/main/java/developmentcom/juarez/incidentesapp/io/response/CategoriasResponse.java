package developmentcom.juarez.incidentesapp.io.response;

import java.util.ArrayList;

import developmentcom.juarez.incidentesapp.model.Categoria;

/**
 * Created by Juarez on 25/01/2017.
 */

public class CategoriasResponse {
    private ArrayList<Categoria> categorias;

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }
}
