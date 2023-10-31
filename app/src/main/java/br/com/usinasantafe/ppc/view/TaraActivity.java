package br.com.usinasantafe.ppc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class TaraActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tara);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkTara = findViewById(R.id.buttonOkPadrao);
        Button buttonCancTara = findViewById(R.id.buttonCancPadrao);

        buttonOkTara.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {
                String tara = editTextPadrao.getText().toString();
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setTaraAmostra(Double.valueOf(tara.replace(",", ".")));
            } else {
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setTaraAmostra(0D);
            }

            Intent it;
            if (ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getTipoColheitaCabec() == 1L) {
                it = new Intent(TaraActivity.this, ToleteActivity.class);
            } else {
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setToleteAmostra(0D);
                it = new Intent(TaraActivity.this, CanaInteiraActivity.class);
            }
            startActivity(it);
            finish();

        });

        buttonCancTara.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(TaraActivity.this, DetalhesCabecalhoActivity.class);
        startActivity(it);
        finish();
    }
}
