package fadergs.squadmaker;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;


import android.widget.AdapterView;

import fadergs.squadmaker.DAO.TeamsDAO;
import fadergs.squadmaker.Model.Team;


public class MainActivity extends AppCompatActivity {

    private ListView teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamList = findViewById(R.id.LVTeams);

        FloatingActionButton btnCreateTeam = (FloatingActionButton) findViewById(R.id.FABCreateTeam);
        btnCreateTeam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, TeamFormActivity.class);
                startActivity( i );
            }
        });


        teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sendIdTeamPlayer((Team) adapterView.getItemAtPosition(i));

            }
        });

        teamList.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.txtChoiceOption));


                builder.setPositiveButton(getString(R.string.txtUpdateTeam), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateTeam( (Team) adapterView.getItemAtPosition(i));
                    }
                });

                builder.setNegativeButton(getString(R.string.txtDeleteTeam), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTeam( (Team) adapterView.getItemAtPosition(i) );
                    }
                });

                builder.create();
                builder.show();

                return true;

            }
        });

    }

    private void deleteTeam(final Team team){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(getString(R.string.txtDeleteTeam));
        alerta.setMessage(getString(R.string.txtConfirmDeleteTeam)
                + team.getName() + "?");
        alerta.setNeutralButton(getString(R.string.txtCancel), null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TeamsDAO.deleteTeam(MainActivity.this, team.getID());
                loadTeamList();
            }
        });
        alerta.show();
    }

    private void updateTeam(final Team team){



        Bundle bundle = new Bundle();
        bundle.putInt("teamID",team.getID());
        bundle.putString("teamName",team.getName());
        Intent intent = new Intent(MainActivity.this,TeamFormActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    private void sendIdTeamPlayer(Team team){
        Bundle bundle = new Bundle();
        bundle.putInt("teamID",team.getID());
        Intent intent = new Intent(MainActivity.this, ListPlayerActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadTeamList();

    }

    private void loadTeamList(){
        List<Team> teamListL = TeamsDAO.getTeam(this);
        if(teamListL.size() == 0){
            teamList.setEnabled(false);
            Team fakeTeam = new Team();
            fakeTeam.setID(0);
            fakeTeam.setName(getString(R.string.txtEmptyList));
            teamListL.add(fakeTeam);
        }

        TeamAdapter teamAdapter = new TeamAdapter(this, teamListL);
        teamList.setAdapter(teamAdapter);
    }
}
