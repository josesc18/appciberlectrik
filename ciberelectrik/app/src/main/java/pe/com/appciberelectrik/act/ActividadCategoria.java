package pe.com.appciberelectrik.act;

import android.os.Bundle;

import android.app.Fragment;
import android.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.adaptadores.AdaptadorCategoria;
import pe.com.appciberelectrik.adaptadores.AdaptadorDistrito;
import pe.com.appciberelectrik.bean.Categoria;
import pe.com.appciberelectrik.dao.CategoriaDAO;
import pe.com.appciberelectrik.imp.CategoriaImp;
import pe.com.appciberelectrik.util.General;


public class ActividadCategoria extends Fragment {

    public static final String TAG="Fragmento Categoria";
    private TextView lblCodCat;
    private EditText txtNomCat;
    private CheckBox chkEstCat;
    private ListView lstCategoria;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    private int cod=0, fila=-1, est=0;
    private String nom="";
    private boolean res=false;

    CategoriaDAO daoCategoria = new CategoriaImp();
    ArrayList<Categoria> registroCategoria;
    Categoria categoria = new Categoria();
    General objgeneral= new General();
    AdaptadorCategoria adaptador;
    FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View raiz=inflater.inflate(R.layout.fragment_actividad_categoria, container, false);
        lblCodCat = raiz.findViewById(R.id.lblCodCat);
        txtNomCat = raiz.findViewById(R.id.txtNomCat);
        chkEstCat = raiz.findViewById(R.id.chkEstCat);
        lstCategoria = raiz.findViewById(R.id.lstCategoria);

        btnRegistrar=raiz.findViewById(R.id.btnRegistrar);
        btnActualizar=raiz.findViewById(R.id.btnActualizar);
        btnEliminar=raiz.findViewById(R.id.btnEliminar);

        registroCategoria = new ArrayList<>();
        registroCategoria = daoCategoria.MostrarCategoria(raiz.getContext());

        if(registroCategoria !=null){
            adaptador = new AdaptadorCategoria(raiz.getContext(),registroCategoria);
            lstCategoria.setAdapter(adaptador);
        }
        lstCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //obtenemos el valor de la fila seleccionada
                fila=position;
                //asignado los valores a los controles
                lblCodCat.setText(""+registroCategoria.get(fila).getId());
                txtNomCat.setText(registroCategoria.get(fila).getName());
                if(registroCategoria.get(fila).getEstado()==1){
                    chkEstCat.setChecked(true);
                }else{
                    chkEstCat.setChecked(false);
                }
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNomCat.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre de la categoria" ,"Registrar Categoria");
                    txtNomCat.requestFocus();
                    raiz.findViewById(R.id.frmCategoria);
                }else{
                    if(chkEstCat.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }
                    categoria.setEstado(est);
                    categoria.setName(txtNomCat.getText().toString());

                    res = daoCategoria.RegistrarCategroia(categoria,raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se registro la categoria correctamente","Registrar Categoria");
                        CargarFragmento();
                        lstCategoria.setAdapter(adaptador);
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCategoria));
                        txtNomCat.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se registro la categoria correctamente","Registrar Categoria");
                        CargarFragmento();
                    }
                }
            }
        });

        return raiz;
    }
    public void CargarFragmento(){
        ActividadCategoria fcategoria= new ActividadCategoria();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor,fcategoria, ActividadPerfil.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }
}