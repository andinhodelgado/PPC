package br.com.usinasantafe.ppc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class DataActivity extends ActivityGeneric {

    private DatePicker datePickerData;
    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        ppcContext = (PPCContext) getApplication();
        datePickerData = findViewById(R.id.datePickerData);

        Button buttonOkData = findViewById(R.id.buttonOkData);
        Button buttonCancData = findViewById(R.id.buttonCancData);

        buttonOkData.setOnClickListener(v -> {

            int dia = datePickerData.getDayOfMonth();
            int mes = datePickerData.getMonth();
            int ano = datePickerData.getYear();

            String data = "" + dia + "/" + (mes + 1) + "/" + ano;

            ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setDthrCabec(data);
            Intent it = new Intent(DataActivity.this, ListaTurnoActivity.class);
            startActivity(it);
            finish();

        });

        buttonCancData.setOnClickListener(v -> {
            Intent it;
            if(ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getMatricAuditor2Cabec() == 0L){
                it = new Intent(DataActivity.this, Auditor2Activity.class);
            } else {
                it = new Intent(DataActivity.this, Auditor3Activity.class);
            }
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}
