package com.team3m.bot.commands;

import com.team3m.bot.commands.abstracts.GameCmd;
import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedBuilder;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;

import java.util.List;

public class StartGameCmd extends GameCmd {

    public StartGameCmd()
    {
        this.name = "start";
        this.help = "shows a random cat";

        this.optionsData.add(
                new OptionData(OptionType.STRING, "name", "Input lobby name of your choosing", true)
        );
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("start")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());

            if(guildManager.getLobbies() != null  && guildManager.getLobbies().size() >= 25)
                event.replyEmbeds(CommandEmbedBuilder.createPlaceholderEmbed(event, "Ops!", "There no more lobbies available sorry", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();

            if(ownerDuplicationCheck(guildManager.getLobbies(), event.getUser().getId()))
                event.replyEmbeds(CommandEmbedBuilder.createPlaceholderEmbed(event, "Hey!", "You cant create multiple lobbies you sneaky", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();

            guildManager.getLobbies().add(new Lobby(event.getUser().getId(), event.getOption("name").getAsString()));

            event.replyEmbeds(CommandEmbedBuilder.createPlaceholderEmbed(event, "Please confirm your chose", " ", ColorHandler.StatusColorEnum.SUCCESS))
                .addActionRow(
                    Button.success("create", Emoji.fromUnicode("U+2714")), // Button with only a label
                    Button.secondary("discard", Emoji.fromUnicode("U+274C"))) // Button with only an emoji
                .setEphemeral(true).queue();
        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("create")) {
            event.replyEmbeds(CommandEmbedBuilder.createPlaceholderEmbed(event, "Lobby successfully created", " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue(); // send a message in the channel
        } else if (event.getComponentId().equals("discard")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            removeByOwner(guildManager.getLobbies(), event.getGuild().getId());
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
}

//event.getMessage().delete().queue();
//event.getMessage().delete().queue();
