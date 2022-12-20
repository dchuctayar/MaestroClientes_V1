package com.example.maestroclientes_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        getSupportFragmentManager().beginTransaction().replace(
                R.id.frameLayout, fragmentClientes).commit();

        //cargando datos layout
        btnclientes = findViewById(R.id.buttonFragmentClientes);
        btnzona = findViewById(R.id.buttonFragmentZona);
        btntipoClientes = findViewById(R.id.buttonFragmentTiposClientes);

        btnclientes.setOnClickListener(eventClientes);
        btnzona.setOnClickListener(eventZona);
        btntipoClientes.setOnClickListener(eventTipoClientes);

    }

    View.OnClickListener eventClientes = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.frameLayout, fragmentClientes).commit();
        }
    };

    View.OnClickListener eventZona = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.frameLayout, fragmentZona).commit();
        }
    };

    View.OnClickListener eventTipoClientes = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.frameLayout, fragmentTipoClientes).commit();
        }
    };

}