import javax.swing.ImageIcon;

public interface obstacleInterface {
	
	public void setX(int xPos);
	public void setY(int yPos);
	public int getY();
	public int getX();
	public void move(boolean Direction,int speed);
	public ImageIcon resizeImage(ImageIcon image,  int cWidth);
	
}
