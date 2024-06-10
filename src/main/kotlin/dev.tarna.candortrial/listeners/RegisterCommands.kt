package dev.tarna.candortrial.listeners

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands

class RegisterCommands : ListenerAdapter() {
    override fun onGuildReady(event: GuildReadyEvent) {
        val commands = listOf(
            Commands.slash("broadcast", "Broadcast a message to the server")
                .addOption(OptionType.STRING, "message", "The message to broadcast", true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
            Commands.slash("playerlist", "Get the list of players on the server"),
            Commands.slash("serverstatus", "Get the status of the server")
        )

        event.guild.updateCommands().addCommands(commands).queue()
    }
}