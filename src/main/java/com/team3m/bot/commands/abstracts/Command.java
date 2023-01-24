package com.team3m.bot.commands.abstracts;

import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Command extends ListenerAdapter {

    protected String name = "null";
    protected String help = "no help available";
    protected List<OptionData> optionsData = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getHelp() {
        return help;
    }

    public void registerCommandData(@NotNull GuildReadyEvent event){
        if(optionsData != null){
            event.getGuild().upsertCommand(new CommandData(this.name, this.help)).addOptions(this.optionsData).queue();
        }
        else {
            event.getGuild().upsertCommand(new CommandData(this.name, this.help)).queue();
        }
    }


    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        Class executor = findExecutor(event.getName());
        Method executorMethod = findExecutorMethod(executor, CommandType.SLASH);
        try {
            executorMethod.invoke(executor.newInstance(), event);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        Class executor = findExecutor(event.getComponentId().split("_")[0]);
        Method executorMethod = findExecutorMethod(executor, CommandType.BUTTON);
        try {
            executorMethod.invoke(executor.newInstance(), event);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        Class executor = findExecutor(event.getComponentId().split("_")[0]);
        Method executorMethod = findExecutorMethod(executor, CommandType.SELECTION);
        try {
            executorMethod.invoke(executor.newInstance(), event);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }


    private Class findExecutor(String eventName){
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(Command.class));
        Set<BeanDefinition> components = provider.findCandidateComponents("com/team3m/bot/commands");
        for (BeanDefinition component : components) {
            try {
                Class executor = Class.forName(component.getBeanClassName());
                if(((Command) executor.newInstance()).getName().startsWith(eventName)){
                    return executor;
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


    private Method findExecutorMethod(Class executor, CommandType executionType){
        for (Method method: executor.getMethods()) {
            CommandAnnotation commandAnnotation = method.getAnnotation(CommandAnnotation.class);
            CommandType commandType = commandAnnotation.value();
            if(commandType.equals(executionType)){
                return method;
            }
        }
        return null;
    }





}
