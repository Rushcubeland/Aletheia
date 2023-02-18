package fr.rushcubeland.aletheia.listeners;

import fr.rushcubeland.aletheia.Aletheia;
import fr.rushcubeland.aletheia.jda.JDAManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QuitEvent extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        TextChannel channel = JDAManager.getShardManager().getTextChannelById(Aletheia.CONFIGURATION.getString("channelReglementID", "id"));
        if (channel != null) {
            MessageHistory history = channel.getHistory();
            List<Message> msgs = history.getRetrievedHistory();
            for (Message msg : msgs){
                msg.removeReaction(Emoji.fromUnicode("✅"), event.getUser()).queue();
            }
        }

    }
}
