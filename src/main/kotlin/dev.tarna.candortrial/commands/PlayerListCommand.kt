package dev.tarna.candortrial.commands

import dev.tarna.candortrial.CandorTrial
import dev.tarna.candortrial.util.escapeMarkdown
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color

class PlayerListCommand(private val plugin: CandorTrial) : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != "playerlist") return

        val players = plugin.server.onlinePlayers.joinToString("\n") { "- ${it.name.escapeMarkdown()}" }
        val embed = EmbedBuilder()
            .setTitle("Players")
            .setColor(Color.RED)
            .setDescription(players)
            .build()

        event.replyEmbeds(embed)
            .setEphemeral(true)
            .queue()
    }
}