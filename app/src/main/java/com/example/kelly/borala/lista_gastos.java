package com.example.kelly.borala;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class lista_gastos extends Activity {


    private ListView listview ;
    ArrayList<gasto> gastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gastos);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Meus Gastos");
        actionBar.setIcon(R.drawable.novo_gasto);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int idViagem = intent.getIntExtra("Valor", 0);

        bd application = (bd) getApplication();
        gastos = application.busca(idViagem).getGastos();
        listview = (ListView)findViewById(R.id.gastosList);
        listview.setAdapter(new adaptador_gastos(this,gastos));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }
}
