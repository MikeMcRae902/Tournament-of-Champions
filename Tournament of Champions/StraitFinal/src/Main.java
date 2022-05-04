public class Main {
    public static void main(String[] args) {

        CharacterCreator creator = new CharacterCreator();
        Player player = creator.generatePlayer("Gizmo");

        System.out.println(player.getName() + " starting health: " + player.getHealth());
        System.out.println("Let the tournament begin");

        Battle battle = new Battle();
        battle.runTournament(player);
    }
}
