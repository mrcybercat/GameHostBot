package com.team3m.bot.commands.inter_lobby;

import com.team3m.bot.commands.abstracts.GameCmd;
import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedBuilder;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class DeleteGameCmd extends GameCmd {

    public DeleteGameCmd()
    {
        this.name = "delete";
        this.help = "Deletes the lobby you owns";
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("delete")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            //Integer lobbyIndex = guildManager.getLobbyIndexByUserID(guildManager.getLobbies(), event.getUser().getId());
            Lobby lobbyToDelete = guildManager.findByOwner(guildManager.getLobbies(), event.getUser().getId());

            if (lobbyToDelete == null) {
                event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Stop", "You cant delete lobby you doesnt own one", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return; // Check if already in any other lobby bby
            }

            guildManager.getLobbies().remove(lobbyToDelete);
            event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Lobby deleted " + lobbyToDelete.getLobbyName(), " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue();
        }
    }

}
