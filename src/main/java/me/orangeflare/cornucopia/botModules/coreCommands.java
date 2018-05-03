package me.orangeflare.cornucopia.botModules;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

import static me.orangeflare.cornucopia.cornucopia.sendMessage;

public class coreCommands {
    public static void pingCommand(MessageReceivedEvent event){
        sendMessage(event.getChannel(), "Pong!");
    }
    public static void addCommand(MessageReceivedEvent event, List<String> args) throws IllegalArgumentException, IndexOutOfBoundsException {
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
