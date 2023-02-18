package fr.rushcubeland.aletheia.listeners;

import fr.rushcubeland.aletheia.utils.JSONKeys;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class JoinEvent extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
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
