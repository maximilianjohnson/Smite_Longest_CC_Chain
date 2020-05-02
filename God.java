package chain;

import java.util.HashSet;
import java.util.Set;

public class God {
    private final String name;
    private Set<Ability> abilities;

    public God(String god, Set<Ability> abs) {
        this.name = god;
        this.abilities = abs;
    }

    public String getName() {
        return name;
    }

    public Set<Ability> getAbilities() {
        return new HashSet<>(abilities);
    }

    public void setting(String setting) {
        Set<Ability> updatedList = new HashSet<>();
        if (setting.equals("No Bracer")) {
            for (Ability ability : abilities) {
                if (!ability.getName().substring(2).equals("B")) {
                    updatedList.add(ability);
                }
            }
            abilities = updatedList;
        }

        if (setting.equals("No Ult")) {
            for (Ability ability : abilities) {
                if (!ability.getName().substring(2).equals("U")) {
                    updatedList.add(ability);
                }
            }
            abilities = updatedList;
        }

        if (setting.equals("No Hard CC")) {
            for (Ability ability : abilities) {
                if (ability.getDR() == 0) {
                    updatedList.add(ability);
                }
            }
            abilities = updatedList;
        }

        if (setting.equals("No DR CC")) {
            for (Ability ability : abilities) {
                if (ability.getDR() != 1 || ability.getName().substring(2).equals("M")
                        || ability.getName().substring(2).equals("B") || ability.getName().substring(2).equals("U")) {
                    updatedList.add(ability);
                }
            }
            abilities = updatedList;
        }

        if (setting.equals("No Soft CC")) {
            for (Ability ability : abilities) {
                if (ability.getDR() != 0 || ability.getName().substring(2).equals("M")
                        || ability.getName().substring(2).equals("B") || ability.getName().substring(2).equals("U")) {
                    updatedList.add(ability);
                }
            }
            abilities = updatedList;
        }

        if (setting.equals("No Med")) {
            for (Ability ability : abilities) {
                if (!ability.getName().substring(2).equals("M")) {
                    updatedList.add(ability);
                }
            }
            abilities = updatedList;
        }

    }
}
