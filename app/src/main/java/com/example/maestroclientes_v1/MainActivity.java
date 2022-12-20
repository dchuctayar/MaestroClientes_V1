package com.example.maestroclientes_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editCodigo, editNombre, editRuc, editZona, editTipo, editEstado;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editCodigo=(EditText)findViewById(R.id.editCodigo);
        editNombre=(EditText)findViewById(R.id.editNombre);
        editRuc=(EditText)findViewById(R.id.editRuc);
        editZona=(EditText)findViewById(R.id.editZona);
        editTipo=(EditText)findViewById(R.id.editTipo);
        editEstado=(EditText)findViewById(R.id.editEstado);
        btnAgregar=(Button)findViewById(R.id.btnAgregar);

        final MaestroClientes clientes=new MaestroClientes(getApplicationContext());

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientes.agregarClientes(editCodigo.getText().toString(),editNombre.getText().toString(),editRuc.getText().toString(),editZona.getText().toString(),editTipo.getText().toString(),editEstado.getText().toString());
                Toast.makeText(getApplicationContext(),"SE AGREGÓ CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });
    }
}