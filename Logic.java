import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
/**
 * 
 * Klasa Logic eshte klase modeluese, eshte e emruar si Logic pasi qe ne te eshte e gjith pjesa logjike qe ndahet 
 * ne metoda te ndryshme 
 *
 */
public class Logic {
	//Deklarimi dhe inicializimi i variables speed me ane te klases Random per gjenerimin e nje numri random
	private int speed = new Random().nextInt(2)+2;

	/**
	 * Metode e cila ben mundesimin e bartjes apo ecjes se dy objekteve ne te njejten kohe me te njejten shpejtesi
	 * ne rast se jane njera mbi tjetren 
	 * @param f: Objekti i pare, ne rastin tone Objekti i Frog-it
	 * @param o: Objekti i dyte, ne rastin tone Objekti i njeres nga pengesat (vecanerisht drunjet dhe breshkat)
	 * @return kthen nje varibel te tipit boolean qe tregon gjendjen se a jane objektet njera mbi tjetrin
	 */
	public boolean moveWithFloats(Frog f, Obstacle o) {
		boolean rez=false;
		//Kontrollimi se a jane ne pozite te njejte ne boshtin Y
		if(f.getY()==o.getY()) {
			
			//Kontrollimi se a eshte objekti pare ne hapsiren e siperfaqes se objektit te dyte
			if(f.getX()+f.getWidth()/2>o.getX()&&f.getX()+f.getWidth()/2<o.getX()+o.getWidth()) {
				
				//Vendosja e pozites ne boshtin X te objektit te pare ne perputhje me objektin e dyte ne shpejtesi te njejt
				f.setX(f.getX()+o.speed());
				f.gif.setBounds((int)f.gif.getLocation().getX()+o.speed(), f.gif.getY(),f.gif.getWidth(),f.gif.getHeight());
				rez=true;
			}
		}
		return rez;
	}

/**
 * Metode e cila ben kontrollimin e perplasjes se dy objekteve mes veti
 * 
 * @param f: Objekti i pare, ne rastin tone Objekti i Frog-ut
 * @param o: Objekti i dyte, ne rastin tone Objekti i pengeses (veqanerisht automjetet)
 * @return kthen nje variabel te tipit boolean qe tregon per gjendjen e perplasjes
 */
	public boolean collisionWithCars(Frog f,Obstacle o) {
		boolean rez=false;
		
		//Kontrollimi se a jane ne pozite te njejte ne boshtin Y
		if(f.getY()==o.getY()) {

			//Kontrollimi se a jane duke u prekur objektet perkatese mes vete ne boshtin X
			if(f.getX()+f.getWidth()>o.getX()&&f.getX()<o.getX()+o.getWidth()) {

				rez= true;
			}			
		}return rez;
	}

	/**
	 * Metode e cila ben kontrollimin dhe rregullimin e pozites se objektit ne rastin tone objektit te Frog-ut
	 * ne menyre qe te kemi kontrolle ne raste se objekti del nga kufijte e dritares se shfaqur dhe rregullimin ne menyre
	 * qe te prekim skajet e dritares pa dale nga dritarja ne raste te veqanta
	 * 
	 * @param f: Objekti i cili kontrollohet
	 * @return kthen variabel te tipit boolean qe tregon gjendjen se a jane kaluar kufijte e dritares
	 */
	public boolean ofScreen(Frog f) {
		boolean rez=false;
		
		//Kontrollimi per anen e djatht te dritares
		if(f.getX()+48>672) {

			//Kontrollimi per pjesen e siperme te dritares, pra pjesa ku fillon (649) dhe mbaron(300) siperfaqja ujore
			if(f.getY()<649&&f.getY()>300) {

				f.setX(632);
				//Kontrollimi nese gjeresia e imazhit eshte me e madhe se 48(eshte duke ecur djathtas apo majtas) ashtu
				//qe te mos ket probleme te zhvendosjes se figures ne pozita te gabuara pasi qe ndryshon pozita e X te
				//JLabel gif ne baze te gjeresise se figures apo kahut te levizjes
				if(f.gif.getWidth()>48) {
					
					//Nese eshte duke levizur djathtas atehere pozita X e gif duhet te jet 48 njesi me mbrapa se pozita X e Frog
					f.gif.setBounds(f.getX()-48, (int)f.gif.getLocation().getY(), f.gif.getWidth(),f.gif.getHeight());
				}else {
					//Nese nuk eshte duke levizur djathtas atehere pozita X e gif eshte sa pozita X e Frog
					f.gif.setBounds(f.getX(), (int)f.gif.getLocation().getY(), f.gif.getWidth(),f.gif.getHeight());
				}
			//Nese eshte me e madhe se kufiri i dritares at her kthe gjendjen se e ka kaluar	
			}else if(f.getX()+48>680) {

				rez=true;
			}
		//Kontrollimi per anen e majt te dritares qe eshte e njejt me te djathten vetem me ane te kunderta
		}else if(f.getX()<0) {

			if(f.getY()<649&&f.getY()>300) {

				f.setX(-8);
				f.gif.setBounds(-8, (int)f.gif.getLocation().getY(),f.gif.getWidth(),f.gif.getHeight());
			}else if(f.getX()<-9) {
				rez=true;	
			}			
		}return rez;
	}

