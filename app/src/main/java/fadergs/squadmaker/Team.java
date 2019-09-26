package fadergs.squadmaker;

public class Team {

    private int id;
    private String name;

    public Team(String name){
        this.setName(name);
    }

    public String getName()
    {
        return this.name;

    }

    public int getID(){
        return this.id;
    }

    public void setName(String name){

        this.name = name;
    }

    @Override
    public String toString(){
        return  "ID do time: "+this.getID()+
                "Nome do time: "+this.getName();
    }
}
