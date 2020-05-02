package chain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class powerSet {

    private Set<Set<God>> powerSets;

    public powerSet(Set<God> gods) {
        powerSets = powerSetFinder(gods);
    }

    private Set<Set<God>> powerSetFinder(Set<God> originalSet) {
        Set<Set<God>> sets = new HashSet<Set<God>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<God>());
            return sets;
        }
        List<God> list = new ArrayList<God>(originalSet);
        God head = list.get(0);
        Set<God> rest = new HashSet<God>(list.subList(1, list.size()));
        for (Set<God> set : powerSetFinder(rest)) {
            Set<God> newSet = new HashSet<God>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    public Set<Set<God>> combos (int godNum) {
        Set<Set<God>> allCombos = new HashSet<>();
        Set<Set<God>> allCombosMorrigan = new HashSet<>();
        for (Set<God> pows : powerSets) {
            if (pows.size() == godNum) {
                allCombos.add(pows);
            }
        }

        for (Set<God> combo : allCombos) {
            int morriganCheck = 0;

            for (God god : combo) {
                if (god.getName().equals("The Morrigan")) {
                    morriganCheck = 1;
                    for (God gods : combo) {
                        if (gods.getName().equals("Yemoja") || gods.getName().equals("Tyr")) {
                            break;
                        }
                        if (!gods.getName().equals("The Morrigan")) {
                            Set<God> mSet = new HashSet<>(combo);
                            for (God morFind : combo) {
                                if (morFind.getName().equals("Nox")) {
                                    for (God moreGods : combo) {
                                        if (moreGods.getName().equals("Kumbhakarna")) {
                                            Set<Ability> noxAbilities = morFind.getAbilities();
                                            noxAbilities.add(new Ability("NXU", 0.0, 45.0, 0, "Nox"));
                                            mSet.remove(morFind);
                                            mSet.add(new God("Nox", noxAbilities));
                                        }
                                    }
                                }
                                if (morFind.getName().equals("The Morrigan")) {
                                    mSet.remove(morFind);
                                }
                            }
                            Set<Ability> morriganSet = new HashSet<>();
                            Set<Ability> morriganCopies = gods.getAbilities();
                            for (Ability checks : morriganCopies) {
                                if (!checks.getName().substring(2).equals("M") && !checks.getName().substring(2).equals("B")) {
                                    morriganSet.add(new Ability("M" + checks.getName().substring(1, 3),
                                            checks.getDuration(), checks.getRCD(), checks.getDR(), "The Morrigan"));
                                }
                            }
                            morriganSet.add(new Ability("MM1", 1.0, 12.0*0.6+1.1, 1,  "The Morrigan"));
                            morriganSet.add(new Ability("MMB", 0.0, 120.0, 0,  "The Morrigan"));
                            morriganSet.add(new Ability("DMM", 0.0, 120.0, 0,  "The Morrigan"));
                            God The_Morrigan = new God("The Morrigan", morriganSet);
                            mSet.add(The_Morrigan);
                            allCombosMorrigan.add(mSet);
                        }
                    }
                }
            }
            for (God god : combo) {
                if (god.getName().equals("Nox") && morriganCheck == 0) {
                    for (God gods : combo) {
                        if (gods.getName().equals("Kumbhakarna")) {
                            Set<God> nSet = new HashSet<>(combo);
                            morriganCheck = 1;
                            Set<Ability> noxAbilities = god.getAbilities();
                            noxAbilities.add(new Ability("NXU", 0.0, 45.0, 0, "Nox"));
                            nSet.remove(god);
                            nSet.add(new God("Nox", noxAbilities));
                            allCombosMorrigan.add(nSet);
                        }
                    }
                }
            }

            if (morriganCheck == 0) {
                allCombosMorrigan.add(combo);
            }
        }
        return allCombosMorrigan;
    }

}
