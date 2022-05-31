package br.com.usinasantafe.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TalhaoActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkTalhao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancTalhao = findViewById(R.id.buttonCancPadrao);

        buttonOkTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    PPCContext.getCabecalhoVARTO().setTalhao(Long.parseLong(editTextPadrao.getText().toString()));
                    Intent it = new Intent(TalhaoActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(TalhaoActivity.this, SecaoActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }
}
