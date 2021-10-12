package pe.com.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.bean.Perfil;

public class AdaptadorPerfil extends BaseAdapter {
    //creamos un ArrayList de la clase Perfil
    private ArrayList<Perfil> listaperfil;
    //creamos un LayoutInflater --> para poder inflar o
    // cargar un layout dentro de otro
    LayoutInflater layoutInflater;

    //creamos un metodo constructor

    public AdaptadorPerfil(Context contexto, ArrayList<Perfil> aperfil) {
        this.listaperfil=aperfil;
        layoutInflater=LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return listaperfil.size();
    }

    @Override
    public Object getItem(int i) {
        return listaperfil.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //evaluamos que la vista no se nula
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_perfil,viewGroup,false);
            //creamos un objeto de la clase Perfil
            Perfil objperfil=(Perfil)getItem(i);
            //creamos los controles
            TextView lblCodPer=view.findViewById(R.id.lblCodPer);
            TextView lblNomPer=view.findViewById(R.id.lblNomPer);
            TextView lblEstPer=view.findViewById(R.id.lblEstPer);
            //agregamos los valores a los controles
            lblCodPer.setText(""+objperfil.getCodigo());
            lblNomPer.setText(""+objperfil.getNombre());
            if(objperfil.getEstado()==1){
                lblEstPer.setText("Habilitado");
            }else{
                lblEstPer.setText("Deshabilitado");
            }
        }
        return view;
    }
}
