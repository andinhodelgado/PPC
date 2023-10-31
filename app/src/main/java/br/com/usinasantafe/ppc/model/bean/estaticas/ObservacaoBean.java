package br.com.usinasantafe.ppc.model.bean.estaticas;

import br.com.usinasantafe.ppc.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbobservacaoest")
public class ObservacaoBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idObservacao;
	@DatabaseField
    private String descObservacao;
	
	public ObservacaoBean() {
	}

	public Long getIdObservacao() {
		return idObservacao;
	}

	public void setIdObservacao(Long idObservacao) {
		this.idObservacao = idObservacao;
	}

	public String getDescObservacao() {
		return descObservacao;
	}

	public void setDescObservacao(String descObservacao) {
		this.descObservacao = descObservacao;
	}
	
}
