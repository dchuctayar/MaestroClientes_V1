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
 * Use the {@link FragmentZona#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentZona extends Fragment {

    private EditText editCodigo, editNombre;
    private Spinner spEstadoZona;
    Button btnAgregarZona;
    Button btnEditarZona;
    Button btnEliminarZona;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentZona() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentZona.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentZona newInstance(String param1, String param2) {
        FragmentZona fragment = new FragmentZona();
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
        View view = inflater.inflate(R.layout.fragment_zona, container, false);

        editCodigo=(EditText)view.findViewById(R.id.editCodigo);
        editNombre=(EditText)view.findViewById(R.id.editNombre);

        llenandoSpinner(view);

        btnAgregarZona = view.findViewById(R.id.btnAgregarZona);
        btnEditarZona = view.findViewById(R.id.btnEditarZona);
        btnEliminarZona = view.findViewById(R.id.btnEliminarZona);

        //evento agregar
        btnAgregarZona.setOnClickListener(eventAgregarZona);
        btnEditarZona.setOnClickListener(eventEditarZona);
        btnEliminarZona.setOnClickListener(eventEliminarZona);

        return view;
    }

    ArrayList<String > listOpcEstReg = new ArrayList<>();
    private void llenandoSpinner(View view) {
        listOpcEstReg.add("Activo");
        listOpcEstReg.add("Inactivo");
        listOpcEstReg.add("Eliminado");

        spEstadoZona=view.findViewById(R.id.spinnerEstadoZona);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(
                view.getContext(), android.R.layout.simple_spinner_item, listOpcEstReg);
        spEstadoZona.setAdapter(adapter);
    }

    View.OnClickListener eventAgregarZona = new View.OnClickListener() {
        final ClienteHelper clientes=new ClienteHelper(getActivity());
        String estadoRegistro = spEstadoZona.getSelectedItem().toString();
        @Override
        public void onClick(View view) {
            clientes.agregarZona(editCodigo.getText().toString(),
                    editNombre.getText().toString(),estadoRegistro);
            Toast.makeText(getActivity(),"SE AGREGÓ CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener eventEditarZona = new View.OnClickListener() {
        final ClienteHelper clientes=new ClienteHelper(getActivity());
        String estadoRegistro = spEstadoZona.getSelectedItem().toString();
        @Override
        public void onClick(View view) {
            clientes.editarZona(editCodigo.getText().toString(),
                    editNombre.getText().toString(),estadoRegistro);
            Toast.makeText(getActivity(),"SE EDITO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }
    };
    View.OnClickListener eventEliminarZona = new View.OnClickListener() {
        final ClienteHelper clientes=new ClienteHelper(getActivity());
        @Override
        public void onClick(View view) {
            clientes.eliminarZona(editCodigo.getText().toString());
            Toast.makeText(getActivity(),"SE ELIMINO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }
    };
}