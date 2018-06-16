package me.orangeflare.cornucopia;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.awt.*;

public class botFunctions {
    public static void sendMessage(IChannel channel, String message) {
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException error){
                System.err.println("[ERROR] Message Failed to Send");
                error.printStackTrace();
            }
        });
    }
    public static void sendEmbedMessage(IChannel channel, String title, Color color, String[] embeds, String[] embedTitles) {
        EmbedBuilder message = new EmbedBuilder();
        message.withTitle(title);
        message.withColor(color);
        for (int i = 1; i <= embeds.length; i++) {
            message.appendField(embedTitles[i-1], embeds[i-1], true);
        }
        message.withFooterText("Cornucopia (c) 2018");
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message.build());
            } catch (DiscordException error) {
                System.err.println("[ERROR] Embedded Message Failed to Send");
                error.printStackTrace();
            }
        });
    }
    public static String getDisplayName(MessageReceivedEvent event) {
        return event.getAuthor().getDisplayName(event.getGuild());
    }
}
