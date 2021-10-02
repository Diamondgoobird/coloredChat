package com.diamondgoobird.mod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class colorchatVariables {
	public static final String MOD_ID = "colorchat";
	public static final String MOD_NAME = "Color Chat";
	public static final String VERSION = "1.0";
	public static final String[] things = {"Toggle", "Other Formatting", "Obfustication"};
	public static String[] stuff = {"Enabled", "Enabled", "Disabled"};
	public static final String[] options = {"Enabled/Disabled", "Enabled/Disabled", "Enabled/Disabled"};
	public static final String minecraftPath = Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
	public static final String Path = minecraftPath + "\\diamondmods\\";
	public static final String ConfigPath = Path + MOD_ID + ".txt";
	public static final String CommandName = "colorchat";
	public static final FileUtils a = new FileUtils();
	public static String logPrefix = "[" + MOD_NAME + "]: ";
	public static File file = new File(ConfigPath);
	public static String everything = "Config for " + MOD_ID + "\n\n";
	
	public static void printConsole(String stuff) {
		System.out.println(logPrefix + stuff);
	}
	
	public static void print(String input) {
		String message = input;
		ChatComponentText e = new ChatComponentText(message);
		char[] a = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','k','l','m','n','o','r'};
		int x = 0;
		int b = 22;
		while (x < b) {
			e = new ChatComponentText(e.getUnformattedText().replaceAll("&" + a[x], "\u00a7" + a[x]));
			e = new ChatComponentText(e.getUnformattedText().replaceAll("&&", "&"));
			
			x++;
			}
		Minecraft.getMinecraft().thePlayer.addChatMessage(e);
	}
	
	public static void printLines(String[] input) {
		int z = 0;
		while (input.length > z) {
			print(input[z]);
			z++;
		}
	}
	
	public static final void printChat(IChatComponent input) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(input);
	}
	
	public static String[] checkConfig(String[] input) {
		createConfig();
		if (input == null) {
			input = things;
		}
		String[] results = new String[input.length];
		int loopedtimes = 0;
		while (results.length > loopedtimes) {
			results[loopedtimes] = checkVariable(input[loopedtimes]);
			loopedtimes++;
		}
		stuff = results;
		return results;
	}
	
	public static String checkVariable(String variable) {
		String[] File = readFile();
		int loopedtimes = 0;
		String Line = null;
		while (File.length > loopedtimes) {
			if (File[loopedtimes] == null) {
				break;
			}
			else if (File[loopedtimes].contains(variable)) {
				Line = File[loopedtimes + 1];
			}
			loopedtimes++;
		}
		return Line;
	}
	
	public static void toggleVariable(String variable) {
		if (checkVariable(variable).equalsIgnoreCase("Disabled")) {
			changeVariable(variable,"Enabled");
		}
		else {
			changeVariable(variable,"Disabled");
		}
	}

	public static void changeVariable(String variable, String value) {
		everything = "Config for " + MOD_ID + "\n\n";
		int x = 0;
		int index = -1;
		while (things.length > x) {
			if (things[x].equalsIgnoreCase(variable)) {
				index = x;
				break;
			}
			x++;
		}
		if (value.contains("&")) {
			value = value.replaceAll("&", "");
		}
		else {
			print("&bSuccessfully Changed Variable &3" + variable + "&b to &3" + value);
		}
		stuff[index] = value;
		saveConfig();
	}
	
	public static String[] readFile() {
		createConfig();
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader rfb = new BufferedReader(fr);
			int loopedtimes = 0;
			String[] ez = new String[100];
			boolean stop = false;
			while (stop != true) {
			try {
				ez[loopedtimes] = rfb.readLine();
				if (ez[loopedtimes] == null) {
					stop = true;
					//System.out.println("End of file.");
				}
				else {
					//System.out.println(ez[loopedtimes]);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
				loopedtimes++;
			}

		return ez;
	}
	
	public static void createConfig() {
		if (file.exists() == false) {
			saveConfig();
		}
	}
	
	public static void saveConfig() {
		int loopedtimes = 0;
		while (things.length > loopedtimes) {
			everything = everything + things[loopedtimes] + " (" + options[loopedtimes] + "):\n" + stuff[loopedtimes] + "\n\n";
			loopedtimes++;
		}
		loopedtimes = 0;
		CharSequence data = everything;
		try {
			a.write(file, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
