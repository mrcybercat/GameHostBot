package com.team3m.bot.commands.inter_lobby;

import com.team3m.bot.commands.abstracts.GameCmd;
import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedBuilder;
import com.team3m.game.abstracts.DefaultGame;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class StartGameCmd extends GameCmd {

    public StartGameCmd()
    {
        this.name = "start";
        this.help = "shows a random cat";
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("start")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            Lobby lobby =  guildManager.findByOwner(guildManager.getLobbies(), event.getUser().getId());

            if(lobby == null){
                event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Huh?", "You dont own an lobby to start", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return;
            }

            if(lobby.getSettings().getGame() instanceof DefaultGame){
                event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Meh...", "You should choose the game before we can start. Edit settings now", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return;
            }

            lobby.getSettings().getGame().start();
            event.reply("Game stated!").setEphemeral(true).queue();
        }
    }
/*
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("create")) {
            event.replyEmbeds(CommandEmbedBuilder.createPlaceholderEmbed(event, "Lobby successfully created", " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue(); // send a message in the channel
        } else if (event.getComponentId().equals("discard")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            removeByOwner(guildManager.getLobbies(), event.getUser().getId());
        }
    }

    private void removeByOwner(List<Lobby> lobbies, String ownerID){
        lobbies.remove(findByOwner(lobbies, ownerID));
    }

    private Lobby findByOwner(List<Lobby> lobbies, String ownerID){
        for(Lobby lobby : lobbies){
            if(lobby.getOwnerID().equals(ownerID)){
                return lobby;
            }
        }
        return null;
    }

    private boolean ownerDuplicationCheck(List<Lobby> lobbies, String ownerID) {
        if(lobbies == null){
            return false;
        }
        for (Lobby lobby: lobbies){
            if(lobby.getOwnerID().equals(ownerID)){
                return true;
            }
        }
        return false;
    }

 */
}

//event.getMessage().delete().queue();
//event.getMessage().delete().queue();
