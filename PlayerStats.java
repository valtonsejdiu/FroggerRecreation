import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*
 * Klasa PlayerStats eshte klase modeluese ne te cilen behet krijimi i statuseve si Koha, Score, Jetat dhe Goal,
 * gjithashtu ndodhen edhe metodat per manovrimin e tyre
 * 
 */
public class PlayerStats {

	//Deklarimi i nje array me JLabel qe do te mbaje imazhet e goalit te arritur
	private JLabel [] goalL = new JLabel[5];
	
	//Deklarimi dhe inicializimi i JLabel objekteve te cilat mbajne statuset perkatse
	private JLabel timerL= new JLabel();
	private JLabel lifesL= new JLabel();
	private JLabel scoreL= new JLabel();
	
	Font f = new Font("Verdana",400, 15);//Fonti te cilin perdor scoreL
	int score = 0;//Score qe ka lojtari momental


	public PlayerStats() {

		//Inicializimi i arrayt goalL me JLabelat perkates te cilat permbajne imazhin e forgut static,ku fillimisht jane invisible
		for(int i=0;i<5;i++) {
			goalL[i]= new JLabel();
			goalL[i].setIcon(new ImageIcon("Images\\frog\\frog1D.png"));
			goalL[i].setBounds(24+(144*i), 25, 48, 48);
			goalL[i].setVisible(false);
		}
		
		//Vendosja e imazhit dhe lokalimi i Timerit 
		timerL.setIcon(new ImageIcon("Images\\bg\\bgnoline.png"));
		timerL.setBounds(250,670,240,20);
		
		//Vendosja e imazhit dhe lokalimi i Jetave te frogut
		lifesL.setIcon(new ImageIcon("images\\frog\\froglifes.png"));
		lifesL.setBounds(570, 670, 150, 20);
		
		//Vendosja e imazhit, fontit dhe lokalimi i Scores
		scoreL.setBounds(40,660,200,30);
		scoreL.setText("Score: "+score);
		scoreL.setFont(f);
		scoreL.setForeground(Color.white);
	}
	
	/*
	 * Metodat ne vazhdim jane metodat per manovrimin me Timerin
	 */

	//Metoda decreaseTime() ben zvogelimin e kohes ne menyre vizuale duke e ngushtuar gjeresine per 4 njesi dhe zhvendos JLabelin
	//ne boshtin e X per 4 njesi ne te dhajt
	public void decreaseTime() {
		timerL.setBounds((int)timerL.getLocation().getX()+4, 670, timerL.getWidth()-4, 20);
	}

	//Metoda resetTimer() ben resetimin apo kthimin ne gjendjen fillestare te Timerit
	public void resetTimer() {
		timerL.setBounds(250,670,240,20);
	}

	public JLabel timer() {
		return timerL;
	}
	
	/*
	 * Metodat ne vazhdim jane metoda per manovrimin me Goal
	 */
	
	public JLabel[] frogs() {
		return goalL;
	}

	//Metoda resetGoal() ben resetimin apo kthimin e JLabelave te Goal ne gjendjen fillestare pra invisible
	public void resetGoal() {
		for(int i=0;i<goalL.length;i++) {
			goalL[i].setVisible(false);
		}
	}
	
	/*
	 * Metodat ne vazhdim jane metoda per manovrimin me Jetat
	 */
	
	//Metoda decreaseLife() ben zvogelimin vizual te jetave duke e zhvendosur JLabel te Jetave ne boshtin X per 34 njesi ne te djatht
	public void decreaseLife() {
		lifesL.setBounds((int)lifesL.getLocation().getX()+34, 670, 150, 20);
	}

	public JLabel lifes() {
		return lifesL;
	}
	
	/*
	 * Metodat ne vazhdim jane metoda per manovrimin me Score
	 */
	public JLabel score() {
		return scoreL;
	}

	//Metoda setScore() ben llogaritjen e scores duke njehesuar gjeresine e JLabel timerL ne proporcion me sekonda
	//dhe vendosjen e saj ne dritare
	public void setScore() {
		score+=100+(timerL.getWidth()/4*10);
		scoreL.setText("Score: "+score);
	}
	
	//Metoda restart() ben resetimin apo kthimin e te gjitha statseve ne gjendjen fillestare
	public void restart() {
		resetGoal();
		score=0;
		scoreL.setText("Score: " +score);
		resetTimer();
		lifesL.setBounds(570, 670, 150, 20);
	}
}

