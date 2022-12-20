package com.example.maestroclientes_v1.Clientes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maestroclientes_v1.R;
import com.example.maestroclientes_v1.View.Contenedor;
import com.example.maestroclientes_v1.dialog.DialogDescriptionCliente;
import com.example.maestroclientes_v1.fragments.FragmentModificarCliente;

import java.util.ArrayList;

public class AdapterClientes extends RecyclerView.Adapter<AdapterClientes.ViewHolderData> {

    private FragmentActivity activity;
    //por ahora string mas adelante clase Clientes
    private ArrayList<Cliente> listClientes;

    public AdapterClientes(ArrayList<Cliente> listClientes, FragmentActivity activity) {
        this.listClientes = listClientes;
        this.activity = activity;
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
        holder.cargarDatos(this.listClientes.get(position), activity);
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
        private Contenedor cont;

        private FragmentActivity itemactivity;

        //fragments de los items del recycler========================
        private FragmentModificarCliente fragmentModificarCliente;
        //para pasarlo a la edicion
        Cliente cliente;
        //===========================================================

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            this.nombreCliente = itemView.findViewById(R.id.textNombreClienteTabla);

            //eventos
            this.btnEditarCliente = itemView.findViewById(R.id.btnEditCliente);
            this.btnEditarCliente.setOnClickListener(eventEditarCliente);

            this.cont = itemView.findViewById(R.id.contenedorItemCliente);
            this.cont.setOnClickListener(eventDescriptionCliente);

            this.btnEliminarCliente = itemView.findViewById(R.id.btnDeleteCliente);
            this.btnEliminarCliente.setOnClickListener(eventEliminarCliente);
        }

        public void cargarDatos(Cliente cliente,
                                FragmentActivity activity) {
            //nombre del cliente
            this.nombreCliente.setText(cliente.getName());
            this.itemactivity = activity;
            this.cliente = cliente;
        }

        //eventos=============================================================
        //para cargar un fragment se debe traer un getActivity() desde el fragment
        //luego que este en el adapatador se llama el getSupport()
        private View.OnClickListener eventEditarCliente = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Si funciona editar",
                        Toast.LENGTH_LONG).show();


                fragmentModificarCliente = FragmentModificarCliente.newInstance(
                        cliente.getCodigo(), cliente.getName(), cliente.getRuc(),
                        cliente.getZona(), cliente.getTipo(), cliente.getEstado()
                );

                //ingresamoa al fragment de editar cliente
                itemactivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragmentModificarCliente)
                        .commit();
            }
        };

        private View.OnClickListener eventEliminarCliente = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Dialogo para confirmar la eliminacion
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Desea eliminar a " + cliente.getName() + "? ")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Toast.makeText(view.getContext(), "Eliminado",
                                        Toast.LENGTH_LONG).show();

                                //************************************************
                                //procedemos a eliminar el cliente
                                //para esto nos conectamos a la base de datos
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss(); //la ventana se cierra
                            }
                        });
                builder.show();

            }
        };

        //description cliente
        private View.OnClickListener eventDescriptionCliente = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Descripcion cliente",
                        Toast.LENGTH_LONG).show();
                DialogDescriptionCliente.newInstance(
                                cliente.toString())
                        .show(activity.getSupportFragmentManager(), null);
            }
        };
        //=========================================================================
    }
}
