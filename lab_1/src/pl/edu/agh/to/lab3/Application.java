package pl.edu.agh.to.lab3;

public class Application {

    public static void main(String[] args) {
        Finder suspects = new Finder(new PersonDataProvider(), new PrisonersDatabase());
        suspects.displayAllSuspectsWithName("Janusz");
    }
}
