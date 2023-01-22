package com.team3m.bot.util;

import com.team3m.bot.commands.abstracts.Command;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

import java.util.List;

public class SelectionMenuHandler {
    public static SelectionMenu createButton(Command command, String label, List<SelectOption> optionList){
        return SelectionMenu.create(command.getName() +"_menu_" +  label).addOptions(optionList).build();
    }
}
