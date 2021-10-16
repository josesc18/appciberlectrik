package pe.com.appciberelectrik.imp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import pe.com.appciberelectrik.bd.Conexion;
import pe.com.appciberelectrik.bean.Distrito;
import pe.com.appciberelectrik.bean.Empleado;
import pe.com.appciberelectrik.bean.Perfil;
import pe.com.appciberelectrik.dao.EmpleadoDAO;

public class EmpleadoImp implements EmpleadoDAO {
    private Conexion objconexion;
    private ArrayList<Empleado> registroempleado = null;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues valores;
    private SQLiteOpenHelper xcon;
    private int version = 1;
    private String nombre="bdciberelectrik";

    public EmpleadoImp() {
        registroempleado=new ArrayList<>();
    }

    @Override
    public ArrayList<Empleado> MostrarEmpleado(Context contexto) {
        try {
            objconexion=new Conexion(contexto,nombre,null,version);
            db=objconexion.getWritableDatabase();
            cursor=db.rawQuery(
                    "select e.codemp, " +
                            "e.nomemp, " +
                            "e.apepemp, " +
                            "e.apememp," +
                            "e.dniemp, " +
                            "e.diremp,"+
                            "d.nomdis, " +
                            "e.telemp, " +
                            "e.celemp," +
                            "e.coremp," +
                            "e.usuemp, " +
                            "e.claemp," +
                            "p.nomper," +
                            "e.sexemp,"+
                            "e.estemp " +
                            "from t_empleado e inner join t_distrito d " +
                            "on e.coddis=d.coddis inner join t_perfil p " +
                            "on e.codper=p.codper",null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    Empleado objempleado= new Empleado();
                    Distrito objdistrito= new Distrito();
                    Perfil objperfil=new Perfil();
                    //le asignamos los valores al objeto
                    objempleado.setCodigo(cursor.getInt(0));
                    objempleado.setNombre(cursor.getString(1));
                    objempleado.setApellidop(cursor.getString(2));
                    objempleado.setApellidom(cursor.getString(3));
                    objempleado.setDni(cursor.getString(4));
                    objempleado.setDireccion(cursor.getString(5));
                    //enviando el distrito
                    objdistrito.setNombre(cursor.getString(6));
                    objempleado.setDistrito(objdistrito);
                    objempleado.setTelefono(cursor.getString(7));
                    objempleado.setCelular(cursor.getString(8));
                    objempleado.setCorreo(cursor.getString(9));
                    objempleado.setUsuario(cursor.getString(10));
                    objempleado.setClave(cursor.getString(11));
                    //enviando el perfil
                    objperfil.setNombre(cursor.getString(12));
                    objempleado.setPerfil(objperfil);
                    objempleado.setSexo(cursor.getString(13));
                    objempleado.setEstado(cursor.getInt(14));
                    //enviamos los datos al arreglo
                    registroempleado.add(objempleado);
                }
            }else{
                registroempleado=null;
            }
            return registroempleado;
        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return null;
        }
    }



    @Override
    public boolean RegistrarEmpleado(Empleado e, Context contexto) {

        objconexion=new Conexion(contexto,nombre,null,version);
        db=objconexion.getWritableDatabase();
        try{

            valores=new ContentValues();

            valores.put("nomemp",e.getNombre());
            valores.put("apepemp",e.getApellidop());
            valores.put("apememp",e.getApellidom());
            valores.put("dniemp",e.getDni());
            valores.put("coddis",e.getDistrito().getCodigo());
            valores.put("telemp",e.getTelefono());
            valores.put("diremp",e.getDireccion());
            valores.put("celemp",e.getCelular());
            valores.put("coremp",e.getCorreo());
            valores.put("usuemp",e.getUsuario());
            valores.put("claemp",e.getClave());
            valores.put("codper",e.getPerfil().getCodigo());
            valores.put("estemp",e.getEstado());
            valores.put("sexemp",e.getSexo());
            //ejecutamos la insercion
            long res=db.insert("t_empleado",null,valores);
            if(res>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            Log.e("Error",ex.toString());
            return false;
        }
    }

    @Override
    public boolean ActualizarEmpleado(Empleado e,Context contexto) {
        objconexion=new Conexion(contexto,nombre,null,version);
        db=objconexion.getWritableDatabase();
        try{
            valores=new ContentValues();

            valores.put("nomemp",e.getNombre());
            valores.put("apepemp",e.getApellidop());
            valores.put("apememp",e.getApellidom());
            valores.put("dniemp",e.getDni());
            valores.put("coddis",e.getDistrito().getCodigo());
            valores.put("telemp",e.getTelefono());
            valores.put("diremp",e.getDireccion());
            valores.put("celemp",e.getCelular());
            valores.put("coremp",e.getCorreo());
            valores.put("usuemp",e.getUsuario());
            valores.put("claemp",e.getClave());
            valores.put("codper",e.getPerfil().getCodigo());
            valores.put("estemp",e.getEstado());
            valores.put("sexemp",e.getSexo());

            int res=db.update(
                    "t_empleado",valores,
                    "codemp="+e.getCodigo(),
                    null);
            if(res == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            Log.e("Error Actualizar:",ex.toString());
            return false;
        }


    }

    @Override
    public boolean EliminarEmpleado(Empleado e,Context contexto) {
        objconexion=new Conexion(contexto,nombre,null,version);
        db=objconexion.getWritableDatabase();
        try{
            valores=new ContentValues();
            valores.put("estemp",0);
            long res=db.update("t_empleado",valores,"codemp="+e.getCodigo(),null);

            if(res==1){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return false;
        }
    }
}
