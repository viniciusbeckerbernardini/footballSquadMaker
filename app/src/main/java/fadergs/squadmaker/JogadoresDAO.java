package fadergs.squadmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class JogadoresDAO{

        public static void inserirJogador(Context contexto, Players jogador){

            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nome",jogador.getIdPlayer());
            valores.put("idtime",jogador.getIdTeam());
            db.insert("jogador",null,valores);
        }

        public static void editarJogador(Context contexto , Players jogador){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("nome",jogador.getName());
            valores.put("idtime",jogador.getIdTeam());
            db.update("jogador",valores,"idjogador="+jogador.getIdPlayer(),null);
        }


        public static void excluir(Context contexto , int idjogador){
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();
            db.delete("jogador","idjogador="+idjogador,null);


        }

        public static List<Players> getjogador(Context contexto){

            List<Players> listajogador = new ArrayList<>();

            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM jogador ORDER BY nome",null);

            if(cursor.getCount()>0){
                cursor.moveToFirst();

                do {
                    Players j = new Players();
                    j.setIdPlayer(cursor.getInt(0));
                    j.setName(cursor.getString(1));
                    j.setIdTeam(cursor.getInt(3));
                    listajogador.add(j);

                }while(cursor.moveToNext());
            }
            return listajogador;

        }

        public static List <Players > getJogadoresById(Context contexto){
            List<Players> listaJogadores = new ArrayList<>();
            DBTeams banco = new DBTeams(contexto);
            SQLiteDatabase db = banco.getReadableDatabase();

            String sql = "SELECT * FROM jogador WHERE id = Idjogador " ;
            Cursor cursor = db.rawQuery( sql ,null);

            if ( cursor.getCount() > 0 ){
                cursor.moveToFirst();

                Players j = new Players();
                j.setIdPlayer(  cursor.getInt( 0 ) );
                j.setName( cursor.getString( 1 ) );
                j.setIdTeam(cursor.getInt(2));

                return (List<Players>) j; // VERIFICAR CLASSE IMPORTADA//
            }else {
                return null;
            }
        }

}
