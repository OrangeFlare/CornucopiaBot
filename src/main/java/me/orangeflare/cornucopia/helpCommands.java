package me.orangeflare.cornucopia;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.orangeflare.cornucopia.botFunctions.getDisplayName;
import static me.orangeflare.cornucopia.botFunctions.sendEmbedMessage;

public class helpCommands {
    @EventSubscriber
    public static void onMessageReceived(MessageReceivedEvent event) {
        String[] argArray = event.getMessage().getContent().split(" ");
        if (argArray.length == 0) { return; }
        if (!argArray[0].toLowerCase().startsWith(main.BOT_PREFIX)) { return; }
        if (event.getAuthor().isBot()) { return; }
        String command = argArray[0].substring(2).toLowerCase();
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0);

        switch(command) {
            case "help":
                help(event);
                System.out.println("[INFO] 'help' Command Received!");
                System.out.println("       Command Sent by " + getDisplayName(event));
                break;
            case "help.test":
                helpTest(event);
                System.out.println("[INFO] 'helpTest' Command Received!");
                System.out.println("       Command Sent by " + getDisplayName(event));
                break;
        }
    }
    public static void help(MessageReceivedEvent event) {
        String[] embedTitles = {"Test Help Sub-menu"};
        String[] embeds = {"c!help.test"};
        sendEmbedMessage(event.getChannel(), "Help", Color.ORANGE, embeds, embedTitles);
    }
    public static void helpTest(MessageReceivedEvent event) {
        String[] embedTitles = {"Test Message", "Test Embed"};
        String[] embeds = {"c!test.message", "c!test.embed"};
        sendEmbedMessage(event.getChannel(), "Help", Color.ORANGE, embeds, embedTitles);
    }
}
