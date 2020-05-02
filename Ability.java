package chain;

public class Ability {
    private final String name;
    private final double duration;
    private final double cooldown;
    private final int diminish;
    private final String godName;

    // GRAMMAR - 1st & 2nd character = God Name
    //         - 3rd character = Ability # or title
    //             - M = meditation
    //             - B = bracer
    //             - U = ultimate ability (ONLY MAGICAL GODS WHO CAN BUY MYRDDIN)
    //             - Numbers = any other ability
    //             - Add morrigan abilities as - "MM1" for the stun, "M(1st letter of other god)1/2/U" for ultimate choices

    // DR:: 0 = Soft CC / NO EFFECT ON DR; 1 = Hard CC / Regular DR; 2 = Green Shield / Adds Stack, Same Duration Always
    public Ability(String name, double dur, double cd, int dr, String godName) {
        this.name = name;
        this.duration = dur;
        this.cooldown = cd;
        this.diminish = dr;
        this.godName = godName;
    }

    public double getRCD() {
        return cooldown;
    }

    public double getDuration() {
        return duration;
    }

    public int getDR() {
        return diminish;
    }

    public String getName() {
        return name;
    }

    public String getGodName() {
        return this.godName;
    }
}
