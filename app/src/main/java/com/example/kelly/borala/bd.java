package com.example.kelly.borala;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


public class bd extends Application{
    private static Map<Integer, viagem> banco = new HashMap<Integer, viagem>();
    private static AtomicLong contador = new AtomicLong(2);

    static {
        ArrayList<gasto> gastos = new ArrayList<gasto>();
        gasto gasto1 = new gasto(50.00,"17/06/2016","transporte");
        gasto gasto2 = new gasto(1000.00,"17/06/2016","hospedagem");
        gastos.add(gasto1);
        gastos.add(gasto2);

        viagem viagem1 = new viagem(R.drawable.lazer,"Luís Correia","17/06/2016",gastos);
        viagem1.setId(1);

        ArrayList<gasto> gastos2 = new ArrayList<gasto>();
        gasto gasto3 = new gasto(40.00,"18/06/2016","transporte");
        gasto gasto4 = new gasto(150.00,"18/06/2016","alimentação");
        gastos2.add(gasto3);
        gastos2.add(gasto4);

        viagem viagem2 = new viagem(R.drawable.negocios,"São Paulo","18/06/2016",gastos2);
        viagem2.setId(2);



        banco.put(1,viagem1);
        banco.put(2,viagem2);

    }

    public void adiciona(viagem viagem) {
        int id = (int) contador.incrementAndGet();
        viagem.setId(id);
        banco.put(id, viagem);
    }

    public viagem busca(int id) {
        return banco.get(id);
    }

    public ArrayList<viagem> listarTodas(){
        ArrayList<viagem> viagens = new ArrayList<viagem>();
        for (int i = 1; i <= banco.size() ; i++) {
            viagens.add(banco.get(i));
            System.out.println(banco.get(i).getId());
        }

        return viagens;
    }


    public ArrayList<String> nomesViagens(){
        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<viagem> viagens = this.listarTodas();
        for (viagem v: viagens) {
            nomes.add(v.getLocalViagem());
        }

        return nomes;
    }

    public viagem remove(long id) {

        return banco.remove(id);
    }
}
