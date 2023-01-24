package com.team3m.bot.commands.controllers.general;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.services.game.StartCommandService;
import com.team3m.bot.commands.services.general.AboutCommandService;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AboutCommandController extends Command {
    public AboutCommandController() {
        this.name = "about";
        this.help = "Gives some info about this darn bot";
    }

    @Autowired
    AboutCommandService aboutCommandService = new AboutCommandService();

    @CommandAnnotation(CommandType.SLASH)
    public void about(SlashCommandEvent event) {
        aboutCommandService.about(event);
    }

}
