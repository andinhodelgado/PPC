package br.com.usinasantafe.ppc.to.tb.estaticas;

import br.com.usinasantafe.ppc.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbauditorest")
public class AuditorESTTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long codAuditor;
	
	public AuditorESTTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getCodAuditor() {
		return codAuditor;
	}

	public void setCodAuditor(Long codAuditor) {
		this.codAuditor = codAuditor;
	}
	
}
