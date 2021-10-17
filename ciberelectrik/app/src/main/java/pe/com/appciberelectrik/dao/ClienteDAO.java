package pe.com.appciberelectrik.dao;

import android.content.Context;

import java.text.ParseException;
import java.util.ArrayList;

import pe.com.appciberelectrik.bean.Cliente;
import pe.com.appciberelectrik.bean.Distrito;

public interface ClienteDAO {
    public ArrayList<Cliente> MostrarClientes(Context contexto) ;
    public boolean RegistrarCliente(Cliente c, Context contexto);
    public boolean ActualizarCliente(Cliente c,Context contexto);
    public boolean EliminarCliente(Cliente c,Context contexto);
}
