package com.example.maestroclientes_v1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maestroclientes_v1.sqlite.ClienteHelper;
import com.example.maestroclientes_v1.R;

import java.util.ArrayList;

public class FragmentAgregarCliente extends Fragment {

    private EditText editCodigo, editNombre, editRuc, editZona, editTipo;
    private Spinner spEstado;  //spinner
    Button btnAgregar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentAgregarCliente() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentAgregarCliente newInstance(String param1, String param2) {
        FragmentAgregarCliente fragment = new FragmentAgregarCliente();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_cliente, container, false);

        editCodigo=(EditText)view.findViewById(R.id.editCodigo);
        editNombre=(EditText)view.findViewById(R.id.editNombre);
        editRuc=(EditText)view.findViewById(R.id.editRuc);
        editZona=(EditText)view.findViewById(R.id.editZona);
        editTipo=(EditText)view.findViewById(R.id.editTipo);
        btnAgregar=(Button)view.findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(eventAgregarCliente);

        llenandoSpinner(view);

        return view;
    }

    ArrayList<String > listOpcEstReg = new ArrayList<>();

    private void llenandoSpinner(View view) {
        listOpcEstReg.add("Activo");
        listOpcEstReg.add("Inactivo");
        listOpcEstReg.add("Eliminado");

        spEstado= view.findViewById(R.id.spinnerEstado);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(
                view.getContext(), android.R.layout.simple_spinner_item, listOpcEstReg);
        spEstado.setAdapter(adapter);
        spEstado.setOnItemSelectedListener(eventSpinnerRegEst);
    }

    //agregar cliente
    private View.OnClickListener eventAgregarCliente = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //conexion con sqlite*******************************************
            final ClienteHelper clientes=new ClienteHelper(getActivity());
            String estadoRegistro = spEstado.getSelectedItem().toString();
            //*************************************************************

            clientes.agregarClientes(editCodigo.getText().toString(),
                    editNombre.getText().toString(),editRuc.getText().toString(),
                    editZona.getText().toString(),editTipo.getText().toString(),
                    estadoRegistro); //spinner

            Toast.makeText(getActivity(),"SE AGREGÓ CORRECTAMENTE",
                    Toast.LENGTH_SHORT).show();
            //nos dirigimos al fragemnt de inicio********************************
            FragmentClientes fragmentClientes = new FragmentClientes();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragmentClientes).commit();
            //******************************************************************
        }
    };

    private AdapterView.OnItemSelectedListener eventSpinnerRegEst =
            new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(view.getContext(), "Position: " +i, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

}