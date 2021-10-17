package pe.com.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.bean.Cliente;

public class AdaptadorCliente extends BaseAdapter {

    private ArrayList<Cliente> listaClientes;
    LayoutInflater layoutInflater;

    public AdaptadorCliente(Context contexto, ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        this.layoutInflater = LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return listaClientes.size();
    }

    @Override
    public Object getItem(int position) {
        return listaClientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_cliente,parent,false);
            Cliente c = (Cliente) getItem(i);

            TextView lblCodCli = view.findViewById(R.id.lblCodCliItem);
            TextView lblNomCli = view.findViewById(R.id.lblNomCliItem);
            TextView lblApeCli = view.findViewById(R.id.lblApeCliItem);
            TextView lblDniCli=view.findViewById(R.id.lblDniCliItem);

            lblCodCli.setText(""+c.getCodigo());
            lblNomCli.setText(""+c.getNombre());
            lblApeCli.setText(""+c.getApellidos());
            lblDniCli.setText(""+c.getDni());

        }
        return view;
    }
}
