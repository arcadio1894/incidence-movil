package developmentcom.juarez.incidentesapp.io.response;

import developmentcom.juarez.incidentesapp.model.Usuario;

/**
 * Created by Juarez on 23/01/2017.
 */

public class LoginResponse {

    private boolean error;
    private String mensaje;
    private Usuario usuario;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
