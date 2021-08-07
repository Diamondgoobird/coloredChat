package com.diamondgoobird.mod;

import java.io.File;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.*;

@Mod(modid = DGBGlobal.MOD_ID, name = DGBGlobal.MOD_NAME, version = DGBGlobal.VERSION)
public class coloredChat {
	public static String customcommand;
	public final static Configuration Config = new Configuration(new File("config/ColoredChat.cfg"));
	public static boolean toggle;
	public static boolean nonColor;
	public final String modPrefix = "§b[COLORED CHAT]: §r";	
	@Instance(DGBGlobal.MOD_ID)
	public static coloredChat instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		System.out.println(modPrefix + "Initiallizing Mod...");	
		Config.load();
		toggle = Config.getBoolean("toggle", "values", true, "Toggles whether the mod is enabled");
		customcommand = Config.getString("customcommand", "values", "diamondgoobird", "Changes what command you can use to trigger the mod (ex: /diamondgoobird)");
		nonColor = Config.getBoolean("nonColor", "values", false, "If true, then only colors can be shown (not italics, underlined, etc.)");
		Config.save();
	}
	@EventHandler
	public void Init(FMLInitializationEvent Event) {
		System.out.println(modPrefix + "Initiallizing Commands...");
		ClientCommandHandler.instance.registerCommand(new colorChatCommand());
		System.out.println(modPrefix + "Completed Initiallizing Commands");
		MinecraftForge.EVENT_BUS.register(new colorChanger());
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
	}	
}


