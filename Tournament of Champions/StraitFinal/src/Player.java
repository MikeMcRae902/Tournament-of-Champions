public class Player extends Character {

    private final String favouriteSaying;

    public Player(String name, int health, Weapon weapon, String favouriteSaying) {
        super(name, health, weapon);
        this.favouriteSaying = favouriteSaying;
    }

    @Override
    public void battleCry() {
        System.out.println(getName() + ": " + favouriteSaying);
    }
}
