package com.example.maestroclientes_v1.Clientes;

public class Cliente {
    private String codigo;
    private String name;
    private String ruc;
    private String zona;
    private String tipo;
    private String estado;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
