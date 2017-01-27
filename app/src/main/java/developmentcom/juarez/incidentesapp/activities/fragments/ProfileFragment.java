package developmentcom.juarez.incidentesapp.activities.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import developmentcom.juarez.incidentesapp.Global;
import developmentcom.juarez.incidentesapp.R;
import developmentcom.juarez.incidentesapp.model.Usuario;

public class ProfileFragment extends Fragment {

    private EditText etUsuario, etEmail, etNombre, etTipo;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        etUsuario = (EditText) view.findViewById(R.id.etUsername);
        etNombre = (EditText) view.findViewById(R.id.etName);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etTipo = (EditText) view.findViewById(R.id.etType);

        //Llenado de campos
        Usuario user = Global.getFromSharedPreferences(getActivity(),"user_login");
        etUsuario.setText(user.getUsuario());
        etNombre.setText(user.getNombre());
        etEmail.setText(user.getEmail());
        etTipo.setText(user.getTipo());
        return view;
    }
}
