package fr.didi955.aletheia.utils;

import fr.didi955.aletheia.jda.JDAManager;
import net.dv8tion.jda.api.entities.Role;

public enum RoleUnit {

    JOUEUR("Joueur", "603870153494495233");

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
