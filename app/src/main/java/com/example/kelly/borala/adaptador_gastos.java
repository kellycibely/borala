package com.example.kelly.borala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class adaptador_gastos extends BaseAdapter {
    private final Context context;
    private final List<gasto> gastos;

    public adaptador_gastos(Context context, ArrayList<gasto> gastos) {
        this.context = context;
        this.gastos = gastos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.gastos_adaptador,parent,false);
        TextView valorGasto = (TextView) view.findViewById(R.id.valorGasto);
        TextView dataGasto = (TextView) view.findViewById(R.id.dataGasto);
        TextView tipoDeGasto = (TextView) view.findViewById(R.id.tipoGasto);

        gasto gasto = gastos.get(position);


        String totalGastosString = String.valueOf(gasto.getValor());

        String gastoValorSemPonto = totalGastosString.replace(".",",");

        valorGasto.setText(gastoValorSemPonto);
        dataGasto.setText(gasto.getData());
        tipoDeGasto.setText(gasto.getTipo());

        return view;
    }
    @Override
    public int getCount() {
        return gastos != null ? gastos.size():0;
    }

    @Override
    public Object getItem(int position) {

        return gastos.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }
}
