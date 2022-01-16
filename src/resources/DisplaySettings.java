package resources;

public class DisplaySettings
{
	public static final int PIXEL_PER_TILE = 65;

	public static final int MILLISECONDS_PER_FRAME_TO_MAINTAIN_FPS = 1000*DisplaySettings.SCALE / DisplaySettings.FPS;
	public static final int SCALE = 1; //TODO MAKE IT WORK
	
	public static final int FPS = 40*SCALE;
}
