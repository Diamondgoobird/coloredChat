package com.diamondgoobird.mod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class colorChatCommand extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "colorchat";
	}

	@Override
	public int getRequiredPermissionLevel()
    {
        return 0;
    }
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Toggles whether colors can be typed in chat by players";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length <= 0) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Please try again (Type '/colorchat help' for options)"));
			return;
        }
		
		else if (args[0].equalsIgnoreCase("enable")) {
			try {
				DGBGlobal.set(coloredChat.Config.getConfigFile(),"toggle","true","    B:toggle=","boolean");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Successfully" + EnumChatFormatting.GREEN + " Enabled " + EnumChatFormatting.AQUA + "Color Chat!"));
		}
		
		else if (args[0].equalsIgnoreCase("disable")) {
			try {
				DGBGlobal.set(coloredChat.Config.getConfigFile(),"toggle","false","    B:toggle=","boolean");
				//DGBGlobal.set(Main.Config.getConfigFile(),"both","false","    B:both=","boolean");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Successfully" + EnumChatFormatting.RED + " Disabled " + EnumChatFormatting.AQUA + "Color Chat!"));
		}
		
		else if (args[0].equalsIgnoreCase("noncolor")) {
			if (coloredChat.nonColor == false) {
				try {
					DGBGlobal.set(coloredChat.Config.getConfigFile(),"nonColor","true","    B:nonColor=","boolean");
					coloredChat.nonColor = true;
					//coloredChat.nonColor = coloredChat.Config.getBoolean("nonColor", "values", false, "If true, then only colors can be shown (not italics, underlined, etc.)");
					//DGBGlobal.set(Main.Config.getConfigFile(),"both","false","    B:both=","boolean");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Successfully enabled non color formatting!"));
			}
			else {
				try {
					DGBGlobal.set(coloredChat.Config.getConfigFile(),"nonColor","false","    B:nonColor=","boolean");
					coloredChat.nonColor = false;
					//DGBGlobal.set(Main.Config.getConfigFile(),"both","false","    B:both=","boolean");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Successfully disabled non color formatting!"));
			}
		}
		
		else if (args[0].equalsIgnoreCase("help")) {
			EnumChatFormatting a = EnumChatFormatting.AQUA;
			EnumChatFormatting da = EnumChatFormatting.DARK_AQUA;
        	sender.addChatMessage(new ChatComponentText(a + "/colorchat " + a + "enable" + da + "/" + a + "disable " + da + "- " + a + "Disables/Enables showing colors"));
        	sender.addChatMessage(new ChatComponentText(a + "/colorchat " + a + "noncolor" + da + "- " + a + "Toggles whether things other than colors can be showed."));
        	sender.addChatMessage(new ChatComponentText(a + "/colorchat " + a + "help " + da + "- " + a + "Displays this message."));
        	sender.addChatMessage(new ChatComponentText(a + "Color codes (& before the character: "));
        	sender.addChatMessage(new ChatComponentText(""));
        	sender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLACK + "0 " + EnumChatFormatting.DARK_BLUE + "1 " + EnumChatFormatting.DARK_GREEN + "2 " + EnumChatFormatting.DARK_AQUA + "3 "));
        	sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "4 " + EnumChatFormatting.DARK_PURPLE + "5 " + EnumChatFormatting.GOLD + "6 " + EnumChatFormatting.GRAY + "7 "));
        	sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GRAY + "8 " + EnumChatFormatting.BLUE + "9 " + EnumChatFormatting.GREEN + "a " + EnumChatFormatting.AQUA + "b "));
        	sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "c " + EnumChatFormatting.LIGHT_PURPLE + "d " + EnumChatFormatting.YELLOW + "e " + EnumChatFormatting.WHITE + "f "));
		}
		
	}
	
	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
    	return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {"help","enable", "disable", "noncolor"}): null;
    }

}
