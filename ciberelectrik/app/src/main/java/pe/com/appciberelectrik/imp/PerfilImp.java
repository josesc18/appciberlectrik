package pe.com.appciberelectrik.imp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import java.util.ArrayList;

import pe.com.appciberelectrik.bd.Conexion;
import pe.com.appciberelectrik.bean.Perfil;
import pe.com.appciberelectrik.dao.PerfilDAO;

public class PerfilImp implements PerfilDAO {
    //creamos un objeto de la clase Conexion
    private Conexion objconexion;
    //generar un ArrayList de tipo Perfil para los datos
    private ArrayList<Perfil> registroperfil = null;
    //creamos una variable para trabajar con la base de datos
    private SQLiteDatabase db;
    //creamos un cursor --> ResultSet
    private Cursor cursor;
    //declarar una variable para edl manejor de los valores
    private ContentValues valores;
    //creamos una variable para la conexion
    private SQLiteOpenHelper xcon;
    //creamos variables para la creacion de la base de datos
    //la version de la base de datos
    private int version = 1;
    //el nombre de la base de datos
    private String nombre="bdciberelectrik";

    public PerfilImp() {
        //creamos el ArrayList
        registroperfil=new ArrayList<>();
    }

    @Override
    public ArrayList<Perfil> MostrarPerfil(Context contexto) {
        try {
            //creamos la conexion
            objconexion=new Conexion(contexto,nombre,null,version);
            //creamos un cursor  y realizamos la consulta a la base de datos
            db=objconexion.getWritableDatabase();
            cursor=db.rawQuery("select * from t_perfil where estper=1",null);
            //evaluamos que al menos exista un dato en el cursor
            if(cursor.getCount()>0){
                //llenamos el cursor
                while(cursor.moveToNext()){
                    //creamos un objeto de la clase Perfil
                    Perfil objperfil=new Perfil();
                    //le asignamos los valores al objeto
                    objperfil.setCodigo(cursor.getInt(0));
                    objperfil.setNombre(cursor.getString(1));
                    objperfil.setEstado(cursor.getInt(2));
                    //enviamos los datos al arreglo
                    registroperfil.add(objperfil);
                }
            }else{
                registroperfil=null;
            }
            return registroperfil;
        }catch (Exception ex){
            Log.e("Error:",ex.toString());
            return null;
        }
    }

    @Override
    public boolean RegistrarPerfil(Perfil p, Context contexto) {
        //creamos la conexion
        objconexion=new Conexion(contexto,nombre,null,version);
        //asignamos el valor para que la base de datos se pueda escribir
        db=objconexion.getWritableDatabase();
        try{
            //creamos el ContentValues
            valores=new ContentValues();
            //asignamos los valores
            valores.put("nomper",p.getNombre());
            valores.put("estper",p.getEstado());
            //ejecutamos la insercion
            long res=db.insert("t_perfil",null,valores);
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
    public boolean ActualizarPerfil(Perfil p) {
        try{
            //creamos los valores
            valores=new ContentValues();
            //asignamos los valores
            valores.put("nomper",p.getNombre());
            valores.put("estper",p.getEstado());
            //ejecutamos la actualizacion
            int res=db.update("t_perfil",valores,"codper="+p.getCodigo(),null);
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

    @Override
    public boolean EliminarPerfil(Perfil p) {
        try{
            //Eliminar clasico --> Eliminar fisico
            // int res=db.delete("t_perfil","codper="+p.getCodigo(),null);
            //creamos los valores
            valores=new ContentValues();
            //asignamos los valores
            valores.put("estper",0);
            int res=db.update("t_perfil",valores,"codper="+p.getCodigo(),null);
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
