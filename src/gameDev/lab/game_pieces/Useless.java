package gameDev.lab.game_pieces;
import java.awt.image.BufferedImage;

import gameDev.lab.tools.UtilityTools;

public class Useless {
	public static void main(String[] args) {
		String addr = "C:\\Users\\Temp\\Desktop\\New folder\\Art\\practice_test\\";
		BufferedImage sprite_sheet = UtilityTools.readImage(addr + "sprites-3.png");
		UtilityTools.cookieCutter(sprite_sheet, addr, "boy_front",0, 4, 4, 1, 4);
		UtilityTools.cookieCutter(sprite_sheet, addr, "boy_back", 1, 4, 4, 2, 4);
		UtilityTools.cookieCutter(sprite_sheet, addr, "boy_right",2, 4, 4, 3, 4);
		UtilityTools.cookieCutter(sprite_sheet, addr, "boy_left", 3, 4, 4, 4, 4);
	}
}
