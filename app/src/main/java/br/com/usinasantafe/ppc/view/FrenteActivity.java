package br.com.usinasantafe.ppc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class FrenteActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frente);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkFrente = findViewById(R.id.buttonOkPadrao);
        Button buttonCancFrente = findViewById(R.id.buttonCancPadrao);

        buttonOkFrente.setOnClickListener(v -> {

            if(!editTextPadrao.getText().toString().equals("")){

                int valor = Integer.valueOf(editTextPadrao.getText().toString());

                if(valor > 0){

                    ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setCodFrenteCabec(Long.parseLong(editTextPadrao.getText().toString()));

                    if(ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getTipoColheitaCabec() == 1L){

                        Intent it = new Intent(FrenteActivity.this, ColhedoraActivity.class);
                        startActivity(it);
                        finish();

                    } else if(ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getTipoColheitaCabec() == 2L){

                        ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setCodColhedoraCabec(0L);
                        ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setMatricOperadorCabec(0L);
                        ppcContext.getPerdaCTR().getCabecDAO().salvarCabecAberto();

                        Intent it = new Intent(FrenteActivity.this, ListaCabecalhoActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }

        });

        buttonCancFrente.setOnClickListener(v -> {
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(FrenteActivity.this, OSActivity.class);
        startActivity(it);
        finish();
    }
}
