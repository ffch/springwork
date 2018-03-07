package com.cff.springwork.ocr.test;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class TesseractExample {
	
	public static void main(String ars[]){
		File imageFile = new File("D:\\Users\\Yuri\\Desktop\\test.png");
		Tesseract instance = new Tesseract();

		//In case you don't have your own tessdata, let it also be extracted for you
		//这样就能使用classpath目录下的训练库了
		String path = System.getProperty("user.dir") + "/tessdata";

		instance.setLanguage("eng");//英文库识别数字比较准确
		System.out.println(path);
		//Set the tessdata path
		instance.setDatapath(path);

		try {
		    String result = instance.doOCR(imageFile);
		    System.out.println(result);
		} catch (TesseractException e) {
		    System.err.println(e.getMessage());
		}
	}
}
