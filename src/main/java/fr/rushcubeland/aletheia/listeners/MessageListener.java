package fr.rushcubeland.aletheia.listeners;

import fr.rushcubeland.aletheia.Aletheia;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!event.getAuthor().isBot() && event.getMember() == null){
            return;
        }
        if(event.getChannel().getId().equals(Aletheia.CONFIGURATION.getString("channelReglementID", "id")) && event.getMember().getUser().equals(event.getJDA().getSelfUser())){
            event.getChannel().addReactionById(event.getMessageId(), Emoji.fromUnicode("✅")).queue();
        }
    }
}
