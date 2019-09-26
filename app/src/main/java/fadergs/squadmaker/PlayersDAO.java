package fadergs.squadmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlayersDAO {

        public static void insertPlay(Context contexto, Players players){

            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nameTeams",players.getIdPlayer());
            values.put("idTeam",players.getIdTeam());
            db.insert("namePlayer",null,values);
        }

        public static void editPlayers(Context contexto , Players players){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("namePlayer",players.getName());
            values.put("idTeam",players.getIdTeam());
            db.update("namePlayer",values,"idPlayer="+players.getIdPlayer(),null);
        }


        public static void exclud(Context contexto , int idPlayer){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();
            db.delete("namePlayer","idPlayer="+idPlayer,null);


        }

        public static List<Players> getPlay(Context contexto){

            List<Players> listPlayers = new ArrayList<>();

            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM Players ORDER BY namePlayer",null);

            if(cursor.getCount()>0){
                cursor.moveToFirst();

                do {
                    Players j = new Players();
                    j.setIdPlayer(cursor.getInt(0));
                    j.setName(cursor.getString(1));
                    j.setIdTeam(cursor.getInt(3));
                    listPlayers.add(j);

                }while(cursor.moveToNext());
            }
            return listPlayers;

        }

        public static Players getPlayersById(Context contexto, int idPlayer){
            List<Players> playersList = new ArrayList<>();
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getReadableDatabase();

            String sql = "SELECT * FROM Players WHERE id = IdPlayer " ;
            Cursor cursor = db.rawQuery( sql ,null);

            if ( cursor.getCount() > 0 ){
                cursor.moveToFirst();

                Players j = new Players();
                j.setIdPlayer(  cursor.getInt( 0 ) );
                j.setName( cursor.getString( 1 ) );
                j.setIdTeam(cursor.getInt(2));

                return j; // VERIFICAR CLASSE IMPORTADA//
            }else {
                return null;
            }
        }

}
