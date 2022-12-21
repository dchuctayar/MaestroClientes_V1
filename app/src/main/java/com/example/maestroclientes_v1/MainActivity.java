package com.example.maestroclientes_v1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maestroclientes_v1.dialog.DialogDescriptionCliente;
import com.example.maestroclientes_v1.dialog.DialogoSalir;
import com.example.maestroclientes_v1.fragments.FragmentClientes;
import com.example.maestroclientes_v1.fragments.FragmentTipoClientes;
import com.example.maestroclientes_v1.fragments.FragmentZona;

public class MainActivity extends AppCompatActivity {

    FragmentClientes fragmentClientes = new FragmentClientes();
    FragmentTipoClientes fragmentTipoClientes = new FragmentTipoClientes();
    FragmentZona fragmentZona = new FragmentZona();

    private Button btnclientes;
    private Button btnzona;
    private Button btntipoClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportFragmentManager().beginTransaction().replace(
          //      R.id.frameLayout, fragmentClientes).addToBackStack(null).commit();
/*
        //cargando datos layout
        btnclientes = findViewById(R.id.buttonFragmentClientes);
        btnzona = findViewById(R.id.buttonFragmentZona);
        btntipoClientes = findViewById(R.id.buttonFragmentTiposClientes);

        btnclientes.setOnClickListener(eventClientes);
        btnzona.setOnClickListener(eventZona);
        btntipoClientes.setOnClickListener(eventTipoClientes);
*/
    }

    View.OnClickListener eventClientes = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //getSupportFragmentManager().beginTransaction().replace(
              //      R.id.frameLayout, fragmentClientes).addToBackStack(null).commit();
        }
    };

    View.OnClickListener eventZona = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //getSupportFragmentManager().beginTransaction().replace(
              //      R.id.frameLayout, fragmentZona).addToBackStack(null).commit();
        }
    };

    View.OnClickListener eventTipoClientes = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //getSupportFragmentManager().beginTransaction().replace(
              //      R.id.frameLayout, fragmentTipoClientes).addToBackStack(null).commit();
        }
    };

/*
    //boton de atras
    // dialogo para salir de la app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK)
        {
            btnclientes.callOnClick();
            //Dialogo para salir de la app o no
            DialogoSalir dialogoSalir= new DialogoSalir();
            dialogoSalir.show(getSupportFragmentManager().beginTransaction(), null);
        }
        return super.onKeyDown(keyCode, event);
    }
*/
}