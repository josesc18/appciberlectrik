package pe.com.appciberelectrik.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.view.*;
import android.widget.*;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.act.ActividadPerfil;

public class General {
    //variable para la transicion del fragmento
    FragmentTransaction ft;
    //declarando variables
    private AlertDialog.Builder dialogo;
    //creamos un procedimiento  que nos permita mostrar un cuadro de dialogo
    //cada vez que vamos a salir del sistema
    public void SalirSistema(final Context contexto){
        //creando el cuadro de dialogo
        dialogo=new AlertDialog.Builder(contexto);
        //agregando un titulo al cuadro de dialogo
        dialogo.setTitle("Saliendo del Sistema");
        //agregamos el mensaje que se mostrar en el cuadro de dialogo
        dialogo.setMessage("¿Estas seguro que quieres salir del sistema?");
        //deshabilitamos la funcion
        dialogo.setCancelable(false);
        //programamos el boton si
        dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((Activity)contexto).finish();
            }
        });
        //programando el boton no
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //mostramos los cuadro de dialogo
        dialogo.show();
    }

    //creamos un procedimiento  que nos permita mostrar un cuadro de dialogo
    //cada vez que vamos a salir del sistema
    public void Mensaje(final Context contexto,String mensaje,String titulo){
        //creando el cuadro de dialogo
        dialogo=new AlertDialog.Builder(contexto);
        //agregando un titulo al cuadro de dialogo
        dialogo.setTitle(titulo);
        //agregamos el mensaje que se mostrar en el cuadro de dialogo
        dialogo.setMessage(mensaje);
        //deshabilitamos la funcion
        dialogo.setCancelable(false);
        //programamos el boton si
        dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //mostramos los cuadro de dialogo
        dialogo.show();
    }
    public void Limpiar(ViewGroup group){
        for (int i=0, count=group.getChildCount();i<count;i++){
            View view = group.getChildAt(i);
            if(view instanceof EditText){
                ((EditText)view).setText("");
            }
            if(view instanceof RadioGroup){
                ((RadioGroup)view).clearCheck();
            }
            if (view instanceof CheckBox){
                ((CheckBox)view).setChecked(false);
            }
            if(view instanceof Spinner){
                ((Spinner)view).setSelection(0);
            }
            if (view instanceof  ViewGroup && ((ViewGroup)view).getChildCount()>0 ){
                Limpiar(((ViewGroup) view));
            }
        }
    }



}
