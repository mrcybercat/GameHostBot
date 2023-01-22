package com.team3m.bot.commands.trash.inter_game;

/*
public class StopGameCmd extends Command {

    public StopGameCmd()
    {
        this.name = "stop";
        this.help = "Stops currently active game and resets lobby to default settings";
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("stop")) {
            GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
            Lobby lobby =  guildManager.findByOwner(guildManager.getLobbies(), event.getUser().getId());

            if(lobby == null){
                event.replyEmbeds(CommandEmbedBuilder.createCommandEmbed(event, "Huh?", "You dont own an lobby to stop", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                return;
            }

            lobby.getSettings().getGame().stop();
            lobby.setGameStarted(false);
            event.reply("Game stated!").setEphemeral(true).queue();
        }
    }


} */

