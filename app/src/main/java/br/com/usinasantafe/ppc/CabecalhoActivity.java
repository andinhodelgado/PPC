package br.com.usinasantafe.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ppc.to.tb.variaveis.AmostraVARTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;

public class CabecalhoActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabecalho);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkCabecalho = (Button) findViewById(R.id.buttonOkMsgCabecalho);
        Button buttonCancCabecalho = (Button) findViewById(R.id.buttonCancMsgCabecalho);

        TextView textViewCabecalho = (TextView) findViewById(R.id.textViewCabecalho);

        CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();
        List listaCabecPesq = cabecalhoVARTO.get("id", PPCContext.getAmostraVARTO().getIdCabecalho());
        cabecalhoVARTO = (CabecalhoVARTO) listaCabecPesq.get(0);

        AmostraVARTO amostraVARTO = new AmostraVARTO();
        List listAmostra = amostraVARTO.get("idCabecalho", PPCContext.getAmostraVARTO().getIdCabecalho());

        String amostra = "";

        if(listAmostra.size() == 0){
            amostra = "NÃO CONTÉM AMOSTRAS.";
        }
        else{
            amostra = "CONTÉM " + listAmostra.size() + " AMOSTRA(S).";
        }

        String msgCabecalho = "TURNO: " + cabecalhoVARTO.getTurno() + " \n" +
                "FRENTE: " + cabecalhoVARTO.getFrente() + " \n" +
                "COLHEDORA: " + cabecalhoVARTO.getColhedora() + " \n" +
                "MOTORISTA:  " + cabecalhoVARTO.getOperador() + " \n" +
                amostra;

        textViewCabecalho.setText(msgCabecalho);

        buttonOkCabecalho.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AmostraVARTO amostraVARTO = new AmostraVARTO();
                List listaAmostra = amostraVARTO.get("idCabecalho", PPCContext.getAmostraVARTO().getIdCabecalho());

                if(listaAmostra.size() < 4){
                    Intent it = new Intent(CabecalhoActivity.this, TaraActivity.class);
                    startActivity(it);
                }
                else{
                    Intent it = new Intent(CabecalhoActivity.this, MsgFecharAnaliseActivity.class);
                    startActivity(it);
                }

            }
        });

        buttonCancCabecalho.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(CabecalhoActivity.this, ListaCabecAmostraActivity.class);
                startActivity(it);
            }
        });

    }
}
