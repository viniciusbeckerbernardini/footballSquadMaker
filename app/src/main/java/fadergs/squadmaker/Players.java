package fadergs.squadmaker;

public class Players {

    private int idPlayer;
    private int idTeam;
    private String name;
    private int numberShirt;

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberShirt() {
        return numberShirt;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setNumberShirt(int numberShirt) {
        this.numberShirt = numberShirt;
    }
}


