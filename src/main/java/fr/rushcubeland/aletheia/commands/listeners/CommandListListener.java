package fr.rushcubeland.aletheia.commands.listeners;

import fr.rushcubeland.aletheia.commands.Command;
import fr.rushcubeland.aletheia.commands.CommandUnit;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandListListener implements Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        StringBuilder builder = new StringBuilder("__**Voici la liste des commandes disponibles**__ :\n\n");
        for (CommandUnit commandUnit : CommandUnit.values()) {
            builder.append("**/").append(commandUnit.getName()).append("**").append(" : ").append(commandUnit.getDescription()).append("\n");
        }
        event.reply(builder.toString()).setEphemeral(true).queue();
    }
}
