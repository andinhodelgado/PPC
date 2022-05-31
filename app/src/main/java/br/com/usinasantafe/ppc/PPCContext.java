package br.com.usinasantafe.ppc;

import br.com.usinasantafe.ppc.to.tb.variaveis.AmostraVARTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;
import android.app.Application;

public class PPCContext extends Application {

	private CabecalhoVARTO cabecalhoVARTO;
	private AmostraVARTO amostraVARTO;
	private Long tipoCabecalho;
	private int tipoFinalizar;

	public static String versaoAPP = "1.01";
	public static String versaoWS = "1.01";

	public PPCContext() {
	}
	
	public CabecalhoVARTO getCabecalhoVARTO() {
		if(cabecalhoVARTO == null)
		cabecalhoVARTO = new CabecalhoVARTO();
		return cabecalhoVARTO;
	}

	public AmostraVARTO getAmostraVARTO() {
		if(amostraVARTO == null)
		amostraVARTO = new AmostraVARTO();
		return amostraVARTO;
	}

	public Long getTipoCabecalho() {
		return tipoCabecalho;
	}

	public void setTipoCabecalho(Long tipoCabecalho) {
		this.tipoCabecalho = tipoCabecalho;
	}

	public int getTipoFinalizar() {
		return tipoFinalizar;
	}

	public void setTipoFinalizar(int tipoFinalizar) {
		this.tipoFinalizar = tipoFinalizar;
	}
	
}
