package developmentcom.juarez.incidentesapp.activities.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import developmentcom.juarez.incidentesapp.Global;
import developmentcom.juarez.incidentesapp.activities.PanelActivity;
import developmentcom.juarez.incidentesapp.io.response.CategoriasResponse;
import developmentcom.juarez.incidentesapp.io.response.RegistroResponse;
import developmentcom.juarez.incidentesapp.model.Categoria;
import developmentcom.juarez.incidentesapp.model.Proyecto;
import developmentcom.juarez.incidentesapp.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import developmentcom.juarez.incidentesapp.R;
import developmentcom.juarez.incidentesapp.io.IncidentesApiAdapter;
import developmentcom.juarez.incidentesapp.io.response.ProyectosResponse;

public class RegistrarFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Context contexto;
    private Spinner dpProyectos, dpCategorias, dpRepro, dpSeveridad, dpPrioridad, dpVisibilidad;
    private EditText etResumen, etDescripcion, etInfo, etPasos, etPlataforma, etSO, etVersion;
    private HashMap<Integer,String> spinnerMap, categoriaMap;
    private Usuario usuario;
    private AlertDialog infoalert;

    public RegistrarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_registrar, container, false);

        usuario = Global.getFromSharedPreferences(getActivity(),"user_login");

        //Spiners
        dpProyectos = (Spinner)view.findViewById(R.id.spProyecto);
        dpCategorias = (Spinner)view.findViewById(R.id.spCategoria);
        dpRepro = (Spinner)view.findViewById(R.id.spReproductibilidad);
        dpSeveridad = (Spinner)view.findViewById(R.id.spSeveridad);
        dpPrioridad = (Spinner)view.findViewById(R.id.spPrioridad);
        dpVisibilidad = (Spinner)view.findViewById(R.id.spVisibilidad);
        //EditText
        etResumen = (EditText)view.findViewById(R.id.etResumen);
        etDescripcion = (EditText)view.findViewById(R.id.etDescripcion);
        etInfo = (EditText)view.findViewById(R.id.etInfo);
        etPasos = (EditText)view.findViewById(R.id.etPasos);
        etPlataforma = (EditText)view.findViewById(R.id.etPlataforma);
        etSO = (EditText)view.findViewById(R.id.etSistemaO);
        etVersion = (EditText)view.findViewById(R.id.etVersionSO);
        //Boton
        Button btnRegistrar = (Button)view.findViewById(R.id.btn_Registrar);

        //Store de Items
        Call<ProyectosResponse> call = IncidentesApiAdapter.getApiService(getActivity()).getProyectos();
        call.enqueue(callbackP);
        String[] itemsRepro = new String[]{"Siempre", "A veces", "Aleatorio","No se ha intentado","No reproducible","Desconocido"};
        String[] itemsSeveridad = new String[]{"Funcionalidad", "Trivial", "Ajuste","Fallo","Bloqueo"};
        String[] itemsPrioridad = new String[]{"Ninguna", "Baja", "Normal","Alta","Urgente"};
        String[] itemsVisibilidad = new String[]{"Privado","Público"};
        //Llenado de Spiners
        llenarSpiner(dpRepro,itemsRepro);
        llenarSpiner(dpSeveridad,itemsSeveridad);
        llenarSpiner(dpPrioridad,itemsPrioridad);
        llenarSpiner(dpVisibilidad,itemsVisibilidad);

        //Listeners
        btnRegistrar.setOnClickListener(myButtonListener);
        dpProyectos.setOnItemSelectedListener(mySpinnerListener);

        //Inicializar contexto
        contexto = getActivity();

        return view;
    }

    private AdapterView.OnItemSelectedListener mySpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //Toast.makeText(contexto,adapterView.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            Call<CategoriasResponse> call = IncidentesApiAdapter.getApiService(getActivity()).getCategorias(spinnerMap.get(i));
            call.enqueue(callbackC);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    private View.OnClickListener myButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_Registrar:
                    Call<RegistroResponse> call = IncidentesApiAdapter.getApiService(getActivity()).postRegistro(spinnerMap.get(dpProyectos.getSelectedItemPosition()),categoriaMap.get(dpCategorias.getSelectedItemPosition()),dpRepro.getSelectedItem().toString(),dpSeveridad.getSelectedItem().toString(),dpPrioridad.getSelectedItem().toString(),
                            etResumen.getText().toString(),etDescripcion.getText().toString(),etInfo.getText().toString(),etPasos.getText().toString(),dpVisibilidad.getSelectedItem().toString(),etPlataforma.getText().toString(),etSO.getText().toString(),etVersion.getText().toString(),usuario.getId());
                    call.enqueue(callbackR);
                    break;
            }
        }
    };

    private Callback<ProyectosResponse> callbackP =  new Callback<ProyectosResponse>(){

        @Override
        public void onResponse(Call<ProyectosResponse> call, Response<ProyectosResponse> response) {
            if (response.isSuccessful()) {
                ProyectosResponse proyectosResponse = response.body();
                List<String> items = new ArrayList<>();
                ArrayList<Proyecto> proyectos = proyectosResponse.getProyectos();
                spinnerMap = new HashMap<Integer, String>();
                int i = 0;
                for (Proyecto proyecto:
                     proyectos) {
                    spinnerMap.put(i,String.valueOf(proyecto.getId()));
                    items.add(proyecto.getNombre());
                    Log.d("Item",proyecto.getNombre());
                    i++;
                }
                String[] simpleitems = new String[items.size()];
                items.toArray(simpleitems);
                llenarSpiner(dpProyectos,simpleitems);
            } else {
                Toast.makeText(contexto, "El servidor envió una respuesta inválida", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ProyectosResponse> call, Throwable t) {
            Toast.makeText(contexto, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private Callback<CategoriasResponse> callbackC =  new Callback<CategoriasResponse>(){

        @Override
        public void onResponse(Call<CategoriasResponse> call, Response<CategoriasResponse> response) {
            if (response.isSuccessful()) {
                CategoriasResponse categoriasResponse = response.body();
                List<String> items = new ArrayList<>();
                ArrayList<Categoria> categorias = categoriasResponse.getCategorias();
                categoriaMap = new HashMap<Integer, String>();
                int i = 0;
                for (Categoria categoria:
                        categorias) {
                    categoriaMap.put(i,String.valueOf(categoria.getId()));
                    items.add(categoria.getNombre());
                    Log.d("Item",categoria.getNombre());
                    i++;
                }
                String[] simpleitems = new String[items.size()];
                items.toArray(simpleitems);
                llenarSpiner(dpCategorias,simpleitems);
            } else {
                Toast.makeText(contexto, "El servidor envió una respuesta inválida", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<CategoriasResponse> call, Throwable t) {
            Toast.makeText(contexto, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private Callback<RegistroResponse> callbackR =  new Callback<RegistroResponse>(){

        @Override
        public void onResponse(Call<RegistroResponse> call, Response<RegistroResponse> response) {
            if (response.isSuccessful()) {
                final RegistroResponse registroResponse = response.body();
                List<String> items = new ArrayList<>();
                String mensaje = registroResponse.getMessage();
                AlertDialog.Builder sincroInfo = new AlertDialog.Builder(contexto);
                sincroInfo.setTitle("Mensaje Respuesta");
                sincroInfo.setCancelable(false);
                sincroInfo.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                if(!registroResponse.isError()) {
                                    Intent intent = new Intent(contexto, PanelActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                sincroInfo.setMessage(mensaje);
                infoalert = sincroInfo.create();
                infoalert.show();
            } else {
                Toast.makeText(contexto, "El servidor envió una respuesta inválida", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<RegistroResponse> call, Throwable t) {
            Toast.makeText(contexto, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };


    private void llenarSpiner(Spinner spiner, String[] items){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        //Llenando spiners
        spiner.setAdapter(adapter);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
