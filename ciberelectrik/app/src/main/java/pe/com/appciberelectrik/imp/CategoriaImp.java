package pe.com.appciberelectrik.imp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import pe.com.appciberelectrik.bd.Conexion;
import pe.com.appciberelectrik.bean.Categoria;
import pe.com.appciberelectrik.dao.CategoriaDAO;

public class CategoriaImp implements CategoriaDAO {

    private Conexion objconexion;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues valores;
    private SQLiteOpenHelper xcon;
    private int version = 1;
    private String nombre="bdciberelectrik";
    private ArrayList<Categoria> registroCategoria = null;

    public CategoriaImp(){this.registroCategoria = new ArrayList<>();}

    @Override
    public ArrayList<Categoria> MostrarCategoria(Context contexto) {


        try{
            objconexion = new Conexion(contexto, nombre, null, version);
            db = objconexion.getWritableDatabase();
            cursor=db.rawQuery("SELECT * FROM t_categoria where estcat=1",null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()) {
                    Categoria cat = new Categoria();

                    cat.setId(cursor.getInt(0));
                    cat.setName(cursor.getString(1));
                    cat.setEstado(cursor.getInt(2));

                    registroCategoria.add(cat);
                }
            }else{
                return null;
            }
            return registroCategoria;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean RegistrarCategroia(Categoria c, Context contexto) {
        objconexion=new Conexion(contexto,nombre,null,version);
        db=objconexion.getWritableDatabase();

        try{
            valores=new ContentValues();

            valores.put("nomcat",c.getName());
            valores.put("estcat",c.getEstado());
            long res=db.insert("t_categoria",null,valores);
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
    public boolean ActualizarCategoria(Categoria c, Context contexto) {
        objconexion=new Conexion(contexto,nombre,null,version);
        db=objconexion.getWritableDatabase();
        try{
            valores=new ContentValues();
            valores.put("nomcat",c.getName());
            valores.put("estcat",c.getEstado());

            long res=db.insert("t_categoria",null,valores);
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
    public boolean EliminarCategoria(Categoria c, Context contexto) {
        try{
            valores=new ContentValues();
            valores.put("estcat",0);
            int res=db.update("t_categoria",valores,"codcat="+c.getId(),null);
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
