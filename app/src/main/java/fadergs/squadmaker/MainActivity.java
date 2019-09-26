package fadergs.squadmaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


import android.widget.AdapterView;


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
                Intent i = new Intent(MainActivity.this, teamFormActivity.class);
                startActivity( i );
            }
        });

        teamList.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
                deleteTeam( (Team) adapterView.getItemAtPosition(i) );
                return true;
            }
        });

    }

    private void deleteTeam(final Team team){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Produto");
        alerta.setMessage("Confirma a exclusão do produto "
                + team.getName() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TeamsDAO.deleteTeam(MainActivity.this, team.getID());
                loadTeamList();
            }
        });
        alerta.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void loadTeamList(){
        List<Team> teamListL = TeamsDAO.getTeam(this);
        if(teamListL.size() == 0){
            teamList.setEnabled(false);
            Team fakeTeam = new Team();
            fakeTeam.setID(0);
            fakeTeam.setName("Lista vazia!");
            teamListL.add(fakeTeam);
        }
        TeamAdapter teamAdapter = new TeamAdapter(this, teamListL);
        teamList.setAdapter(teamAdapter);
    }
}
