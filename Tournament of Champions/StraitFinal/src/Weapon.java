public enum Weapon {
    AXE("Axe", 10),
    BOW("Bow", 7),
    STAFF("Staff", 4),
    DAGGER("Dagger", 6),
    SPEAR("Spear", 13),
    HAMMER("Hammer", 9),
    MACE("Mace", 7),
    NINJA_STAR("Ninja Star", 2),
    DIRK("Dirk", 3),
    SWORD("Sword", 9);

    private final String name;
    private final int damage;

    Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}
