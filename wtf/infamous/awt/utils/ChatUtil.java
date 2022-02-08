package wtf.infamous.awt.utils;

import net.minecraft.client.*;
import wtf.infamous.awt.*;
import net.minecraft.util.*;

public class ChatUtil
{
    public static void print(final String message) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.thePlayer.addChatMessage(new ChatComponentText("§6" + Dawn.name + "§c>>§7 " + message));
    }
}
