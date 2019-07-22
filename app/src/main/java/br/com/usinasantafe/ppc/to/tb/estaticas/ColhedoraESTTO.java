package br.com.usinasantafe.ppc.to.tb.estaticas;

import br.com.usinasantafe.ppc.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcolhedoraest")
public class ColhedoraESTTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idColhedora;
    
    public ColhedoraESTTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdColhedora() {
		return idColhedora;
	}

	public void setIdColhedora(Long idColhedora) {
		this.idColhedora = idColhedora;
	}
	
}
