package br.com.usinasantafe.ppc;

import br.com.usinasantafe.ppc.control.ConfigCTR;
import br.com.usinasantafe.ppc.control.PerdaCTR;
import android.app.Application;

public class PPCContext extends Application {

	private ConfigCTR configCTR;
	private PerdaCTR perdaCTR;

	public static String versaoWS = "2.00";

	public PPCContext() {
	}

	public ConfigCTR getConfigCTR(){
		if (configCTR == null)
			configCTR = new ConfigCTR();
		return configCTR;
	}

	public PerdaCTR getPerdaCTR(){
		if (perdaCTR == null)
			perdaCTR = new PerdaCTR();
		return perdaCTR;
	}

	
}
