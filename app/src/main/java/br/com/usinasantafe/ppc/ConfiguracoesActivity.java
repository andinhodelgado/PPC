package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.ppc.bo.ConexaoWeb;
import br.com.usinasantafe.ppc.bo.ManipDadosReceb;

public class ConfiguracoesActivity extends ActivityGeneric  {

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        Button btAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarDados);
        Button buttonRetConfig = (Button) findViewById(R.id.buttonRetConfig);

        btAtualBDConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfiguracoesActivity.this)){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    ManipDadosReceb.getInstance().atualizarBD(progressBar);
                    ManipDadosReceb.getInstance().setContext(ConfiguracoesActivity.this);

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracoesActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();
                }

            }
        });

        buttonRetConfig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ConfiguracoesActivity.this, PrincipalActivity.class);
                startActivity(it);
            }

        });

//        buttonTesteExcluirFoto.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//                try{
//
//                    File teste = Environment.getExternalStorageDirectory();
//                    File testeNovo = new File(teste + "/DCIM/Camera");
//                    byte[] bs = null;
//                    String[] url = {""};
//
//                    File dir = testeNovo;
//                    for(File arq : dir.listFiles()) {
//                        InputStream in = new FileInputStream(arq);
//                        bs = IOUtils.toBytes(in);
//                    }
//
//                    ConHttpMultipartPost conHttpMultipartPost = new ConHttpMultipartPost(bs);
//                    conHttpMultipartPost.execute(url);
//
//                }
//                catch(Exception exception){
//
//                }
//            }
//
//        });

    }
}
