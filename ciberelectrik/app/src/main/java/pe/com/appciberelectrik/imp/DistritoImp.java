package pe.com.appciberelectrik.imp;

import android.content.Context;

import java.util.ArrayList;

import pe.com.appciberelectrik.bean.Distrito;
import pe.com.appciberelectrik.dao.DistritoDAO;

public class DistritoImp implements DistritoDAO {
    @Override
    public ArrayList<Distrito> MostrarDistrito(Context contexto) {
        return null;
    }

    @Override
    public boolean RegistrarDistrito(Distrito d, Context contexto) {
        return false;
    }

    @Override
    public boolean ActualizarDistrito(Distrito d) {
        return false;
    }

    @Override
    public boolean EliminarDistrito(Distrito d) {
        return false;
    }
}
