package fr.didi955.aletheia.reglement;

import fr.didi955.aletheia.jda.JDAManager;
import fr.didi955.aletheia.utils.JSONKeys;
import net.dv8tion.jda.api.entities.TextChannel;

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
