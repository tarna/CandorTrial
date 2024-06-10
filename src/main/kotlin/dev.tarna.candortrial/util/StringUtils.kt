package dev.tarna.candortrial.util

import net.kyori.adventure.text.minimessage.MiniMessage

val mm = MiniMessage.miniMessage()

fun String.color() = mm.deserialize(this)

fun String.escapeMarkdown() = replace("_", "\\_")