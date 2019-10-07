package com.github.julyss2019.mcsp.julylibrary.utils;

import com.github.julyss2019.mcsp.julylibrary.message.JulyMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {
    public static ItemStack getItemByID(String idStr) {
        String[] aId = idStr.split(":");

        for (String id : aId) {
            if (id.matches("[0-9]+")) {
                throw new RuntimeException("非法的ID");
            }
        }

        return new ItemStack(Integer.parseInt(aId[0]), 1, aId.length == 1 ? 0 : Short.parseShort(aId[1]));
    }

    /**
     * 判断物品是否有效（不为null且meta不为null）
     * @param itemStack
     * @return
     */
    public static boolean isValidItem(ItemStack itemStack) {
        return itemStack != null && itemStack.getItemMeta() != null;
    }

    /**
     * 判断物品是否包含指定lore
     * @param itemStack
     * @param lore
     * @return
     */
    public static boolean containsLore(ItemStack itemStack, String lore) {
        if (!isValidItem(itemStack)) {
            return false;
        }

        List<String> lores = itemStack.getItemMeta().getLore();

        return lores != null && lores.contains(JulyMessage.toColoredMessage(lore));
    }

    /**
     * 得到物品lore
     * @param itemStack
     * @return 不为空的`List
     */
    public static List<String> getLores(ItemStack itemStack) {
        if (!isValidItem(itemStack)) {
            return new ArrayList<>();
        }

        List<String> lores = itemStack.getItemMeta().getLore();

        return lores == null ? new ArrayList<>() : lores;
    }
}
