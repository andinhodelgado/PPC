package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class PedacoActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedaco);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkPedaco = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPedaco = findViewById(R.id.buttonCancPadrao);

        buttonOkPedaco.setOnClickListener(v -> {

            if(!editTextPadrao.getText().toString().equals("")){

                String valor = editTextPadrao.getText().toString();
                Double valorDouble = Double.valueOf(valor.replace(",", "."));

                if(ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().getTaraAmostra() < valorDouble){
                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPedacoAmostra(valorDouble);
                    proxTela();
                } else {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(PedacoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("O PESO ESTA ABAIXO DO PESO TARA. POR FAVOR DIGITE NOVAMENTE O PESO.");
                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();
                }

            } else {
                proxTela();
            }

        });

        buttonCancPedaco.setOnClickListener(v -> {
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    private void proxTela(){
        Intent it;
        ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setRepiqueAmostra(0D);
        if (ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getTipoColheitaCabec() == 1L) {
            ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPedacoAmostra(0D);
            it = new Intent(PedacoActivity.this, PonteiroActivity.class);
        } else {
            it = new Intent(PedacoActivity.this, RepiqueActivity.class);
        }
        startActivity(it);
        finish();
    }

    public void onBackPressed() {
        Intent it = new Intent(PedacoActivity.this, TocoActivity.class);
        startActivity(it);
        finish();
    }
}
