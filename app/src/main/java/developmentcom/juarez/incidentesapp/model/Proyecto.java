package developmentcom.juarez.incidentesapp.model;

/**
 * Created by Juarez on 25/01/2017.
 */

public class Proyecto {
    private int id;
    private String nombre;

    public Proyecto() {
        this.id = 0;
        this.nombre = "";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
