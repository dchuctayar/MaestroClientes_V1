package com.example.maestroclientes_v1.sqlite;

import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.maestroclientes_v1.Clientes.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="clientes.bd";
    private static final int VERSION_BD=1;

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

    public ArrayList<Cliente> mostrarClientes(){
        //trabajar en modo lectura
        SQLiteDatabase bd=getReadableDatabase();
        //recuperar datos mediante select
        Cursor cursor = bd.rawQuery("SELECT * FROM CLIENTES", null);
        //declaramos una lista
        ArrayList<Cliente> listCli = new ArrayList<>();
        //verificamos si hay al menos un registro
        if(cursor.moveToFirst()){
            do {
                listCli.add(new Cliente(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));
            } while (cursor.moveToNext());
        }
        return listCli;
    }

    public void editarClientes(String codigo, String nombre, String ruc, String zona, String tipo, String estado){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("UPDATE CLIENTES SET "+
                    "NOMBRE='"+nombre+"', " +
                    "RUC='"+ruc+"', " +
                    "ZONA='"+zona+"', " +
                    "TIPO='"+tipo+"', " +
                    "ESTADO='"+estado+"' " +
                    "WHERE CODIGO='"+ codigo +"'");
            bd.close();
        }
    }

    public void eliminarClientes(String codigo){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("UPDATE CLIENTES SET "+
                    "ESTADO='Eliminado' " +
                    "WHERE CODIGO='"+ codigo +"'");
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

    public void editarTipoCliente(String codigo, String nombre, String estado){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("UPDATE TIPO_CLIENTE SET "+
                    "NOMBRE='"+nombre+"', " +
                    "ESTADO='"+estado+"' " +
                    "WHERE CODIGO='"+ codigo +"'");
            bd.close();
        }
    }

    public void eliminarTipoCliente(String codigo){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("UPDATE TIPO_CLIENTE SET "+
                    "ESTADO='Eliminado' " +
                    "WHERE CODIGO='"+ codigo +"'");
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
    public void editarZona(String codigo, String nombre,String estado){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("UPDATE ZONA SET " +
                    "NOMBRE='"+nombre+"'," +
                    "ESTADO='"+estado+"' "+
                    "WHERE CODIGO='"+ codigo +"'");
            bd.close();
        }
    }
    public void eliminarZona(String codigo){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("UPDATE ZONA SET " +
                    "ESTADO='Eliminado' "+
                    "WHERE CODIGO='"+ codigo +"'");
            bd.close();
        }
    }

    //llenado de datos
    public void datosPredeterminados() {
        //puede colgarse debido a un problema con el hilo principal
        //se recomienda trabajar con otro hilo ante un trabajo pesado
        //Zona
        agregarZona("Z-0001", "Zona A-4", "Activo");
        agregarZona("Z-0002", "Zona B-1", "Activo");
        agregarZona("Z-0003", "Zona C-5", "Activo");
        agregarZona("Z-0004", "Zona D-3", "Activo");
        //Tipos de Clientes
        agregarTipoClientes("T-0001", "Habitual", "Activo");
        agregarTipoClientes("T-0002", "Poco frecuente", "Activo");
        agregarTipoClientes("T-0003", "Nuevo", "Activo");

        //Clientes
        agregarClientes("CLI-0001",
                "Luis Rodriguez",
                "12345678",
                "Z-0002",
                "T-0003",
                "Activo");
        agregarClientes("CLI-0002",
                "Diego Alvarez",
                "12345678",
                "Z-0001",
                "T-0003",
                "Activo");
        agregarClientes("CLI-0003",
                "Juan Flores",
                "12345678",
                "Z-0001",
                "T-0003",
                "Activo");
        agregarClientes("CLI-0004",
                "Pedro Quispe",
                "12345678",
                "Z-0001",
                "T-0002",
                "Activo");
        agregarClientes("CLI-0005",
                "Roberto Choque",
                "12345678",
                "Z-0002",
                "T-0002",
                "Activo");
        agregarClientes("CLI-0006",
                "Ernesto Villalba",
                "12345678",
                "Z-0003",
                "T-0002",
                "Activo");
        agregarClientes("CLI-0007",
                "Victor Chuctaya",
                "12345678",
                "Z-0004",
                "T-0001",
                "Activo");
        agregarClientes("CLI-0008",
                "Enrique Ruiz",
                "12345678",
                "Z-0004",
                "T-0001",
                "Activo");
        agregarClientes("CLI-0009",
                "Alexandra Salinas",
                "12345678",
                "Z-0004",
                "T-0001",
                "Activo");
        agregarClientes("CLI-0010",
                "Paola Flores",
                "12345678",
                "Z-0004",
                "T-0002",
                "Activo");
    }
}