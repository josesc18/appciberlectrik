package pe.com.appciberelectrik.dao;

import android.content.Context;

import java.util.ArrayList;

import pe.com.appciberelectrik.bean.Empleado;

public interface EmpleadoDAO {
    public ArrayList<Empleado> MostrarEmpleado(Context contexto);
    public boolean RegistrarEmpleado(Empleado e, Context contexto);
    public boolean ActualizarEmpleado(Empleado e,Context contexto);
    public boolean EliminarEmpleado(Empleado e,Context contexto);
}
