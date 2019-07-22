package br.com.usinasantafe.ppc.to.tb.variaveis;

import br.com.usinasantafe.ppc.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcabecalhovar")
public class CabecalhoVARTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long id;
	@DatabaseField
    private Long tipo;
	@DatabaseField
    private Long auditor1;
	@DatabaseField
    private Long auditor2;
	@DatabaseField
    private Long auditor3;
	@DatabaseField
    private String data;
	@DatabaseField
    private String dhEnvio;
	@DatabaseField
    private Long turno;
	@DatabaseField
    private Long secao;
	@DatabaseField
    private Long talhao;
	@DatabaseField
    private Long os;
	@DatabaseField
    private Long frente;
	@DatabaseField
    private Long colhedora;
	@DatabaseField
    private Long operador;
	@DatabaseField
	private Long status;
	
    public CabecalhoVARTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public Long getAuditor1() {
		return auditor1;
	}

	public void setAuditor1(Long auditor1) {
		this.auditor1 = auditor1;
	}

	public Long getAuditor2() {
		return auditor2;
	}

	public void setAuditor2(Long auditor2) {
		this.auditor2 = auditor2;
	}

	public Long getAuditor3() {
		return auditor3;
	}

	public void setAuditor3(Long auditor3) {
		this.auditor3 = auditor3;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDhEnvio() {
		return dhEnvio;
	}

	public void setDhEnvio(String dhEnvio) {
		this.dhEnvio = dhEnvio;
	}

	public Long getTurno() {
		return turno;
	}

	public void setTurno(Long turno) {
		this.turno = turno;
	}

	public Long getSecao() {
		return secao;
	}

	public void setSecao(Long secao) {
		this.secao = secao;
	}

	public Long getTalhao() {
		return talhao;
	}

	public void setTalhao(Long talhao) {
		this.talhao = talhao;
	}

	public Long getOs() {
		return os;
	}

	public void setOs(Long os) {
		this.os = os;
	}

	public Long getFrente() {
		return frente;
	}

	public void setFrente(Long frente) {
		this.frente = frente;
	}

	public Long getColhedora() {
		return colhedora;
	}

	public void setColhedora(Long colhedora) {
		this.colhedora = colhedora;
	}

	public Long getOperador() {
		return operador;
	}

	public void setOperador(Long operador) {
		this.operador = operador;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
}
