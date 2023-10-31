package br.com.usinasantafe.ppc.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppc.model.pst.Entidade;

@DatabaseTable(tableName="tbauditorest")
public class AuditorBean extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long matricAuditor;
	
	public AuditorBean() {
	}

	public Long getMatricAuditor() {
		return matricAuditor;
	}

	public void setMatricAuditor(Long matricAuditor) {
		this.matricAuditor = matricAuditor;
	}
	
}
