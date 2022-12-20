package com.example.maestroclientes_v1.Clientes;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maestroclientes_v1.R;

import java.util.ArrayList;

public class AdapterClientes extends RecyclerView.Adapter<AdapterClientes.ViewHolderData> {

    //por ahora string mas adelante clase Clientes
    private ArrayList<String> listClientes;

    public AdapterClientes(ArrayList<String> listClientes) {
        this.listClientes = listClientes;
    }

    @NonNull
    @Override
    public AdapterClientes.ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_clientes, null, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClientes.ViewHolderData holder, int position) {
        //mas despues cargamos los datos
        holder.cargarDatos(this.listClientes.get(position));
    }

    @Override
    public int getItemCount() {
        return listClientes.size();
    }

    //clase view holder
    public class ViewHolderData extends RecyclerView.ViewHolder {

        private TextView nombreCliente;
        private ImageButton btnEditarCliente;
        private ImageButton btnEliminarCliente;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            this.nombreCliente = itemView.findViewById(R.id.textNombreClienteTabla);

            //eventos
            this.btnEditarCliente = itemView.findViewById(R.id.btnEditCliente);
            this.btnEditarCliente.setOnClickListener(eventEditarCliente);

            this.btnEliminarCliente = itemView.findViewById(R.id.btnDeleteCliente);
            this.btnEliminarCliente.setOnClickListener(eventEliminarCliente);
        }

        public void cargarDatos(String nombre) {
            //nombre del cliente
            this.nombreCliente.setText(nombre);
        }

        //eventos=============================================================
        //para cargar un fragment se debe traer un getActivity() desde el fragment
        //luego que este en el adapatador se llama el getSupport()
        private View.OnClickListener eventEditarCliente = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Si funciona editar",
                        Toast.LENGTH_LONG).show();
            }
        };

        private View.OnClickListener eventEliminarCliente = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Si funciona eliminar",
                        Toast.LENGTH_LONG).show();
            }
        };
        //=========================================================================
    }
}
