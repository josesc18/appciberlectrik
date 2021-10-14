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
import pe.com.appciberelectrik.dao.DistritoDAO;

public class DistritoImp implements DistritoDAO {

    private Conexion objconexion;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues valores;
    private SQLiteOpenHelper xcon;
    private int version = 1;
    private String nombre="bdciberelectrik";

    private ArrayList<Distrito> registroDistrito = null;

    public DistritoImp() {
        this.registroDistrito = new ArrayList<>();
    }

    @Override
    public ArrayList<Distrito> MostrarDistrito(Context contexto) {
        try{
            objconexion=new Conexion(contexto,nombre,null,version);
            db=objconexion.getWritableDatabase();
            cursor=db.rawQuery("SELECT coddis,nomdis,estdis FROM t_distrito where estdis=1",null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()) {
                    Distrito objdistrito = new Distrito();

                    objdistrito.setCodigo(cursor.getInt(0));
                    objdistrito.setNombre(cursor.getString(1));
                    objdistrito.setEstado(cursor.getInt(2));

                    registroDistrito.add(objdistrito);
                }
            }else{
                return null;
            }
            return registroDistrito;
        }

        catch (Exception ex){
            Log.e("Error:",ex.toString());
            return null;
        }
    }

    @Override
    public boolean RegistrarDistrito(Distrito d, Context contexto) {

        objconexion=new Conexion(contexto,nombre,null,version);
        db=objconexion.getWritableDatabase();

        try{
            valores=new ContentValues();

            valores.put("nomdis",d.getNombre());
            valores.put("estdis",d.getEstado());
            long res=db.insert("t_distrito",null,valores);
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
    public boolean ActualizarDistrito(Distrito d,Context contexto) {

        objconexion=new Conexion(contexto,nombre,null,version);
        //asignamos el valor para que la base de datos se pueda escribir
        db=objconexion.getWritableDatabase();
        try{
            //creamos el ContentValues
            valores=new ContentValues();
            //asignamos los valores
            valores.put("nomdis",d.getNombre());
            valores.put("estdis",d.getEstado());
            //ejecutamos la insercion
            long res=db.insert("t_distrito",null,valores);
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
    public boolean EliminarDistrito(Distrito d) {
        try{
            valores=new ContentValues();
            valores.put("estdis",0);
            int res=db.update("t_distrito",valores,"coddis="+d.getCodigo(),null);
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
