package chain;

import java.util.*;

class GameSim {

    private HashSet<String> abilityName = new HashSet<>();
    private HashMap<String, Double> abilityDuration = new HashMap<>();
    private HashMap<String, Double> necessary_cooldown = new HashMap<>();
    private HashMap<String, Integer> diminishingReturns = new HashMap<>();
    private HashMap<String, String> god = new HashMap<>();

    private Set<String> godNames = new HashSet<>();
    private ArrayList<String> order = new ArrayList<>();

    private double bestDur = 0.0;
    private Long startTime = 0L;
    private Long firstAbTime = 0L;
    private Set<String> bannedAbsLol = new HashSet<>();

    /* INFINITE 5 HARD CC */
//    void setup() {
//        ability_add("NX1", 2.0, 8.0, 2);
//        ability_add("JN1", 1.5, 12*0.6+0.3, 2);
//        ability_add("HE1", 2.0, 14.0*0.6+0.6, 2);
//        ability_add("TY1", 1.8, 6.0+1.4, 2);
//        ability_add("HR1", 1.7, 14*0.6+0.6, 2);
//        ability_add("D1M", 0.0, 80, 0);
//        ability_add("D2M", 0.0, 80, 0);
//        ability_add("D3M", 0.0, 80, 0);
//        ability_add("NXB", 0.0, 80, 0);
//        ability_add("HEB", 0.0, 80, 0);
//    }

    /* INFINITE 4 CRIPPLE CC */
//    void setup() {
//        ability_add("NX1", 2.0, 8.0, 0);
//        ability_add("SC1", 1.75, 6.2, 0);
//        ability_add("AR1", 2.0, 6.2, 0);
//        ability_add("KK2", 2.0, 7.5, 0);
//        ability_add("KK1", 3.0, 15, 0);
//    }

    /* INFINITE 3 SOFT ROOTS */
//    void setup() {
//        ability_add("SC1", 1.6, 6.3, 0);
//        ability_add("AR1", 1.9, 6.2, 0);
//        ability_add("NWA", 1.4, 1.0 / 2.0 * 6.0, 0);
//    }
    public GameSim(Set<God> gods) {
        for (God god: gods) {
            godNames.add(god.getName());
            Set<Ability> godAbilities = god.getAbilities();
            for (Ability ability: godAbilities) {
                ability_add(ability.getName(), ability.getDuration(), ability.getRCD(), ability.getDR(), ability.getGodName());
            }
        }
    }

    private void ability_add(String name, double duration, double cd, int diminish, String godName) {
        abilityName.add(name);
        abilityDuration.put(name, duration);
        necessary_cooldown.put(name, cd);
        diminishingReturns.put(name, diminish);
        god.put(name, godName);
    }

    public double longest_cc_chain() {
        startTime = System.currentTimeMillis();
        firstAbTime = System.currentTimeMillis();
        HashMap<String, Double> cooldown = new HashMap<>(necessary_cooldown);
        for (String name : abilityName) {
            ability_iterate(0.0, 0, name, cooldown, new ArrayList<>(),
                    0.0, 5.0, new HashMap<>(), 0, 15.0, 10.0, 8.0, 0);
        }
        System.out.println(bestDur);
        System.out.println(order);
        return bestDur;
    }

