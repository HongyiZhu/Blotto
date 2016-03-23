package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class myFile
{
	public static ArrayList<String> readFile(String path) throws Exception
	{
		String s = new String();
		ArrayList<String> al = new ArrayList<String>();
		BufferedReader br = new BufferedReader(
				new FileReader(new File(path)));
		while((s = br.readLine()) != null)
			al.add(s);
		br.close();
		return al;
	}

	public static ArrayList<Hashtable<String, String>> readDelimitedFile(
			String path, String delimiter) throws Exception {
		ArrayList<Hashtable<String, String>> al = 
				new ArrayList<Hashtable<String, String>>();
		ArrayList<String> alTmp;
		alTmp = readFile(path);
		String[] header = alTmp.get(0).split(delimiter);
		for(int row = 1; row < alTmp.size(); row ++)
		{
			Hashtable<String, String> h =  new Hashtable<String, String>();
			for(int column = 0; column < header.length; column ++)
			{
				String[] data = alTmp.get(row).split(delimiter);
				h.put(header[column], data[column]);
			}
			al.add(h);
		}
		return al;
	}

	public static void writeFile(ArrayList<String> al, String path) throws Exception
	{
		BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter(path));
		for(String s : al)
		{
			bw.write(s + "\r\n");
		}
		bw.close();
	}

	public static void deleteFile(String path) throws Exception
	{   
		File f = new File(path);
		if(f.exists())
		{
			if(f.isFile())
				f.delete();
			else
			{
				File[] fList = f.listFiles();       
				for(File fChld : fList)
				{
					deleteFile(fChld.getPath());
				}
				f.delete();
			}
		}
	}

	public static void copyFile(String srcPath, String dstPath) throws Exception
	{
		InputStream is = new FileInputStream(new File(srcPath));
		OutputStream os = new FileOutputStream(new File(dstPath));
		int length;
		byte[] buffer = new byte[1024];
		while ((length = is.read(buffer)) > 0)
			os.write(buffer, 0, length);
		is.close();
		os.close();
	}
}
