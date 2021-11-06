package pe.com.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.bean.Categoria;
import pe.com.appciberelectrik.bean.Cliente;

public class AdaptadorCategoria extends BaseAdapter {
    private ArrayList<Categoria> listaCategoria;
    LayoutInflater layoutInflater;

    public AdaptadorCategoria(Context contexto,ArrayList<Categoria> listaCategoria){
        this.layoutInflater = LayoutInflater.from(contexto);
        this.listaCategoria = listaCategoria;
    }

    @Override
    public int getCount() {
        return listaCategoria.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCategoria.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_categoria,parent,false);
            Categoria c = (Categoria) getItem(position);

            TextView lblCodCat = view.findViewById(R.id.lblCodCatEle);
            TextView lblNomCat = view.findViewById(R.id.lblNomCatEle);
            TextView lblEstCat = view.findViewById(R.id.lblCatEstEle);

            lblCodCat.setText(""+c.getId());
            lblNomCat.setText(""+c.getName());
            if(c.getEstado()==1){
                lblEstCat.setText("Habilitado");
            }else{
                lblEstCat.setText("Deshabilitado");
            }

        }
        return view;
    }
}
