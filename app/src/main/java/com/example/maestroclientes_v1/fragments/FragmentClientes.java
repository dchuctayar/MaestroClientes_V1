package com.example.maestroclientes_v1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.maestroclientes_v1.Clientes.AdapterClientes;
import com.example.maestroclientes_v1.Clientes.Cliente;
import com.example.maestroclientes_v1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientes extends Fragment {


    //por ahora string mas adelante clase Clientes
    private ArrayList<Cliente> listClientes;
    private RecyclerView recycler;

    private Button btnAddClient;
    private Button btnSearchClient;

    //CRUD
    private FragmentAgregarCliente fragmentAgregarCliente = new FragmentAgregarCliente();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentClientes.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentClientes newInstance(String param1, String param2) {
        FragmentClientes fragment = new FragmentClientes();
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
        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        //nos mandara al formulario agregar cliente
        //lo que avanzo Santos
        this.btnAddClient = view.findViewById(R.id.buttonFragmentAgregarCliente);
        this.btnAddClient.setOnClickListener(eventAddClient);

        //event search
        this.btnSearchClient = view.findViewById(R.id.buttonBuscarCliente);
        this.btnSearchClient.setOnClickListener(eventSearchClient);

        referenciarAdaptador(view);

        return view;
    }

    private void referenciarAdaptador(View view) {
        //Recycler View=======================
        //Referenciamos el RecyclerView del layout
        recycler = view.findViewById(R.id.recyclerClientes);
        //para cargar una lista vertical
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));
        //llenando datos de comunidad
        listClientes = new ArrayList<>();
        //llenado de datos
        llenadoDatos(); //despues eliminar

        //enviamos los datos al adaptador de Comunidad
        //le damos el getActivity para que se pueda cambiar de fragment en el adapter
        AdapterClientes adapter = new AdapterClientes(listClientes, getActivity());
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recycler.setAdapter(adapter);
        ///===================================
    }

    private View.OnClickListener eventAddClient = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragmentAgregarCliente).commit();
        }
    };

    private View.OnClickListener eventSearchClient = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Buscando cliente",
                    Toast.LENGTH_LONG).show();
        }
    };



    private void llenadoDatos() {

        listClientes.add(new Cliente("CLI-0001",
                "Luis Rodriguez",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
        listClientes.add(new Cliente("CLI-0002",
                "Diego Alvarez",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
        listClientes.add(new Cliente("CLI-0003",
                "Juan Flores",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
        listClientes.add(new Cliente("CLI-0004",
                "Pedro Quispe",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
        listClientes.add(new Cliente("CLI-0005",
                "Roberto Choque",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
        listClientes.add(new Cliente("CLI-0006",
                "Ernesto Villalba",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
        listClientes.add(new Cliente("CLI-0007",
                "Victor Chuctaya",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
        listClientes.add(new Cliente("CLI-0008",
                "Enrique Ruiz",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
        listClientes.add(new Cliente("CLI-0009",
                "Alexandra Salinas",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
        listClientes.add(new Cliente("CLI-0010",
                "Paola Flores",
                "12345678",
                "A-4",
                "Empleado",
                "Activo"));
    }
}