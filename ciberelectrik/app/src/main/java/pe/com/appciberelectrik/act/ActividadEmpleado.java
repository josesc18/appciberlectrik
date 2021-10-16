package pe.com.appciberelectrik.act;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.*;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.adaptadores.AdaptadorComboDistrito;
import pe.com.appciberelectrik.adaptadores.AdaptadorComboPerfil;
import pe.com.appciberelectrik.adaptadores.AdaptadorDistrito;
import pe.com.appciberelectrik.adaptadores.AdaptadorEmpleado;
import pe.com.appciberelectrik.bean.Distrito;
import pe.com.appciberelectrik.bean.Empleado;
import pe.com.appciberelectrik.bean.Perfil;
import pe.com.appciberelectrik.dao.DistritoDAO;
import pe.com.appciberelectrik.dao.EmpleadoDAO;
import pe.com.appciberelectrik.dao.PerfilDAO;
import pe.com.appciberelectrik.imp.DistritoImp;
import pe.com.appciberelectrik.imp.EmpleadoImp;
import pe.com.appciberelectrik.imp.PerfilImp;
import pe.com.appciberelectrik.util.General;

public class ActividadEmpleado extends Fragment {
    //creamos una variable estatica
    public static final String TAG = "Fragmento Empleado";
    //creando controles
    private Spinner cboPerfil, cboDistrito;
    private EditText txtNom, txtApep, txtApem, txtDir, txtDni,
            txtTel, txtCel, txtCor, txtUsu, txtCla;
    private TextView lblCodDis, lblCodPer, lblCodEmp;
    private RadioButton rbMas, rbFem, RbOtr;
    private RadioGroup rbgSex;
    private CheckBox chkEst;
    private ListView lstEmpleado;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    public String genero = "";
    private int cod=0, fila=-1, est=0;
    private boolean res=false;

    General objgeneral= new General();

    AdaptadorComboPerfil adaptadorComboPerfil;
    ArrayList<Perfil> registroperfil;
    PerfilDAO daoperfil= new PerfilImp();
    AdaptadorComboDistrito adaptadorComboDistrito;
    ArrayList<Distrito> registrodistrito;
    DistritoDAO daodistrito= new DistritoImp();

    Empleado empleado =  new Empleado();
    ArrayList<Empleado> registroEmpelado = null;
    AdaptadorEmpleado adaptadorEmpleado;
    FragmentTransaction ft;

    EmpleadoDAO empleadoDao = new EmpleadoImp();

    public ActividadEmpleado() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View raiz=inflater.inflate(R.layout.actividad_empleado, container, false);
        lstEmpleado = raiz.findViewById(R.id.lstEmpleado);
        registroEmpelado = new ArrayList<>();
        registroEmpelado = new EmpleadoImp().MostrarEmpleado(raiz.getContext());
        if(registroEmpelado != null){
            adaptadorEmpleado = new AdaptadorEmpleado(raiz.getContext(),registroEmpelado);
            lstEmpleado.setAdapter(adaptadorEmpleado);
        }


        cboPerfil=raiz.findViewById(R.id.cboPerfil);
        cboDistrito=raiz.findViewById(R.id.cboDistrito);
        lblCodPer=raiz.findViewById(R.id.lblCodPer);
        lblCodDis = raiz.findViewById(R.id.lblCoddis);
        lblCodEmp= raiz.findViewById(R.id.lblCodEmp);
        registroperfil=new ArrayList<>();
        registrodistrito=new ArrayList<>();

        registroperfil=daoperfil.MostrarPerfil(raiz.getContext());
        registrodistrito=daodistrito.MostrarDistrito(raiz.getContext());

        if(registroperfil!=null){

            adaptadorComboPerfil=new AdaptadorComboPerfil(raiz.getContext(), registroperfil);

            cboPerfil.setAdapter(adaptadorComboPerfil);
        }
        if(registrodistrito!=null){
            adaptadorComboDistrito=new AdaptadorComboDistrito(raiz.getContext(),registrodistrito);
            cboDistrito.setAdapter(adaptadorComboDistrito);
        }

