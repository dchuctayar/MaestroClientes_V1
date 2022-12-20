package com.example.maestroclientes_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAutenticacion extends AppCompatActivity {

    private static String TAG = "ActivityAutotenticacion";

    EditText edtUsuario;
    EditText edtPassword;
    TextView txtMensaje;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacion);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        txtMensaje = findViewById(R.id.txtMensaje);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        //evento
        btnLogin.setOnClickListener(eventoAutenticacion);


    }

    private View.OnClickListener eventoAutenticacion = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String usuario = edtUsuario.getText().toString();
            String password = edtPassword.getText().toString();
            if (usuario.equals("admin") && password.equals("1234")) {
                Log.d(TAG, "valida," + usuario + "," + password);
                txtMensaje.setText("Autenticacion valida");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            } else {
                txtMensaje.setText("Autenticacion no valida");
                Log.d(TAG, "invalido," + usuario + "," + password);
            }
        }
    };
}