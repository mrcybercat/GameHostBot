package com.team3m.bot.commands.services.general;

import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.stereotype.Service;

@Service
public class StatusCommandService {

    public void status(SlashCommandEvent event) {
        GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.append("Webhook ping: " + event.getJDA().getGatewayPing() + " ms\n");
        messageBuilder.append("Active guild lobbies: " + guildManager.getLobbies().size());
        event.reply(messageBuilder.build()).queue();
    }
}
