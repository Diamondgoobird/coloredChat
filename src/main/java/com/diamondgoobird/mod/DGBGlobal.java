package com.diamondgoobird.mod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import net.minecraftforge.fml.relauncher.FMLInjectionData;

public class DGBGlobal {

	public static final String MOD_ID = "colorChat";
	public static final String MOD_NAME = "Colored Chat";
	public static final String VERSION = "1.0";
	
	public static String[] data = new String[32767];
	
	public static final String DGB_CLIENT_PROXY = "com.diamondgoobird.mod.proxy.ClientProxy";
	public static final String DGB_COMMON_PROXY = "com.diamondgoobird.mod.proxy.CommonProxy";
	
	public static void set(File file, String object, String value, String line, String type) throws IOException { 
		new Thread( () -> {
		String basePath = ((File)(FMLInjectionData.data()[6])).getAbsolutePath().replace(File.separatorChar, '/').replace("/.", "");
        String path = file.getAbsolutePath().replace(File.separatorChar, '/').replace("/./", "/").replace(basePath, "");
        System.out.println("whole path: "+ basePath + path);
        path = basePath + path;
        Path x = Paths.get(path);
        File z = FileUtils.getFile(x.toString());
        //System.out.print(z.toString());
		
        try {
			changeValue(z,object,value,line);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}).start();
	}
	public static void changeValue(File z, String object, String value, String line) throws IOException {
		FileReader fr = new FileReader(z);
		BufferedReader ReadFileBuffer = new BufferedReader(fr);


		String read;
		int a = 0;
		
		data = new String[32767];
		while((read = ReadFileBuffer.readLine()) != null) {
			data[a] = read;
			if (data[a].contains(object)) {
				data[a] = line + value;
			}
			a++;
		}
		save(z,data);
		ReadFileBuffer.close();
	}
	public static void save(File z,String[] data1) throws IOException {
		FileWriter fw = new FileWriter(z);
		BufferedWriter WriterFileBuffer = new BufferedWriter(fw);
		int h = 0;
		System.out.println("Saving data... ");
		while(data1[h]!= null) {
			WriterFileBuffer.write(data1[h]);
			WriterFileBuffer.newLine();
			System.out.println(data1[h]);
			h++;
		}
		WriterFileBuffer.close();
	}
}
