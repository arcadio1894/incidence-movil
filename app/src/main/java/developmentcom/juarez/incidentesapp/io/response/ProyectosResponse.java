package developmentcom.juarez.incidentesapp.io.response;

import java.util.ArrayList;

import developmentcom.juarez.incidentesapp.model.Proyecto;

/**
 * Created by Juarez on 25/01/2017.
 */

public class ProyectosResponse {
    private ArrayList<Proyecto> proyectos;

    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(ArrayList<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
}
