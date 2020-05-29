import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*
 * Klasa Frog eshte klase modeluese e cila fillimisht zgjeron klasen JLabel dhe ne kete klase jane te vendosura atributet 
 * dhe metodat perkatese qe objekti Frog eshte e domosdoshme ti kete.
 *
 */
public class Frog extends JLabel{

	//Atributet apo variablat te cilat vlejne per lokalimin e objektit Frog new Dritaren e lojes
	private int x,y;
	//Atributet apo variablat ne te cilat inicializohet vlera madhesis se levizjes t'objektit Frog ne njesi pikselash
	private int moveSize=48;
	//Variabel ne te cilen eshte i inicializuar numri i jetave te objekti Frog
	private int life=3;

	/**
	 * Deklarimi dhe inicializimi i objekteve si JLabel dhe ImageIcon te cilat sherbejne per paraqitjen vizuale
	 * te levizjeve te objektit Frog
	 * gif: JLabel ne te cilin vendosen pamjet vizuale (Imazhin) te tipit gif
	 * gifL: ImageIcon i inicializuar (si parameter hyres i objekti) me imazhin e tipi gif e Frogut duke levizur ne te majt
	 * gifR: ImageIcon i inicializuar  me imazhin e tipi gif e Frogut duke levizur ne te djatht
	 * gifD: ImageIcon i inicializuar  me imazhin e tipi gif e Frogut duke levizur poshte
	 * gifU: ImageIcon i inicializuar  me imazhin e tipi gif e Frogut duke levizur lart
	 */
	JLabel gif = new JLabel();
	ImageIcon gifL = new ImageIcon("images\\frog\\froggif2L.gif");
	ImageIcon gifR = new ImageIcon("images\\frog\\froggif2R.gif");
	ImageIcon gifD = new ImageIcon("images\\frog\\froggif2D.gif");
	ImageIcon gifU = new ImageIcon("images\\frog\\froggif2U.gif");

	/**
	 * 
	 * @param xpos : variabela ne te cilen vendoset(inicializohet) pozita fillestare e Frogut ne boshtin X ne dritaren e lojes
	 * @param ypos : variabela ne te cilen vendoset pozita fillestare e Frogut ne boshtin X ne dritaren e lojes
	 * @param h : variabela ne te cilen vendoset lartesia apo gjatesia objektit Frog e cila eshte klase zgjeruese e klases JLabel
	 */
	public Frog(int xpos,int ypos, int h) {

		this.x=xpos;
		this.y=ypos;

		setBounds(xpos, ypos, 48,48);		

		gif.setIcon(gifU);
		gif.setBounds(xpos, ypos, 48, 96);

		//Vendosja e borderave (rrethuesve) te objektit Frog dhe objektit gif per spjegim se si funksionon logjika

		setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.red));
		gif.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int xpos) {
		this.x =xpos;
	}

	public void setY(int ypos) {
		this.y =ypos;
	}

	public int getLifes() {
		return life;
	}

	//Metode e cila realizon levizjen e Frogut ne te majt per moveSize njesi 
	public void moveLeft(){	 

		/*
		 * Fillimisht kontrollohet me if statment me kusht qe pozita e X te objektit te frogut te jet me e madhe se 0
		 * ne menyre qe mos te munde te levize ne te majt te dritares nese eshte ne skaj te saj
		 */
		if(getX()>0) {

			this.setX(this.getX()-moveSize);//Rivendosja e pozites X te objektit te Frogut per moveSize njesi ne te majt
			gifL.getImage().flush();//Refreshim i imazhit, per mundsi perseritjeje
			gif.setIcon(gifL);//Vendosja e imashit gifL, imazh i cili levize ne te majt
			gif.setBounds(getX(),getY(),96,48);//Rivendosja e pozites X dhe Y te JLabel gif ne poziten e objektit Frog
		}
	}

	//Metode e cila realizon levizjen e Frogut ne te djatht per moveSize njesi 
	public void moveRight(){

		/*
		 * Fillimisht kontrollohet me if statment me kusht qe pozita e X te objektit te frogut te jet me e vogel se 628
		 * ne menyre qe mos te munde te levize ne te djatht te dritares nese eshte ne skaj te saj
		 */
		if(getX()<628) {

			this.setX(this.getX()+moveSize);
			gifR.getImage().flush();
			gif.setIcon(gifR);
			gif.setBounds(getX()-48,getY(),96,48);
		}
	}

	//Metode e cila realizon levizjen e Frogut lart per jumpSize njesi 
	public void jump(){

		this.setY(this.getY()-moveSize);//Rivendosja e pozites Y te objektit te Frogut per moveSize njesi lart
		gifU.getImage().flush();
		gif.setIcon(gifU);
		gif.setBounds(getX(),getY(),48,96);
	}

	//Metode e cila realizon levizjen e Frogut lart per jumpSize njesi 
	public void back(){

		//Fillimisht kontrollohet a eshte me e vogel se 601 ashtu qe mos te dale jasht pjese se lojes
		if(getY()<601){

			this.setY(this.getY()+moveSize);
			gifD.getImage().flush();
			gif.setIcon(gifD);
			gif.setBounds(getX(),getY()-48,48,96);
		}
	}

	/*
	 * Metoda destroy eshte metode e cila realizon pamjen vizuale te shkatrrimit apo vdekjes se forgut si dhe zbritjen e
	 * jetave gjithashtu dhe kthimin e objektit dhe jlabel gif ne poziten fillestare 
	 * 
	 */	
	public void destroy() {

		gif.setBounds(getX(), getY(), 48, 48);//Vendosja e pozites se gif ne poziten e forg

		//Nderrimi i imazhit te gif, vendosja e imazhit te forgut dead me ndimen e metodes resizeImg me lartesi 48
		gif.setIcon(resizeImg(new ImageIcon("Images\\Frog\\dead.png"),48));

		life--;//Zbritja e numrit te jetave per 1

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();			
		}

		//Rivendosja ne piken villestare 601,300 te forgut dhe jlabel gif dhe rivendosja e imazhit adekuat
		setY(601);
		setX(300);
		gif.setIcon(gifU);
		gifU.getImage().flush();
		gif.setBounds(getX(),getY(),48,96);
	}

	/**
	 * Metode e cila realizon nderrimin e madhesis se imazhit pa qrregullimin e proporcionit te imazhit
	 * @param image: Imazhi i cili deshirojme te rregullojme
	 * @param cHeight: madhesia e gjatesis se deshiruar te imazhit
	 * @return nje objekt ImageIcon i cili permbane imazhin me gjatesis cHeight dhe gjeresine ne proporcione me lartesine
	 */
	private static ImageIcon resizeImg(ImageIcon image,int cHeight) {

		double k = image.getIconHeight()/cHeight;//Variabel e cila ndimon ne llogaritjen e proporcionit te gjatesis me gjeresin
		ImageIcon imageS = new ImageIcon(image.getImage().getScaledInstance((int)(image.getIconWidth()/k),cHeight, Image.SCALE_DEFAULT));
		return imageS;
	}
}