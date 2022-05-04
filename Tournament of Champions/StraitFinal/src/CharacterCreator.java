import java.util.Random;

public class CharacterCreator implements Generator {

    public Enemy generateEnemy(String name) {
        Random random = new Random();
        String[] taunts = {
                "You're weak",
                "Better luck next time",
                "Take that",
                "I'm not impressed"
        };

        return new Enemy(
                name,
                random.nextInt(20, 51),
                generateWeapon(),
                taunts
        );
    }

    public Player generatePlayer(String name) {
        Random random = new Random();
        
        return new Player(
                name,
                random.nextInt(20, 51),
                generateWeapon(),
                "I'm the best!"
        );
    }

    public Weapon generateWeapon() {
        Random r = new Random();
        return Weapon.values()[r.nextInt(Weapon.values().length)];
    }
}
