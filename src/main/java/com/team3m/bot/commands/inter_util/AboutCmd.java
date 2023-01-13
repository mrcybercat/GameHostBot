package com.team3m.bot.commands.inter_util;

import com.team3m.bot.commands.abstracts.GameCmd;
import com.team3m.bot.commands.inter_game.StartGameCmd;
import com.team3m.bot.commands.inter_game.StopGameCmd;
import com.team3m.bot.commands.inter_lobby.*;
import com.team3m.bot.util.ColorHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ApplicationInfo;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.rmi.dgc.Lease;

public class AboutCmd extends GameCmd {

    public AboutCmd() {
        this.name = "about";
        this.help = "Gives some info about this darn bot";
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("about")) {
            EmbedBuilder builder = new EmbedBuilder();
            ApplicationInfo info = event.getJDA().retrieveApplicationInfo().complete();

            builder.setAuthor(event.getMember().getUser().getAsTag() + " (" + info.getId() + ")", null,
                    info.getIconUrl());
            builder.setDescription("This is discord bot for playing games.\n" +
                    "You can [`invite`](" + info.getInviteUrl() + ") to your server btw");
            builder.addField("Commands",
                "**"+ new AboutCmd().getName() +  "**\t  -  This command right in front of you\n"+
                "**"+ new CreateGameCmd().getName() +  "**\t  -  " + new CreateGameCmd().getHelp() + "\n"+
                "**"+ new DeleteGameCmd().getName() +  "**\t  -  " + new DeleteGameCmd().getHelp() + "\n" +
                "**"+ new EditGameSettingsCmd().getName() +  "**\t  -  " + new EditGameSettingsCmd().getHelp() + "\n" +
                "**"+ new JoinGameCmd().getName() +  "**\t  -  " + new JoinGameCmd().getHelp() + "\n" +
                "**"+ new LeaveGameCmd().getName() +  "**\t  -  " + new LeaveGameCmd().getHelp() + "\n" +
                "**"+ new StartGameCmd().getName() +  "**\t  -  " + new StartGameCmd().getHelp() + "\n" +
                "**"+ new StopGameCmd().getName() +  "**\t  -  " + new StopGameCmd().getHelp(),
                false);

            builder.addField("Stats", event.getJDA().getUsers().size() + " Users\n" + event.getJDA().getGuilds().size() + " Servers", true);
            builder.addField("", event.getJDA().getTextChannels().size() + " Text Channels\n" + event.getJDA().getVoiceChannels().size() + " Voice Channels", true);


            builder.setImage(info.getIconUrl());
            builder.setThumbnail(info.getIconUrl());
            builder.setFooter(info.getName(), info.getIconUrl());
            builder.setColor(ColorHandler.getColor(ColorHandler.StatusColorEnum.VICTORY));

            event.replyEmbeds(builder.build()).queue();
        }
    }
}
