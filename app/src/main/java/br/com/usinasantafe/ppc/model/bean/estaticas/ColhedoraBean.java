package br.com.usinasantafe.ppc.model.bean.estaticas;

import br.com.usinasantafe.ppc.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcolhedoraest")
public class ColhedoraBean extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long nroColhedora;
    
    public ColhedoraBean() {
	}

	public Long getNroColhedora() {
		return nroColhedora;
	}

	public void setNroColhedora(Long nroColhedora) {
		this.nroColhedora = nroColhedora;
	}
	
}
