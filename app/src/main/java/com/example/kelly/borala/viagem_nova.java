package com.example.kelly.borala;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class viagem_nova extends Activity {
    public static String destino = "";
    public static String tipo = "";
    public static String data = "";

    private com.example.kelly.borala.viagem viagem ;
    bd bd;
    private static Button bt_Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viagem_nova);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Nova viagem");
        actionBar.setIcon(R.drawable.nova_viagem);
        actionBar.setDisplayHomeAsUpEnabled(true);
        viagem = new viagem();
        bd = (bd) getApplication();
        bt_Data = (Button) findViewById(R.id.bt_DatePicker);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_nova_viagem, menu);

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

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rb_Lazer:
                if (checked)
                    tipo="Lazer";
                    viagem.setIcone(R.drawable.lazer);
                break;
            case R.id.rb_Negocios:
                if(checked)
                    tipo = "Negocios";
                    viagem.setIcone(R.drawable.negocios);
                break;
        }
    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
            bt_Data.setText(data);

        }
    }


    public void showDatePickerDialog(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void gravarViagem(View view){
        EditText et_Destino = (EditText) findViewById(R.id.et_Destino);
        destino = et_Destino.getText().toString();
        if(destino.isEmpty()){
            toast("Destino não não defenido!");
        }else{
            if(tipo.equals("")){
                toast("Tipo não defenido!");
            }else{
                if(data.equals("")){
                    toast("Data não defenido!");
                }else{
                    viagem.setLocalViagem(destino);
                    viagem.setData(data);
                    bd.adiciona(viagem);
                    toast("salvo com sucesso!");
                }
            }
        }
        onRestart();
    }

    @Override
    protected void onRestart() {

        // TODO Auto-generated method stub
        super.onRestart();
        Intent i = new Intent(viagem_nova.this,viagem_nova.class);  //your clas
        startActivity(i);
        finish();

    }


}
