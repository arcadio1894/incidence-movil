package developmentcom.juarez.incidentesapp.io;

import android.content.Context;

import developmentcom.juarez.incidentesapp.dal.Utilitario;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Juarez on 22/01/2017.
 */

public class IncidentesApiAdapter {
    private static IncidentesApiService API_SERVICE;

    public static IncidentesApiService getApiService(Context ctx) {

        // Creating the interceptor, and setting the log level
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        String url;
        url= Utilitario.readProperties(ctx).getProperty("IP_SERVER");
        String baseUrl = "http://" + url;

        //String baseUrl = "http://192.168.173.1:50/incidence-managment/CargaAndroid/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- using the log level
                    .build();
            API_SERVICE = retrofit.create(IncidentesApiService.class);
        }

        return API_SERVICE;
    }
}
