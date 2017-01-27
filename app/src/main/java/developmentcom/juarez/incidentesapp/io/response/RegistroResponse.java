package developmentcom.juarez.incidentesapp.io.response;

/**
 * Created by Juarez on 24/01/2017.
 */

public class RegistroResponse {
    private boolean error;
    private String message;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
