package fr.rushcubeland.aletheia.jda;

import fr.rushcubeland.aletheia.Aletheia;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.EnumSet;

public class JDAManager {

    private static final ShardManager shardManager = buildShard();

    public static ShardManager getShardManager() {
        return shardManager;
    }

    private static ShardManager buildShard(){

        EnumSet<GatewayIntent> intents = EnumSet.of(
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_BANS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_TYPING,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_EMOJIS_AND_STICKERS
        );

        return DefaultShardManagerBuilder.create(intents).
                setToken(Aletheia.CONFIGURATION.getString("token", "Token")).
                setShardsTotal(Aletheia.CONFIGURATION.getInt("shardTotal", 1)).
                setMemberCachePolicy(MemberCachePolicy.ALL).
                setChunkingFilter(ChunkingFilter.ALL).
                enableCache(CacheFlag.ONLINE_STATUS, CacheFlag.ACTIVITY)
                .build();
    }
}
