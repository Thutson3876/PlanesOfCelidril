import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Game {
	//Allows the player to choose between x amount of choices with the choices being displayed with the string y
	public static int choice(int x, String y) {
		Scanner in = new Scanner(System.in);
		int input = -1;
		boolean isInt = false;
		boolean loop = false;
		do {
			System.out.println(y);
			if(in.hasNextInt()) {
				input = in.nextInt();
				in.nextLine();
				isInt = true;
			}
			else {
				in.nextLine();
				isInt = false;
			}
			
			if (!isInt || input <= 0 || input > x) {
				loop = true;
				System.out.println("You didn't enter a valid input. Please try again.");
			}
			else {
				loop = false;
			}
		} while(loop);
		return input;
	}
	
	public static void main(String[] args) {
		Character player = new Character ();
		Character player2 = new Character();
		Character enemy = new Character ();
		Character chosen = new Character ();
		Character[] allies;
		Character[] enemies;
		Combat c;
		Scanner in = new Scanner(System.in);
		Random rng = new Random();
		int input;
		int enemyChoice;
		boolean game2 = false;
		
		player.setPlayer(true);
		player2.setPlayer(true);
		
		input = choice(2,"1: Single Player 2: 2 Player");
		
		if (input == 2) {
			game2 = true;
		}
		//2 player PvP
		if (game2) {
			allies = new Character[1];
			enemies = new Character[1];
			input = choice(6,"Player 1: Choose your class| 1: Warrior 2: Rogue 3: Archer 4: Mage 5: Conjurer 6: Druid");
			
			if (input == 1) {
				player = new Warrior();
			}
			else if (input == 2) {
				player = new Rogue();
			}
			else if (input == 3) {
				player = new Archer();
			}
			else if (input == 4) {
				player = new Mage();
			}
			else if (input == 5) {
				player = new Conjurer();
			}
			else if (input == 6) {
				player = new Druid();
			}
			
			System.out.println("Enter your name: ");
			player.setUsername(in.nextLine());
			if(player.getUsername().contentEquals("Boar")) {
				player = new Boar();
				player.setHealthAndMax(player.getHealth()*3);
			}
			else if(player.getUsername().contentEquals("Treant")) {
				player = new Treant();
				player.setHealthAndMax(player.getHealth()*3);
			}
			else if(player.getUsername().contentEquals("Thief")) {
				player = new Thief();
				player.setHealthAndMax(player.getHealth()*2);
			}
			else if(player.getUsername().contentEquals("Dragon")) {
				player = new Dragon();
			}
			else if(player.getUsername().contentEquals("Mind Flayer")) {
				player = new MindFlayer();
				player.setHealthAndMax(player.getHealth()*3);
			}
			else if(player.getUsername().contentEquals("Mirror")) {
				player = new Mirror();
			}
			else if(player.getUsername().contentEquals("Giant Boar")) {
				player = new GiantBoar();
				player.setHealthAndMax(player.getHealth()*2);
			}
			else if(player.getUsername().contentEquals("Dark Mage")) {
				player = new DarkMage();
			}
			
			player.setPlayer(true);
			
			input = choice(6,"Player 2: Choose your class| 1: Warrior 2: Rogue 3: Archer 4: Mage 5: Conjurer 6: Druid");
			
			if (input == 1) {
				player2 = new Warrior();
			}
			else if (input == 2) {
				player2 = new Rogue();
			}
			else if (input == 3) {
				player2 = new Archer();
			}
			else if (input == 4) {
				player2 = new Mage();
			}
			else if (input == 5) {
				player2 = new Conjurer();
			}
			else if (input == 6) {
				player2 = new Druid();
			}
			System.out.println("Enter your name: ");
			player2.setUsername(in.nextLine());
			if(player2.getUsername().contentEquals("Boar")) {
				player2 = new Boar();
				player2.setHealthAndMax(player.getHealth()*3);
			}
			else if(player2.getUsername().contentEquals("Treant")) {
				player2 = new Treant();
				player2.setHealthAndMax(player.getHealth()*3);
			}
			else if(player2.getUsername().contentEquals("Thief")) {
				player2 = new Thief();
				player2.setHealthAndMax(player.getHealth()*2);
			}
			else if(player2.getUsername().contentEquals("Dragon")) {
				player2 = new Dragon();
			}
			else if(player2.getUsername().contentEquals("Mind Flayer")) {
				player2 = new MindFlayer();
				player2.setHealthAndMax(player.getHealth()*3);
			}
			else if(player2.getUsername().contentEquals("Mirror")) {
				player2 = new Mirror();
			}
			else if(player2.getUsername().contentEquals("Giant Boar")) {
				player2 = new GiantBoar();
				player2.setHealthAndMax(player.getHealth()*2);
			}
			else if(player2.getUsername().contentEquals("Dark Mage")) {
				player2 = new DarkMage();
			}
			
			player2.setPlayer(true);
			
			allies[0] = player;
			enemies[0] = player2;
			System.out.println(player2.getUsername() + " wanders through the forest until suddenly they are attacked by " + player.getUsername() + "!");
			c = new Combat(allies, enemies);
			c.combat2();
		}
		//Single Player Campaign
		else {
			allies = new Character[1];
			input = choice(6,"Choose your class| 1: Warrior 2: Rogue 3: Archer 4: Mage 5: Conjurer 6: Druid");
			
			if (input == 1) {
				player = new Warrior();
			}
			else if (input == 2) {
				player = new Rogue();
			}
			else if (input == 3) {
				player = new Archer();
			}
			else if (input == 4) {
				player = new Mage();
			}
			else if (input == 5) {
				player = new Conjurer();
			}
			else if (input == 6) {
				player = new Druid();
			}
			allies[0] = player;
			player.setPlayer(true);
			//Random enemy
			/*enemyChoice = rng.nextInt(4);
			if (enemyChoice == 0) {
				enemy = new Character("Warrior");
			}
			else if (enemyChoice == 1) {
				enemy = new Character("Rogue");
			}
			else if (enemyChoice == 2) {
				enemy = new Character("Archer");
			}
			else if (enemyChoice == 3) {
				enemy = new Character("Mage");
			}*/
			
			//Add items to player inventory
			player.getInv().add("Health Potion");
			player.getInv().add("Fire Potion");
			
			/*enemies[0] = new Character("Thief");
			enemies[0].username = "Thief";
			enemies[1] = new Character("Thief");
			enemies[1].username = "Thief 2";
			enemy.username = enemy.name;
			*/
			//Flavor text
			//System.out.println("You wander through the forest until suddenly you are attacked by two thieves!");
			//Initiate Combat
			//Character.multicombat(player, enemies);
			//Rewards
			//player.inv.add("Apple");
			//player.inv.gold += 8 + rng.nextInt(3);
			//Choose inventory item
			input = choice(6, "Choose an item | 1: Health Potion 2: Apple 3: Banana 4: Rage Potion 5: Poison Vial 6: Fire Potion");
			if(input == 1) {
				player.getInv().add("Health Potion");
			}
			else if(input == 2) {
				player.getInv().add("Apple");
			}
			else if(input == 3) {
				player.getInv().add("Banana");
			}
			else if(input == 4) {
				player.getInv().add("Rage Potion");
			}
			else if(input == 5) {
				player.getInv().add("Poison Vial");
			}
			else if(input == 6) {
				player.getInv().add("Fire Potion");
			}
			
			//Choose fight
			enemyChoice = choice(9,"Choose your enemy | 1: Thieves 2: Dragons 3: Boars 4: Mind Flayers 5: Treants 6: Mirrors 7: Wee Boar 8: Giant Boar 9: Dark Mage");
			input = choice(10,"Enter amount of enemies: ");
			enemies = new Character[input];
			if(enemyChoice == 1) {
				for(int i = 0; i < input; i++) {
					enemies[i] = new Thief();
					enemies[i].setUsername(enemies[i].getName() + " " + (i+1));
				}
			}
			else if(enemyChoice == 2) {
				for(int i = 0; i < input; i++) {
					enemies[i] = new Dragon();
					enemies[i].setUsername(enemies[i].getName() + " " + (i+1));
				}
			}
			else if(enemyChoice == 3) {
				for(int i = 0; i < input; i++) {
					enemies[i] = new Boar();
					enemies[i].setUsername(enemies[i].getName() + " " + (i+1));
				}
			}
			else if(enemyChoice == 4) {
				for(int i = 0; i < input; i++) {
					enemies[i] = new MindFlayer();
					enemies[i].setUsername(enemies[i].getName() + " " + (i+1));
				}
			}
			else if(enemyChoice == 5) {
				for(int i = 0; i < input; i++) {
					enemies[i] = new Treant();
					enemies[i].setUsername(enemies[i].getName() + " " + (i+1));
				}
			}
			else if(enemyChoice == 6) {
				for(int i = 0; i < input; i++) {
					enemies[i] = new Mirror();
					enemies[i].setUsername(enemies[i].getName() + " " + (i+1));
				}
			}
			else if(enemyChoice == 7) {
				for(int i = 0; i < input; i++) {
					enemies[i] = new WeeBoar();
					enemies[i].setUsername(enemies[i].getName() + " " + (i+1));
				}
			}
			else if(enemyChoice == 8) {
				for(int i = 0; i < input; i++) {
					enemies[i] = new GiantBoar();
					enemies[i].setUsername(enemies[i].getName() + " " + (i+1));
				}
			}
			else if(enemyChoice == 9) {
				for(int i = 0; i < input; i++) {
					enemies[i] = new DarkMage();
					enemies[i].setUsername(enemies[i].getName() + " " + (i+1));
				}
			}
			
			c = new Combat(allies, enemies);
			c.multicombat();
		}
	}
}





