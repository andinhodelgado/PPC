package br.com.usinasantafe.ppc.to.tb.estaticas;

import br.com.usinasantafe.ppc.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tboperadorest")
public class OperadorESTTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idOperador;
    
    public OperadorESTTO() {
		// TODO Auto-generated constructor stub
	}
    
	public Long getIdOperador() {
		return idOperador;
	}
	
	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}
    
}
