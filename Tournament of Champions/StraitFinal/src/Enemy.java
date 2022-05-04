import java.util.Random;

public class Enemy extends Character {

    private final String[] taunts;

    public Enemy(String name, int health, Weapon weapon, String[] taunts) {
        super(name, health, weapon);
        this.taunts = taunts;
    }

    @Override
    public void battleCry() {
        Random r = new Random();
        System.out.println(getName() + ": " + taunts[r.nextInt(taunts.length)]);
    }
}
