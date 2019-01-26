package com.cff.springwork.ocr.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

public class TesseractService {
	private String tessdataPath;
	private String tessdataLang;

	public String ocr(String filePath) {
		try {
			File imageFile = new File(filePath);
			Tesseract instance = new Tesseract();
			// 使用classpath目录下的训练库
			String path = tessdataPath;
			instance.setLanguage(tessdataLang);// 英文库识别数字比较准确

			instance.setDatapath(path);
			String result = instance.doOCR(imageFile);
			return result;
		} catch (TesseractException e) {
			e.printStackTrace();
			return null;
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