	/**
	 * Metode e cila mundeson levizjen e secilit objekt qe gjendet ne ArrayListen e Arrayt te Pengesave 
	 * me nje shpejtesi te caktuar dhe me kah te caktuar
	 * 
	 * @param a: ArrayLista qe permban te gjitha objektet si nje matrice
	 * @param r: Variabel e cila cakton rreshtat cift apo tek te matrices qe te levizin
	 * @param lr: Kahu apo drejtimi i levizjes , ne te majt(-) apo ne te djaht(+)
	 */
	public void movingObjects(ArrayList<Obstacle[]> a,int r,boolean lr) {

		//Dy unaza for per kapjen e secilit rresht cift apo tek dhe secilit objekt brenda rreshtit
		for(int i =r;i<a.size();i+=2) {

			for(int j=0;j<a.get(i).length;j++) {

				a.get(i)[j].move(lr,speed +i);//Vendosja e kahut dhe shpejtesise te objektit "j" ne rreshtin "i"

				//Kontrollimi i objektit nese ka kaluar dritaren ne anen e djatht
				if(a.get(i)[j].getX()>672) {

					//Nese ka kaluar ateher te kthehet ne anen tjeter te dritares, ashtu qe te mos krijohen shum objekte
					a.get(i)[j].setX(a.get(i)[j].getX()-672-a.get(i)[j].getWidth());			
				}
				//Kontrollimi i objektit nese ka kaluar dritaren ne anen e majt
				else if (a.get(i)[j].getX()<0-a.get(i)[j].getWidth()) {

					a.get(i)[j].setX(a.get(i)[j].getX()+672+a.get(i)[j].getWidth());	
				}
			}
		}
	}
	
	/**
	 * Metode e cila ben kontrollimin kur forg tentoj te arrij ne Goal
	 * @param f: Objekti i frog i cili kontrollohet
	 * @param g: Objektet e goal te cilat duhet aktivizohen apo te behen visible ne rast se arrin
	 * @return kthen nje array te tipit boolean ku elementi i pare tregon se a eshte boshe ai vend i goal, ndersa elementi
	 * 		   i dyte kthen gjendjen se a eshte ne pjesen e goal-ave(false) apo ne shkurre (true) 
	 */
	public boolean[] goal(Frog f,JLabel[] g) {
		boolean[] rez= {false,false};
		int place=-1;//Variabla e cila tregon numrin e goal-ve visible duke filluar nga 0
		int count =0;//Variabla e cila tregon a eshte prekur njera nga goal

		//Kontrollimi me unazen for nese eshte prekur ndonjera nga goal
		for(int i=0;i<5*144;i+=144) {

			//Kushti pra nese pozita ne boshtin Y eshte me e vogel se 73 ku perfshine pjesen e goal dhe kontrollimi
			//ne cilen prej vendeve te goal eshte
			if(f.getY()<73&&f.getX()>=10+i&&f.getX()<56+i) {

				place=i/144;//Vendosja ne cilin goal po tenton te hyj, pra 0(10),1(154),2(298),3(442),4(586)
				count =1;//Pra eshte ne njeren prej goal
			}
		}
		//Kontrollimi pra nese eshte pozita e forg ne boshtin Y me e vogel se 73 dhe nuk eshte ne njeren prej goal
		//ateher beje elementin e 2 true, apo kthe se eshte ne shkurre
		if(f.getY()<73&&count<1) {

			rez[1]=true;
		}

		//Kontrollimi se a eshte duke tentuar te hyje ne njeren prej goal
		if(place>=0) {

			//Kontrollo se ne ate goal a eshte vendi i lire, dmth a ka hyre ndonje here
			if(!g[place].isVisible()) {

				/*
				 * Nese po ateher beje te dukshme ate goal
				 * kthe gjendjen se ka hyre ne njeren prej goal
				 * vendose objektin Frog dhe gif ne pozitat fillestare
				 */
				g[place].setVisible(true);
				rez[0]=true;
				f.setX(336);
				f.setY(601);
				f.gif.setBounds(f.getX(), f.getY(), 96, 96);

			}
			//Per ndryshe kthe objektin forg ne poziten para se te tentoj te hyj
			else {

				f.setY(f.getY()+48);
				f.gif.setBounds(f.getX(), f.getY(), 96, 96);
			}
		}
		return rez;
	}
}
