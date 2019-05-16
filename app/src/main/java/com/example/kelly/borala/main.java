package com.example.kelly.borala;

import android.content.Intent;
import android.view.View;
import android.widget.SearchView;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class main extends Activity {
public final String data_agora = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("BoraLÁ");
        actionBar.setIcon(R.drawable.ic_logo);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_search){
            toast("Clicou no Search!");
            return true;
        }else if(id == R.id.action_refresh){
            toast("Clicou no refresh!");
            return true;
        }else if(id == R.id.action_settings){
            toast("Sobre nós");
            Intent intent = new Intent(this, SobreNos.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.action_share){
            toast("Clicou no share!");
            return true;
        } else if(id == R.id.action_extras){
            Intent intent = new Intent(this, Mais.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onSearch());

        return true;
    }

    private SearchView.OnQueryTextListener onSearch(){
        return new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                toast("Buscar o texto: " + query);
                return  false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                return false;
            }
        };
    }



    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }

    public void selecionarOpcao(){
        toast("voce selecionou: ");
    }

    public void novaViagem(View view){
        Intent intent = new Intent(this, viagem_nova.class);
        startActivity(intent);
    }

    public void novoGasto(View view){
        Intent intent = new Intent(this, gasto_novo.class);
        startActivity(intent);
    }

    public void minhasViagens(View view){
        Intent intent = new Intent(this, lista_viagens.class);
        startActivity(intent);
    }
    private void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }




}
