package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class CanaInteiraActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cana_inteira);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkCanaInteira = findViewById(R.id.buttonOkPadrao);
        Button buttonCancCanaInteira = findViewById(R.id.buttonCancPadrao);

        buttonOkCanaInteira.setOnClickListener(v -> {

            if(!editTextPadrao.getText().toString().equals("")){

                String valor = editTextPadrao.getText().toString();
                Double valorDouble = Double.valueOf(valor.replace(",", "."));

                if(ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().getTaraAmostra() < valorDouble){

                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setCanaInteiraAmostra(valorDouble);
                    Intent it = new Intent(CanaInteiraActivity.this, TocoActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(CanaInteiraActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("O PESO ESTA ABAIXO DO PESO TARA. POR FAVOR DIGITE NOVAMENTE O PESO.");

                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();

                }

            } else {

                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setCanaInteiraAmostra(0D);
                Intent it = new Intent(CanaInteiraActivity.this, TocoActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonCancCanaInteira.setOnClickListener(v -> {
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it;
        if (ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getTipoColheitaCabec() == 1L) {
            it = new Intent(CanaInteiraActivity.this, ToleteActivity.class);
        } else {
            it = new Intent(CanaInteiraActivity.this, TaraActivity.class);
        }
        startActivity(it);
        finish();
    }
}
