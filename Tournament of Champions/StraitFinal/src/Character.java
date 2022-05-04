public abstract class Character {
    private final String name;
    private int health;
    private final Weapon weapon;
    private final int originalHealth;

    public Character(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.originalHealth = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void resetHealth() { health = originalHealth; }

    public void attack(Character character) {
        character.takeDamage(weapon.getDamage());
    }

    abstract public void battleCry();
}
