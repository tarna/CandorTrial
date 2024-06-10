package dev.tarna.candortrial

import dev.tarna.candortrial.commands.BroadcastCommand
import dev.tarna.candortrial.commands.PlayerListCommand
import dev.tarna.candortrial.commands.ServerStatusCommand
import dev.tarna.candortrial.listeners.RegisterCommands
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import org.bukkit.plugin.java.JavaPlugin

class CandorTrial : JavaPlugin() {
    lateinit var jda: JDA

    val startTime = System.currentTimeMillis()

    override fun onEnable() {
        val now = System.currentTimeMillis()

        saveDefaultConfig()

        val token = config.getString("token")
        if (token == null) {
            logger.severe("Token not found in config.yml, disabling plugin")
            server.pluginManager.disablePlugin(this)
            return
        }

        jda = JDABuilder.createDefault(token)
            .addEventListeners(
                RegisterCommands(),

                BroadcastCommand(),
                PlayerListCommand(this),
                ServerStatusCommand(this)
            )
            .build()

        val activity = config.getString("activity")
        if (!activity.isNullOrBlank()) {
            jda.presence.activity = Activity.playing(activity)
            logger.info("Set bot activity to $activity")
        }

        logger.info("Enabled in ${System.currentTimeMillis() - now}ms")
    }

    override fun onDisable() {
        val now = System.currentTimeMillis()
        logger.info("Disabled in ${System.currentTimeMillis() - now}ms")
    }
}