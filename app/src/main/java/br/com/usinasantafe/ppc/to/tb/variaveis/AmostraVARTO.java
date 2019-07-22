package br.com.usinasantafe.ppc.to.tb.variaveis;

import br.com.usinasantafe.ppc.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbamostravar")
public class AmostraVARTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long id;
	@DatabaseField
    private Long idCabecalho;
	@DatabaseField
	private Long num;
	@DatabaseField
    private Double tara;
	@DatabaseField
    private Double tolete;
	@DatabaseField
    private Double canaInteira;
	@DatabaseField
    private Double toco;
	@DatabaseField
    private Double pedaco;
	@DatabaseField
    private Double ponteiro;
	@DatabaseField
    private Double lascas;
	@DatabaseField
    private Double soqueiraKg;
	@DatabaseField
    private Double soqueiraNum;
	@DatabaseField
    private Double repique;
	@DatabaseField
    private String obsv;
	
    public AmostraVARTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCabecalho() {
		return idCabecalho;
	}

	public void setIdCabecalho(Long idCabecalho) {
		this.idCabecalho = idCabecalho;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
	public Double getTara() {
		return tara;
	}

	public void setTara(Double tara) {
		this.tara = tara;
	}

	public Double getTolete() {
		return tolete;
	}

	public void setTolete(Double tolete) {
		this.tolete = tolete;
	}

	public Double getCanaInteira() {
		return canaInteira;
	}

	public void setCanaInteira(Double canaInteira) {
		this.canaInteira = canaInteira;
	}

	public Double getToco() {
		return toco;
	}

	public void setToco(Double toco) {
		this.toco = toco;
	}

	public Double getPedaco() {
		return pedaco;
	}

	public void setPedaco(Double pedaco) {
		this.pedaco = pedaco;
	}

	public Double getPonteiro() {
		return ponteiro;
	}

	public void setPonteiro(Double ponteiro) {
		this.ponteiro = ponteiro;
	}

	public Double getLascas() {
		return lascas;
	}

	public void setLascas(Double lascas) {
		this.lascas = lascas;
	}

	public Double getSoqueiraKg() {
		return soqueiraKg;
	}

	public void setSoqueiraKg(Double soqueiraKg) {
		this.soqueiraKg = soqueiraKg;
	}

	public Double getSoqueiraNum() {
		return soqueiraNum;
	}

	public void setSoqueiraNum(Double soqueiraNum) {
		this.soqueiraNum = soqueiraNum;
	}

	public Double getRepique() {
		return repique;
	}

	public void setRepique(Double repique) {
		this.repique = repique;
	}

	public String getObsv() {
		return obsv;
	}

	public void setObsv(String obsv) {
		this.obsv = obsv;
	}
	
}
