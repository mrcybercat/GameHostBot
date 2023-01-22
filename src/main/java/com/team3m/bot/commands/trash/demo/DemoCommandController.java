package com.team3m.bot.commands.trash.demo;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.abstracts.CommandType;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DemoCommandController extends Command {

    @Autowired
    DemoCommandService demoCommandService = new com.team3m.bot.commands.trash.demo.DemoCommandService();


    public DemoCommandController()
    {
        this.name = "demo";
        this.help = "A yo that a demo";
    }

    @CommandAnnotation(CommandType.SLASH)
    public void slashDemo(SlashCommandEvent event) {
        demoCommandService.slashDemoServiceSide(event);
    }

    @CommandAnnotation(CommandType.BUTTON)
    public void buttonDemo(ButtonClickEvent event) {
        demoCommandService.buttonDemoServiceSide(event);
    }

}
