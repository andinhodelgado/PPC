package br.com.usinasantafe.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;

public class FrenteActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frente);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkFrente = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancFrente = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    int valor = Integer.valueOf(editTextPadrao.getText().toString());

                    if(valor > 0){

                        if(PPCContext.getCabecalhoVARTO().getTipo() == 1L){

                            PPCContext.getCabecalhoVARTO().setFrente(Long.parseLong(editTextPadrao.getText().toString()));
                            Intent it = new Intent(FrenteActivity.this, ColhedoraActivity.class);
                            startActivity(it);

                        }
                        else if(PPCContext.getCabecalhoVARTO().getTipo() == 2L){

                            PPCContext.getCabecalhoVARTO().setFrente(Long.parseLong(editTextPadrao.getText().toString()));
                            PPCContext.getCabecalhoVARTO().setColhedora(0L);
                            PPCContext.getCabecalhoVARTO().setOperador(0L);

                            CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();

                            if(!cabecalhoVARTO.hasElements()){
                                PPCContext.getCabecalhoVARTO().setId((long) 1);
                            }
                            else{
                                List listCabec = cabecalhoVARTO.orderBy("id", false);
                                if(listCabec.size() == 0){
                                    PPCContext.getCabecalhoVARTO().setId((long) 1);
                                }
                                else{
                                    cabecalhoVARTO = (CabecalhoVARTO) listCabec.get(0);
                                    Long codigo = cabecalhoVARTO.getId() + 1;
                                    PPCContext.getCabecalhoVARTO().setId(codigo);
                                }
                            }

                            PPCContext.getCabecalhoVARTO().setStatus(1L);
                            PPCContext.getCabecalhoVARTO().insert();

                            Intent it = new Intent(FrenteActivity.this, ListaTipoApontActivity.class);
                            startActivity(it);

                        }

                    }

                }

            }
        });

        buttonCancFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(FrenteActivity.this, OSActivity.class);
                    startActivity(it);
                }

            }
        });

    }
}
