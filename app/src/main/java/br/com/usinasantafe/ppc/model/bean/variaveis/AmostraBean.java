package br.com.usinasantafe.ppc.model.bean.variaveis;

import br.com.usinasantafe.ppc.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbamostravar")
public class AmostraBean extends Entidade {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idAmostra;
	@DatabaseField
    private Long idCabecAmostra;
	@DatabaseField
    private Double taraAmostra;
	@DatabaseField
    private Double toleteAmostra;
	@DatabaseField
    private Double canaInteiraAmostra;
	@DatabaseField
    private Double tocoAmostra;
	@DatabaseField
    private Double pedacoAmostra;
	@DatabaseField
    private Double ponteiroAmostra;
	@DatabaseField
    private Double lascasAmostra;
	@DatabaseField
    private Double repiqueAmostra;
	@DatabaseField
	private Long pedraAmostra;
	@DatabaseField
	private Long tocoArvoreAmostra;
	@DatabaseField
	private Long plantaDaninhasAmostra;
	@DatabaseField
	private Long formigueiroAmostra;
	
    public AmostraBean() {
	}

	public Long getIdAmostra() {
		return idAmostra;
	}

	public void setIdAmostra(Long idAmostra) {
		this.idAmostra = idAmostra;
	}

	public Long getIdCabecAmostra() {
		return idCabecAmostra;
	}

	public void setIdCabecAmostra(Long idCabecAmostra) {
		this.idCabecAmostra = idCabecAmostra;
	}

	public Double getTaraAmostra() {
		return taraAmostra;
	}

	public void setTaraAmostra(Double taraAmostra) {
		this.taraAmostra = taraAmostra;
	}

	public Double getToleteAmostra() {
		return toleteAmostra;
	}

	public void setToleteAmostra(Double toleteAmostra) {
		this.toleteAmostra = toleteAmostra;
	}

	public Double getCanaInteiraAmostra() {
		return canaInteiraAmostra;
	}

	public void setCanaInteiraAmostra(Double canaInteiraAmostra) {
		this.canaInteiraAmostra = canaInteiraAmostra;
	}

	public Double getTocoAmostra() {
		return tocoAmostra;
	}

	public void setTocoAmostra(Double tocoAmostra) {
		this.tocoAmostra = tocoAmostra;
	}

	public Double getPedacoAmostra() {
		return pedacoAmostra;
	}

	public void setPedacoAmostra(Double pedacoAmostra) {
		this.pedacoAmostra = pedacoAmostra;
	}

	public Double getPonteiroAmostra() {
		return ponteiroAmostra;
	}

	public void setPonteiroAmostra(Double ponteiroAmostra) {
		this.ponteiroAmostra = ponteiroAmostra;
	}

	public Double getLascasAmostra() {
		return lascasAmostra;
	}

	public void setLascasAmostra(Double lascasAmostra) {
		this.lascasAmostra = lascasAmostra;
	}

	public Double getRepiqueAmostra() {
		return repiqueAmostra;
	}

	public void setRepiqueAmostra(Double repiqueAmostra) {
		this.repiqueAmostra = repiqueAmostra;
	}

	public Long getPedraAmostra() {
		return pedraAmostra;
	}

	public void setPedraAmostra(Long pedraAmostra) {
		this.pedraAmostra = pedraAmostra;
	}

	public Long getTocoArvoreAmostra() {
		return tocoArvoreAmostra;
	}

	public void setTocoArvoreAmostra(Long tocoArvoreAmostra) {
		this.tocoArvoreAmostra = tocoArvoreAmostra;
	}

	public Long getPlantaDaninhasAmostra() {
		return plantaDaninhasAmostra;
	}

	public void setPlantaDaninhasAmostra(Long plantaDaninhasAmostra) {
		this.plantaDaninhasAmostra = plantaDaninhasAmostra;
	}

	public Long getFormigueiroAmostra() {
		return formigueiroAmostra;
	}

	public void setFormigueiroAmostra(Long formigueiroAmostra) {
		this.formigueiroAmostra = formigueiroAmostra;
	}
}
