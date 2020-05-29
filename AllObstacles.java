import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * Klasa AllObstacles eshte klase modeluese e cila ne vete permban te gjitha pengesat qe loja i ka
 * dhe metodat per rregullimin dhe organizimin e pengesave
 *
 */
public class AllObstacles{
	//ArrayList e tipit Obstacle array, pra elementa ka array te tipit Obstacle qe vlene per deklarimin e klaseve per
	//veturat, nje lloj matrice
	private ArrayList<Obstacle[]> car = new ArrayList<Obstacle[]>();
	//ArrayList e tipit Obstacle array, pra elementa ka array te tipit Obstacle qe vlene per deklarimin e klaseve per
	//breshkat dhe drunjt, nje lloj matrice
	private ArrayList<Obstacle[]> turTree = new ArrayList<Obstacle[]>();
	//Array i tipit String qe permban si elemente paths te pengesave vetura
	private String[] carsPic= {"Images\\Cars\\kcarL.png","Images\\Cars\\gcarR.png","Images\\Cars\\zcarL.png","Images\\Cars\\ycarR.png","Images\\Cars\\bcarL.png"};
	//Array i tipit String qe permban si elemente paths te pengesave breshka dhe drunj
	private String[] turTreePic= {"Images\\Wood\\mwood.png","Images\\Turtle\\2turtlesl.png","Images\\Wood\\lwood.png","Images\\\\Wood\\\\swood.png","Images\\Turtle\\3turtlesr.png"};
	private Random r = new Random();
	//Array i tipit int ku si elemente ka numrin e veturave apo breshkave/drunjve ne rresht
	private int[]carsNum={2,3,2,2,3};
	private int[]turTreeNum= {2,2,2,2,2};


	public AllObstacles() {
		carsDistance(150);
		turTreeDistance(150);
	}

	/**
	 * Metode e cila ben inicializimin, vendosjen e imazhit perkates, organizimin e objekteve ne rresht me distance te
	 * caktuar ne baze te objektit te pare qe lokalizuar ne pozita X ne menyre randome ne permes klases Random dhe Y
	 * 
	 * @param distanca: vlera apo madhsia e distances ne mes pengesave ne rresht ne njesi pikselash
	 */
	public void carsDistance(int distanca) {

		//Mbushja e ArrayList me Array-t, array te cilet kane numer te objekteve perkates 
		for(int i=0;i<5;i++) {

			car.add(new Obstacle[carsNum[i]]);
		}

		//Inicializimi objektit te pare ne secilin rresht
		for(int i=0;i<car.size();i++) {

			car.get(i)[0]=new Obstacle(r.nextInt(672),361+(i*48),48,carsPic[i]);
		}

		//Organizimi i secilit objekt ne secilin rresht ne baze te objektit te pare te rreshtit perkates me distance te caktuar
		for(int i=0;i<car.size();i++) {

			for(int j=1;j<car.get(i).length;j++) {

				car.get(i)[j]= new Obstacle(car.get(i)[0].getX()+distanca*j*2,car.get(i)[0].getY(),48,carsPic[i]);
			}
		}		
	}
	/**
	 * Metode e cila ben inicializimin, vendosjen e imazhit perkates, organizimin e objekteve ne rresht me distance te
	 * caktuar ne baze te objektit te pare qe lokalizuar ne pozita X ne menyre randome ne permes klases Random dhe Y
	 * 
	 * @param distanca: vlera apo madhsia e distances ne mes pengesave ne rresht ne njesi pikselash
	 */
	public void turTreeDistance(int distanca) {

		for(int i=0;i<5;i++) {

			turTree.add(new Obstacle[turTreeNum[i]]);			
		}

		for(int i=0;i<turTree.size();i++) {

			turTree.get(i)[0]=new Obstacle(r.nextInt(672),73+(i*48),48,turTreePic[i]);
		}

		for(int i=0;i<turTree.size();i++) {

			for(int j=1;j<turTree.get(i).length;j++) {

				turTree.get(i)[j]= new Obstacle(turTree.get(i)[0].getX()+distanca*j*2,turTree.get(i)[0].getY(),48,turTreePic[i]);
			}
		}
	}

	public ArrayList<Obstacle[]> car(){
		return car;
	}

	public ArrayList<Obstacle[]> turTree(){
		return turTree;
	}
}

