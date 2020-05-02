package chain;

import java.util.HashSet;
import java.util.Set;

public class Setup {
    private Set<God> gods = new HashSet<>();

    public Setup(Set<String> godNames) {
        for (String god: godNames) {

            if (god.equals("Kumbhakarna")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("KK1", 3.0, 18 * 0.6 + 0.5, 1, "Kumbhakarna"));
                abilities.add(new Ability("KK2", 2.0, 10 * 0.6 + 1.5, 0, "Kumbhakarna"));
                abilities.add(new Ability("KKU", 2.2, 45.0, 2, "Kumbhakarna"));
                abilities.add(new Ability("KKB", 0.0, 120.0, 0, "Kumbhakarna"));
                abilities.add(new Ability("DKM", 0.0, 120.0, 0, "Kumbhakarna"));
                God Kumbhakarna = new God("Kumbhakarna", abilities);
                this.gods.add(Kumbhakarna);
            }

            if (god.equals("Artemis")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("AR1", 2.0, 10 * 0.6 + 0.2, 0, "Artemis"));
                abilities.add(new Ability("ARB", 0.0, 120.0, 0, "Artemis"));
                abilities.add(new Ability("DAM", 0.0, 120.0, 0, "Artemis"));
                God Artemis = new God("Artemis", abilities);
                this.gods.add(Artemis);
            }

            if (god.equals("Scylla")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("SC1", 1.75, 10 * 0.6 + 0.2, 0, "Scylla"));
                abilities.add(new Ability("SCB", 0.0, 120.0, 0, "Scylla"));
                abilities.add(new Ability("DSM", 0.0, 120.0, 0, "Scylla"));
                God Scylla = new God("Scylla", abilities);
                this.gods.add(Scylla);
            }

            if (god.equals("Nu Wa")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("NWA", 1.5, 1.0 / 2.0 * 6.0, 0, "Nu Wa"));
                abilities.add(new Ability("DWM", 0.0, 120.0, 0, "Nu Wa"));
                God Nu_Wa = new God("Nu Wa", abilities);
                this.gods.add(Nu_Wa);
            }

            if (god.equals("Nox")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("NX1", 2.0, 8.0, 2, "Nox")); //change this to 0 if only using the cripple
                abilities.add(new Ability("NXU", 0.0, 60.0, 0, "Nox")); //change this to 0 if only using the cripple
                abilities.add(new Ability("NXB", 0.0, 120.0, 0, "Nox"));
                abilities.add(new Ability("DXM", 0.0, 120.0, 0, "Nox"));
                God Nox = new God("Nox", abilities);
                this.gods.add(Nox);
            }

            if (god.equals("The Morrigan")) {
                Set<Ability> abilities = new HashSet<>();
                God The_Morrigan = new God("The Morrigan", abilities);
                this.gods.add(The_Morrigan);
            }

            if (god.equals("Janus")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("JN1", 1.5, 12.0 * 0.6 + 0.3, 2, "Janus"));
                abilities.add(new Ability("JNU", 0.0, 90.0, 0, "Janus"));
                abilities.add(new Ability("JNB", 0.0, 120.0, 0, "Janus"));
                abilities.add(new Ability("DJM", 0.0, 120.0, 0, "Janus"));
                God Janus = new God("Janus", abilities);
                this.gods.add(Janus);
            }

            if (god.equals("Hera")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("HE1", 2.0, 14 * 0.6 + 0.6, 2, "Hera"));
                abilities.add(new Ability("HEB", 0.0, 120.0, 0, "Hera"));
                abilities.add(new Ability("HEU", 0.0, 100.0 * 0.6, 0, "Hera"));
                abilities.add(new Ability("DHM", 0.0, 120.0, 0, "Hera"));
                God Hera = new God("Hera", abilities);
                this.gods.add(Hera);
            }

            if (god.equals("Tyr")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("TR1", 1.8, 10 * 0.6 + 1.3, 2, "Tyr"));
                abilities.add(new Ability("TRB", 0.0, 120.0, 0, "Tyr"));
                abilities.add(new Ability("DTM", 0.0, 120.0, 0, "Tyr"));
                God Tyr = new God("Tyr", abilities);
                this.gods.add(Tyr);
            }

            if (god.equals("Hercules")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("HR1", 1.7, 14 * 0.6 + 0.6, 2, "Hercules"));
                abilities.add(new Ability("HRB", 0.0, 120.0, 0, "Hercules"));
                abilities.add(new Ability("DRM", 0.0, 120.0, 0, "Hercules"));
                God Hercules = new God("Hercules", abilities);
                this.gods.add(Hercules);
            }

            if (god.equals("Ne Zha")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("NZ1", 1.0, 14 * 0.6, 2, "Ne Zha"));
                abilities.add(new Ability("NZ2", 4.0, 90, 2, "Ne Zha"));
                abilities.add(new Ability("NZB", 0.0, 120.0, 0, "Ne Zha"));
                abilities.add(new Ability("DZM", 0.0, 120.0, 0, "Ne Zha"));
                God Ne_Zha = new God("Ne Zha", abilities);
                this.gods.add(Ne_Zha);
            }

            if (god.equals("Heimdallr")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("HM1", 6.0, 100.0 * 0.6, 2, "Heimdallr"));
                abilities.add(new Ability("HMB", 0.0, 120.0, 0, "Heimdallr"));
                abilities.add(new Ability("DLM", 0.0, 120.0, 0, "Heimdallr"));
                God Heimdallr = new God("Heimdallr", abilities);
                this.gods.add(Heimdallr);
            }

            if (god.equals("Yemoja")) {
                Set<Ability> abilities = new HashSet<>();
                abilities.add(new Ability("YY1", 1.4, 100.0, 1, "Yemoja"));
                abilities.add(new Ability("YYU", 0.0, 100.0, 0, "Yemoja"));
                abilities.add(new Ability("YYB", 0.0, 120.0, 0, "Yemoja"));
                abilities.add(new Ability("DYM", 0.0, 120.0, 0, "Yemoja"));
                God Yemoja = new God("Yemoja", abilities);
                this.gods.add(Yemoja);
            }

        }
    }

    public Set<God> getGods() {
        return new HashSet<>(gods);
    }
}
