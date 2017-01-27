package developmentcom.juarez.incidentesapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import developmentcom.juarez.incidentesapp.model.Usuario;

/**
 * Created by Juarez on 24/01/2017.
 */

public class Global {
    public static void saveInSharedPreferences(Activity activity, String key, Usuario usuario) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        editor.putString(key, json);
        editor.apply();
    }

    public static Usuario getFromSharedPreferences(Activity activity, String key)
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(key, "");
        Usuario obj = gson.fromJson(json, Usuario.class);
        return obj;
    }

    public static void clearSharedPreferences(Activity activity)
    {
        saveInSharedPreferences(activity,"user_login",null);
    }
}
