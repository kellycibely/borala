package com.example.kelly.borala;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SobreNos extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sobre_nos);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Sobre Nós");
        actionBar.setIcon(R.drawable.novo_gasto);
        actionBar.setDisplayHomeAsUpEnabled(true);


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



