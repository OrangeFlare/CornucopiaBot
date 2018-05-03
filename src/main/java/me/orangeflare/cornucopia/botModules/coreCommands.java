package me.orangeflare.cornucopia.botModules;

import sun.plugin2.message.Message;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;
import me.orangeflare.cornucopia.cornucopia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class coreCommands {
    public static void sendMessage(IChannel channel, String message) {
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException error){
                System.err.println("[ERROR] Message Failed to send: ");
                error.printStackTrace();
            }
        });
    }
    public static String getDisplayName(MessageReceivedEvent event) { return event.getAuthor().getDisplayName(event.getGuild()) + " (" + event.getAuthor().getName() + ")"; }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] argArray = event.getMessage().getContent().split(" ");
        if (argArray.length == 0) { return; }
        if (!argArray[0].toLowerCase().startsWith(cornucopia.BOT_PREFIX)) { return; }
        String command = argArray[0].substring(2).toLowerCase();
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0);
        switch(command) {
            case "ping":
                pingCommand(event);
                System.out.println("[INFO] 'ping' Command Received!");
                System.out.println("       Command sent by " + getDisplayName(event));
            case "add":
                addCommand(event, argsList);
                System.out.println("[INFO] 'add' Command Received!");
                System.out.println("       Command sent by " + getDisplayName(event));
        }
    }
    private void pingCommand(MessageReceivedEvent event){
        sendMessage(event.getChannel(), "Pong!");
    }
    private void addCommand(MessageReceivedEvent event, List<String> args) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (args.size() != 2) {
            sendMessage(event.getChannel(), "You Can Only Supply 2 Arguments!");
            sendMessage(event.getChannel(), "    Ex: c!add 1 2");
        } else {
            try {
                int number = Integer.parseInt(args.get(0) + args.get(1));
            } catch (IllegalArgumentException e) {
                sendMessage(event.getChannel(), "Error");
                throw e;
            } catch (IndexOutOfBoundsException e){
                sendMessage(event.getChannel(), "Error");
                throw e;
            }
            int number = Integer.parseInt(args.get(0) + args.get(1));
            sendMessage(event.getChannel(), Integer.toString(number));
        }
    }
}
