package com.example.kelly.borala;

public class gasto {
    private double valor;
    private String data;
    private String tipo;

    gasto(double valor, String data, String tipo){
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getData(){

        return this.data;
    }

    public Double getValor(){
        return this.valor;
    }

    public String getTipo(){

        return this.tipo;
    }

}
