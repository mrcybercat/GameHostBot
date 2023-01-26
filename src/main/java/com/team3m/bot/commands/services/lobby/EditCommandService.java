package com.team3m.bot.commands.services.lobby;

import com.team3m.bot.commands.controllers.lobby.EditCommandController;
import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedHandler;
import com.team3m.bot.util.EmojiHandler;
import com.team3m.bot.util.SelectionMenuHandler;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditCommandService {


    public void editLobby(SlashCommandEvent event) {
        GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
        if (guildManager.findByOwner(guildManager.getLobbies(), event.getUser().getId()) == null) {
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Nah-a", "You dont own a lobby yo edit", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
            return; // Check if already in any other lobby bby
        }

        event.replyEmbeds(
                        CommandEmbedHandler.createCommandEmbed(event, "Game", "Please choose game from the dropdown menu", ColorHandler.StatusColorEnum.SUCCESS),
                        CommandEmbedHandler.createCommandEmbed(event, "Players", "Please choose player count from the dropdown menu", ColorHandler.StatusColorEnum.SUCCESS))
                .addActionRow(SelectionMenuHandler.createButton(new EditCommandController(), "game", gameOptions()))
                .addActionRow(SelectionMenuHandler.createButton(new EditCommandController(), "playerCount", playerCountOptions()))
                .setEphemeral(true).queue();
    }


    public void chooseLobbyToEdit(SelectionMenuEvent event) {
        String option = event.getComponentId().split("_")[2];
        Lobby lobbyToEdit = getLobbyToEdit(event) == null ? null : getLobbyToEdit(event);

        switch (option) {
            case "confirm":
                confirmChooseLobbyToEdit(event, lobbyToEdit);

            case "discard":
                discardChooseLobbyToEdit(event, lobbyToEdit);

        }
    }

    private void confirmChooseLobbyToEdit(SelectionMenuEvent event, Lobby lobbyToEdit) {
        if (lobbyToEdit == null) return;
        lobbyToEdit.getSettings().setGameByName(event.getValues().get(0));
        event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Lobby" + lobbyToEdit.getLobbyName() + "successfully edited ", " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue();

    }

    private void discardChooseLobbyToEdit(SelectionMenuEvent event, Lobby lobbyToEdit) {
        if (lobbyToEdit == null) return;
        lobbyToEdit.getSettings().setMaxPlayers(Integer.valueOf(event.getValues().get(0).substring(8)));
        event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Lobby" + lobbyToEdit.getLobbyName() + "successfully edited ", " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue();

    }

    private Lobby getLobbyToEdit(SelectionMenuEvent event) {
        GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
        Lobby lobbyToEdit = guildManager.findByOwner(guildManager.getLobbies(), event.getUser().getId());

        if (lobbyToEdit == null) {
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Ahh", "You dont own a lobby anymore", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
            return null; // Check if already in any other lobby bby
        }
        return lobbyToEdit;
    }

    private List<SelectOption> gameOptions() {
        return List.of(
                SelectOption.of("Mafia", "mafia") //  way to create a SelectOption
                        .withDescription("A classic mafia game") // this time with a description
                        .withEmoji(Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.MAFIA))),
                SelectOption.of("Coup", "coup") //  way to create a SelectOption
                        .withDescription("A classic coup game") // this time with a description
                        .withEmoji(Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.COUP)))); // and an emoji
    }

    private List<SelectOption> playerCountOptions() {
        List<SelectOption> playerCountOptions = new ArrayList<>();
        for (int i = 3; i <= 10; i++) {
            playerCountOptions.add(
                    SelectOption.of("Max players " + i, "players_" + i) //  way to create a SelectOption
                            .withEmoji(Emoji.fromUnicode(getPlayerEmoji(i))));
        }
        return playerCountOptions;
    }

    private String getPlayerEmoji(Integer playerCount) {
        if (playerCount >= 3 && playerCount <= 4)
            return EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.FEW_PLAYERS);
        if (playerCount >= 5 && playerCount <= 6)
            return EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.ENOUGH_PLAYERS);
        if (playerCount >= 7 && playerCount <= 8)
            return EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.MANY_PLAYERS);
        if (playerCount >= 9 && playerCount <= 10)
            return EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.LOT_OF_PLAYERS);

        return EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.FEW_PLAYERS);
    }

}
