package me.orangeflare.cornucopia.commands;

import me.orangeflare.cornucopia.main;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.orangeflare.cornucopia.botFunctions.*;
import static me.orangeflare.cornucopia.helpCommands.*;

public class testingCommands {
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
            case "test.message":
                testMessage(event);
                System.out.println("[INFO] 'testMessage' Command Received!");
                System.out.println("       Command Sent by " + getDisplayName(event));
                break;
            case "test.embed":
                testEmbed(event);
                System.out.println("[INFO] 'testEmbed' Command Received!");
                System.out.println("       Command Sent by " + getDisplayName(event));
                break;
            case "test":
                helpTest(event);
                break;
        }
    }
    private static void testMessage(MessageReceivedEvent event) {
        sendMessage(event.getChannel(), "Test Message");
    }
    private static void testEmbed(MessageReceivedEvent event) {
        String[] embeds = {"Content 1", "Content 2"};
        String[] embedTitles = {"Title 1", "Title 2"};
        sendEmbedMessage(event.getChannel(), "Test Embed", Color.GREEN, embeds, embedTitles);
    }
}
