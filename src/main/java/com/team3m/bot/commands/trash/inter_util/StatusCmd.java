package com.team3m.bot.commands.trash.inter_util;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
/*
public class StatusCmd extends Command {

    public StatusCmd() {
        this.name = "status";
        this.help = "Gives some debug info";
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("status")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            MessageBuilder messageBuilder = new MessageBuilder();
            messageBuilder.append("Webhook ping: " + event.getJDA().getGatewayPing() + " ms\n");
            messageBuilder.append("Active guild lobbies: " + guildManager.getLobbies().size());
            event.reply(messageBuilder.build()).queue();
        }
    }
}

 */
