package com.team3m.bot.commands;

import com.team3m.bot.commands.abstracts.GameCmd;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;


public class EditGameSettingsCmd extends GameCmd {
    public EditGameSettingsCmd()
    {
        this.name = "edit";
        this.help = "shows a random cat";
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event){
        if(event.getCommandString().contains("edit")){
            //event.replyEmbeds(CommandPlaceholderEmbed.createPlaceholderEmbed(event, "join").build()).queue();
        }
    }
}
