package com.team3m.bot.commands.inter_lobby;

import com.team3m.bot.commands.abstracts.GameCmd;
import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedBuilder;
import com.team3m.bot.util.EmojiHandler;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;

public class CreateGameCmd extends GameCmd {


    public CreateGameCmd()
    {
        this.name = "create";
        this.help = "Creates a lobby for you and your friends";

        this.optionsData.add(
                new OptionData(OptionType.STRING, "name", "Input lobby name of your choosing", true)
        );
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("create")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());

            if(guildManager.getLobbies() != null  && guildManager.getLobbies().size() >= 25){
                event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Ops!", "There no more lobbies available sorry", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return;
            }

            if(guildManager.ownerDuplicationCheck(guildManager.getLobbies(), event.getUser().getId())){
                event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Hey!", "You cant create multiple lobbies you sneaky", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return;
            }
            //guildManager.getLobbies().add(new Lobby(event.getUser().getId());

            event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Please confirm your chose\nLobby name",  event.getOption("name").getAsString(), ColorHandler.StatusColorEnum.SUCCESS))
                .addActionRow(
                    Button.success("create", Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.CONFIRM))), // Button with only a label
                    Button.secondary("discard", Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.DISCARD)))) // Button with only an emoji
                .setEphemeral(true).queue();
        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("create")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            if(guildManager.ownerDuplicationCheck(guildManager.getLobbies(), event.getUser().getId())){
                event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Hey!", "You cant create multiple lobbies you sneaky", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return;
            }
            guildManager.getLobbies().add(new Lobby(event.getUser().getId(), event.getMessage().getEmbeds().get(0).getFields().get(0).getValue()));
            event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Lobby successfully created", " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue(); // send a message in the channel
        } else if (event.getComponentId().equals("discard")) {
            event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Lobby creation discarded", " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue(); // send a message in the channel

            //GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            //guildManager.removeByOwner(guildManager.getLobbies(), event.getUser().getId());
        }
    }
}
