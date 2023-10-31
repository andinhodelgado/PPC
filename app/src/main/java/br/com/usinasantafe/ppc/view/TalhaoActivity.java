package br.com.usinasantafe.ppc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class TalhaoActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkTalhao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancTalhao = findViewById(R.id.buttonCancPadrao);

        buttonOkTalhao.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setNroTalhaoCabec(Long.parseLong(editTextPadrao.getText().toString()));
                Intent it = new Intent(TalhaoActivity.this, OSActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancTalhao.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);
        startActivity(it);
        finish();
    }

}
