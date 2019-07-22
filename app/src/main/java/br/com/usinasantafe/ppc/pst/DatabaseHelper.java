package br.com.usinasantafe.ppc.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.usinasantafe.ppc.to.tb.estaticas.AuditorESTTO;
import br.com.usinasantafe.ppc.to.tb.estaticas.ColhedoraESTTO;
import br.com.usinasantafe.ppc.to.tb.estaticas.OSESTTO;
import br.com.usinasantafe.ppc.to.tb.estaticas.ObservacaoESTTO;
import br.com.usinasantafe.ppc.to.tb.estaticas.OperadorESTTO;
import br.com.usinasantafe.ppc.to.tb.estaticas.TipoAmostradorESTTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.AmostraVARTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		
		super(context, Database.FORCA_DB_NAME,
				null, Database.FORCA_BD_VERSION);
		// TODO Auto-generated constructor stub
		instance = this;
		
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		// TODO Auto-generated method stub
		try{
			
			TableUtils.createTable(cs, AuditorESTTO.class);
			TableUtils.createTable(cs, ColhedoraESTTO.class);
			TableUtils.createTable(cs, ObservacaoESTTO.class);
			TableUtils.createTable(cs, OperadorESTTO.class);
			TableUtils.createTable(cs, TipoAmostradorESTTO.class);
			TableUtils.createTable(cs, OSESTTO.class);
			
			TableUtils.createTable(cs, CabecalhoVARTO.class);
			TableUtils.createTable(cs, AmostraVARTO.class);
			
		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		// TODO Auto-generated method stub
		try {
			if(oldVersion == 1 && newVersion == 2){
				//TableUtils.createTable(cs, ConfiguracaoTO.class);
				oldVersion = 2;
			}
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
