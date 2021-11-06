package pe.com.appciberelectrik.dao;

import android.content.Context;

import java.util.ArrayList;

import pe.com.appciberelectrik.bean.Categoria;

public interface CategoriaDAO {
    public ArrayList<Categoria> MostrarCategoria(Context contexto);
    public boolean RegistrarCategroia(Categoria c, Context contexto);
    public boolean ActualizarCategoria(Categoria c,Context contexto);
    public boolean EliminarCategoria(Categoria c,Context contexto);
}
