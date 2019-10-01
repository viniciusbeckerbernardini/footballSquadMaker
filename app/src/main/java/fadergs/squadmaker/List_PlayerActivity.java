package fadergs.squadmaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fadergs.squadmaker.DAO.PlayersDAO;
import fadergs.squadmaker.DAO.TeamsDAO;
import fadergs.squadmaker.Model.Players;
import fadergs.squadmaker.Model.Team;

public class List_PlayerActivity extends AppCompatActivity {

    private ListView lvPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__player);

        lvPlayer = findViewById(R.id.lvPlayer);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_PlayerActivity.this, PlayerFormActivity.class);
                startActivity(intent);
            }
        });

        lvPlayer.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(List_PlayerActivity.this);
                builder.setTitle("Escolha uma opção");


                builder.setPositiveButton("Atualizar time", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateTeam( (Players) adapterView.getItemAtPosition(i));
                    }
                });

                builder.setNegativeButton("Excluir time", new DialogInterface.OnClickListener() {
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
    }
    private void deleteTeam(final Players players){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Produto");
        alerta.setMessage("Confirma a exclusão do produto "
                + players.getName() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PlayersDAO.exclud(List_PlayerActivity.this, players.getIdPlayer());
                loadTeamList();
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

    private void deletePlayer(final Players players){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Produto");
        alerta.setMessage("Confirma a exclusão do produto "
                + players.getName() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PlayersDAO.exclud(List_PlayerActivity.this, players.getIdPlayer());
                //loadPlayerList();
            }
        });
        alerta.show();
    }

    private void loadTeamList(){
        List<Players> teamListL = PlayersDAO.getPlay(this);
        if(teamListL.size() == 0){
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
        loadPlayerList();
    }

    private void loadPlayerList(){
        List<Players> playersList = PlayersDAO.getPlay(this);
        if(playersList.size() == 0){
            lvPlayer.setEnabled(false);
            Players fakePlayer = new Players();
            fakePlayer.setIdPlayer(0);
            fakePlayer.setName("Lista vazia!");
            fakePlayer.setNumberShirt("Lista vazia!");
            playersList.add(fakePlayer);
        }

        playersList.toString();
        Toast.makeText(this, playersList.toString(), Toast.LENGTH_SHORT).show();

        PlayerAdapter playerAdapter = new PlayerAdapter(this, playersList);
        lvPlayer.setAdapter(playerAdapter);


    }
}

