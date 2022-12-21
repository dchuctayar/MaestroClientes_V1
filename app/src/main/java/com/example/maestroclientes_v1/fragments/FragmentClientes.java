package com.example.maestroclientes_v1.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maestroclientes_v1.Clientes.AdapterClientes;
import com.example.maestroclientes_v1.Clientes.Cliente;
import com.example.maestroclientes_v1.R;
import com.example.maestroclientes_v1.sqlite.ClienteHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientes extends Fragment {

    //componte para buscar cliente
    private EditText editTextNombreCliBus;
    private Button btnSearchClient;

    //por ahora string mas adelante clase Clientes
    private ArrayList<Cliente> listClientes;
    private RecyclerView recycler;

    private Button btnAddClient;

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

        referenciarAdaptador(view);

        this.editTextNombreCliBus = view.findViewById(R.id.editTextBuscarCliente); //para buscar
        //nos mandara al formulario agregar cliente
        //lo que avanzo Santos
        this.btnAddClient = view.findViewById(R.id.buttonFragmentAgregarCliente);
        this.btnAddClient.setOnClickListener(eventAddClient);

        //event search
        this.btnSearchClient = view.findViewById(R.id.buttonBuscarCliente);
        this.btnSearchClient.setOnClickListener(eventSearchClient);

        return view;
    }

    private void referenciarAdaptador(View view) {
        //Recycler View=======================
        //Referenciamos el RecyclerView del layout
        recycler = view.findViewById(R.id.recyclerClientes);
        //para cargar una lista vertical
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));

        //conexion con la base de datos
        final ClienteHelper clientes=new ClienteHelper(getActivity());
        listClientes = clientes.mostrarClientes();

        //enviamos los datos al adaptador de Clientes
        //le damos el getActivity para que se pueda cambiar de fragment en el adapter
        AdapterClientes adapter = new AdapterClientes(listClientes, getActivity());
        //por ultimo al recycler le enviamos el adaptador de la Clientes
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

    //buscar cliente en los elementos del recycler**********************************
    private View.OnClickListener eventSearchClient = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ArrayList<Cliente> listaClientesBuscados = new ArrayList<>();
            String nombreBuscado = editTextNombreCliBus.getText().toString();

            //print
            Toast.makeText(getActivity(), "Buscando cliente",
                    Toast.LENGTH_LONG).show();

            //en caso el texto este vacio
            if(nombreBuscado.equalsIgnoreCase("")){
                //enviamos los datos al adaptador de Clientes
                AdapterClientes adapter = new AdapterClientes(listClientes, getActivity());
                //por ultimo al recycler le enviamos el adaptador de la Clientes
                recycler.setAdapter(adapter);
            }else{

                //buscando de forma lineal==============================================
                for (int i = 0; i < listClientes.size(); i++){
                    if(nombreBuscado.equalsIgnoreCase(listClientes.get(i).getName()
                            .substring(0,nombreBuscado.length()))){
                        listaClientesBuscados.add(listClientes.get(i)); //agregandolo a nueva lista
                    }
                }
                //======================================================================

                //en caso de que se encontro
                if (listaClientesBuscados.size() > 0){
                    //enviamos los datos al adaptador de Clientes
                    AdapterClientes adapterbuscados = new AdapterClientes(
                            listaClientesBuscados, getActivity());
                    //por ultimo al recycler le enviamos el adaptador de la Clientes
                    recycler.setAdapter(adapterbuscados);

                }else{
                    //Dialogo para confirmar la eliminacion
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("No se encontro al cliente buscado. ")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss(); //la ventana se cierra
                                }
                            });
                    builder.show();
                }

            }

        }
    };
}