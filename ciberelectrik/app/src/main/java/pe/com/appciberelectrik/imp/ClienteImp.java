package pe.com.appciberelectrik.imp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pe.com.appciberelectrik.bd.Conexion;
import pe.com.appciberelectrik.bean.Cliente;
import pe.com.appciberelectrik.bean.Distrito;
import pe.com.appciberelectrik.dao.ClienteDAO;

public class ClienteImp implements ClienteDAO {

    private Conexion objconexion;
    private ArrayList<Cliente> registroCliente = null;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues valores;
    private SQLiteOpenHelper xcon;
    private int version = 1;
    private String nombre="bdciberelectrik";

    public ClienteImp(){
        this.registroCliente = new ArrayList<>();
    }


    @Override
    public ArrayList<Cliente> MostrarClientes(Context contexto) {
        try {
            objconexion = new Conexion(contexto, nombre, null, version);
            db = objconexion.getWritableDatabase();

            cursor = db.rawQuery("select " +
                    "c.codcli," +
                    "c.nomcli," +
                    "c.apecli," +
                    "c.dnicli," +
                    "c.telcli," +
                    "c.celcli," +
                    "c.dircli," +
                    "c.corcli," +
                    "d.nomdis," +
                    "c.estcli," +
                    "c.fnacli from t_cliente c " +
                    "inner join t_distrito d " +
                    "on c.coddis = d.coddis", null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Cliente c = new Cliente();
                    Distrito d = new Distrito();
                    c.setCodigo(cursor.getInt(0));
                    c.setNombre(cursor.getString(1));
                    c.setApellidos(cursor.getString(2));
                    c.setDni(cursor.getString(3));
                    c.setTelefono(cursor.getString(4));
                    c.setCelular(cursor.getString(5));
                    c.setDireccion(cursor.getString(6));
                    c.setCorreo(cursor.getString(7));
                    d.setNombre(cursor.getString(8));
                    c.setDistrito(d);
                    c.setEstado(cursor.getInt(9));
                    c.setfNacieminto(cursor.getString(10));
                    registroCliente.add(c);
                }
            } else {
                registroCliente = null;
            }
            return  registroCliente;
        }catch (Exception ex){
            return null;
        }

    }

    @Override
    public boolean RegistrarCliente(Cliente c, Context contexto) {

        objconexion = new Conexion(contexto, nombre, null, version);
        db = objconexion.getWritableDatabase();

        try{
            valores=new ContentValues();
            valores.put("nomcli",c.getNombre());
            valores.put("apecli",c.getApellidos());
            valores.put("dnicli",c.getDni());
            valores.put("telcli",c.getTelefono());
            valores.put("celcli",c.getCelular());
            valores.put("dircli",c.getDireccion());
            valores.put("corcli",c.getCorreo());
            valores.put("coddis",c.getDistrito().getCodigo());
            valores.put("fnacli",""+c.getfNacieminto());
            valores.put("estcli",c.getEstado());

            long res=db.insert("t_cliente",null,valores);
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
    public boolean ActualizarCliente(Cliente c, Context contexto) {
        objconexion = new Conexion(contexto, nombre, null, version);
        db = objconexion.getWritableDatabase();

        try{
            valores=new ContentValues();
            valores.put("nomcli",c.getNombre());
            valores.put("apecli",c.getApellidos());
            valores.put("dnicli",c.getDni());
            valores.put("telcli",c.getTelefono());
            valores.put("celcli",c.getCelular());
            valores.put("dircli",c.getDireccion());
            valores.put("corcli",c.getCorreo());
            valores.put("coddis",c.getDistrito().getCodigo());
            valores.put("fnacli",""+c.getfNacieminto());
            valores.put("estcli",c.getEstado());

            int res=db.update(
                    "t_cliente",valores,
                    "codcli="+c.getCodigo(),
                    null);
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
    public boolean EliminarCliente(Cliente c, Context contexto) {

        objconexion=new Conexion(contexto,nombre,null,version);
        db=objconexion.getWritableDatabase();
        try{
            valores=new ContentValues();
            valores.put("estcli",0);
            long res=db.update("t_cliente",valores,"codcli="+c.getCodigo(),null);

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
