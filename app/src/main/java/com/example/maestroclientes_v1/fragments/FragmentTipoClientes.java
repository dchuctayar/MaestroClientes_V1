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
 * Use the {@link FragmentTipoClientes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTipoClientes extends Fragment {

    private EditText editCodigoTipoCliente, editNombreTipoCliente;
    private Spinner spTipoCliente;
    Button btnAgregarTipoCliente;
    Button btnModificarTipoCliente;
    Button btnEliminarTipoCliente;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTipoClientes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTipoClientes.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTipoClientes newInstance(String param1, String param2) {
        FragmentTipoClientes fragment = new FragmentTipoClientes();
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
        View view = inflater.inflate(R.layout.fragment_tipo_clientes, container, false);

        editCodigoTipoCliente=(EditText)view.findViewById(R.id.editCodigoTipoCliente);
        editNombreTipoCliente=(EditText)view.findViewById(R.id.editNombreTipoCliente);

        llenandoSpinner(view);

        //botones
        btnAgregarTipoCliente = view.findViewById(R.id.btnAgregarTipoCliente);
        btnModificarTipoCliente = view.findViewById(R.id.btnEditarTipoCliente);
        btnEliminarTipoCliente = view.findViewById(R.id.btnEliminarTipoCliente);

        //eventos
        btnAgregarTipoCliente.setOnClickListener(eventAddTypeClient);
        btnModificarTipoCliente.setOnClickListener(eventEditarTypeClient);
        btnEliminarTipoCliente.setOnClickListener(eventDeleteTypeClient);
        return view;
    }

    ArrayList<String > listOpcEstReg = new ArrayList<>();
    private void llenandoSpinner(View view) {
        listOpcEstReg.add("Activo");
        listOpcEstReg.add("Inactivo");
        listOpcEstReg.add("Eliminado");

        spTipoCliente = view.findViewById(R.id.spinnerEstadoTipoCliente);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(
                view.getContext(), android.R.layout.simple_spinner_item, listOpcEstReg);
        spTipoCliente.setAdapter(adapter);
    }

    private View.OnClickListener eventAddTypeClient = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final ClienteHelper clientes=new ClienteHelper(getActivity());
            String estadoRegistro = spTipoCliente.getSelectedItem().toString();

            clientes.agregarTipoClientes(editCodigoTipoCliente.getText().toString(),
                    editNombreTipoCliente.getText().toString(),estadoRegistro);

            Toast.makeText(getActivity(),"SE AGREGÓ CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener eventEditarTypeClient = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final ClienteHelper clientes=new ClienteHelper(getActivity());
            String estadoRegistro = spTipoCliente.getSelectedItem().toString();

            clientes.editarTipoCliente(editCodigoTipoCliente.getText().toString(),
                    editNombreTipoCliente.getText().toString(),estadoRegistro);

            Toast.makeText(getActivity(),"SE EDITO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener eventDeleteTypeClient = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final ClienteHelper clientes=new ClienteHelper(getActivity());

            clientes.eliminarTipoCliente(editCodigoTipoCliente.getText().toString());

            spTipoCliente.setSelection(2); //lo damos como eliminado en el estado

            Toast.makeText(getActivity(),"SE ELIMINO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }
    };
}
