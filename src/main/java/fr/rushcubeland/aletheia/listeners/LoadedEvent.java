package fr.rushcubeland.aletheia.listeners;

import fr.rushcubeland.aletheia.reglement.Reglement;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class LoadedEvent extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        Reglement.MessageReglement();
    }

}
