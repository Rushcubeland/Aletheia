package fr.rushcubeland.aletheia.utils;

import fr.rushcubeland.aletheia.jda.JDAManager;
import net.dv8tion.jda.api.entities.Role;

public enum RoleUnit {

    BOT("Bot", "603879232455704576"),
    ALETHEIA("Aletheia", "932947641959084083"),
    JOUEUR("Joueur", "603870153494495233"),
    HELPER("Helper", "602568691325993102"),

    MODERATEUR("Modérateur", "602568643737419805"),
    DEVELOPPEUR("Développeur", "602568561193386015"),
    BUILDER("Builder", "603879681577713664"),
    RESPONSABLE("Responsable", "603879567651766282"),
    ADMIN("Administrateur", "602568434307301395");


    private final String name;
    private final String id;

    RoleUnit(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Role getRole(){
        return JDAManager.getShardManager().getRoleById(this.id);
    }
}
