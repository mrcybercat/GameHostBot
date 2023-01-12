package com.team3m.bot.commands.abstracts;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class GameCmd extends ListenerAdapter {

    protected String name = "null";
    protected String help = "no help available";
    protected List<OptionData> optionsData = new ArrayList<>();

    public void registerCommandData(@NotNull GuildReadyEvent event){
        if(optionsData != null){
            event.getGuild().upsertCommand(new CommandData(this.name, this.help)).addOptions(this.optionsData).queue();
        }
        else {
            event.getGuild().upsertCommand(new CommandData(this.name, this.help)).queue();
        }
    }

}
