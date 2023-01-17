package com.team3m.bot.commands.inter_lobby;

import com.team3m.bot.commands.abstracts.GameCmd;
import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedBuilder;

import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;


public class LeaveGameCmd extends GameCmd {

    public LeaveGameCmd() {
        this.name = "leave";
        this.help = "You will leave the current lobby";
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("leave")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            Integer lobbyIndex = guildManager.getLobbyIndexByUserID(guildManager.getLobbies(), event.getUser().getId());

            if (lobbyIndex == null) {
                event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Uhm", "You cant leave lobby you do not enter any", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return; // Check if already in any other lobby bby
            }

            guildManager.getLobbies().get(lobbyIndex).getPlayerIDs().remove(event.getUser().getId());
            event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Left lobby " + guildManager.getLobbies().get(lobbyIndex).getLobbyName(), " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue();
        }
    }
}
