package com.diamondgoobird.mod;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class colorchatCommand extends CommandBase {
	public final String[] options = {"help","enable","disable","other","obfustication","variable","debug","formatting"};
	public final String help = "/colorchat help for help";
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return colorchatVariables.CommandName;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return help;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
		if (args.length <= 0) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Please try again (Type '/"+ colorchatVariables.CommandName + " help' for options)"));
			return;
        }
		
		else if (args[0].equalsIgnoreCase(options[0])) {
			colorchatVariables.printLines(new String[]{
					"&3&l---------------",
					"&b/colorchat formatting &3- &bPrints possible formatting.",
					"&b/colorchat help &3- &bPrints this message",
					"&b/colorchat enable &3- &bEnables the mod",
					"&b/colorchat disable &3- &bDisables the mod",
					"&b/colorchat other &3- &bToggles other formatting such as &lbold &r&bor &oitalics&r&b.",
					"&b/colorchat obfustication &3- &bToggles text like &khello&r&b.",
					"&3&l---------------"
			});
		}
		
		else if (args[0].equalsIgnoreCase(options[1])) {
			colorchatVariables.changeVariable("Toggle", "Enabled");
		}
		
		else if (args[0].equalsIgnoreCase(options[2])) {
			colorchatVariables.changeVariable("Toggle", "Disabled");
		}
		else if (args[0].equalsIgnoreCase(options[3])) {
			colorchatVariables.toggleVariable("Other Formatting");
		}
		else if (args[0].equalsIgnoreCase(options[4])) {
			colorchatVariables.toggleVariable("Obfustication");
		}
		else if (args[0].equalsIgnoreCase(options[5])) {
			colorchatVariables.changeVariable(args[1], args[2]);
			debug();
		}
		else if (args[0].equalsIgnoreCase(options[6])) {
			debug();
		}
		else if (args[0].equalsIgnoreCase(options[7])) {
			colorchatVariables.printLines(new String[]{
					"&bTo type the formatting type &&(code) to change the formatting.",
					"&bNote: only other players with the mod see your formatting.",
					"&00 &11 &22 &33 &44",
					"&55 &66 &77 &88 &99",
					"&aa &bb &cc &dd &ee",
					"&bOther Formatting:",
					"&ff &ll&r &mm&r &nn",
					"&oo&r r",
					"&bObfustication:",
					"&kTest &r (k)"
			});
		}
	}
	
	public static void debug() {
		String[] stuff = colorchatVariables.checkConfig(null);
		String[] things = colorchatVariables.things;
		String output = "";
		int x = 0;
		while (stuff.length > x) {
			String comma = ", ";
			if (stuff.length == x + 1) {
				comma = "";
			}
			output = output + things[x] + ": " + stuff[x] + comma;
			x++;
		}
		colorchatVariables.print(output);
		colorchatVariables.printConsole(output);
	
	}
	
	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, options): null;
    }
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
	
	@Override
	public int getRequiredPermissionLevel()
    {
        return 0;
    }
}
