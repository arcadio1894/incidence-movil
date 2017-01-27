package developmentcom.juarez.incidentesapp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import developmentcom.juarez.incidentesapp.Global;
import developmentcom.juarez.incidentesapp.R;
import developmentcom.juarez.incidentesapp.io.IncidentesApiAdapter;
import developmentcom.juarez.incidentesapp.io.response.LoginResponse;
import developmentcom.juarez.incidentesapp.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Callback<LoginResponse> {

    private EditText etLogin, etPassword;
    private ProgressDialog prgrssdlgCargando;
    private Context contexto;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        contexto = this;
        activity = this;
        etLogin = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        Usuario usuario = Global.getFromSharedPreferences(this,"user_login");
        if(usuario!=null){
            Intent intent = new Intent(this, PanelActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                final String user = etLogin.getText().toString();
                final String password = etPassword.getText().toString();
                Call<LoginResponse> call = IncidentesApiAdapter.getApiService(contexto).getLogin(user, password);
                prgrssdlgCargando = ProgressDialog.show(contexto, "Login|Entrar", "Verificando sus datos.", true);
                prgrssdlgCargando.setMessage(Html.fromHtml("<font color='white'>" + "Verificando..." + "</font>"));
                call.enqueue(this);
                break;
        }
    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if (response.isSuccessful()) {
            LoginResponse loginResponse = response.body();
            if (prgrssdlgCargando!= null)
                prgrssdlgCargando.dismiss();
            if (loginResponse.isError()) {
                Toast.makeText(this, loginResponse.getMensaje() , Toast.LENGTH_SHORT).show();
            } else {
                Global.saveInSharedPreferences(activity, "user_login", response.body().getUsuario());
                Intent intent = new Intent(this, PanelActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            Toast.makeText(this, "El servidor envió una respuesta inválida", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        if (prgrssdlgCargando!= null)
            prgrssdlgCargando.dismiss();
        Toast.makeText(this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

}

