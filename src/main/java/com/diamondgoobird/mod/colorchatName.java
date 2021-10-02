package com.diamondgoobird.mod;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = colorchatVariables.MOD_ID, name = colorchatVariables.MOD_NAME, version = colorchatVariables.VERSION)
public class colorchatName {
	@Instance(colorchatVariables.MOD_ID)
	public static colorchatName instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		colorchatVariables.printConsole("Starting preinit...");
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent Event) {
		colorchatVariables.printConsole("Initializing Mod...");
		colorchatVariables.checkConfig(null);
		MinecraftForge.EVENT_BUS.register(new colorchatListeners());
		ClientCommandHandler.instance.registerCommand(new colorchatCommand());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
		colorchatVariables.printConsole("Completing Initialization...");
	}
}
