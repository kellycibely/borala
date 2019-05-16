package com.example.kelly.borala;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class gasto_novo extends Activity{
    public static String data = "";

    private List<String> destino;
    private bd bd;
    private  static  Button bt_data;
    private String destino_nome ="teste";
    private String tipo_nome ="teste";
    private com.example.kelly.borala.gasto gasto;
    private com.example.kelly.borala.viagem viagem;
    private static Double db_valor = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gasto_novo);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Novo gasto");
        actionBar.setIcon(R.drawable.novo_gasto);
        actionBar.setDisplayHomeAsUpEnabled(true);
        bd = (bd) getApplication();
        destino = bd.nomesViagens();
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, destino);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id){
                destino_nome = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
        final Spinner tipos = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> gastosAdapter = ArrayAdapter.createFromResource(this, R.array.tipos_de_gasto, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tipos.setAdapter(gastosAdapter);
        tipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id){
                tipo_nome = tipos.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
        bt_data = (Button) findViewById(R.id.bt_DatePicker);

    }

    public void salvarGasto(View v){
        EditText ed_Valor = (EditText) findViewById(R.id.et_Valor);
        if(ed_Valor.getText().toString().equals("")){
            toast("Valor do gasto não defenido!");
        }else{
            db_valor = Double.parseDouble(ed_Valor.getText().toString().replace(",","."));
            if(data.equals("")){
                toast("Data do gasto não defenida!");
            }else{
                gasto = new gasto(db_valor, data, tipo_nome);
                bd.busca((destino.indexOf((destino_nome)) + 1)).setGastos(gasto);
                toast("gasto salvo com sucesso!");
            }
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_novo_gasto, menu);

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

    public void showDatePickerDialog(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstance){
            final Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }

        public void onDateSet(DatePicker view, int ano, int mes, int dia ){
            data = (dia + "/" + (mes+1) + "/" + ano);
            bt_data.setText(data);

        }
    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
