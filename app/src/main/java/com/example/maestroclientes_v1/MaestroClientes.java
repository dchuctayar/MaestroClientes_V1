package com.example.maestroclientes_v1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MaestroClientes extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="clientes.bd";
    private static final int VERSION_BD=1;
    private static final String TABLA_CLIENTES="CREATE TABLE CLIENTES (CODIGO TEXT PRIMARY KEY, NOMBRE TEXT, RUC TEXT, ZONA TEXT, TIPO TEXT, ESTADO TEXT)";

    public MaestroClientes(@Nullable Context context) {
        super(context, NOMBRE_BD,null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_CLIENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_CLIENTES);
        sqLiteDatabase.execSQL(TABLA_CLIENTES);
    }

    public void agregarClientes(String codigo, String nombre, String ruc, String zona, String tipo, String estado){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("INSERT INTO CLIENTES VALUES('"+codigo+"','"+nombre+"','"+ruc+"','"+zona+"','"+tipo+"','"+estado+"')");
            bd.close();
        }
    }
}