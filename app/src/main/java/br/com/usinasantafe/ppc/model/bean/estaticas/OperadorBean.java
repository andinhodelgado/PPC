package br.com.usinasantafe.ppc.model.bean.estaticas;

import br.com.usinasantafe.ppc.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tboperadorest")
public class OperadorBean extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long matricOperador;
    
    public OperadorBean() {
	}
    
	public Long getMatricOperador() {
		return matricOperador;
	}
	
	public void setMatricOperador(Long matricOperador) {
		this.matricOperador = matricOperador;
	}
    
}
