package com.github.julyss2019.mcsp.julylibrary.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListenerCaller implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (JulyChatFilter.hasChatFilter(player)) {
            ChatFilter chatFilter = JulyChatFilter.getChatFilter(player);
            ChatListener chatListener = chatFilter.getChatListener();

            if (!chatFilter.isTimeout()) {
                chatListener.onChat(event);
            } else {
                chatListener.onTimeout();
                event.setCancelled(true);
                JulyChatFilter.unregisterChatFilter(player);
            }
        }
    }
}
