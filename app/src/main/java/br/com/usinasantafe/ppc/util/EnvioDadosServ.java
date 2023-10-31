package br.com.usinasantafe.ppc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.ppc.control.PerdaCTR;
import br.com.usinasantafe.ppc.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.ppc.util.conHttp.UrlsConexaoHttp;
import br.com.usinasantafe.ppc.view.EnvioDadosActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class EnvioDadosServ {
	
	private static EnvioDadosServ instance = null;
	private UrlsConexaoHttp urlsConexaoHttp;
	private Context context;
	private ProgressDialog progressDialog;
	private int tipoEnvio;
	public static int status; //1 - Existe Dados para Enviar; 2 - Enviado; 3 - Todos os Dados Foram Enviados;

	public EnvioDadosServ() {
	}
	
    public static EnvioDadosServ getInstance() {
        if (instance == null){
        	instance = new EnvioDadosServ();
        }
        return instance;
    }

	public void envioDados(){

		if (verifAnaliseEnvio()) {
			enviarAnalise();
		} else {
			status = 3;
		}

        
	}

	public Boolean verifAnaliseEnvio() {
		PerdaCTR perdaCTR = new PerdaCTR();
		return perdaCTR.verifCabecFechado();
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

	public void deletarAnalise(){
		
		CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();
		List listCabec = cabecalhoVARTO.get("status", 2L);
		
		for(int i = 0; i < listCabec.size(); i++){
			
			cabecalhoVARTO = (CabecalhoVARTO) listCabec.get(i);
			AmostraVARTO amostraVARTO = new AmostraVARTO();
			List listaAmostra = amostraVARTO.get("idCabecalho", cabecalhoVARTO.getId());
			
			for(int j = 0; j < listaAmostra.size(); j++){
				amostraVARTO = (AmostraVARTO) listaAmostra.get(j);
				amostraVARTO.delete();
			}
			
			cabecalhoVARTO.delete();
			
		}
		
		respostaEnvio(true);
		
	}

    
}
