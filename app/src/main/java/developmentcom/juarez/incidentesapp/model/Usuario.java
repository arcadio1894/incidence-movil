package developmentcom.juarez.incidentesapp.model;

/**
 * Created by Juarez on 23/01/2017.
 */

public class Usuario {
    private int id;
    private String nombre;
    private String usuario;
    private String email;
    private String tipo;

    public Usuario() {
        this.setId(0);
        this.setNombre("");
        this.setUsuario("");
        this.setEmail("");
        this.setTipo("");
    }

    public Usuario(int id, String nombre, String usuario, String email, String tipo) {
        this.setId(id);
        this.setNombre(nombre);
        this.setUsuario(usuario);
        this.setEmail(email);
        this.setTipo(tipo);
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
