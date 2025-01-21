public class SoccerTeam {
    private String id;
    private String name;
    private String country;
    private int players;

    public SoccerTeam(String id, String name, String country, int players) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nTeam Name: " + name + "\nCountry: " + country + "\nNumber of Players: " + players;
    }
}
