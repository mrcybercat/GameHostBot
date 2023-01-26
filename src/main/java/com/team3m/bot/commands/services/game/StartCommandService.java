package com.team3m.bot.commands.services.game;

import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedHandler;
import com.team3m.game.abstracts.DefaultGame;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.stereotype.Service;

@Service
public class StartCommandService {

    public void startGame(SlashCommandEvent event){
        GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
        Lobby lobby =  guildManager.findByOwner(guildManager.getLobbies(), event.getUser().getId());

        if(lobby == null){
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Huh?", "You dont own an lobby to start", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
            return;
        }

        if(lobby.getSettings().getGame() instanceof DefaultGame){
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Meh...", "You should choose the game before we can start. Edit settings now", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
            return;
        }

        lobby.getSettings().getGame().start();
        lobby.setGameStarted(true);
        event.reply("Game stated!").setEphemeral(true).queue();
    }
}
