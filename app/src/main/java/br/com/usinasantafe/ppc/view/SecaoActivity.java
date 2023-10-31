package br.com.usinasantafe.ppc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class SecaoActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkSecao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancSecao = findViewById(R.id.buttonCancPadrao);

        buttonOkSecao.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setCodSecaoCabec(Long.parseLong(editTextPadrao.getText().toString()));
                Intent it = new Intent(SecaoActivity.this, TalhaoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancSecao.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(SecaoActivity.this, ListaTurnoActivity.class);
        startActivity(it);
        finish();
    }

}
