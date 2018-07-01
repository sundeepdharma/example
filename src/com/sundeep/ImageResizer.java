package com.sundeep;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageResizer {

	public static void main(String[] args) {

		String path = "C:\\Users\\Sundeep\\Pictures\\Sundeep\\Sundar\\candid\\part2";
		String newPath = "C:\\Users\\Sundeep\\Pictures\\Sundeep\\Sundar\\resized\\candid\\part2";
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				if(listOfFiles[i].getName().contains("JPG") || listOfFiles[i].getName().contains("jpg") || listOfFiles[i].getName().contains("PNG") || listOfFiles[i].getName().contains("png")){
					createResizedImage(listOfFiles[i], newPath);
				}				
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
			System.out.println("Image # :"+i);
		}
	}

	public static void createResizedImage(File file, String newPath) {

		try {
			BufferedImage originalImage = ImageIO.read(file);
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
			BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			ImageIO.write(resizeImageJpg, "jpg", new File(newPath+"\\"+file.getName()));

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		int IMG_WIDTH = 1920;
		int IMG_HEIGHT = 1080;
		
		System.out.println("Image Width:"+originalImage.getWidth());
		System.out.println("Image Height:"+originalImage.getHeight());
		double withFraction;
		withFraction = originalImage.getWidth()/1920.00;
		try{
			if(originalImage.getWidth()<1920){
				return originalImage;
			}else{
				//IMG_HEIGHT = Math.round((1920/originalImage.getWidth())*originalImage.getHeight());
				System.out.println("originalImage.getWidth(): "+originalImage.getWidth());
				System.out.println("originalImage.getHeight(): "+originalImage.getHeight());
				if(originalImage.getHeight()>originalImage.getWidth()){
					withFraction = originalImage.getHeight()/1920.00;
					IMG_HEIGHT = 1920;
					IMG_WIDTH = (int) Math.round(originalImage.getWidth()/withFraction);
				}else{
					IMG_HEIGHT = (int) Math.round(originalImage.getHeight()/withFraction);
				}
				
			}
			System.out.println("IMG_WIDTH: "+IMG_WIDTH);
			System.out.println("IMG_HEIGHT: "+IMG_HEIGHT);
			System.out.println("withFraction:");
			System.out.printf("%.3f", withFraction);
			
			BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
			g.dispose();

			return resizedImage;
		}catch(Exception e){
			e.printStackTrace();
		}
		return originalImage;
	}

}
