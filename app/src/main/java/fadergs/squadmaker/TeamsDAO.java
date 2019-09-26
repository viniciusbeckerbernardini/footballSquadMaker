package fadergs.squadmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

    public class TeamsDAO {

        public static void insertTeam(Context contexto , Team teams){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            ContentValues valores = new ContentValues();

            valores.put("name",());

            db.insert("team",null,valores);
        }


        public static void editartime(Context contexto , Team team){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();


            ContentValues valores = new ContentValues();
            valores.put("name",team.get());
            db.update("team",valores,"id="+team.getId(),null);

        }

        public static void excludeTeam(Context contexto, int idtime){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            db.delete("team","id="+idtime,null);

        }

        public static List<Team> getTime(Context contexto){

            List<Team> listTeams = new ArrayList<>();

            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM Teams ORDER BY name",null);

            if(cursor.getCount()>0){
                cursor.moveToFirst();

                do {
                    Team t = new Team();
                    t.setId(cursor.getInt(0));
                    t.setNome(cursor.getString(1));
                    list.add(t);
                }while(cursor.moveToNext());
            }
            return list;
        }

        public static Team getTimeByID(Context contexto , int idtime){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            Cursor cursor = db.rawQuery( "SELECT * FROM team WHERE id = " + idtime ,null);

            if(cursor.getCount()>0){

                cursor.moveToFirst();

                Team t = new Team();

                t.setId(cursor.getInt(0));
                t.setNome(cursor.getString(1));

                return t;
            }else{

                return null;
            }

        }







    }
}
