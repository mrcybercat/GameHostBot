package com.teamMMM.bot.commands;

import com.teamMMM.bot.util.CommandPlaceholderEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StartGameCmd extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event){
        if(event.getName().contains("start")){
            event.replyEmbeds(CommandPlaceholderEmbed.createPlaceholderEmbed(event, "start").build()).queue();
        }
    }
}
