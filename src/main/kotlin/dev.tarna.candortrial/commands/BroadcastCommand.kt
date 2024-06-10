package dev.tarna.candortrial.commands

import dev.tarna.candortrial.util.color
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit

class BroadcastCommand : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != "broadcast") return

        val message = event.getOption("message")?.asString ?: return
        Bukkit.broadcast(message.color())
        event.reply("Message Broadcasted!")
            .setEphemeral(true)
            .queue()
    }
}