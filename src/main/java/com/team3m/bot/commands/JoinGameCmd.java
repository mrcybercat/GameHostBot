package com.team3m.bot.commands;

import com.team3m.bot.commands.abstracts.GameCmd;
import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedBuilder;
import com.team3m.bot.util.EmojiHandler;
import com.team3m.game.abstracts.DefaultGame;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.coup.CoupGame;
import com.team3m.game.mafia.MafiaGame;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;


import java.util.ArrayList;
import java.util.List;

public class JoinGameCmd extends GameCmd {

    public JoinGameCmd() {
        this.name = "join";
        this.help = "shows a random cat";
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("join")) {

            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());

            if(guildManager == null){
                event.replyEmbeds(CommandEmbedBuilder.createPlaceholderEmbed(event, "ERROR!", "Something wrong with guild", ColorHandler.StatusColorEnum.ERROR)).setEphemeral(true).queue();
                return;
            }

            if(guildManager.getLobbies().size() == 0){
                event.replyEmbeds(CommandEmbedBuilder.createPlaceholderEmbed(event, "Ops...", "There are no lobbies to join sorry", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return;
            }

            List<SelectOption> lobbies = new ArrayList<>();
            for (int i = 0; i < guildManager.getLobbies().size(); i++) {

                lobbies.add(
                    SelectOption.of(guildManager.getLobbies().get(i).getLobbyName(), "lobby_" + i) // another way to create a SelectOption
                        .withDescription(
                            getGameName(guildManager.getLobbies().get(i)) + " "
                            +  guildManager.getLobbies().get(i).getPlayerIDs().size() + " / "
                            +  guildManager.getLobbies().get(i).getSettings().getMaxPlayers()
                        ) // this time with a description
                        .withEmoji(Emoji.fromUnicode(getGameEmoji(guildManager.getLobbies().get(i)))) // and an emoji
                );
            }

            event.replyEmbeds(CommandEmbedBuilder.createPlaceholderEmbed(event, "Lobbies", "Please choose lobby from the dropdown menu", ColorHandler.StatusColorEnum.SUCCESS))
                    .addActionRow(SelectionMenu.create("choose-lobby").addOptions(lobbies).build()).setEphemeral(true).queue();
        }
    }

    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        if (event.getComponentId().equals("choose-lobby")) {
        //    event.getMessage().delete().queue();
            event.reply("Connected to " + event.getValues().get(0)).setEphemeral(true).queue();
        }
    }

    private String getGameEmoji(Lobby lobby){
        if(lobby.getSettings().getGame() instanceof DefaultGame)
            return EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.DEFAULT_GAME);
        if(lobby.getSettings().getGame() instanceof MafiaGame)
            return EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.MAFIA);
        if(lobby.getSettings().getGame() instanceof CoupGame)
            return EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.COUP);

        return EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.DEFAULT_GAME);
    }

    private String getGameName(Lobby lobby){
        if(lobby.getSettings().getGame() instanceof DefaultGame)
            return "Not chosen";
        if(lobby.getSettings().getGame() instanceof MafiaGame)
            return "Mafia";
        if(lobby.getSettings().getGame() instanceof CoupGame)
            return "Coup";

        return "Not chosen";
    }

}

/*

  @Override
    public void onSlashCommand(SlashCommandEvent event){
        if(event.getCommandString().contains("join")){
            event.replyEmbeds(CommandPlaceholderEmbed.createPlaceholderEmbed(event, "join").build()).queue();
        }
    }   //.addOption("Pizza", "pizza", "Classic") // SelectOption with only the label, value, and description}

 if(!event.getMessage().getAuthor().equals(event.getUser())){
                event.replyEmbeds(CommandEmbedBuilder.createPlaceholderEmbed(event, "Hey!", "You cant touch that you damn wizard", ColorHandler.StatusColorEnum.ERROR)).setEphemeral(true).queue();
            }

 */

