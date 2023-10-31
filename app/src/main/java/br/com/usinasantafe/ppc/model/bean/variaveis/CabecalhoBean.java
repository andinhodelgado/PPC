package br.com.usinasantafe.ppc.model.bean.variaveis;

import br.com.usinasantafe.ppc.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcabecalhovar")
public class CabecalhoBean extends Entidade {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
    private Long idCabec;
	@DatabaseField
    private Long tipoColheitaCabec;
	@DatabaseField
    private Long matricAuditor1Cabec;
	@DatabaseField
    private Long matricAuditor2Cabec;
	@DatabaseField
    private Long matricAuditor3Cabec;
	@DatabaseField
    private String dthrCabec;
	@DatabaseField
    private String dthrEnvioCabec;
	@DatabaseField
    private Long nroTurnoCabec;
	@DatabaseField
    private Long codSecaoCabec;
	@DatabaseField
    private Long nroTalhaoCabec;
	@DatabaseField
    private Long nroOSCabec;
	@DatabaseField
    private Long codFrenteCabec;
	@DatabaseField
    private Long codColhedoraCabec;
	@DatabaseField
    private Long matricOperadorCabec;
	@DatabaseField
	private Long statusCabec; // 1 - ABERTA; 2 - FECHADA; 3 - ENVIADO
	
    public CabecalhoBean() {
	}

	public Long getIdCabec() {
		return idCabec;
	}

	public void setIdCabec(Long idCabec) {
		this.idCabec = idCabec;
	}

	public Long getTipoColheitaCabec() {
		return tipoColheitaCabec;
	}

	public void setTipoColheitaCabec(Long tipoColheitaCabec) {
		this.tipoColheitaCabec = tipoColheitaCabec;
	}

	public Long getMatricAuditor1Cabec() {
		return matricAuditor1Cabec;
	}

	public void setMatricAuditor1Cabec(Long matricAuditor1Cabec) {
		this.matricAuditor1Cabec = matricAuditor1Cabec;
	}

	public Long getMatricAuditor2Cabec() {
		return matricAuditor2Cabec;
	}

	public void setMatricAuditor2Cabec(Long matricAuditor2Cabec) {
		this.matricAuditor2Cabec = matricAuditor2Cabec;
	}

	public Long getMatricAuditor3Cabec() {
		return matricAuditor3Cabec;
	}

	public void setMatricAuditor3Cabec(Long matricAuditor3Cabec) {
		this.matricAuditor3Cabec = matricAuditor3Cabec;
	}

	public String getDthrCabec() {
		return dthrCabec;
	}

	public void setDthrCabec(String dthrCabec) {
		this.dthrCabec = dthrCabec;
	}

	public String getDthrEnvioCabec() {
		return dthrEnvioCabec;
	}

	public void setDthrEnvioCabec(String dthrEnvioCabec) {
		this.dthrEnvioCabec = dthrEnvioCabec;
	}

	public Long getNroTurnoCabec() {
		return nroTurnoCabec;
	}

	public void setNroTurnoCabec(Long nroTurnoCabec) {
		this.nroTurnoCabec = nroTurnoCabec;
	}

	public Long getCodSecaoCabec() {
		return codSecaoCabec;
	}

	public void setCodSecaoCabec(Long codSecaoCabec) {
		this.codSecaoCabec = codSecaoCabec;
	}

	public Long getNroTalhaoCabec() {
		return nroTalhaoCabec;
	}

	public void setNroTalhaoCabec(Long nroTalhaoCabec) {
		this.nroTalhaoCabec = nroTalhaoCabec;
	}

	public Long getNroOSCabec() {
		return nroOSCabec;
	}

	public void setNroOSCabec(Long nroOSCabec) {
		this.nroOSCabec = nroOSCabec;
	}

	public Long getCodFrenteCabec() {
		return codFrenteCabec;
	}

	public void setCodFrenteCabec(Long codFrenteCabec) {
		this.codFrenteCabec = codFrenteCabec;
	}

	public Long getCodColhedoraCabec() {
		return codColhedoraCabec;
	}

	public void setCodColhedoraCabec(Long codColhedoraCabec) {
		this.codColhedoraCabec = codColhedoraCabec;
	}

	public Long getMatricOperadorCabec() {
		return matricOperadorCabec;
	}

	public void setMatricOperadorCabec(Long matricOperadorCabec) {
		this.matricOperadorCabec = matricOperadorCabec;
	}

	public Long getStatusCabec() {
		return statusCabec;
	}

	public void setStatusCabec(Long statusCabec) {
		this.statusCabec = statusCabec;
	}
	
}
