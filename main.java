package chain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class main {

    public static void main(String[] args) {
        Set<String> gods = new HashSet<>();

        /* !! CHOOSE YOUR SETTINGS BELOW !! */

        /* The more stuff you allow, the greater the calculation time */
        int godsInCombo = 3; // change this to change # of gods in combo
        boolean softCC = false; // t/f to allow soft cc (artemis trap, scylla cripple/root ect.) change Nox in Setup.java
        boolean hardCC = true; // t/f to allow hard cc
        boolean ult = true; // t/f to allow ultimates (and thus myrddin)
        boolean meditation = true; // t/f to allow med
        boolean bracer = true; // t/f to allow bracer

        /* comment out gods to not check */

        gods.add("Kumbhakarna");
        gods.add("Nox");
        gods.add("Ne Zha");
//        gods.add("Yemoja");
//        gods.add("Heimdallr");
//        gods.add("Hercules");
//        gods.add("Artemis");
//        gods.add("Nu Wa");
//        gods.add("Scylla");
//        gods.add("The Morrigan"); //Significantly increases test time :)
//        gods.add("Hera");
//        gods.add("Hercules");
//        gods.add("Tyr");

        /* END OF SETTINGS */

        Setup game1 = new Setup(gods);
        Set<God> allGods = game1.getGods();
        powerSet combinations = new powerSet(allGods);
        Set<Set<God>> godComboLength = combinations.combos(godsInCombo);

        System.out.println("Testing " + godComboLength.size() + " Different Combinations of Gods.");
        System.out.println("\n.\n.\n.");

        double currentBest = 0.0;
        Set<String> godNames = new HashSet<>();
        List<String> abilityOrder = new ArrayList<>();
        Map<Set<String>, Double> allResults = new HashMap<>();

        for (Set<God> godCombo : godComboLength) {

            System.out.println("\nCurrently testing: ");
            for (God god : godCombo) {
                System.out.print(god.getName() + " ");

                if (!softCC) {
                    god.setting("No Soft CC");
                }
                if (!hardCC) {
                    god.setting("No Hard CC");
                }
                if (!ult) {
                    god.setting("No Ult");
                }
                if (!meditation) {
                    god.setting("No Med");
                }
                if (!bracer) {
                    god.setting("No Bracer");
                }

            }

            System.out.println();

            GameSim smite = new GameSim(godCombo);
            double longest = smite.longest_cc_chain();

            allResults.put(smite.getGods(), longest);

            if (longest > currentBest) {
                currentBest = longest;
                godNames = smite.getGods();
                abilityOrder = smite.getOrder();
            }

        }

        System.out.println(".\n.\n.\n.\n.\n\n");
        System.out.println("CALCULATION COMPLETE!");
        System.out.println(godComboLength.size()+" Gods Tested.");
        System.out.print("LONGEST POSSIBLE CC CHAIN: ");
        if (currentBest < 200) {
            System.out.println(currentBest);
        } else {
            System.out.println("Infinite");
        }
        System.out.print("Gods used: ");
        System.out.println(godNames);
        System.out.print("Ability order: ");
        System.out.println(abilityOrder);
        System.out.print("All Results: ");
        System.out.println(allResults);
    }
}