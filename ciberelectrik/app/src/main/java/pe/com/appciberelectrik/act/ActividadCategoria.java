package pe.com.appciberelectrik.act;

import android.os.Bundle;

import android.app.Fragment;
import android.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.com.appciberelectrik.R;


public class ActividadCategoria extends Fragment {

    FragmentTransaction ft;

    public ActividadCategoria() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actividad_categoria, container, false);
    }
    public void CargarFragmento(){
        ActividadCategoria fcategoria= new ActividadCategoria();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor,fcategoria, ActividadPerfil.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }
}