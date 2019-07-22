package br.com.usinasantafe.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecaoActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secao);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkSecao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancSecao = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkSecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    PPCContext.getCabecalhoVARTO().setSecao(Long.parseLong(editTextPadrao.getText().toString()));
                    Intent it = new Intent(SecaoActivity.this, TalhaoActivity.class);
                    startActivity(it);

                }

            }
        });

        buttonCancSecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(SecaoActivity.this, TurnoActivity.class);
                    startActivity(it);
                }

            }
        });

    }
}
