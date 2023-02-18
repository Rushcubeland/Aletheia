package fr.rushcubeland.aletheia.reglement;

import fr.rushcubeland.aletheia.jda.JDAManager;
import fr.rushcubeland.aletheia.utils.JSONKeys;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Reglement {

    public static void MessageReglement(){

        TextChannel channel = JDAManager.getShardManager().getTextChannelById(JSONKeys.CHANNEL_REGLEMENT_ID.getConfigString());
        if(channel != null){
            channel.getHistory().retrievePast(1).queue(messages -> {
                if(messages.size() == 0){
                    channel.sendMessage(JSONKeys.MESSAGES_REGLEMENT.getMessageString()).queue();
                }
            });
        }
    }
}
