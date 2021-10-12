package pe.com.appciberelectrik.act;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.util.General;

public class ActividadInicio extends AppCompatActivity {
    //creamos un objeto de la clase General
    General objgeneral=new General();
    //declarando los controles
    private EditText txtUsu,txtCla;
    private Button btnIngresar, btnSalir;
    //declaramos una viable para el contexto
    private Context contexto;
    //declarando variables
    private AlertDialog.Builder dialogo;
    //declaramos el Intent
    private Intent formulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_inicio);
        //definimos el contexto
        contexto=this;
        //creamos los controles
        txtUsu=findViewById(R.id.txtUsu);
        txtCla=findViewById(R.id.txtCla);
        btnIngresar=findViewById(R.id.btnIngresar);
        btnSalir=findViewById(R.id.btnSalir);
        //agregamos los eventos
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mostramos un mensaje
                MensajeIngreso(contexto);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objgeneral.SalirSistema(contexto);
            }
        });
    }

    //creamos un procedimiento para quenos muestra mensaje al ingreso de sistema
    public void MensajeIngreso(final Context contexto){
        //creando el cuadro de dialogo
        dialogo=new AlertDialog.Builder(contexto);
        //agregando un titulo al cuadro de dialogo
        dialogo.setTitle("Ingresando al Sistema");
        //agregamos el mensaje que se mostrar en el cuadro de dialogo
        dialogo.setMessage("Bienvenidos al Sistema");
        //deshabilitamos la funcion Cancelar
        dialogo.setCancelable(false);
        //programamos el boton si
        dialogo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //creamos el Intent
                formulario=new Intent(contexto, ActividadMenu.class);
                startActivity(formulario);
                finish();
            }
        });
        //mostramos los cuadro de dialogo
        dialogo.show();
    }
}