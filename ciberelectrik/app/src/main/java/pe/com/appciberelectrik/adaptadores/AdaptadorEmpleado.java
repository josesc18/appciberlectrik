package pe.com.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.bean.Empleado;

public class AdaptadorEmpleado extends BaseAdapter {

    private ArrayList<Empleado> listaEmpleado;
    LayoutInflater layoutInflater;

    public AdaptadorEmpleado(Context contexto,ArrayList<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
        this.layoutInflater = LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return listaEmpleado.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEmpleado.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_empleado,parent,false);
            //creamos un objeto de la clase Perfil
            Empleado objempleado=(Empleado) getItem(i);
            //creamos los controles
            TextView lblCodEmp=view.findViewById(R.id.codEmplitem);
            TextView lblNomEmp=view.findViewById(R.id.nomEmplItem);
            TextView lblPatEmp=view.findViewById(R.id.patEmplItem);
            TextView lblMatEmp=view.findViewById(R.id.matEmplItem);
            TextView lblEstPer=view.findViewById(R.id.estEmplItem);
            TextView lblDirEmp=view.findViewById(R.id.dirEmplItem);
            //agregamos los valores a los controles
            lblCodEmp.setText(""+objempleado.getCodigo());
            lblNomEmp.setText(""+objempleado.getNombre());
            lblPatEmp.setText(""+objempleado.getApellidop());
            lblMatEmp.setText(""+objempleado.getApellidom());
            lblDirEmp.setText(""+objempleado.getDireccion());
            if(objempleado.getEstado()==1){
                lblEstPer.setText("Habilitado");
            }else{
                lblEstPer.setText("Deshabilitado");
            }
        }
        return view;
    }
}
