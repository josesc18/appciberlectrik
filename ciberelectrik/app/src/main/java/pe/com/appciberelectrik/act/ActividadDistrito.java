package pe.com.appciberelectrik.act;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import android.app.Fragment;
import android.app.FragmentTransaction;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.adaptadores.AdaptadorComboDistrito;
import pe.com.appciberelectrik.adaptadores.AdaptadorDistrito;
import pe.com.appciberelectrik.bean.Distrito;
import pe.com.appciberelectrik.dao.DistritoDAO;
import pe.com.appciberelectrik.imp.DistritoImp;
import pe.com.appciberelectrik.util.General;


public class ActividadDistrito extends Fragment {

    public static final String TAG="Fragmento Distrito";
    private TextView lblCodDis;
    private EditText txtNomDis;
    private CheckBox chkEstDis;
    private ListView lstDistrito;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    private int cod=0, fila=-1, est=0;
    private String nom="";
    private boolean res=false;

    DistritoDAO daoDistrito = new DistritoImp();

    General objgeneral= new General();

    AdaptadorDistrito adaptador;

    ArrayList<Distrito> registroDistrito;
    Distrito distrito = new Distrito();

    FragmentTransaction ft;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        final View raiz=inflater.inflate(R.layout.actividad_distrito, container, false);
        lblCodDis=raiz.findViewById(R.id.lblCodDis);
        txtNomDis=raiz.findViewById(R.id.txtNomDis);
        chkEstDis=raiz.findViewById(R.id.chkEstDis);
        lstDistrito = raiz.findViewById(R.id.lstDistritos);
        btnRegistrar=raiz.findViewById(R.id.btnRegistrar);
        btnActualizar=raiz.findViewById(R.id.btnActualizar);
        btnEliminar=raiz.findViewById(R.id.btnEliminar);

        registroDistrito = new ArrayList<>();
        registroDistrito = daoDistrito.MostrarDistrito(raiz.getContext());

        if(registroDistrito != null){
            adaptador = new AdaptadorDistrito(raiz.getContext(),registroDistrito);
            lstDistrito .setAdapter(adaptador);
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNomDis.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre del distrito" ,"Registran Distrito");
                    txtNomDis.requestFocus();
                    raiz.findViewById(R.id.frmDistrito);
                }else{
                    nom=txtNomDis.getText().toString();
                    if(chkEstDis.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }
                    distrito.setNombre(nom);
                    distrito.setEstado(est);

                    res=daoDistrito.RegistrarDistrito(distrito,raiz.getContext());

                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se registro el distrito correctamente","Registrar Distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstDistrito.setAdapter(adaptador);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNomDis.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se registro el distrito correctamente","Registrar Distrito");
                        CargarFragmento();
                    }
                }
            }
        });

        lstDistrito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //obtenemos el valor de la fila seleccionada
                fila=i;
                //asignado los valores a los controles
                lblCodDis.setText(""+registroDistrito.get(fila).getCodigo());
                txtNomDis.setText(registroDistrito.get(fila).getNombre());
                if(registroDistrito.get(fila).getEstado()==1){
                    chkEstDis.setChecked(true);
                }else{
                    chkEstDis.setChecked(false);
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validar para que se selecciona un elemento de la lista
                if(fila>=0){
                    cod=Integer.parseInt(lblCodDis.getText().toString());
                    nom=txtNomDis.getText().toString();
                    if(chkEstDis.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }
                    //enviamos los datos al objeto
                    distrito.setCodigo(cod);
                    distrito.setNombre(nom);
                    distrito.setEstado(est);
                    //agregamos los valores para actualizar
                    res=daoDistrito.ActualizarDistrito(distrito,raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se actualizo el distrito correctamente","Actualizar distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstDistrito.setAdapter(adaptador);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNomDis.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el distrito correctamente","Actualziar distrito");
                        CargarFragmento();
                    }

                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista","Actualizar distrito");
                    CargarFragmento();
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validamos que se seleccione un elemento de la lista
                if(fila>=0){
                    cod=Integer.parseInt(lblCodDis.getText().toString());
                    //enviamos el codigo
                    distrito.setCodigo(cod);
                    //eliminamos
                    res=daoDistrito.EliminarDistrito(distrito);
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se elimino el distrito correctamente","Eliminar distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstDistrito.setAdapter(adaptador);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNomDis.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el distrito correctamente","Eliminar distrito");
                        CargarFragmento();
                    }
                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista","Eliminar distrito");
                    CargarFragmento();
                }

            }
        });

        return raiz;

    }

    public void CargarFragmento(){
        ActividadDistrito fdistrito= new ActividadDistrito();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor,fdistrito, ActividadPerfil.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

}