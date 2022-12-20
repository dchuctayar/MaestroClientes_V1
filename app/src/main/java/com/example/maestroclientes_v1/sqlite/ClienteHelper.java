package com.example.maestroclientes_v1.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClienteHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="clientes.bd";
    private static final int VERSION_BD=3;

    private static final String ZONA = "CREATE TABLE ZONA (" +
            "CODIGO TEXT PRIMARY KEY NOT NULL, " +
            "NOMBRE TEXT NOT NULL," +
            "ESTADO TEXT NOT NULL" +
            ")";

    private static final String TABLA_TIPO_CLIENTES = "CREATE TABLE TIPO_CLIENTE (" +
            "CODIGO TEXT PRIMARY KEY NOT NULL, " +
            "NOMBRE TEXT NOT NULL," +
            "ESTADO TEXT NOT NULL" +
        ")";

    //maestro clientes
    private static final String TABLA_CLIENTES="CREATE TABLE CLIENTES (" +
            "CODIGO TEXT PRIMARY KEY NOT NULL, " +
            "NOMBRE TEXT, " +
            "RUC TEXT, " +
            "ZONA TEXT NOT NULL, " +
            "TIPO TEXT NOT NULL, " +
            "ESTADO TEXT NOT NULL," +
            "FOREIGN KEY(TIPO) REFERENCES TIPO_CLIENTES(CODIGO)," +
            "FOREIGN KEY(TIPO) REFERENCES ZONA(CODIGO)" +
            ")";


    public ClienteHelper(@Nullable Context context) {
        super(context, NOMBRE_BD,null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_TIPO_CLIENTES);
        sqLiteDatabase.execSQL(ZONA);
        sqLiteDatabase.execSQL(TABLA_CLIENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TIPO_CLIENTE");
        sqLiteDatabase.execSQL(TABLA_TIPO_CLIENTES);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ZONA");
        sqLiteDatabase.execSQL(ZONA);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLIENTES");
        sqLiteDatabase.execSQL(TABLA_CLIENTES);
    }

    //CRUD CLIENTES==========================================================
    public void agregarClientes(String codigo, String nombre, String ruc, String zona, String tipo, String estado){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("INSERT INTO CLIENTES VALUES(" +
                    "'"+codigo+"'," +
                    "'"+nombre+"'," +
                    "'"+ruc+"'," +
                    "'"+zona+"'," +
                    "'"+tipo+"'," +
                    "'"+estado+"'" +
                    ")"
            );
            bd.close();
        }
    }
    //CRUD TIPO CLIENTE===========================================================
    public void agregarTipoClientes(String codigo, String nombre,String estado){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("INSERT INTO TIPO_CLIENTE VALUES(" +
                    "'"+codigo+"'," +
                    "'"+nombre+"'," +
                    "'"+estado+"'" +
                    ")"
            );
            bd.close();
        }
    }

    //CRUD ZONA===========================================================
    public void agregarZona(String codigo, String nombre,String estado){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("INSERT INTO ZONA VALUES(" +
                    "'"+codigo+"'," +
                    "'"+nombre+"'," +
                    "'"+estado+"'" +
                    ")"
            );
            bd.close();
        }
    }
}