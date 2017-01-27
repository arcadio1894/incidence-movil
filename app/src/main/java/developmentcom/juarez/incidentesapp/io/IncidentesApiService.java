package developmentcom.juarez.incidentesapp.io;

import developmentcom.juarez.incidentesapp.io.response.CategoriasResponse;
import developmentcom.juarez.incidentesapp.io.response.LoginResponse;
import developmentcom.juarez.incidentesapp.io.response.ProyectosResponse;
import developmentcom.juarez.incidentesapp.io.response.RegistroResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Juarez on 22/01/2017.
 */

public interface IncidentesApiService {

    @GET("loginmobile.php")
    Call<LoginResponse> getLogin(@Query("user") String email, @Query("pass") String password);

    @POST("registrar_incidente.php")
    Call<RegistroResponse> postRegistro(@Query("proyectos") String proyectos, @Query("categorias") String categorias, @Query("reproductibilidad") String reproductibilidad, @Query("severidad") String severidad, @Query("prioridad") String prioridad, @Query("resumen") String resumen, @Query("descripcion") String descripcion, @Query("info") String info, @Query("pasos") String pasos, @Query("visibilidad") String visibilidad, @Query("plataforma") String plataforma, @Query("so") String so, @Query("version") String version, @Query("idUsuario") int idUsuario);

    @GET("proyectos.php")
    Call<ProyectosResponse> getProyectos();

    @GET("categorias.php")
    Call<CategoriasResponse> getCategorias(@Query("proyecto") String idProyecto);

}
