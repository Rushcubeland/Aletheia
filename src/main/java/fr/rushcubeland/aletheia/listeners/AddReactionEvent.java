package fr.rushcubeland.aletheia.listeners;

import fr.rushcubeland.aletheia.Aletheia;
import fr.rushcubeland.aletheia.utils.RoleUnit;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddReactionEvent extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        Role role = RoleUnit.JOUEUR.getRole();

        MessageChannel channel = event.getChannel();
        MessageReaction messageReaction = event.getReaction();

        if(channel.getId().equals(Aletheia.CONFIGURATION.getString("channelReglementID", "id"))){
            Member member = event.getMember();
            if(!(member != null && member.getUser().equals(event.getJDA().getSelfUser()))){
                if(messageReaction.getEmoji().getName().equals("✅")){
                    List<Role> roles;
                    if (member != null) {
                        roles = new ArrayList<>(member.getRoles());
                        roles.add(role);
                        event.getGuild().modifyMemberRoles(member, roles).queue();
                    }
                }
            }
        }
    }

}
