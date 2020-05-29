import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 
 *	Eshte klase modeluese e cila implementon interfacen obstacleInterface, ne te jane metodat kryesore qe duhet te permbaje nje
 *	pengese e lojes
 *
 */
public class Obstacle extends JLabel implements obstacleInterface {
	
	//Deklarimi i variablav dhe objekteve te nevojshme per nje pengese
	private int x,y,h;
	private ImageIcon img;
	private String p;
	private int s;
	
	/**
	 * Konstruktori ne te cilen behet inicializimi i variablave dhe objekteve perkatese te pengeses ne fjale
	 * 
	 * @param xPos: Pozita fillestare ne boshtin X te panelit
	 * @param yPos: Pozita fillestare ne boshtin Y te panelit
	 * @param height: Gjatesia e imashit te pengeses e cila vendoset ne panel
	 * @param path: Pathi apo vendodhja lokale e imazhit ne paisje 
	 */
	public Obstacle(int xPos,int yPos, int height,String path) {

		this.x=xPos;
		this.y=yPos;
		this.h=height;
		this.p=path;
		this.img=new ImageIcon(p);

		img = this.resizeImage(img, height);//Rregullimi i madhesise se imazhit me ndimen e metodes resizeImage()

		int w = img.getIconWidth();
		int h = img.getIconHeight();
		setIcon(img);
		setBounds(x, y, w, h);
	}

	@Override
	public void setX(int xPos) {
		// TODO Auto-generated method stub
		this.x=xPos;		
	}

	@Override
	public void setY(int yPos) {
		// TODO Auto-generated method stub
		this.y=yPos;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	//Metoda e cila ben rregullimin e madhesise se imazhit sipas deshires duke dhene si parameter foton qe deshironi t'ia ndrroni
	//dhe gjatesine e kerkuar
	public ImageIcon resizeImage(ImageIcon image, int cHeight) {
		double k = image.getIconHeight()/cHeight;

		ImageIcon imageS = new ImageIcon(image.getImage().getScaledInstance((int)(image.getIconWidth()/k),cHeight, Image.SCALE_DEFAULT));

		return imageS;
	}
	
	//Metoda e cila ben mundesimin e levizjes se pengeses ne te djatht apo ne te majt varesisht prej
	//parametrit Direction te tipit boolean me shpejtesin e caktuar tek parametri speed i tipit int
	@Override
	public void move(boolean Direction, int speed) {
		// TODO Auto-generated method stub
		if(Direction) {
			this.s=speed;
			this.setX(this.getX()+speed);//Zhvendosja e pengeses ne te djatht per speed njesi ne boshtin X
		}else {
			this.s=-speed;
			this.setX(this.getX()-speed);//Zhvendosja e pengeses ne te majt per speed njesi ne boshtin X
		}
	}
	public int speed() {
		return s;
	}
}
