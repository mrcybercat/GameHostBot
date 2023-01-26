package com.team3m.bot.commands.services.lobby;

import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedHandler;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommandService {
    public void deleteLobby(SlashCommandEvent event) {
        GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
        //Integer lobbyIndex = guildManager.getLobbyIndexByUserID(guildManager.getLobbies(), event.getUser().getId());
        Lobby lobbyToDelete = guildManager.findByOwner(guildManager.getLobbies(), event.getUser().getId());

        if (lobbyToDelete == null) {
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Stop", "You cant delete lobby you doesnt own one", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
            return; // Check if already in any other lobby bby
        }

        guildManager.getLobbies().remove(lobbyToDelete);
        event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Lobby deleted " + lobbyToDelete.getLobbyName(), " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue();
    }
}
