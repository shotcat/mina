package codec;

import java.awt.image.BufferedImage;

public class ImageResponse {

	private BufferedImage image1;
	private BufferedImage image2;
	
	
	public ImageResponse(BufferedImage image1, BufferedImage image2) {
		super();
		this.image1 = image1;
		this.image2 = image2;
	}
	public BufferedImage getImage1() {
		return image1;
	}
	public void setImage1(BufferedImage image1) {
		this.image1 = image1;
	}
	public BufferedImage getImage2() {
		return image2;
	}
	public void setImage2(BufferedImage image2) {
		this.image2 = image2;
	}
	
}
