package br.com.usinasantafe.ppc.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import br.com.usinasantafe.ppc.R;

public class CameraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {

                Bitmap bitmap = (Bitmap) bundle.get("data");
                ImageView img = findViewById(R.id.imagem);
                img.setImageBitmap(bitmap);

            }
        }

    }

}
