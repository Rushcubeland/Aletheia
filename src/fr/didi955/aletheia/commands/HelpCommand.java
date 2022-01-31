package fr.didi955.aletheia.commands;

import fr.didi955.aletheia.Aletheia;
import fr.didi955.aletheia.jda.JDAManager;
import fr.didi955.aletheia.utils.JSONKeys;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class HelpCommand {

    private Aletheia aletheia;

    public HelpCommand(Aletheia aletheia) {
        this.aletheia = aletheia;
    }

    @Command(name="help", description="Liste toutes les commandes", sender=Command.ExecutorType.USER)
    public void onHelp(){
        TextChannel channel = JDAManager.getShardManager().getTextChannelById(JSONKeys.CHANNEL_COMMANDS_ID.getConfigString());
        if(channel != null){
            MessageBuilder builder = new MessageBuilder();
            builder.append("__Liste des commandes__:");
            channel.sendMessage(builder.build()).queue();
            for(CommandsUnit cmd : CommandsUnit.values()){
                if(cmd.getExecutorType().equals(Command.ExecutorType.USER)) {
                    builder = new MessageBuilder();
                    builder.append("**!").append(cmd.getName()).append("**: ").append(cmd.getDescription());
                    channel.sendMessage(builder.build()).queue();
                }
            }
        }
    }
}
