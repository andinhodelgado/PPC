package br.com.usinasantafe.ppc.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.ppc.model.bean.estaticas.AuditorBean;
import br.com.usinasantafe.ppc.model.bean.estaticas.ColhedoraBean;
import br.com.usinasantafe.ppc.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ppc.model.bean.estaticas.OperadorBean;
import br.com.usinasantafe.ppc.model.bean.estaticas.TipoAmostradorBean;
import br.com.usinasantafe.ppc.model.bean.variaveis.AmostraBean;
import br.com.usinasantafe.ppc.model.bean.variaveis.CabecalhoBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "ppc_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);
		instance = this;
	}
	
	@Override
	public void close() {
		super.close();
		instance = null;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		try {
			
			TableUtils.createTable(cs, AuditorBean.class);
			TableUtils.createTable(cs, ColhedoraBean.class);
			TableUtils.createTable(cs, OperadorBean.class);
			TableUtils.createTable(cs, TipoAmostradorBean.class);
			TableUtils.createTable(cs, OSBean.class);
			
			TableUtils.createTable(cs, CabecalhoBean.class);
			TableUtils.createTable(cs, AmostraBean.class);
			
		} catch(Exception e) {
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
