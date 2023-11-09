package br.com.usinasantafe.ppc.util;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.ppc.control.PerdaCTR;
import br.com.usinasantafe.ppc.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.ppc.util.conHttp.UrlsConexaoHttp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class EnvioDadosServ {
	
	private static EnvioDadosServ instance = null;
	private UrlsConexaoHttp urlsConexaoHttp;
	private ProgressDialog progressDialog;
	private Context telaAtual;
	private Class telaProx;
	public static int status; //1 - Existe Dados para Enviar; 2 - Enviado; 3 - Todos os Dados Foram Enviados;

	public EnvioDadosServ() {
		urlsConexaoHttp = new UrlsConexaoHttp();
	}
	
    public static EnvioDadosServ getInstance() {
        if (instance == null){
        	instance = new EnvioDadosServ();
        }
        return instance;
    }

	public void envioDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog){

		this.telaAtual = telaAtual;
		this.telaProx = telaProx;
		this.progressDialog = progressDialog;

		enviarAnalise();

	}

	public void enviarAnalise() {
		PerdaCTR perdaCTR = new PerdaCTR();
		envio(urlsConexaoHttp.getsInserirAnalise(), perdaCTR.dadosAnaliseEnvio());
	}

	public void envio(String url, String dados){

		String[] strings = {url};
		Map<String, Object> parametrosPost = new HashMap<>();
		parametrosPost.put("dado", dados);

		Log.i("PMM", "URL = " + url + " - Dados de Envio = " + dados);
		PostCadGenerico postCadGenerico = new PostCadGenerico();
		postCadGenerico.setParametrosPost(parametrosPost);
		postCadGenerico.execute(strings);
	}

	public void recDados(String result){
		PerdaCTR perdaCTR = new PerdaCTR();
		perdaCTR.recDados(result);
		msgSucessoEnvio();
	}

	public void msgSucessoEnvio(){

		progressDialog.dismiss();

		AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
		alerta.setTitle("ATENCAO");
		alerta.setMessage("DADOS ENVIADO COM SUCESSO.");
		alerta.setPositiveButton("OK", (dialog, which) -> {
			Intent it = new Intent(telaAtual, telaProx);
			telaAtual.startActivity(it);
		});

		alerta.show();
	}

	public void msgFalhaEnvio() {

		progressDialog.dismiss();
		AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
		alerta.setTitle("ATENCAO");
		alerta.setMessage("FALHA NO ENVIO DE DADOS! POR FAVOR, TENTE REENVIAR NOVAMENTE OS DADOS.");
		alerta.setPositiveButton("OK", (dialog, which) -> {
			Intent it = new Intent(telaAtual, telaProx);
			telaAtual.startActivity(it);
		});

		alerta.show();

	}


}
