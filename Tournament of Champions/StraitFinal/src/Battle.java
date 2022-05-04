public class Battle {
    /***
     *This class records and stores the winner and runner up for the battles
     * contains getters
     */
    private class RoundResult {
        private Character winner;
        private Character runnerUp;

        public Character getWinner() {
            return winner;
        }
        public Character getRunnerUp() {
            return runnerUp;
        }

        /***
         * @param winner the winner from the battles
         * @param runnerUp the runner up from the battles (Second best)
         */
        public RoundResult (Character winner, Character runnerUp) {
            this.winner = winner;
            this.runnerUp = runnerUp;
        }
    }

    /***
     *This class records and stores the win count from the tournament to help determine standings
     * win count starts at 0
     */
    private class CharacterWithWinCount{
        public Character character;
        public int winCount;

        public CharacterWithWinCount (Character character) {
            this.character = character;
            this.winCount = 0;
        }
    }

    /***
     *THis runs the entire tournament. Including 2 round robins (groupA and groupB), semi-finals, and final.
     * Outputs the results and winner information
     * Uses 2 arrays for battle results in round robin, groupA and groupB
     *
     * @param player the player or user (Gizmo)
     */
    public void runTournament (Player player) {
        String[] groupANames = {"Apple", "Ambrosia", "Albert", "Audrey"};
        String[] groupBNames = {"Bob", "Bill", "Ben", "Bailey", "Becka"};
        /***
         * create 2 groups of 5 people each
         */
        Character[] groupA = new Character[5];
        Character[] groupB = new Character[5];

        /***
         * This loops through the groups to determine amount of participants
         * player is in groupA + 4 enemies
         * groupB is 5 enemies
         */
        CharacterCreator characterCreator = new CharacterCreator();
        groupA[0] = player;
        for (int i = 1; i < groupA.length; i++) {
            groupA[i] = characterCreator.generateEnemy(groupANames[i - 1]);
        }
        for (int i = 0; i < groupB.length; i++) {
            groupB[i] = characterCreator.generateEnemy(groupBNames[i]);
        }

        /***
         * run group and declare winner and runner up
         */
        System.out.println("\n**** Running Group A ****");
        RoundResult groupAResult = runGroup(groupA);
        System.out.println("\n**** Running Group B ****");
        RoundResult groupBResult = runGroup(groupB);

        /***
         * 2 winner and 2 runner ups (Semi Final)
         */
        System.out.println("\n**** Semi Final 1 ****");
        Character semiOneWinner = runRound(groupAResult.getWinner(), groupBResult.getRunnerUp());
        System.out.println("\n**** Semi Final 2 ****");
        Character semiTwoWinner = runRound(groupBResult.getWinner(), groupAResult.getRunnerUp());

        /***
         * Semi-Winner vs Semi-Winner in Final
         */
        System.out.println("\n**** Final Match ****");
        Character finalWinner = runRound(semiOneWinner, semiTwoWinner);

        /***
         * Print out tournament winner. Message specific to player.
         */
        System.out.println("\n**** Tournament Winner is: " + finalWinner.getName() + " ****" );
        if (finalWinner == player) {
            System.out.println("Good Job " + player.getName() + ", you won the tournament!");
        }
        else {
            System.out.println("Better luck next time " + player.getName() + ", Loser");
        }
    }


    /***
     * This runs the round robin and calculates the win count and the eventual winner and runner up
     * @param characters the characters in the character array
     * @return the  results (winner and the runner up) from the battle rounds
     */
    private RoundResult runGroup (Character[] characters) {

        CharacterWithWinCount[] charactersWithWinCount = new CharacterWithWinCount[characters.length];
        for (int i = 0; i < characters.length; i++ ) {
            charactersWithWinCount[i] = new CharacterWithWinCount(characters[i]);
        }

        for (int i = 0; i < characters.length; i++ ) {
            for (int j = i + 1; j < characters.length; j++) {
                Character winner = runRound(characters[i], characters[j]);
                if (winner == characters[i]) {
                    charactersWithWinCount[i].winCount++;
                } else charactersWithWinCount[j].winCount++;
            }
        }

        /***
         * This finds the winner from each group. Removes it than loops back in to find the new winner(runner up)
         */
        CharacterWithWinCount winner = findHighestWinCount(charactersWithWinCount);
        winner.winCount = -1;
        CharacterWithWinCount runnerUp = findHighestWinCount(charactersWithWinCount);
        return new RoundResult(winner.character, runnerUp.character);
    }

    /***
     * This finds the highest number (win count) in the array. replacing it when finding a higher win count.
     * @param characters the character names from the CharacterWithWinCount array
     * @return the highest wni count total (number) in the array
     */
    private CharacterWithWinCount findHighestWinCount (CharacterWithWinCount[] characters) {
        CharacterWithWinCount maxSoFar = characters[0];
        for (int i = 0; i < characters.length; i++) {
            if (maxSoFar.winCount < characters[i].winCount) {
                maxSoFar = characters[i];
            }
        }
        return maxSoFar;
    }

    /***
     *This runs the round of battles while healing both involved before beginning
     * @param player character in battle
     * @param enemy  character in battle
     * @return the winner from the battle
     */
    private Character runRound (Character player, Character enemy) {
        Character winner = runBattle(player, enemy);
        player.resetHealth();
        enemy.resetHealth();
        return winner;
    }

    /***
     *This is the runBattle code. It causes an enemy and player to attack with the calculated attributes
     * @param player character involved in battle
     * @param enemy character involved in battle
     * @return winner (enemy or player)
     */
   public Character runBattle(Character player, Character enemy) {
        while (true) {
            player.battleCry();
            player.attack(enemy);
            battleUpdate(player, enemy);

            if (enemy.getHealth() <= 0) {
                declareWinner(player);
                return player;
            }

            enemy.battleCry();
            enemy.attack(player);
            battleUpdate(enemy, player);

            if (player.getHealth() <= 0) {
                declareWinner(enemy);
                return enemy;
            }
        }
    }

    /***
     *This prints out/displays the battle results as they are happening. Or in this case updates
     * @param attacker character involved in battle
     * @param defender character involved in battle
     */
    private void battleUpdate(Character attacker, Character defender) {
        System.out.println(attacker.getName() + " attacks " + defender.getName() + " with their " + attacker.getWeapon().getName() + " for " + attacker.getWeapon().getDamage() +
                " damage. Health remaining: " + defender.getHealth());
    }

    /***
     *This declares the winner based on the battle results
     * @param winner Winner of the tournament/battle to be displayed
     */
    private void declareWinner(Character winner) {
        System.out.println("The winner is " + winner.getName());
    }
}
