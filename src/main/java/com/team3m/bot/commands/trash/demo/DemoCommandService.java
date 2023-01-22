package com.team3m.bot.commands.trash.demo;

import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedHandler;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.stereotype.Service;

@Service
public class DemoCommandService {

    public void slashDemoServiceSide(SlashCommandEvent event) {
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Ta da!", "Demo is working", ColorHandler.StatusColorEnum.VICTORY)).setEphemeral(true).queue();
    }
    public void buttonDemoServiceSide(ButtonClickEvent event) {
        String cases = event.getComponentId().split("_")[2];
        switch (cases){
            case "join":

                return;

            case "delete":

                return;
        }
    }


}
