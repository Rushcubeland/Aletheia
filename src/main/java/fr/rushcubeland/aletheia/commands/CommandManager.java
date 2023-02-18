package fr.rushcubeland.aletheia.commands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for(CommandUnit commandUnit : CommandUnit.values()){
            if(event.getName().equals(commandUnit.getName())){
                commandUnit.getCommand().execute(event);
            }
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        for(CommandUnit commandUnit : CommandUnit.values()){
            commandData.add(Commands.slash(commandUnit.getName(), commandUnit.getDescription()));
        }

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}
