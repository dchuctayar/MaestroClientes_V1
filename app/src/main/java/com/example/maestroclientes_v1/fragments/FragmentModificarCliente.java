package com.example.maestroclientes_v1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maestroclientes_v1.R;
import com.example.maestroclientes_v1.sqlite.ClienteHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentModificarCliente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentModificarCliente extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private EditText editCodigo, editNombre, editRuc, editZona, editTipo;
    Button btnModificar;
    private Spinner spEstado;  //spinner

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "codigo";
    private static final String ARG_PARAM2 = "name";
    private static final String ARG_PARAM3 = "ruc";
    private static final String ARG_PARAM4 = "zona";
    private static final String ARG_PARAM5 = "tipo";
    private static final String ARG_PARAM6 = "estado";



    // Cliente datos
    private String codigo;
    private String name;
    private String ruc;
    private String zona;
    private String tipo;
    private String estado;

    public FragmentModificarCliente() {
        // Required empty public constructor
    }

    public static FragmentModificarCliente newInstance(String param1, String param2, String param3,
                                                       String param4, String param5, String param6) {
        FragmentModificarCliente fragment = new FragmentModificarCliente();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putString(ARG_PARAM6, param6);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.codigo = getArguments().getString(ARG_PARAM1);
            this.name = getArguments().getString(ARG_PARAM2);
            this.ruc = getArguments().getString(ARG_PARAM3);
            this.zona = getArguments().getString(ARG_PARAM4);
            this.tipo = getArguments().getString(ARG_PARAM5);
            this.estado = getArguments().getString(ARG_PARAM6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modificar_cliente, container, false);

        editCodigo = view.findViewById(R.id.editCodigo);
        editNombre = view.findViewById(R.id.editNombre);
        editRuc = view.findViewById(R.id.editRuc);
        editZona = view.findViewById(R.id.editZona);
        editTipo = view.findViewById(R.id.editTipo);
        btnModificar = view.findViewById(R.id.btnModificar);

        //spinner
        llenandoSpinner(view);
        //se colocan los datos del cliente solicitado
        llenarformulario();

        //event
        btnModificar.setOnClickListener(eventModificarCliente);

        return view;
    }

    ArrayList<String > listOpcEstReg = new ArrayList<>();
    private void llenandoSpinner(View view) {
        listOpcEstReg.add("Activo");
        listOpcEstReg.add("Inactivo");
        listOpcEstReg.add("Eliminado");

        spEstado= view.findViewById(R.id.spinnerEditEstado);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(
                view.getContext(), android.R.layout.simple_spinner_item, listOpcEstReg);
        spEstado.setAdapter(adapter);
    }

    private void llenarformulario() {
        editCodigo.setText(this.codigo);
        //bloqueamos este campo para que no se pueda editar ya que es el codigo
        editCodigo.setEnabled(false);

        editNombre.setText(this.name);
        editRuc.setText(this.ruc);
        editZona.setText(this.zona);
        editTipo.setText(this.tipo);

        //Colocando el valor en la lista desplegable(spinner)
        if (this.estado.equals("Activo")){
            spEstado.setSelection(0);
        }else{
            if (this.estado.equals("Inactivo")){
                spEstado.setSelection(1);
            }else{
                spEstado.setSelection(2);
            }
        }

    }

    private View.OnClickListener eventModificarCliente = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_LONG).show();
            String estadoRegistro = spEstado.getSelectedItem().toString();

            //aqui se llama al ClientHelper para guardarlo en la base de datos
            final ClienteHelper clientes=new ClienteHelper(getActivity());
            clientes.editarClientes(editCodigo.getText().toString(), editNombre.getText().toString(),
                    editRuc.getText().toString(), editZona.getText().toString(),
                    editTipo.getText().toString(), estadoRegistro);

            //nos dirigimos al fragemnt de inicio********************************
            FragmentClientes fragmentClientes = new FragmentClientes();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragmentClientes).commit();
            //******************************************************************

        }
    };

}