package fadergs.squadmaker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTeams extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "AppSquadMaker";

    public DBTeams(Context context){
        super(context,NOME,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS teams("+
                "idTeam INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"+
                "nameTeam TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS players("+
                "idPlayer INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"+
                "nameTeam TEXT,"+
                "IdTeam INTEGER,"+
                "numberShirt INTEGER,"+
                "FOREIGN KEY(IdTeam) REFERENCES team(idTeam));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
