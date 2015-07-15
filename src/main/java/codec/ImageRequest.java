package codec;

public class ImageRequest {

	private int width;
	private int height;
	private int numberOfCharacters;
	
	
	public ImageRequest(int width, int height, int numberOfCharacters) {
		super();
		this.width = width;
		this.height = height;
		this.numberOfCharacters = numberOfCharacters;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getNumberOfCharacters() {
		return numberOfCharacters;
	}


	public void setNumberOfCharacters(int numberOfCharacters) {
		this.numberOfCharacters = numberOfCharacters;
	}


	@Override
	public String toString() {
		return "ImageRequest [width=" + width + ", height=" + height + ", numberOfCharacters=" + numberOfCharacters
				+ "]";
	}
	
}
