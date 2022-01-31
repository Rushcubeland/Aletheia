package fr.didi955.aletheia.commands;

import fr.didi955.aletheia.Aletheia;
import fr.didi955.aletheia.jda.JDAManager;
import fr.didi955.aletheia.utils.JSONKeys;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class InfoCommand {

    private Aletheia aletheia;

    public InfoCommand(Aletheia aletheia) {
        this.aletheia = aletheia;
    }

    @Command(name="info", description="En savoir plus sur Rushcubland", sender=Command.ExecutorType.USER)
    public void onInfo(){
        TextChannel channel = JDAManager.getShardManager().getTextChannelById(JSONKeys.CHANNEL_COMMANDS_ID.getConfigString());
        if(channel != null){
            MessageBuilder builder = new MessageBuilder();
            builder.append("**Rushcubeland** est un serveur Minecraft mini-jeux, disponible à partir de " +
                    "la version 1.15 du jeu. Le thème de ce serveur se base sur la Grèce Antique et sa mythologie.");
            channel.sendMessage(builder.build()).queue();
        }
    }
}