    private double ability_iterate(double current_duration, Integer cdr, String ability, Map<String, Double> cc,
                                          ArrayList<String> abo, double morDuration, double medDuration, Map<String,
                                          Double> myr, int tyr, double timeSince, double omni, double rebuke, int mUlt) {
        /* VARIABLES */
        Map<String, Double> cooldown =  new HashMap<>(cc);
        ArrayList<String> currentOrder = new ArrayList<>(abo);
        Map<String, Double> myrddin =  new HashMap<>(myr);
        double morriganWatch = morDuration;
        int newCdr = cdr;
        double medTime = medDuration;
        double newOmni = omni;
        double newRebuke = rebuke;
        int morUlt = mUlt;

        /* FILTERS */
        // Just adjust this to cap at however much you want, currently 5 minutes
        if (System.currentTimeMillis() - startTime > 1000 * 60 * 5) {
            return current_duration;
        }
        if (current_duration > 2000) {
            startTime = 1000 * 60 * 5L;
            return 2000;
        }
        if ((currentOrder.isEmpty() && ability.substring(2).equals("BS"))) {
            return current_duration;
        } else if (currentOrder.size() > 0) {
            if (currentOrder.get(currentOrder.size()-1).equals("MM1") && ability.substring(0, 1).equals("M")) {
                return current_duration;
            }
            if (currentOrder.get(currentOrder.size()-1).substring(0, 1).equals("M") && ability.equals("MM1")) {
                return current_duration;
            }

            if (currentOrder.get(currentOrder.size()-1).equals("AR1") && ability.equals("KK1")) {
                return current_duration;
            }
        }

        /* ABILITIES */
        if (cooldown.get(ability) >= necessary_cooldown.get(ability)) {

            /* MEDITATION */
            if (ability.substring(2).equals("M")) {
                medTime = 0.0; /* IF medTime <= 4.00, abilities cooldowns are 1 second shorter **/
                cooldown.put(ability, 0.0);

            /* BRACER OF UNDOING */
            } else if (ability.substring(2).equals("B")) {
                cooldown = bracer(cooldown, ability); /* take 3 seconds off the cooldown for all of current gods abilities */
                cooldown.put(ability, 0.0);
                if (ability.equals("YYB")) {
                    newOmni = yemojaOmni(omni, 3.0, ability, rebuke);
                }

            /* HARD CC ABILITY */
            } else {
                /* check diminishing returns to calculate time */
                double time = ccdr_check(ability, cdr, cooldown, medTime); /* update current cooldown times here */
                current_duration = current_duration + time; /* update combo duration */

                /* YEMOJA */
                newOmni = yemojaOmni(omni, time, ability, rebuke);
                newRebuke = yemojaRebuke(time, ability, rebuke);

                /* STAFF OF MYRDDIN */
                if (ability.substring(2).equals("U")) {
                    myrddin.put(ability, time);
                } else {
                    for (Map.Entry<String, Double> myrs : myr.entrySet()) {
                        if (myrs.getKey().substring(0, 2).equals(ability.substring(0, 2)) && myrs.getValue() < 10) {
                            cooldown.put(ability, necessary_cooldown.get(ability) + time);
                            myrddin.remove(myrs.getKey());

                        } else {
                            double myrTime = myrs.getValue() + time;
                            myrddin.put(myrs.getKey(), myrTime);
                        }
                    }
                }

                /* TYR STANCE */
                if(ability.equals("TY1")) {
                    if (tyr == 0) {
                        tyr = 1;
                    } else {
                        double currentCD = cooldown.get(ability);
                        currentCD = currentCD - 3.0;
                        cooldown.put(ability, currentCD);
                        tyr = 0;
                    }
                }

                if (diminishingReturns.get(ability) == 0) {
                    timeSince += time;
                } else {
                    timeSince = time;
                }
                newCdr = dr_check(ability, cdr, timeSince); /* update diminishing returns */

                medTime = medTime + time; /* update time that meditation has been active / inactive for */

                /* check if the morrigan has overstayed her welcome in someone else's body (10 seconds) */
                if (godNames.contains("The Morrigan")) {
                    morriganWatch = morriganCheck(morDuration, ability, time);
                    cooldown = morriganUpdate(cooldown, morriganWatch, morDuration);

                    if (morriganWatch > 9 && morUlt == 0) {
                        morUlt = 1;
                    }
                    if (morUlt == 1 && ability.equals("MM1")) {
                        cooldown.put("MM1", necessary_cooldown.get("MM1") - 1.1);
                        morUlt = 2;
                    }
                }
            }

            currentOrder.add(ability); /* update order of abilities used in combo */

            /* check if new best has been found! */
            if (current_duration > bestDur) {
                System.out.print("\nFound new best: ");
                System.out.println(current_duration);
                System.out.println(currentOrder);
                bestDur = current_duration;
                order = currentOrder;
            }

            cooldown = omniCheck(newOmni, cooldown, godNames);

            /* iterate through all available abilities */
            for (String name : abilityName) {
                if (necessary_cooldown.get(name) <= cooldown.get(name)) {
                    ability_iterate(current_duration, newCdr, name, cooldown, currentOrder, morriganWatch,
                            medTime, myrddin, tyr, timeSince, newOmni, newRebuke, morUlt);
                }
            }

            /* if no more available abilities, return combo time */
            return current_duration;
        }
        return current_duration;
    }

