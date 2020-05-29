import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * Klasa Gui eshte klase grafike apo visuale e cila zgjeron JPanel dhe ne te behet shtimi apo paraqitja e secilit objekt
 * ne panelin e dritares se lojes
 *
 */
public class Gui extends JPanel {

/**
 * Konstruktori i klases ne te cilin behet deklarimi, inicializimi deh vendosja e secilit objet ne panon e dritares se lojes
 * 
 * 
 * @param f: Objekti i Frogut te cilin do ta vendosim ne panelin e dritares se lojes 
 * @param o: Objekti i pengesave te cilat do te vendosen ne panel
 * @param k: Objekti i klases e cila ben kontrollimin e tasteve te ndrydhura ne menyre qe ti jep urdher dritares perkatese
 * @param ps: Objekti i statuseve te lojes te cilat do vendosen ne panel
 */
	public Gui(Frog f,AllObstacles o, ControllKeys k,PlayerStats ps) {
		JLabel bg = new JLabel();//JLabel qe permbane imazhin e backgroundit te lojes
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
		frame.setSize(688, 740);
		frame.setResizable(false);
		frame.setTitle("Frogger Game");
		frame.addKeyListener(k);		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		
		//Vendosja e layout te panelit ne null per manovrim me te lehte te zhvendosjes se objekteve
		setLayout(null);
		bg.setIcon(new ImageIcon("Images\\Bg\\bgnoline.png"));
		bg.setBounds(0,0,688,768);

		//Ne vazhdim behet shtimi i tgjitha objekteve te duhura ne panelin e dritares, duke filluar nga score, frog, timer
		//lifes, pengesat levizese dhe backgroundi
		add(ps.score());
		add(f.gif);
		add(f);
		add(ps.timer());
		add(ps.lifes());
		for(int i =0;i<o.car().size();i++) {

			for(int j=0;j<o.car().get(i).length;j++) {

				this.add(o.car().get(i)[j]);
			}
		}
		for(int i =0;i<5;i++) {

			this.add(ps.frogs()[i]);			
		}

		for(int i =0;i<o.turTree().size();i++) {

			for(int j=0;j<o.turTree().get(i).length;j++) {

				this.add(o.turTree().get(i)[j]);
			}
		}
		add(bg);

	}
	public void paintComponent(Graphics g) {
		repaint();
	}

}
