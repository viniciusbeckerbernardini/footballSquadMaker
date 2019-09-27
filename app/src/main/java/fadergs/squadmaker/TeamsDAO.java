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

            valores.put("nameTeam", teams.getName());

            db.insert("teams",null,valores);
        }


        public static void editartime(Context contexto , Team team){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();


            ContentValues valores = new ContentValues();
            valores.put("nameTeam",team.getName());
            db.update("teams",valores,"IdTeam="+team.getID(),null);

        }

        public static void deleteTeam(Context contexto, int idtime){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            db.delete("teams","IdTeam="+idtime,null);

        }

        public static List<Team> getTeam(Context contexto){


            List<Team> listTeams = new ArrayList<>();
            DBTeams banco = new DBTeams(contexto);


            SQLiteDatabase db = banco.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM teams ORDER BY nameTeam",null);

            if(cursor.getCount()>0){
                cursor.moveToFirst();

                System.out.println(cursor);

                do {
                    Team t = new Team();
                    t.setID(cursor.getInt(0));
                    t.setName((cursor.getString(1)));
                    listTeams.add(t);
                }while(cursor.moveToNext());
            }
            return listTeams;
        }

        public static Team getTeamByID(Context context, int teamID){
            DBTeams banco = new DBTeams(context);
            SQLiteDatabase db = banco.getWritableDatabase();

            Cursor cursor = db.rawQuery( "SELECT * FROM teams WHERE IdTeam = " + teamID ,null);

            if(cursor.getCount()>0){

                cursor.moveToFirst();

                Team t = new Team();

                t.setID(cursor.getInt(0));
                t.setName(cursor.getString(1));

                return t;
            }else{

                return null;
            }

        }
    }

