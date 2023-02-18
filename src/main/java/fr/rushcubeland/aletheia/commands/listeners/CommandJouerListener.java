package fr.rushcubeland.aletheia.commands.listeners;

import fr.rushcubeland.aletheia.commands.Command;
import fr.rushcubeland.aletheia.utils.JSONKeys;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandJouerListener implements Command {

        @Override
        public void execute(SlashCommandInteractionEvent event) {
            event.reply(JSONKeys.SERVER_CONNEXION_INFOS.getMessageString()).setEphemeral(true).queue();
        }

}
