package com.diamondgoobird.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class colorchatListeners {
	
	@SubscribeEvent
	public void getChat(ClientChatReceivedEvent event) {
		colorchatVariables.checkConfig(null);
		if (colorchatVariables.checkVariable("Toggle").equalsIgnoreCase("Disabled")) {
			
		}
		else {
//		UnaryOperator <IChatComponent> operator = null;
//		event.message.getSiblings().replaceAll(operator);
		String message = event.message.getUnformattedText();
		//System.out.println(message);
		char[] characters = message.toCharArray();
		EnumChatFormatting[] colors = new EnumChatFormatting[message.length()];
		boolean changed = false;
			if (message.contains("&")) {
				int x = 0;
				char[] a = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','r'};
				int b = 17;
				if (colorchatVariables.checkVariable("Other Formatting").equalsIgnoreCase("Enabled")) {
					x = 0;
					char[] c = new char[]{'l','m','n','o'};
					while (c.length > x) {
					event.message = new ChatComponentText(event.message.getFormattedText().replaceAll("&" + c[x], "\u00a7" + c[x]));
					changed = true;
					x++;
					}
					x = 0;
				}
				if (colorchatVariables.checkVariable("Obfustication").equalsIgnoreCase("Enabled")) {
					event.message = new ChatComponentText(event.message.getFormattedText().replaceAll("&" + "k", "\u00a7" + "k"));
					changed = true;
				}
				while (x < b) {	
				event.message = new ChatComponentText(event.message.getFormattedText().replaceAll("&" + a[x], "\u00a7" + a[x]));
				changed = true;
				x++;
				}
			}
			if (changed == true) {
			event.setCanceled(true);
			Minecraft.getMinecraft().thePlayer.addChatMessage(event.message);
		}
		}
	}
}
