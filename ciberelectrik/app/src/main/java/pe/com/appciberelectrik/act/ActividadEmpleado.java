package pe.com.appciberelectrik.act;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.adaptadores.AdaptadorComboDistrito;
import pe.com.appciberelectrik.adaptadores.AdaptadorComboPerfil;
import pe.com.appciberelectrik.bean.Distrito;
import pe.com.appciberelectrik.bean.Perfil;
import pe.com.appciberelectrik.dao.DistritoDAO;
import pe.com.appciberelectrik.dao.PerfilDAO;
import pe.com.appciberelectrik.imp.DistritoImp;
import pe.com.appciberelectrik.imp.PerfilImp;

public class ActividadEmpleado extends Fragment {
    //creamos una variable estatica
    public static final String TAG="Fragmento Empleado";
    //creando controles
    private Spinner cboPerfil, cboDistrito;
    //creamos un adaptador de tipo ComboPerfil
    AdaptadorComboPerfil adaptadorComboPerfil;
    //creamos un Arraylist de tipo Perfil
    ArrayList<Perfil> registroperfil;
    //implemento la interfaz Perfil
    PerfilDAO daoperfil= new PerfilImp();
    //creamos un Adaptador de tipo Distrito
    AdaptadorComboDistrito adaptadorComboDistrito;
    //cremoas un arreglo de tipo Distrito
    ArrayList<Distrito> registrodistrito;
    //implementamos la interfaz distrito
    DistritoDAO daodistrito= new DistritoImp();


    public ActividadEmpleado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View raiz=inflater.inflate(R.layout.actividad_empleado, container, false);
        //creamos los controles
        cboPerfil=raiz.findViewById(R.id.cboPerfil);
        cboDistrito=raiz.findViewById(R.id.cboDistrito);
        //creamos el arreglo
        registroperfil=new ArrayList<>();
        registrodistrito=new ArrayList<>();
        //llamamos a la funcion mostrar perfiles
        registroperfil=daoperfil.MostrarPerfil(raiz.getContext());
        registrodistrito=daodistrito.MostrarDistrito(raiz.getContext());
        //evaluamos que haya elementos
        if(registroperfil!=null){
            //creamos el adaptador
            adaptadorComboPerfil=new AdaptadorComboPerfil(raiz.getContext(), registroperfil);
            //asignamos el adaptador al combo
            cboPerfil.setAdapter(adaptadorComboPerfil);
        }
        if(registrodistrito!=null){
            adaptadorComboDistrito=new AdaptadorComboDistrito(raiz.getContext(),registrodistrito);
            cboDistrito.setAdapter(adaptadorComboDistrito);
        }
        return raiz;

    }
}