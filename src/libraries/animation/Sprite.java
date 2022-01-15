package libraries.animation;

/**
 * I prefere create such class myself but this part of the project is bonus so
 * unused for now 
 * found on https://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system
 * @author Savlon
 *
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import resources.CreaturesInfos;

public class Sprite {

    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = CreaturesInfos.MOTER_SRITE_SHEET_SIZE; //image/CreaturesInfos.MOTER_CELLS_NB

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
//        try {
//            sprite = ImageIO.read(new File("images/" + file + ".png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("AnimationSpriteSheet");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}