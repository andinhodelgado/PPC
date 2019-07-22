package br.com.usinasantafe.ppc.conWEB;

public class UrlsConexaoHttp {

	private int tipoEnvio = 1;

	public static String urlPrincipal = "http://www.usinasantafe.com.br/cpc/";
	public static String urlPrincEnvio = "http://www.usinasantafe.com.br/cpc/";
	
	public static String localPSTVariavel = "br.com.usinasantafe.ppc.to.tb.variaveis.";
	public static String localPSTEstatica = "br.com.usinasantafe.ppc.to.tb.estaticas.";
	public static String localUrl = "br.com.usinasantafe.ppc.conWEB.UrlsConexaoHttp";
	
	public static String AuditorESTTO = urlPrincipal + "auditor.php";
	public static String ColhedoraESTTO = urlPrincipal + "colhedora.php";
	public static String ObservacaoESTTO = urlPrincipal + "observacao.php";
	public static String OperadorESTTO = urlPrincipal + "operador.php";
	public static String TipoAmostradorESTTO = urlPrincipal + "tipoamostrador.php";
	public static String OSESTTO = urlPrincipal + "os.php";

	public UrlsConexaoHttp() {
		// TODO Auto-generated constructor stub
	}

	public String getsInsereAnaliseProd(){
		if(tipoEnvio == 1)
		return urlPrincEnvio + "apontperda.php";
		else
		return urlPrincEnvio + "apontperda.php";
	}
	
}