package dev.tarna.candortrial.commands

import dev.tarna.candortrial.CandorTrial
import dev.tarna.candortrial.util.escapeMarkdown
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.buttons.Button
import java.awt.Color
import kotlin.math.round

class ServerStatusCommand(private val plugin: CandorTrial) : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != "serverstatus") return

        val players = "${plugin.server.onlinePlayers.size}/${plugin.server.maxPlayers}"
        val uptimeString = "<t:${plugin.startTime / 1000}:R>"
        val tps = (round(plugin.server.tps[0] * 100) / 100).toString()
        val embed = EmbedBuilder()
            .setTitle("Server Status")
            .setColor(Color.RED)
            .addField("Players", players, true)
            .addField("Uptime", uptimeString, true)
            .addField("TPS", tps, true)
            .build()

        val viewPlayersButton = Button.primary("view_players", "View Players")

        event.replyEmbeds(embed)
            .addActionRow(viewPlayersButton)
            .setEphemeral(true)
            .queue()
    }

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        if (event.componentId != "view_players") return

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