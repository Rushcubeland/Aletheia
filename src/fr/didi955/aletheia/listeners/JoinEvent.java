package fr.didi955.aletheia.listeners;

import fr.didi955.aletheia.utils.JSONKeys;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

import java.awt.*;

public class JoinEvent {

    public static void onJoin(GuildMemberJoinEvent event){
        EmbedBuilder builder = new EmbedBuilder();
        User user = event.getUser();
        builder.setTitle(JSONKeys.TITLE_WELCOME.getMessageString());
        builder.setImage(user.getAvatarUrl());
        builder.setDescription(JSONKeys.MESSAGE_WELCOME.getMessageString().replace("%user%", user.getAsMention()));
        builder.setColor(Color.GREEN);
        TextChannel channel = event.getGuild().getTextChannelById(JSONKeys.CHANNEL_JOIN_ID.getConfigString());
        if(channel != null){
            channel.sendMessageEmbeds(builder.build()).queue();
        }

    }
}
