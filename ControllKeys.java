import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * 
 * Klasa ControllKeys eshte klase kontrolluese e cila zgjeron klasen abstrakte KeyAdapter  ne te cilen behet mundesimi i
 *  levizjes se objektit forg me taste t'veqanta 
 *
 */
public class ControllKeys extends KeyAdapter {

	//Deklarimi i objektit si objekt fushe per perdorim ne gjtih klasen
	private Frog f;
	/**
	 * Konstruktori i cili inicializon objekti Frog
	 * @param f: objekti perkates i cili levize
	 */
	public ControllKeys(Frog f) {
		this.f=f;
	}

	public void keyReleased(KeyEvent e) {
		//Perdorimi i if statment per kontrollimin se a jane te ndrydhur tasti A ose Left, nese po thirr metoden moveLeft() te klases forg
		if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
			f.moveLeft();	
		}
		//Perdorimi i if statment per kontrollimin se a jane te ndrydhur tasti D ose Right, nese po thirr metoden moveRight() te klases forg
		if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D) {
			f.moveRight();		
		}
		//Perdorimi i if statment per kontrollimin se a jane te ndrydhur tasti W ose Up, nese po thirr metoden jump() te klases forg
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W) {
			f.jump();			
		}
		//Perdorimi i if statment per kontrollimin se a jane te ndrydhur tasti S ose Down, nese po thirr metoden back() te klases forg
		if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
			f.back();			
		}		
	}

	public void keyPressed(KeyEvent e) {}
}
