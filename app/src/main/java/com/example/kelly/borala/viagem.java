package com.example.kelly.borala;

import java.util.ArrayList;


public class viagem {
    private int icone;
    private String localViagem;
    private String data;
    private int id;
    private ArrayList<gasto> gastos = new ArrayList<gasto>();

    viagem(){

    }

    viagem(int icone, String localViagem, String data, ArrayList<gasto> gastos){
        this.icone = icone ;
        this.gastos = gastos;
        this.data = data;
        this.localViagem = localViagem ;
    }

    public void setId(int id){

        this.id = id ;
    }

    public int getId(){

        return this.id;
    }

    public int getIcone() {

        return icone;
    }

    public String getLocalViagem() {

        return localViagem;
    }

    public ArrayList<gasto> getGastos() {

        return gastos;
    }

    public String getData() {

        return data;
    }

    public void setGastos(gasto gasto){

        this.gastos.add(gasto);
    }

    public double totalGastos(){
        double total = 0;
        for (gasto gastos: this.gastos) {
            total = total + gastos.getValor();
        }
        return total ;
    }

    public viagem setIcone(int icone) {
        this.icone = icone;
        return this;
    }

    public void setLocalViagem (String localViagem) {

        this.localViagem = localViagem;
    }

    public viagem setData(String data) {
        this.data = data;
        return this;
    }

}
