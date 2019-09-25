package fadergs.squadmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView teamList = (ListView) findViewById(R.id.LVTeams);

        ArrayList<String> teams = fakeTeams();

        ArrayAdapter<String> teamsFakeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,teams);

        teamList.setAdapter(teamsFakeAdapter);


    }

    public ArrayList<String> fakeTeams(){
        ArrayList<String> teams = new ArrayList<String>();
        teams.add("Grêmio");
        teams.add("Internacional");
        teams.add("Glória de vacaria");

        return teams;
    }
}
