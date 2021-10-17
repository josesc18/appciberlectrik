package pe.com.appciberelectrik.act;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.adaptadores.AdaptadorCliente;
import pe.com.appciberelectrik.adaptadores.AdaptadorComboDistrito;
import pe.com.appciberelectrik.bean.Cliente;
import pe.com.appciberelectrik.bean.Distrito;
import pe.com.appciberelectrik.dao.ClienteDAO;
import pe.com.appciberelectrik.dao.DistritoDAO;
import pe.com.appciberelectrik.imp.ClienteImp;
import pe.com.appciberelectrik.imp.DistritoImp;
import pe.com.appciberelectrik.util.General;


public class ActividadCliente extends Fragment {

    public static final String TAG="Fragmento Cliente";
    private TextView lblCodCli,lblCodDis;
    private EditText txt_nom,txt_ape,txt_cel,txt_tel,txt_dni,txt_dir,txt_cor,txt_fnac;
    private Spinner cbDistrito;
    private CheckBox chkEstCli;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    private int cod=0, fila=-1, est=0;
    private boolean res=false;
    private ListView lstCliente;

    FragmentTransaction ft;

    AdaptadorComboDistrito adaptadorComboDistrito;
    ArrayList<Distrito> registrodistrito;
    DistritoDAO daodistrito= new DistritoImp();

    Cliente cliente = new Cliente();
    ArrayList<Cliente> registroCliente;
    AdaptadorCliente adaptadorCliente ;
    ClienteDAO clienteDAO = new ClienteImp();

    General objgeneral= new General();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        final View raiz=inflater.inflate(R.layout.actividad_cliente, container, false);

        cbDistrito = raiz.findViewById(R.id.cboDistrito);
        registrodistrito = daodistrito.MostrarDistrito(raiz.getContext());

        if(registrodistrito!=null){
            adaptadorComboDistrito=new AdaptadorComboDistrito(raiz.getContext(),registrodistrito);
            cbDistrito.setAdapter(adaptadorComboDistrito);
        }

        lblCodCli = raiz.findViewById(R.id.lblCodCli);
        lblCodDis = raiz.findViewById(R.id.lblCoddis);

        txt_nom = raiz.findViewById(R.id.txtNomCli);
        txt_ape = raiz.findViewById(R.id.txtApeCli);
        txt_dni = raiz.findViewById(R.id.txtDniCli);
        txt_tel = raiz.findViewById(R.id.txtTelCli);
        txt_cel = raiz.findViewById(R.id.txtCelCli);
        txt_dir = raiz.findViewById(R.id.txtDirCli);
        txt_cor = raiz.findViewById(R.id.txtCorCli);
        txt_fnac = raiz.findViewById(R.id.txtFNacCli);

        lstCliente = raiz.findViewById(R.id.lstCliente);

        registroCliente = clienteDAO.MostrarClientes(raiz.getContext());
        if(registroCliente != null ){
            adaptadorCliente = new AdaptadorCliente(raiz.getContext(), registroCliente);
            lstCliente.setAdapter(adaptadorCliente);
        }

        chkEstCli = raiz.findViewById(R.id.chkEstCli);

        btnRegistrar = raiz.findViewById(R.id.btnRegistrar);
        btnActualizar = raiz.findViewById(R.id.btnActualizar);
        btnEliminar = raiz.findViewById(R.id.btnEliminar);

        cbDistrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Distrito distrito = registrodistrito.get(i);
                lblCodDis.setText(""+distrito.getCodigo());
                cliente.setDistrito(distrito);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        lstCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fila=position;
                lblCodCli.setText(""+registroCliente.get(position).getCodigo());
                txt_nom.setText(""+registroCliente.get(position).getNombre());
                txt_ape.setText(""+registroCliente.get(position).getApellidos());
                txt_cel.setText(""+registroCliente.get(position).getCelular());
                txt_cor.setText(""+registroCliente.get(position).getCorreo());
                txt_dir.setText(""+registroCliente.get(position).getDireccion());
                txt_dni.setText(""+registroCliente.get(position).getDni());
                txt_tel.setText(""+registroCliente.get(position).getTelefono());
                txt_fnac.setText(""+registroCliente.get(position).getfNacieminto());
                if(registroCliente.get(position).getEstado() == 1){
                    chkEstCli.setChecked(true);
                }else{
                    chkEstCli.setChecked(false);
                }
                cbDistrito.setSelection(
                        getItemDistrito(adaptadorComboDistrito,registroCliente.get(fila).getDistrito())
                );


            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(txt_nom.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre del cliente" ,"Registrar Cliente");
                    txt_nom.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }else if(txt_ape.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el apellido del cliente" ,"Registrar Cliente");
                    txt_ape.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }else if(txt_fnac.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese fecha de nacimiento del cliente" ,"Registrar Cliente");
                    txt_fnac.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }else if(txt_dir.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese direccion del cliente" ,"Registrar Cliente");
                    txt_dir.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }else if(txt_cel.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese celular del cliente" ,"Registrar Cliente");
                    txt_cel.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }else if(txt_tel.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese telefono del cliente" ,"Registrar Cliente");
                    txt_tel.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }else if(txt_dni.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese DNI del cliente" ,"Registrar Cliente");
                    txt_dni.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }else if(txt_cor.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese correo del cliente" ,"Registrar Cliente");
                    txt_cor.requestFocus();
                    raiz.findViewById(R.id.frmCliente);
                }else{

                    if(chkEstCli.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }

                    cliente.setEstado(est);
                    cliente.setNombre(txt_nom.getText().toString());
                    cliente.setApellidos(txt_ape.getText().toString());
                    cliente.setDni(txt_dni.getText().toString());
                    cliente.setTelefono(txt_tel.getText().toString());
                    cliente.setCelular(txt_cel.getText().toString());
                    cliente.setCorreo(txt_cor.getText().toString());
                    cliente.setDireccion(txt_dir.getText().toString());
                    cliente.setfNacieminto(txt_fnac.getText().toString());

                    res = clienteDAO.RegistrarCliente(cliente, raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se registro el cliente correctamente","Registrar Cliente");
                        CargarFragmento();

                        lstCliente.setAdapter(adaptadorCliente);

                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCliente));
                        txt_nom.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se registro el cliente correctamente","Registrar Cliente");
                        CargarFragmento();
                    }
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fila>=0){
                    if(chkEstCli.isChecked()){
                        est=1;
                    }else{
                        est=0;
                    }
                    int cod = Integer.parseInt(lblCodCli.getText().toString());
                    cliente.setCodigo(cod);
                    cliente.setEstado(est);
                    cliente.setNombre(txt_nom.getText().toString());
                    cliente.setApellidos(txt_ape.getText().toString());
                    cliente.setDni(txt_dni.getText().toString());
                    cliente.setTelefono(txt_tel.getText().toString());
                    cliente.setCelular(txt_cel.getText().toString());
                    cliente.setCorreo(txt_cor.getText().toString());
                    cliente.setDireccion(txt_dir.getText().toString());
                    cliente.setfNacieminto(txt_fnac.getText().toString());

                    res = clienteDAO.ActualizarCliente(cliente, raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se actualizo el cliente correctamente","Actualizar Cliente");
                        CargarFragmento();

                        lstCliente.setAdapter(adaptadorCliente);

                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCliente));
                        txt_nom.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el cliente correctamente","Actualizar Cliente");
                        CargarFragmento();
                    }
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fila>=0){
                    cod=Integer.parseInt(lblCodCli.getText().toString());
                    cliente.setCodigo(cod);

                    res=clienteDAO.EliminarCliente(cliente, raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se elimino el empleado correctamente","Eliminar empleado");
                        CargarFragmento();
                        lstCliente.setAdapter(adaptadorCliente);

                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmCliente));
                        txt_nom.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el cliente correctamente","Eliminar cliente");
                        CargarFragmento();
                    }
                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista","Eliminar cliente");
                    CargarFragmento();
                }
            }
        });
        return raiz;

    }

    public int getItemDistrito(AdaptadorComboDistrito adaptador,Distrito obj) {
        int posicion = 0;
        for(int i=0; i < adaptador.getCount();i++){
            Distrito objIns = (Distrito)adaptador.getItem(i);
            if(objIns.getNombre().equals(obj.getNombre())){
                posicion = i;
                break;
            }
        }
        return posicion;
    }


    public void CargarFragmento(){
        ActividadCliente fcliente= new ActividadCliente();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor,fcliente, ActividadPerfil.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }


}