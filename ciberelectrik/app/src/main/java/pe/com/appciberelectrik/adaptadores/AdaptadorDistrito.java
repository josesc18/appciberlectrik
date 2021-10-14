package pe.com.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.bean.Distrito;
import pe.com.appciberelectrik.bean.Perfil;

public class AdaptadorDistrito extends BaseAdapter {

    private ArrayList<Distrito> listaDistrito;
    LayoutInflater layoutInflater;

    public AdaptadorDistrito(Context contexto,ArrayList<Distrito> aListaDistrito){
        this.listaDistrito = aListaDistrito;
        this.layoutInflater=LayoutInflater.from(contexto);
    }
    @Override
    public int getCount() {
        return listaDistrito.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDistrito.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_distrito,parent,false);
            //creamos un objeto de la clase Perfil
            Distrito objdistrito=(Distrito) getItem(i);
            //creamos los controles
            TextView lblCodPer=view.findViewById(R.id.lblCodDisEle);
            TextView lblNomPer=view.findViewById(R.id.lblNomDisEle);
            TextView lblEstPer=view.findViewById(R.id.lblDisEle);
            //agregamos los valores a los controles
            lblCodPer.setText(""+objdistrito.getCodigo());
            lblNomPer.setText(""+objdistrito.getNombre());
            if(objdistrito.getEstado()==1){
                lblEstPer.setText("Habilitado");
            }else{
                lblEstPer.setText("Deshabilitado");
            }
        }
        return view;
    }
}
