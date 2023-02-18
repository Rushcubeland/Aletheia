package fr.rushcubeland.aletheia.commands;

import fr.rushcubeland.aletheia.commands.listeners.CommandInfosListener;
import fr.rushcubeland.aletheia.commands.listeners.CommandJouerListener;
import fr.rushcubeland.aletheia.commands.listeners.CommandListListener;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;

public enum CommandUnit {

    INFOS("infos", "Affiche les informations du serveur", new CommandInfosListener(), new ArrayList<>()),
    JOUER("jouer", "Affiche les informations de connexion du serveur", new CommandJouerListener(), new ArrayList<>()),
    LIST("list", "Affiche la liste des commandes", new CommandListListener(), new ArrayList<>())

    ;

    private final String name;
    private final String description;
    private final Command command;
    private final ArrayList<OptionData> options;

    CommandUnit(String name, String description, Command command, ArrayList<OptionData> options) {
        this.name = name;
        this.description = description;
        this.command = command;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<OptionData> getOptions() {
        return options;
    }

    public Command getCommand() {
        return command;
    }
}
