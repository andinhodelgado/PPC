package br.com.usinasantafe.ppc.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.ppc.EnvioDadosActivity;
import br.com.usinasantafe.ppc.ListaTipoApontActivity;
import br.com.usinasantafe.ppc.MsgFecharAnaliseActivity;
import br.com.usinasantafe.ppc.PonteiroActivity;
import br.com.usinasantafe.ppc.RepiqueActivity;
import br.com.usinasantafe.ppc.conWEB.ConHttpPostGenerico;
import br.com.usinasantafe.ppc.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.ppc.to.tb.variaveis.AmostraVARTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.j256.ormlite.field.DatabaseField;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

public class ManipDadosEnvio {
	
	private static ManipDadosEnvio instance = null;
	private Context context;
	private ProgressDialog progressDialog;
	private int tipoEnvio;
	
	public ManipDadosEnvio() {
	}
	
    public static ManipDadosEnvio getInstance() {
        if (instance == null){
        	instance = new ManipDadosEnvio();
        }
        return instance;
    }

	public void enviarDados(){
		
		CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();
		List listCabec = cabecalhoVARTO.get("status", 2L);
		
		JsonArray jsonArrayCabec = new JsonArray();
		JsonArray jsonArrayAmostra = new JsonArray();
		
		for(int i = 0; i < listCabec.size(); i++){
			
			cabecalhoVARTO = (CabecalhoVARTO) listCabec.get(i);
			Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecalhoVARTO, cabecalhoVARTO.getClass()));
			
            Log.i("ECM - Cabec", "id = " + cabecalhoVARTO.getId());
            Log.i("ECM - Cabec", "tipo = " + cabecalhoVARTO.getTipo());
            Log.i("ECM - Cabec", "auditor1 = " + cabecalhoVARTO.getAuditor1());
            Log.i("ECM - Cabec", "auditor2 = " + cabecalhoVARTO.getAuditor2());
            Log.i("ECM - Cabec", "auditor3 = " + cabecalhoVARTO.getAuditor3());
            Log.i("ECM - Cabec", "data = " + cabecalhoVARTO.getData());
            Log.i("ECM - Cabec", "dhEnvio = " + cabecalhoVARTO.getDhEnvio());
            Log.i("ECM - Cabec", "turno = " + cabecalhoVARTO.getTurno());
            Log.i("ECM - Cabec", "secao = " + cabecalhoVARTO.getSecao());
            Log.i("ECM - Cabec", "talhao = " + cabecalhoVARTO.getTalhao());
            Log.i("ECM - Cabec", "os = " + cabecalhoVARTO.getOs());
            Log.i("ECM - Cabec", "frente = " + cabecalhoVARTO.getFrente());
            Log.i("ECM - Cabec", "colhedora = " + cabecalhoVARTO.getColhedora());
            Log.i("ECM - Cabec", "operador = " + cabecalhoVARTO.getOperador());
            Log.i("ECM - Cabec", "status = " + cabecalhoVARTO.getStatus());
            
			AmostraVARTO amostraVARTO = new AmostraVARTO();
			List listaAmostra = amostraVARTO.get("idCabecalho", cabecalhoVARTO.getId());
			
			for(int j = 0; j < listaAmostra.size(); j++){
				
				amostraVARTO = (AmostraVARTO) listaAmostra.get(j);
				Gson gsonAmostra = new Gson();
				jsonArrayAmostra.add(gsonAmostra.toJsonTree(amostraVARTO, amostraVARTO.getClass()));
				
	            Log.i("ECM - Amostra", "id = " + amostraVARTO.getId());
	            Log.i("ECM - Amostra", "idCabec = " + amostraVARTO.getIdCabecalho());
	            Log.i("ECM - Amostra", "num = " + amostraVARTO.getNum());
	            Log.i("ECM - Amostra", "tara = " + amostraVARTO.getTara());
	            Log.i("ECM - Amostra", "tolete = " + amostraVARTO.getTolete());
	            Log.i("ECM - Amostra", "canaInteira = " + amostraVARTO.getCanaInteira());
	            Log.i("ECM - Amostra", "toco = " + amostraVARTO.getToco());
	            Log.i("ECM - Amostra", "pedaco = " + amostraVARTO.getPedaco());
	            Log.i("ECM - Amostra", "ponteiro = " + amostraVARTO.getPonteiro());
	            Log.i("ECM - Amostra", "lascas = " + amostraVARTO.getLascas());
	            Log.i("ECM - Amostra", "soqueiraKg = " + amostraVARTO.getSoqueiraKg());
	            Log.i("ECM - Amostra", "soqueiraNum = " + amostraVARTO.getSoqueiraNum());
	            Log.i("ECM - Amostra", "repique = " + amostraVARTO.getRepique());
	            Log.i("ECM - Amostra", "obsv = " + amostraVARTO.getObsv());
	            
			}
			
		}
		
		JsonObject jsonCabec = new JsonObject();
		jsonCabec.add("cabecalho", jsonArrayCabec);
		
		JsonObject jsonAmostra = new JsonObject();
		jsonAmostra.add("amostra", jsonArrayAmostra);
		
        String dados = jsonCabec.toString() + "_" + jsonAmostra.toString();

		UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsereAnaliseProd()};
		Map<String, Object> parametrosPost = new HashMap<String, Object>();
		parametrosPost.put("dado", dados);

		Log.i("ECM", "DADOS = " + dados);

		ConHttpPostGenerico conHttpPostGenerico = new ConHttpPostGenerico();
		conHttpPostGenerico.setParametrosPost(parametrosPost);
		conHttpPostGenerico.execute(url);
        
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
	
	public void respostaEnvio(boolean verif){
		
		String msg = "";
		
		if(verif){
			msg = "FOI ENVIADO COM SUCESSO.";
		}
		else{
			msg = "HOUVE FALHA NO ENVIO. REENVIE OS DADOS NOVAMENTE!";
		}
		
		progressDialog.dismiss();
		
		AlertDialog.Builder alerta = new AlertDialog.Builder(this.context);
		alerta.setTitle("ATENCAO");
		alerta.setMessage(msg);
		alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				if(tipoEnvio == 1){
					Intent it = new Intent(context, ListaTipoApontActivity.class);
					context.startActivity(it);
				}
				else if(tipoEnvio == 2){
					Intent it = new Intent(context, EnvioDadosActivity.class);
					context.startActivity(it);
				}
			}
		});
		
		alerta.show();
		
	}

	public void setContext(Context context, ProgressDialog progressDialog, int tipoEnvio){
		this.context = context;
		this.progressDialog = progressDialog;
		this.tipoEnvio = tipoEnvio;
	}

    
}
