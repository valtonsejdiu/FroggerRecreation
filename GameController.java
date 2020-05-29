import javax.swing.JOptionPane;
/**
 * Klasa GameController eshte klase kontrolluese, ne te cilen perfshihet i gjith kontrollimi i lojes apo pjesa ku
 * zhvillohet e gjith loja me ndihmen e klaseve tjera
 * *
 */
public class GameController {	

	//Deklarimi i klaseve te nevojshme per ekzekutim te lojes
	private Logic logic = new Logic();
	private AllObstacles o;
	private Frog f;
	private PlayerStats ps;
	private Gui gui;

	//Vairabel e cila ndihmon per kontrollimin nese forg eshte ne njerin prej objekteve ne uje
	boolean	inFloat;
	//Variabel e cila ndihmon ne simulimin e kohes (sekondave) permes se ciles ne njefar menyre kontrollohet timer
	int timer;
	//Variabel e cila ndihmon ne numerimin e goal te arritur per vleresimin e lojtarit
	int goal;

	/**
	 * Konstruktori ne te cilin inicializohen objektet e klaseve te nevojshme per ekzekutim te lojes
	 * 
	 * @param obs: Objekt i klases AllObstackes nga e cila marim detaje per pengesat, dhe manovrojme me to
	 * @param frog: Objekt i klases Frog nga e cila marim detaje per forg, dhe manovrojme me te
	 * @param s: Objekt i klases PlayerStats nga e cila marim detje per timer, score, jetat dhe manovrojme me to
	 * @param gui: Objekt i klases Gui per te cilin e perdorim qe dritaret shtese te jene te drejtuara ne kete dritare
	 */
	public GameController(AllObstacles obs,Frog frog,PlayerStats s,Gui gui){
		this.gui=gui;
		o=obs;
		f=frog;
		ps=s;
	}

	//Metode ndihmese e cila ne vete permban metodat te cilat nevojiten kur Frog humbet nje jete
	public void dead() {
		f.destroy();
		ps.decreaseLife();
		ps.resetTimer();
	}

	//Metoda ne te cilen thirren metodat kryesore te te gjitha klaseve qe nevojiten per funksionim te lojes
	public void run() {
		//Nje unaze while ashtu qe te jete cdo here e ekzekutuar deri ne momentin kur ndalojme per aryse te lojes
		while(true) {

			//Ne njefar menyre simulues i kohes reale ne baze te proporcionit te thread sleep me sekondat reale te aplikacionit
			if(timer%31==0) {
				ps.decreaseTime();
			}
			timer++;
//******************************************************************************************************************************/

			//Thirrja e metodave te klases Logic te cilat mundesojne levizjen e objekteve (pengesave)
			logic.movingObjects(o.car(), 0, false);
			logic.movingObjects(o.car(), 1, true);

			logic.movingObjects(o.turTree(),0, true);
			logic.movingObjects(o.turTree(),1, false);
//******************************************************************************************************************************/
		
			//Thirja e metodes nga klasa Logic per kontrollimin e objektit (frog) per rregullat e dritares
			logic.ofScreen(f);
//******************************************************************************************************************************/
			
			//Kontrollimi permes metodes nga klasa Logic nese eshte arritur goal
			if(logic.goal(f,ps.frogs())[0]) {
				/*
				 * Nese po ateher rrit goal per 1
				 * Thirr metoden setScore() nga klasa PlayerStats per llogaritje te scores
				 * Thirr metoden resetTimer() nga e njejta klase per kthimin e timer ne gjendjen fillestare
				 */
				goal++;
				ps.setScore();
				ps.resetTimer();

				//Kontrollimi se a gjendet frog ne shkurre apo ne goal, nese ne shkurre ateher thirr metoden dead()
			}else if(logic.goal(f,ps.frogs())[1]) {

				dead();
			}
//******************************************************************************************************************************/

			//Kontrollimi per secilin objekt a eshte ne kontakt me frog me ane te unazave te dyfishta for,
			//nese po thirr metoden dead()
			for(int i=0;i<o.car().size();i++) {
				for(int j=0;j<o.car().get(i).length;j++) {
					if(logic.collisionWithCars(f,o.car().get(i)[j])) {
						dead();
					}
				}
			}
//******************************************************************************************************************************/
			
			//Kontrollimi per secilin objekt a eshte frog mbi te me ane te unazave te dyfishta for,
			//nese po thirr bej variablen inFloat true ku me vone kontrollohet se a eshte ne uje apo mbi objekt
			inFloat=false;
			if(f.getY()<313&&f.getY()>72) {
				for(int i=0;i<o.turTree().size();i++) {
					for(int j=0;j<o.turTree().get(i).length;j++) {
						if(logic.moveWithFloats(f,o.turTree().get(i)[j])) {
							inFloat=true;
						}
					}
				}
				//Nese nuk eshte mbi objekt ateher thirr metoden dead()
				if(!inFloat) {
					
					dead();
				}
			}

//******************************************************************************************************************************/

			//Kontrollimi i kohes, nese eshte 0 ateher thirr metoden dead()
			if(ps.timer().getWidth()==0) {
				dead();
			}

			//Kontrollimi i numrit te jetave, nese tani me jane 0, ateher shfaq nje dialog opsioni duke pyetur
			//a deshironi te luani perseri dhe arritjen e tij (score)
			if(ps.lifes().getX()>650) {
				String[] options = new String[] {"Yes","No"};
				int response = JOptionPane.showOptionDialog(gui, "Ju Humbet!"+"\n"+ps.score().getText()+"\nDeshironi te luani perseri?", "Frogger Game",
						JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE,null, options, options[1]);
				//Nese opsioni i zgjedhur eshte "Yes" ateher restarto lojen dhe bej goal=0
				if(response==0) {
					ps.restart();
					goal=0;
				}else System.exit(0);//Per ndryshe mbylle aplikacionin
			}

			//Kontrollimi se a ka arritur te mbush te gjith goal, nese po vendos goal 0 dhe thirr metoden qe reseton
			//imazhet e goal mirepo me te njejten score dhe jete ku ka mbetur
			if(goal>4) {
				goal=0;
				ps.resetGoal();
			}

			try {
				Thread.sleep(32);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
}




