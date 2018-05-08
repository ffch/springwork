package com.cff.springwork.ocr.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import net.sourceforge.tess4j.util.LoadLibs;

public class TesseractExample {

	public static void main(String ars[]) {
		File imageFile = new File("C:\\Users\\fufei\\Desktop\\test.png");
		Tesseract instance = new Tesseract();

		// In case you don't have your own tessdata, let it also be extracted
		// for you
		// 这样就能使用classpath目录下的训练库了
		String path = System.getProperty("user.dir") + "/tessdata";

		instance.setLanguage("eng");// 英文库识别数字比较准确
		System.out.println(path);
		// Set the tessdata path
		instance.setDatapath(path);

		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}

	public static BufferedImage change(File file) {

		// 读取图片字节数组
		BufferedImage textImage = null;
		try {
			InputStream in = new FileInputStream(file);
			BufferedImage image = ImageIO.read(in);
			textImage = ImageHelper
					.convertImageToGrayscale(ImageHelper.getSubImage(image, 0, 0, image.getWidth(), image.getHeight())); // 对图片进行处理
			textImage = ImageHelper.getScaledInstance(image, image.getWidth() * 5, image.getHeight() * 5); // 将图片扩大5倍

		} catch (IOException e) {
			e.printStackTrace();
		}

		return textImage;
	}
}
