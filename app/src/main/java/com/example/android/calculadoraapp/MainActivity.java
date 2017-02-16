package com.example.android.calculadoraapp;

import java.text.NumberFormat;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {


    // Definição dos objetos e variáveis modulares que
    // serão utilizados dentro da classe

    EditText primeiro;
    EditText segundo;
    EditText resultado;
    Button btnSomar;
    Button btnSubtrair;
    Button btnMultiplicar;
    Button btnDividir;
    Double result = (double)0;

    // Chamada quando a activity é criada pela primeira vez
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        // Associa um arquivo de layout a esta classe
        //
        setContentView(R.layout.activity_main);

        try{
            //
            // Instanciando os objetos de acordo com o layout XML
            // definido no arquivo main.xml
            //
            primeiro = (EditText)findViewById(R.id.edNumero1);
            segundo = (EditText)findViewById(R.id.edNumero2);
            resultado = (EditText)findViewById(R.id.edResultado);
            btnSomar = (Button)findViewById(R.id.btnSomar);
            btnSubtrair = (Button)findViewById(R.id.btnSubtrair);
            btnMultiplicar = (Button)findViewById(R.id.btnMultiplicar);
            btnDividir = (Button)findViewById(R.id.btnDividir);

            //
            // Associando o evento OnClickListener de cada um dos botões
            // a uma fução específica
            //
            btnSomar.setOnClickListener(onClick_btnSomar);
            btnSubtrair.setOnClickListener(onClick_btnSubtrair);
            btnMultiplicar.setOnClickListener(onClick_btnMultiplicar);
            btnDividir.setOnClickListener(onClick_btnDividir);
        }
        catch (Exception e) {
            // Tratamento de erro
            Mensagem(e.getMessage());
        }
    }

    // Cria um anonymous implementation para OnClickListener
    OnClickListener onClick_btnSomar = new OnClickListener() {
        public void onClick(View v) {
            try
            {
                result = getDouble(primeiro.getText().toString()) + getDouble(segundo.getText().toString());
                MostraResultado();
                Mensagem("onClick_btnSomar");
                //Gravar no LogCat uma informação para auxiliar
                //no processo de desenvolvimento e correção de bugs
                Log.i("Somar", "clicou no botão Somar");
            }
            catch (Exception e) {
                //Gravar no LogCat o erro ocorrido
                TrataErro(e, "Botão Somar");
            }
        }
    };

    OnClickListener onClick_btnSubtrair = new OnClickListener() {
        public void onClick(View v){
            try
            {
                result = Double.valueOf(primeiro.getText().toString()) - Double.valueOf(segundo.getText().toString());
                MostraResultado();
                Mensagem("onClick_btnSubtrair");
            }
            catch (Exception e) {
                TrataErro(e, "Botão Subtrair");
            }
        }
    };

    OnClickListener onClick_btnMultiplicar = new OnClickListener() {
        public void onClick(View v) {
            try
            {
                result = Double.valueOf(primeiro.getText().toString()) * Double.valueOf(segundo.getText().toString());
                MostraResultado();
                Mensagem("onClick_btnMultiplicar");
            }
            catch (Exception e) {
                TrataErro(e, "Botão Multiplicar");
            }
        }
    };

    OnClickListener onClick_btnDividir = new OnClickListener() {
        public void onClick(View v) {
            try
            {
                result = Double.valueOf(primeiro.getText().toString()) / Double.valueOf(segundo.getText().toString());
                MostraResultado();
                Mensagem("onClick_btnDividir");
            }
            catch (Exception e) {
                TrataErro(e, "Botão Dividir");
            }
        }
    };

    //
    // Desativando o botão voltar do aparelho
    //
    @Override
    public void onBackPressed() {
        return;
    }

    public void onClick_Sair(View v) {
        try
        {
            //
            // Criando uma caixa de diálogo para sair ou não do sistema
            //
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Você tem certeza que deseja sair?")
                    //informando o título da caixa de texto
                    .setTitle("Atenção:")
                    .setIcon(android.R.drawable.ic_menu_info_details)

                            //setCancelable = false
                            //desabilita o botão Voltar com isso obrigando o usuário
                            //a selecionar um dos botões do AlertDialog
                    .setCancelable(false)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        catch (Exception e) {
            Mensagem(e.getMessage());
        }
    }

    //
    // Apresentar o resultado do cálculo no objeto "resultado"
    // através do método setText
    //
    private void MostraResultado()
    {
        String strResult = NumberFormat.getInstance().format(result);
        resultado.setText(strResult);
    }

    //
    // Converter um valor String para Double
    //
    private Double getDouble(String pValue)
    {
        if (pValue == null || pValue.length() == 0 || pValue.equals("."))
            return Double.valueOf(0);
        else
            return Double.parseDouble(pValue);
    }

    //
    // Apresentar mensagem de erro ou informações ao usuário
    //
    private void Mensagem(String pMens)
    {
        Toast.makeText(this, pMens, Toast.LENGTH_SHORT).show();
    }

    private void TrataErro(Exception e, String pMens)
    {
        Mensagem(e.getMessage());
        Log.e(getString(R.string.app_name), pMens, e);
    }
}
