package fadergs.squadmaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fadergs.squadmaker.DAO.PlayersDAO;
import fadergs.squadmaker.Model.Players;
import fadergs.squadmaker.Model.Team;

public class List_PlayerActivity extends AppCompatActivity {

    private Integer idTeam;
    private ListView lvPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__player);

        lvPlayer = findViewById(R.id.lvPlayer);
        //idTeam = getIntent().getExtras().getInt("idTeam");

        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();

        idTeam = extras.getInt("idTeam");
        loadPlayerList(idTeam);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(List_PlayerActivity.this, PlayerFormActivity.class);
                intent.putExtra("idTeam", idTeam);
                startActivity(intent);
            }
        });

        lvPlayer.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(List_PlayerActivity.this);
                builder.setTitle(getString(R.string.txtChoiceOption));


                builder.setPositiveButton(getString(R.string.txtUpdateTeam), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateTeam( (Players) adapterView.getItemAtPosition(i));
                    }
                });

                builder.setNegativeButton(getString(R.string.txtDeleteTeam), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTeam( (Players) adapterView.getItemAtPosition(i) );
                    }
                });

                builder.create();
                builder.show();

                return true;

            }
        });

        lvPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sendIdTeamPlayer((Team) adapterView.getItemAtPosition(i));
                //Intent intent = new Intent(MainActivity.this, List_PlayerActivity.class);
                //intent.putExtra("idTeam", team.getID());
                //startActivity(intent);
            }
        });
    }
    private void deleteTeam(final Players players){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(getString(R.string.txtDeleteTeam));
        alerta.setMessage(getString(R.string.txtConfirmDeleteTeam)
                + players.getName() + "?");
        alerta.setNeutralButton(getString(R.string.txtCancel), null);
        alerta.setPositiveButton(getString(R.string.txtYes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PlayersDAO.exclud(List_PlayerActivity.this, players.getIdPlayer());
                //loadTeamList();
            }
        });
        alerta.show();
    }

    private void updateTeam(final Players players){



        Bundle bundle = new Bundle();
        bundle.putInt("idPlayer",players.getIdPlayer());
        bundle.putString("namePlayer",players.getName());
        bundle.putString("numberShirt",players.getNumberShirt());
        Intent intent = new Intent(List_PlayerActivity.this,PlayerFormActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    private void sendIdTeamPlayer(final Team team){

        Bundle bundle = new Bundle();
        bundle.putInt("teamID",team.getID());
        //bundle.putString("teamName",team.getName());
        Intent intent = new Intent(List_PlayerActivity.this,PlayerFormActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void deletePlayer(final Players players){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(getString(R.string.txtDeletePlayer));
        alerta.setMessage(getString(R.string.txtConfirmDeletePlayer)
                + players.getName() + "?");
        alerta.setNeutralButton(getString(R.string.txtCancel), null);
        alerta.setPositiveButton(getString(R.string.txtYes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PlayersDAO.exclud(List_PlayerActivity.this, players.getIdPlayer());
                //loadPlayerList();
            }
        });
        alerta.show();
    }


   private void loadTeamList(Players players, Bundle extras){
       List<Players> teamListL = PlayersDAO.getPlay(this, idTeam);
       if(teamListL.size() == 0 || extras.getInt("idTeam") != players.getIdTeam() ) {
          lvPlayer.setEnabled(false);
           Players fakeTeam = new Players();
           fakeTeam.setIdPlayer(0);
           fakeTeam.setName("Lista vazia!");
           teamListL.add(fakeTeam);
       }

       PlayerAdapter playerAdapter= new PlayerAdapter(this, teamListL);
       lvPlayer.setAdapter(playerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadPlayerList(idTeam);
    }

   private void loadPlayerList(Integer idTeam){
      // idTeam = extras.getInt("idTeam");
       List<Players> playersList = PlayersDAO.getPlay(this,idTeam);
       if(playersList.size() == 0){
            lvPlayer.setEnabled(false);
            Players fakePlayer = new Players();
            fakePlayer.setIdPlayer(0);
            fakePlayer.setName(getString(R.string.txtEmptyList));
            fakePlayer.setNumberShirt(getString(R.string.txtEmptyList));
            playersList.add(fakePlayer);
        }


       playersList.toString();
       Toast.makeText(this, playersList.toString(), Toast.LENGTH_SHORT).show();

       PlayerAdapter playerAdapter = new PlayerAdapter(this, playersList);
       lvPlayer.setAdapter(playerAdapter);


   }
}

