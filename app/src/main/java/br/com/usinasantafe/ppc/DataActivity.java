package br.com.usinasantafe.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class DataActivity extends ActivityGeneric {

    private DatePicker datePickerData;
    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        PPCContext = (PPCContext) getApplication();
        datePickerData = (DatePicker) findViewById(R.id.datePickerData);

        Button buttonOkData = (Button) findViewById(R.id.buttonOkData);
        Button buttonCancData = (Button) findViewById(R.id.buttonCancData);

        buttonOkData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int dia = datePickerData.getDayOfMonth();
                int mes = datePickerData.getMonth();
                int ano = datePickerData.getYear();

                String data = "" + dia + "/" + (mes + 1) + "/" + ano;

                PPCContext.getCabecalhoVARTO().setData(data);

                Intent it = new Intent(DataActivity.this, TurnoActivity.class);
                startActivity(it);

            }
        });

        buttonCancData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DataActivity.this, Auditor3Activity.class);
                startActivity(it);
            }
        });

    }
}
