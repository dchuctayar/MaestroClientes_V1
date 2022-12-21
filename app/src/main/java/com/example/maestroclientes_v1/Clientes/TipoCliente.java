package com.example.maestroclientes_v1.Clientes;

public class TipoCliente {
    private String codigo;
    private String name;
    private String estado;

    public TipoCliente(String codigo, String name, String estado) {
        this.codigo = codigo;
        this.name = name;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
