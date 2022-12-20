package com.example.maestroclientes_v1.Clientes;

public class Cliente {
    String codigo;
    String name;
    String ruc;
    String zona;
    String tipo;
    String estado;

    public Cliente(String codigo, String name, String ruc,
                   String zona, String tipo, String estado) {
        this.codigo = codigo;
        this.name = name;
        this.ruc = ruc;
        this.zona = zona;
        this.tipo = tipo;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return  "Codigo: " + codigo + '\n' +
                "Name:   " + name + '\n' +
                "RUC:    " + ruc + '\n' +
                "Zona:   " + zona + '\n' +
                "Tipo:   " + tipo + '\n' +
                "Estado: " + estado + '\n';
    }
}
