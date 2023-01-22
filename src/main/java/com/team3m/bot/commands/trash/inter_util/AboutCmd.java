package com.team3m.bot.commands.trash.inter_util;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.controllers.game.StartCommandController;
import com.team3m.bot.commands.controllers.game.StopCommandController;
import com.team3m.bot.commands.controllers.lobby.*;
import com.team3m.bot.util.ColorHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ApplicationInfo;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
/*
public class AboutCmd extends Command {

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
                "**"+ new CreateCommandController().getName() +  "**\t  -  " + new CreateCommandController().getHelp() + "\n"+
                "**"+ new DeleteCommandController().getName() +  "**\t  -  " + new DeleteCommandController().getHelp() + "\n" +
                "**"+ new EditCommandController().getName() +  "**\t  -  " + new EditCommandController().getHelp() + "\n" +
                "**"+ new JoinCommandController().getName() +  "**\t  -  " + new JoinCommandController().getHelp() + "\n" +
                "**"+ new LeaveCommandController().getName() +  "**\t  -  " + new LeaveCommandController().getHelp() + "\n" +
                "**"+ new StartCommandController().getName() +  "**\t  -  " + new StartCommandController().getHelp() + "\n" +
                "**"+ new StopCommandController().getName() +  "**\t  -  " + new StopCommandController().getHelp(),
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

 */
