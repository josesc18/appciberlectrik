package pe.com.appciberelectrik.dao;

import android.content.Context;

import java.util.ArrayList;

import pe.com.appciberelectrik.bean.Perfil;

public interface PerfilDAO {
    public ArrayList<Perfil> MostrarPerfil(Context contexto);
    public boolean RegistrarPerfil(Perfil p, Context contexto);
    public boolean ActualizarPerfil(Perfil p);
    public boolean EliminarPerfil(Perfil p);
}
