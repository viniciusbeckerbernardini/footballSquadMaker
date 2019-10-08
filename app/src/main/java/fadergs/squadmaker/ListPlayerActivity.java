package fadergs.squadmaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fadergs.squadmaker.DAO.PlayersDAO;
import fadergs.squadmaker.Model.Players;
import fadergs.squadmaker.Model.Team;

public class ListPlayerActivity extends AppCompatActivity {

    private ListView lvPlayer;
    private int idTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__player);
        idTeam = getIntent().getExtras().getInt("teamID");
        lvPlayer = findViewById(R.id.lvPlayer);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFormPlayer(idTeam);

            }
        });

        lvPlayer.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ListPlayerActivity.this);
                builder.setTitle(getString(R.string.txtChoiceOption));


                builder.setPositiveButton(getString(R.string.txtUpdatePlayer), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updatePlayer( (Players) adapterView.getItemAtPosition(i));
                    }
                });

                builder.setNegativeButton(getString(R.string.txtDeletePlayer), new DialogInterface.OnClickListener() {
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
                sendIdTeamPlayer((Players) adapterView.getItemAtPosition(i));

            }
        });
    }
    private void deleteTeam(final Players players){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(getString(R.string.txtDeletePlayer));
        alerta.setMessage(getString(R.string.txtConfirmDeletePlayer)
                + players.getName() + "?");
        alerta.setNeutralButton(getString(R.string.txtCancel), null);
        alerta.setPositiveButton(getString(R.string.txtYes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PlayersDAO.exclud(ListPlayerActivity.this, players.getIdPlayer());
                loadPlayerList();
            }
        });
        alerta.show();

    }

    private void updatePlayer(final Players players){



        Bundle bundle = new Bundle();
        bundle.putInt("idPlayer",players.getIdPlayer());
        bundle.putString("namePlayer",players.getName());
        bundle.putInt("numberShirt",players.getNumberShirt());
        bundle.putBoolean("update",true);
        Intent intent = new Intent(ListPlayerActivity.this,PlayerFormActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    private void sendIdTeamPlayer(final Players players){

        Bundle bundle = new Bundle();
        bundle.putInt("teamID",players.getIdTeam());
        Intent intent = new Intent(ListPlayerActivity.this,PlayerFormActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }




    private void sendFormPlayer(final Integer team){
        Intent intent = new Intent(ListPlayerActivity.this, PlayerFormActivity.class);
        intent.putExtra("teamID", idTeam);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadPlayerList();
    }

   private void loadPlayerList(){
       List<Players> playersList = PlayersDAO.getPlay(ListPlayerActivity.this,idTeam);
       Toast.makeText(ListPlayerActivity.this, " id: " + idTeam + " \n" + "total: " + playersList.size(),
               Toast.LENGTH_SHORT).show();
       if(playersList.size() == 0){
            lvPlayer.setEnabled(false);
            Players fakePlayer = new Players();
            fakePlayer.setIdPlayer(0);
            fakePlayer.setName(getString(R.string.txtEmptyList));
            //fakePlayer.setNumberShirt(getString(R.string.txtEmptyList));
            playersList.add(fakePlayer);
        }


       playersList.toString();
      // Toast.makeText(this, playersList.toString(), Toast.LENGTH_SHORT).show();

       PlayerAdapter playerAdapter = new PlayerAdapter(this, playersList);
       lvPlayer.setAdapter(playerAdapter);


   }
}

