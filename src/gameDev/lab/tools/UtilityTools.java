package gameDev.lab.tools;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class UtilityTools {
	
	public static String getAddr() {
		return "C:\\Users\\Temp\\Desktop\\New folder\\Art\\practice_test";
	}
	
	// Returns the BufferedImage of a file
	public static BufferedImage readImage(String addr) {
		
		try {
			File file = new File(addr);
			BufferedImage bf = ImageIO.read(file);
			return bf;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void writeImage(String addr, BufferedImage bf) {
		File file = new File(addr);
		
		//if(file.exists()) {
			try {
				ImageIO.write(bf, "png", file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}
	
	public static BufferedImage cropSprite(BufferedImage bi,
			int x, int y, int width, int height) {
		
		BufferedImage croppedImage =
				bi.getSubimage(x, y, width, height);
		
		
		return croppedImage;
	} 
	
	
	/*
		Creates individual sprites out of a spritesheet
		takes as paramenters:
		
		-bi: BufferedImage containing the spritesheet
		-addr: a file address where the sprites are going to be created
		-name: the shared name of the individual sprites
		-n_vertical: number of individual vertical images
		-n_horizontal: number of individual horizontal images 
		
	 */
	public static void cookieCutter(BufferedImage bi, String addr, String name, 
			int n_vertical, int n_horizontal) {
		
		int width, height, imgWidth, imgHeight, count = 0;
		
		width = bi.getWidth();
		height = bi.getHeight();
		
		imgWidth = width/n_horizontal;
		imgHeight = height/n_vertical;
		
		for(int i = 0; i < n_vertical; i++) {
			for(int j = 0; j < n_horizontal; j++) {
				//System.out.println(imgHeight + (imgHeight*j));
				BufferedImage sprite = cropSprite(bi, imgWidth * j, imgHeight * i, imgWidth, imgHeight);
				writeImage(addr + name + "-" + count + ".png", sprite);
				count++;
			}
		}
		
	}
	
	public static void cookieCutter(BufferedImage bi, String addr, String name, int start_point, 
			int n_vertical, int n_horizontal, int row, int column) {
		
		int width, height, imgWidth, imgHeight, count = 0;
		
		width = bi.getWidth();
		height = bi.getHeight();
		
		imgWidth = width/n_horizontal;
		imgHeight = height/n_vertical;
		
		for(int i = start_point; i < row; i++) {
			for(int j = 0; j < column; j++) {
				//System.out.println(imgHeight + (imgHeight*j));
				BufferedImage sprite = cropSprite(bi, imgWidth * j, imgHeight * i, imgWidth, imgHeight);
				writeImage(addr + name + "-" + count + ".png", sprite);
				count++;
			}
		}
	
	}

}
