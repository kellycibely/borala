package com.example.kelly.borala;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.util.ArrayList;

public class lista_viagens extends Activity implements AdapterView.OnItemClickListener {
    private ListView listview ;
    ArrayList<viagem> viagens;
    ArrayList<viagem> busca;




    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.viagens);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Minhas viagens");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setIcon(R.drawable.minhas_viagens);
        actionBar.setDisplayHomeAsUpEnabled(true);

        bd application = (bd) getApplication();
        viagens = application.listarTodas();
        listview = (ListView)findViewById(R.id.minhasViagensList);
        listview.setAdapter(new adaptador_viagens(this, viagens));
        listview.setOnItemClickListener(this);
        busca = new ArrayList<>();

    }

    public void onItemClick(AdapterView<?> parent, View view,int idx, long id){
        int item = (int) listview.getItemIdAtPosition(idx);
        viagem v = this.viagens.get(item);
        Intent intent = new Intent(this,lista_gastos.class);
        intent.putExtra("Valor", v.getId());
        startActivity(intent);
        Toast.makeText(this, "Local: " + v.getLocalViagem(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_minhas_viagens, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onSearch());
        //ActionShare
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider share = (ShareActionProvider) shareItem.getActionProvider();
        Intent intent = new Intent(Intent.ACTION_SEND);
        share.setShareIntent(getDefaultIntent());

        return true;
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
            toast("Clicou no settings");
            return true;
        } else if(id == R.id.action_share){
            toast("Clicou no share!");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private SearchView.OnQueryTextListener onSearch(){
        return new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                int id;
                for (viagem v: viagens) {
                    System.out.println(v.getId());
                    if(v.getLocalViagem().toUpperCase().equals(query.toUpperCase())){
                        id = v.getId();
                        busca.add(v);
                        System.out.println(id);
                        break;
                    }
                }

                listview.removeAllViewsInLayout();
                listview.setAdapter(new adaptador_viagens(lista_viagens.this,busca));
                listview.setOnItemClickListener(lista_viagens.this);
                listview.refreshDrawableState();

                toast("Resultado da busca para " + query);
                return  false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                return false;
            }
        };
    }

    private Intent getDefaultIntent(){
        String texto = "";
        for (viagem v: viagens) {
            texto = texto + v.getLocalViagem() + "\n" + v.getData() + "\n" + v.totalGastos() + "\n\n";
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, texto);
        return intent;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }

    private void toast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();


    }

}
