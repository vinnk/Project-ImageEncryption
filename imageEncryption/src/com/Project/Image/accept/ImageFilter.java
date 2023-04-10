package com.Project.Image.accept;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageFilter extends FileFilter {		//extends an abstract class
	public final static String JPG= "jpg";
	public final static String JPEG= "jpeg";
	public final static String PNG= "png";
	public final static String GIF= "gif";
	public final static String TIF= "tif";
	public final static String TIFF= "tiff";

	@Override
	public boolean accept(File f) {					//defining the inherited abstract methods
		if(f.isDirectory()) 
			return true;
		
		String extension= getExtension(f);
		if(extension!= null) {
			if(extension.equals(JPG) || extension.equals(JPEG) || extension.equals(PNG) || extension.equals(GIF) || extension.equals(TIF)
					|| extension.equals(TIFF)) {
				return true;
			}
			else
				return false;
		}
		return false;
	}

	private String getExtension(File f) {
		String extension= null;
		String s= f.getName();
		int i= s.lastIndexOf('.');
		
		if(i>0 && i<s.length()-1) 
			extension= s.substring(i+1).toLowerCase();
		
		return extension;
	}

	@Override
	public String getDescription() {				//defining the inherited abstract method
		return "Image Onty";
	}

}