        cboPerfil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Perfil perfil=registroperfil.get(i);
                lblCodPer.setText(""+perfil.getCodigo());
                empleado.setPerfil(perfil);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cboDistrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Distrito distrito = registrodistrito.get(i);
                lblCodDis.setText(""+distrito.getCodigo());
                empleado.setDistrito(distrito);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtNom = raiz.findViewById(R.id.txtNom);
        txtApep = raiz.findViewById(R.id.txtApep);
        txtApem = raiz.findViewById(R.id.txtApem);
        txtDir =raiz.findViewById(R.id.txtDir);
        txtDni = raiz.findViewById(R.id.txtDni);
        txtTel = raiz.findViewById(R.id.txtTel);
        txtCel = raiz.findViewById(R.id.txtCel);
        txtCor = raiz.findViewById(R.id.txtCor);
        txtUsu = raiz.findViewById(R.id.txtUsu);
        txtCla = raiz.findViewById(R.id.txtCla);

        chkEst = raiz.findViewById(R.id.chkEst);

        rbgSex = raiz.findViewById(R.id.rg_sexo);
        rbMas = raiz.findViewById(R.id.rbMas);
        rbFem = raiz.findViewById(R.id.rbFem);
        RbOtr = raiz.findViewById(R.id.rbOtr);




        rbgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rbMas){
                    genero =rbMas.getText().toString();
                }
                else if (checkedId == R.id.rbFem){
                    genero =rbFem.getText().toString();
                }
                else if (checkedId == R.id.rbOtr){
                    genero =RbOtr.getText().toString();
                }
            }
        });

        lstEmpleado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fila=position;
                txtNom.setText(registroEmpelado.get(fila).getNombre());
                txtApep.setText(registroEmpelado.get(fila).getApellidop());
                txtApem.setText(registroEmpelado.get(fila).getApellidom());
                txtDni.setText(registroEmpelado.get(fila).getDni());
                txtTel.setText(registroEmpelado.get(fila).getTelefono());
                txtCel.setText(registroEmpelado.get(fila).getCelular());
                txtCor.setText(registroEmpelado.get(fila).getCorreo());
                txtUsu.setText(registroEmpelado.get(fila).getUsuario());
                txtCla.setText(registroEmpelado.get(fila).getClave());
                txtDir.setText(registroEmpelado.get(fila).getDireccion());
                lblCodEmp.setText(""+registroEmpelado.get(fila).getCodigo());
                if(registroEmpelado.get(fila).getEstado() == 1){
                    chkEst.setChecked(true);
                }else{
                    chkEst.setChecked(false);
                }

                if(registroEmpelado.get(fila).getSexo().equals("Masculino")){
                    rbMas.setChecked(true);
                }else if(registroEmpelado.get(fila).getSexo().equals("Femenino")){
                    rbFem.setChecked(true);
                }else if(registroEmpelado.get(fila).getSexo().equals("Otro")) {
                    RbOtr.setChecked(true);
                }

                cboDistrito.setSelection(
                        getItemDistrito(adaptadorComboDistrito,registroEmpelado.get(fila).getDistrito())
                );

                cboPerfil.setSelection(
                        getItemPerfil(adaptadorComboPerfil,registroEmpelado.get(fila).getPerfil())
                );
            }
        });

        btnRegistrar=raiz.findViewById(R.id.btnRegistrar);
        btnActualizar=raiz.findViewById(R.id.btnActualizar);
        btnEliminar=raiz.findViewById(R.id.btnEliminar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNom.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre del empleado" ,"Registro Empleado");
                    txtNom.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }
                if(txtApem.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el Apellido Materno del empleado" ,"Registro Empleado");
                    txtApem.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }
                if(txtApep.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el Apellido Paterno del empleado" ,"Registro Empleado");
                    txtApep.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }if(txtDir.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese la direccion del empleado" ,"Registro Empleado");
                    txtDir.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }if(txtDni.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el DNI del empleado" ,"Registro Empleado");
                    txtDni.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }if(txtCel.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el Celular del empleado" ,"Registro Empleado");
                    txtCel.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }if(txtTel.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el Telefono del empleado" ,"Registro Empleado");
                    txtTel.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }if(txtUsu.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el Usuario del empleado" ,"Registro Empleado");
                    txtUsu.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }if(txtCla.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese la clave del empleado" ,"Registro Empleado");
                    txtCla.requestFocus();
                    raiz.findViewById(R.id.frmEmpleado);
                }else{
                    est = 0;
                    if(chkEst.isChecked()){
                        est = 1;
                    }

                    empleado.setNombre(txtNom.getText().toString());
                    empleado.setApellidom(txtApem.getText().toString());
                    empleado.setApellidop(txtApep.getText().toString());

                    empleado.setDireccion(txtDir.getText().toString());

                    empleado.setDni(txtDni.getText().toString());
                    empleado.setSexo(genero);
                    empleado.setTelefono(txtTel.getText().toString());
                    empleado.setCelular(txtCel.getText().toString());
                    empleado.setCorreo(txtCor.getText().toString());
                    empleado.setUsuario(txtUsu.getText().toString());
                    empleado.setClave(txtCla.getText().toString());
                    empleado.setEstado(est);

                    res = empleadoDao.RegistrarEmpleado(empleado,raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se registro el empleado correctamente","Registrar Empleado");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstEmpleado.setAdapter(adaptadorEmpleado);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmEmpleado));
                        txtNom.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se registro el empleado correctamente","Registrar Empleado");
                        CargarFragmento();
                    }

                }

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fila>=0){
                    cod = Integer.parseInt(lblCodEmp.getText().toString());
                    empleado.setCodigo(cod);
                    empleado.setDni(txtDni.getText().toString());
                    empleado.setNombre(txtNom.getText().toString());
                    empleado.setApellidop(txtApep.getText().toString());
                    empleado.setApellidom(txtApem.getText().toString());
                    empleado.setDireccion(txtDir.getText().toString());
                    empleado.setCelular(txtCel.getText().toString());
                    empleado.setTelefono(txtTel.getText().toString());
                    empleado.setCorreo(txtCor.getText().toString());
                    empleado.setUsuario(txtUsu.getText().toString());
                    empleado.setClave(txtCla.getText().toString());

                    if(chkEst.isChecked()){
                        est = 1;
                    }else{
                        est = 0;
                    }

                    if(rbMas.isChecked()){
                        empleado.setSexo(rbMas.getText().toString());
                    }
                    if(rbFem.isChecked()){
                        empleado.setSexo(rbFem.getText().toString());
                    }
                    if(RbOtr.isChecked()){
                        empleado.setSexo(RbOtr.getText().toString());
                    }
                    empleado.setEstado(est);

                    res = empleadoDao.ActualizarEmpleado(empleado, raiz.getContext());

                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se actualizo el empleado correctamente","Actualizar Empleado");
                        CargarFragmento();
                        lstEmpleado.setAdapter(adaptadorEmpleado);
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmEmpleado));
                        txtNom.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el empleado correctamente","Actualziar Empleado");
                        CargarFragmento();
                    }

                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista","Actualizar Empleado");
                    CargarFragmento();
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validamos que se seleccione un elemento de la lista
                if(fila>=0){
                    cod=Integer.parseInt(lblCodEmp.getText().toString());
                    //enviamos el codigo
                    empleado.setCodigo(cod);

                    //eliminamos
                    res=empleadoDao.EliminarEmpleado(empleado, raiz.getContext());
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(), "Se elimino el empleado correctamente","Eliminar empleado");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstEmpleado.setAdapter(adaptadorEmpleado);
                        //Limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmEmpleado));
                        txtNom.requestFocus();
                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el empleado correctamente","Eliminar empleado");
                        CargarFragmento();
                    }
                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista","Eliminar empleado");
                    CargarFragmento();
                }

            }
        });


        return raiz;

    }
    public void CargarFragmento(){
        ActividadEmpleado fempleado= new ActividadEmpleado();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor,fempleado, ActividadPerfil.TAG);
        ft.addToBackStack(null);
        ft.commit();
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

    public int getItemPerfil(AdaptadorComboPerfil adaptador, Perfil perfil){
        int posicion = 0;
        for(int i=0; i < adaptador.getCount();i++){
            Perfil objIns = (Perfil)adaptador.getItem(i);
            if(objIns.getNombre().equals(perfil.getNombre())){
                posicion = i;
                break;
            }
        }
        return posicion;
    }


}