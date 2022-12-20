package com.example.maestroclientes_v1.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.maestroclientes_v1.R;

public class DialogDescriptionCliente extends DialogFragment {

    //Data
    private static final String ARG_PARAM = "description";

    static public DialogDescriptionCliente newInstance(String descripcionCliente) {
        //se referencia asi mismo
        DialogDescriptionCliente p = new DialogDescriptionCliente();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, descripcionCliente);
        p.setArguments(args);
        return p;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //recibiendo por el flujo de datos
        final String cargaDesriptionCliente = getArguments().getString(ARG_PARAM);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        //enlazando con el layaout que va en el dialogo
        View view = inflater.inflate(R.layout.dialog_description_cliente, null);

        //Descripcion de cliente insertada en el dialogo
        TextView txtDescriptionPostulante = view.findViewById(
                R.id.insertarDescripcionCliente);
        txtDescriptionPostulante.setText(cargaDesriptionCliente);

        builder.setView(view);
        return builder.create();
    }

}
