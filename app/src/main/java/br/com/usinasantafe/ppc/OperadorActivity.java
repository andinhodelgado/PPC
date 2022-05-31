package br.com.usinasantafe.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ppc.to.tb.estaticas.OperadorESTTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;

public class OperadorActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operador);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkOperador= findViewById(R.id.buttonOkPadrao);
        Button buttonCancOperador = findViewById(R.id.buttonCancPadrao);

        buttonOkOperador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long idOperador = Long.parseLong(editTextPadrao.getText().toString());

                if (editTextPadrao.getText().toString().length() > 0) {

                    OperadorESTTO operadorESTTO = new OperadorESTTO();
                    List operadorESTTOPesq = operadorESTTO.get("idOperador", idOperador);

                    if (operadorESTTOPesq.size() > 0) {

                        PPCContext.getCabecalhoVARTO().setOperador(idOperador);
                        CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();

                        if (!cabecalhoVARTO.hasElements()) {
                            PPCContext.getCabecalhoVARTO().setId((long) 1);
                        } else {
                            List listCabec = cabecalhoVARTO.orderBy("id", false);
                            if (listCabec.size() == 0) {
                                PPCContext.getCabecalhoVARTO().setId((long) 1);
                            } else {
                                cabecalhoVARTO = (CabecalhoVARTO) listCabec.get(0);
                                Long codigo = cabecalhoVARTO.getId() + 1;
                                PPCContext.getCabecalhoVARTO().setId(codigo);
                            }
                        }

                        PPCContext.getCabecalhoVARTO().setStatus(1L);
                        PPCContext.getCabecalhoVARTO().insert();

                        Intent it = new Intent(OperadorActivity.this, ListaTipoApontActivity.class);
                        startActivity(it);
                        finish();

                    }


                }

            }

        });


        buttonCancOperador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(OperadorActivity.this, ColhedoraActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }
}
