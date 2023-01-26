package com.team3m.bot.commands.services.game;

import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedHandler;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.stereotype.Service;

@Service
public class StopCommandService {

    public void stopGame(SlashCommandEvent event) {
        GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
        Lobby lobby =  guildManager.findByOwner(guildManager.getLobbies(), event.getUser().getId());

        if(lobby == null){
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Huh?", "You dont own an lobby to stop", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
            return;
        }

        lobby.getSettings().getGame().stop();
        lobby.setGameStarted(false);
        event.reply("Game stopped!").setEphemeral(true).queue();
    }
}