    private double ccdr_check(String ability, Integer cdr, Map<String, Double> cooldown, double medTime) {
        double newCD = 0.0;
        if (medTime <= 4.00) {
            newCD = 1.0;
        }
        if (cdr > 1 && diminishingReturns.get(ability) == 1) {
            cooldown.put(ability, newCD);
            increment_cd(Math.max(abilityDuration.get(ability) * 0.34, 0.5), cooldown);
            return Math.max(abilityDuration.get(ability) * 0.34, 0.5);
        } else if (cdr == 1 && diminishingReturns.get(ability) == 1) {
            cooldown.put(ability, newCD);
            increment_cd(Math.max(abilityDuration.get(ability) * 0.67, 0.5), cooldown);
            return Math.max(abilityDuration.get(ability) * 0.67, 0.5);
        } else {
            cooldown.put(ability, newCD);
            increment_cd(abilityDuration.get(ability), cooldown);
            return abilityDuration.get(ability);
        }
    }

    private int dr_check(String ability, Integer cdr, double timeSince) {
        if (timeSince >= 15.0) {
            return 0;
        }
        if (cdr > 1 && diminishingReturns.get(ability) != 0) {
            return 2;
        } else if (cdr == 1 && diminishingReturns.get(ability) != 0) {
            return 2;
        } else if (cdr == 0 && diminishingReturns.get(ability) != 0){
            return 1;
        } else {
            return cdr;
        }
    }

    private void increment_cd(Double duration, Map<String, Double> cc) {
        for (HashMap.Entry<String, Double> ability : cc.entrySet()) {
            double cd = ability.getValue()+duration;
            cc.put(ability.getKey(), cd);
        }
    }

    private Map<String, Double> bracer(Map<String, Double> cc, String ab) {
        Map<String, Double> newCD = new HashMap<>(cc);
        String godname = god.get(ab);
        for (HashMap.Entry<String, Double> ability : cc.entrySet()) {
            if (god.get(ability.getKey()).equals(godname) && !ability.getKey().substring(2).equals("M")) {
                double cd = ability.getValue() + 3.0;
                newCD.put(ability.getKey(), cd);
            }
        }
        return newCD;
    }

    private Map<String, Double> morriganUpdate(Map<String, Double> cc, double morTime, double oldTime) {
        Map<String, Double> updateMap = new HashMap<>(cc);

        if (oldTime == 0.0 && morTime > 0) {
            for (HashMap.Entry<String, Double> ab : cc.entrySet()) {
                if (ab.getKey().equals("MM1")) {
                    double cd = ab.getValue() - 999;
                    updateMap.put("MM1", cd);
                }
            }
        }
        if (cc.get("MM1") < -900 && morTime >= 9.0) {
            for (HashMap.Entry<String, Double> ab : cc.entrySet()) {
                if (ab.getKey().substring(0, 1).equals("M") && !ab.getKey().substring(1, 2).equals("M")) {
                    double cd = ab.getValue() - 999;
                    updateMap.put(ab.getKey(), cd);

                } else if (ab.getKey().equals("MM1")) {
                    updateMap.put(ab.getKey(), necessary_cooldown.get("MM1") - 1.1);
                }
            }
        }

        return updateMap;
    }

    private double morriganCheck(Double duration, String ability, double time) {
        if (duration < 10) {
            if (duration == 0.0) {
                if (ability.substring(0, 1).equals("M") && !ability.substring(1, 2).equals("M")) {
                    return time;
                } else {
                    return 0.0;
                }
            } else {
                if (duration + time > 9.0) {
                    return 12.0;
                } else {
                    return duration + time;
                }
            }
        }
        return 12.0;
    }

    private double yemojaOmni(double omni, double time, String ability, double rebuke) {
        double newOmni;
        if (ability.equals("YY1")) {
            newOmni = omni - 4.0 + time * 0.4;
            if (rebuke < 7.0) {
                newOmni = newOmni + time * 0.5;
            }
        } else {
            newOmni = omni + time * 0.4;
            if (rebuke < 7.0) {
                newOmni = newOmni + time * 0.5;
            }
            if (newOmni > 10.0) {
                newOmni = 10.0;
            }
        }
        return newOmni;
    }

    private double yemojaRebuke(double time, String ability, double rebuke) {
        if (ability.equals("YYU")) {
            return 0.0;
        } else {
            return rebuke + time;
        }
    }

    private Map<String, Double> omniCheck(double omni, Map<String, Double> cc, Set<String> gods) {
        Map<String, Double> newCD = new HashMap<>(cc);
        if (gods.contains("Yemoja")) {
            if (omni < 4.0) {
                newCD.put("YY1", 0.0);
            } else {
                newCD.put("YY1", 101.0);
            }
        }
        return newCD;
    }

    public Set<String> getGods() {
        return new HashSet<>(godNames);
    }

    public ArrayList<String> getOrder() {
        return new ArrayList<>(order);
    }
}