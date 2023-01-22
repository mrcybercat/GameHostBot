package com.team3m.bot.commands.trash.inter_lobby;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedHandler;
import com.team3m.bot.util.EmojiHandler;
import com.team3m.game.abstracts.DefaultGame;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.coup.CoupGame;
import com.team3m.game.mafia.MafiaGame;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;


import java.util.ArrayList;
import java.util.List;
/*
public class JoinGameCmd extends Command {

    public JoinGameCmd() {
        this.name = "join";
        this.help = "This command gives you a list of all lobbies in guild";
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("join")) {

            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());

            if(guildManager == null){
                event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "ERROR!", "Something wrong with guild", ColorHandler.StatusColorEnum.ERROR)).setEphemeral(true).queue();
                return;
            }

            if(guildManager.getLobbies().size() == 0){
                event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Ops...", "There are no lobbies to join sorry", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return;
            }

            List<SelectOption> lobbies = existingLobbiesToSelectOptions(guildManager.getLobbies());

            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Lobbies", "Please choose lobby from the dropdown menu", ColorHandler.StatusColorEnum.SUCCESS))
                    .addActionRow(SelectionMenu.create("choose-lobby").addOptions(lobbies).build()).setEphemeral(true).queue();
        }
    }

    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        if (event.getComponentId().equals("choose-lobby")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            Lobby lobbySelected = guildManager.getLobbies().get(Integer.parseInt((event.getValues().get(0)).substring(6)));

            if(lobbySelected.getPlayerIDs().contains(event.getUser().getId())){
                event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Sorry pal..\n" ,"You can not join lobby twice", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return;    // Check if is  in this lobby
            }

            if(guildManager.getLobbyIndexByUserID(guildManager.getLobbies(), event.getUser().getId()) != null){
                event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Hey you!\nYou must first leave other lobby before joining this one\nDo you want to leave previous lobby and join this one?", event.getValues().get(0) + " - " + lobbySelected.getLobbyName() , ColorHandler.StatusColorEnum.WARNING))
                    .addActionRow(
                        Button.success("join", Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.CONFIRM))), // Button with only a label
                        Button.secondary("cancel", Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.DISCARD)))) // Button with only an emoji
                    .setEphemeral(true).queue();
                return; // Check if already in any other lobby bby
            }

            lobbySelected.getPlayerIDs().add(event.getUser().getId());
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Connected to " + lobbySelected.getLobbyName(), " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue();
        }
    }
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("join")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            Integer lobbyIndexToLeave = guildManager.getLobbyIndexByUserID(guildManager.getLobbies(), event.getUser().getId());
            Lobby lobbySelectedToJoin = guildManager.getLobbies().get(Integer.parseInt((event.getMessage().getEmbeds().get(0).getFields().get(0).getValue().substring(6).split(" ")[0])));

            if (lobbyIndexToLeave == null) {
                event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Uhm", "You cant leave lobby you do not enter any", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return; // Check if already in any other lobby bby
            }

            if (guildManager.getLobbies().get(lobbyIndexToLeave).equals(lobbySelectedToJoin)) {
                event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "HEY", "Stop presing this button you dont want leave lobby you just joined!", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return; // Check if already in any other lobby bby
            }

            guildManager.getLobbies().get(lobbyIndexToLeave).getPlayerIDs().remove(event.getUser().getId());
            lobbySelectedToJoin.getPlayerIDs().add(event.getUser().getId());

            event.replyEmbeds(
                CommandEmbedHandler.createCommandEmbed(event, "Left lobby " + guildManager.getLobbies().get(lobbyIndexToLeave).getLobbyName(), " ", ColorHandler.StatusColorEnum.SUCCESS),
                CommandEmbedHandler.createCommandEmbed(event, "Joined lobby " + lobbySelectedToJoin.getLobbyName(), " ", ColorHandler.StatusColorEnum.SUCCESS))
            .setEphemeral(true).queue();
        } else if (event.getComponentId().equals("cancel")) {
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Got it staying in this lobby", " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue(); // send a message in the channel
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

    private List<SelectOption> existingLobbiesToSelectOptions(List<Lobby> lobbies){
        List<SelectOption> lobbiesOptions = new ArrayList<>();

        for (int i = 0; i < lobbies.size(); i++) {
            lobbiesOptions.add(
                    SelectOption.of(lobbies.get(i).getLobbyName(), "lobby_" + i) //  way to create a SelectOption
                            .withDescription(
                                    getGameName(lobbies.get(i)) + " "
                                            +  lobbies.get(i).getPlayerIDs().size() + " / "
                                            +  lobbies.get(i).getSettings().getMaxPlayers()) // this time with a description
                            .withEmoji(Emoji.fromUnicode(getGameEmoji(lobbies.get(i)))) // and an emoji
            );
        }

        return lobbiesOptions;
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

//event.reply("Connected to " + event.getValues().get(0)).setEphemeral(true).queue();
//GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
//guildManager.removeByOwner(guildManager.getLobbies(), event.getUser().getId());

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

