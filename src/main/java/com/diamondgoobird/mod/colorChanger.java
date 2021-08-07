package com.diamondgoobird.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class colorChanger {
	@SubscribeEvent
	public void getChat(ClientChatReceivedEvent event) {
		coloredChat.Config.load();
		coloredChat.toggle = coloredChat.Config.getBoolean("toggle", "values", true, "Toggles whether the mod is enabled");
		coloredChat.nonColor = coloredChat.Config.getBoolean("nonColor", "values", false, "If true, then only colors can be shown (not italics, underlined, etc.)");
		if (coloredChat.toggle == false) {
			
		}
		else {
		String message = event.message.getUnformattedText();
		char[] characters = message.toCharArray();
		EnumChatFormatting[] colors = new EnumChatFormatting[message.length()];
		boolean changed = false;
			if (message.contains("&")) {
				changed = true;
				int x = 0;
				char[] a = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','k','l','m','n','o','r'};

				int b = 16;
				if (coloredChat.nonColor == true) {
					b = 22;
				}
				while (x < b) {	
				message = message.replaceAll("&" + a[x], "\u00a7" + a[x]);
				x++;
				}
			}

		if (changed == true) {
			event.setCanceled(true);
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
		}
		}
	}
}
