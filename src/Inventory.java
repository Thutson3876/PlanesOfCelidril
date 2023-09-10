import java.util.Scanner;
import java.util.Random;

public class Inventory {
	private int numberOfEntries = 0;
	final int DEFAULT_SIZE = 6;
	private int gold = 0;
	private Character c;
	private String[] bag;
	private String[] allItems = new String[20];
	
	//Allows the player to choose between the items in their inventory
	//Returns the chosen item
	public String itemChoice() {
		Scanner in = new Scanner(System.in);
		String input = "";
		String chosen = "";
		boolean loop = false;
		boolean valid = false;

		do {
			input = in.nextLine();
			for(int i = 0; i < bag.length; i++) {
				if(input.equals(bag[i])) {
					valid = true;
					chosen = bag[i];
					break;
				}
			}
			if (!valid) {
				loop = true;
				System.out.println("You didn't enter a valid input. Please try again.");
			}
			else {
				loop = false;
			}
		} while(loop);
		return chosen;
	}
	
	//Displays interactive UI for the player to see/use/drop items in their inventory
	//Returns true if the player used an item
	//Returns false if the player dropped an item or did nothing
	public boolean display(Character[] enemies) {
		Scanner in = new Scanner(System.in);
		String item;
		String temp;
		int input = -1;
		//Accessing Items
		if (!isEmpty()) {
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("Gold: " + gold);
			System.out.println("Items: ");
			for(int i = 0; i < numberOfEntries; i++) {
				System.out.println(bag[i]);
			}
			System.out.println("-------------------------------------------------------------------------------------------------");
			
			input = Game.choice(3, "1: Use Item 2: Drop Item 3: Back");
			
			if(input == 1) {
				System.out.println("Use | Enter item name: ");
				temp = use(itemChoice(), enemies);
				if(temp != null) {
					System.out.println(temp);
					return true;
				}
				else {
					return false;
				}
				
			}
			else if(input == 2) {
				System.out.println("Drop | Enter item name: ");
				item = itemChoice();
				System.out.println("You dropped " + item + "!");
				drop(item);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			System.out.println("Your inventory is empty.");
			return false;
		}
	}
	
	//Checks if an item is in the character's inventory
	//Returns the index if it is in their inventory
	//Returns -1 if it isn't in their inventory
	public int find(String item) {
		for(int i = 0; i < numberOfEntries; i++) {
			if(bag[i].equals(item)) {
				return i;
			}
		}
		return -1;
	}
	
	//Add's an item to the character's inventory
	//Returns true if the item was added
	//Returns false if it wasn't added
	public boolean add(String item) {
		if(!isFull()) {
			bag[numberOfEntries] = item;
			numberOfEntries++;
			System.out.println(item + " was added to your inventory!");
			return true;
		}
		else {
			return false;
		}
	}
	
	//Drops an item from the character's inventory
	//Returns true if drop was successful
	//Returns false if drop wasn't successful
	public boolean drop(String item) {
		if(!isEmpty()) {
			if(find(item) != -1) {
				for(int i = find(item); i < numberOfEntries; i++) {
					bag[i] = bag[i+1];
				}
				numberOfEntries--;
				//System.out.println("You dropped " + item + "!");
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	//Checks if character's inventory is full
	//Returns true if their inventory is full
	//Returns false if it isn't full
	public boolean isFull() {
		if(numberOfEntries == bag.length) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Checks if character's inventory is empty
	//Returns true if their inventory is empty
	//Returns false if it isn't empty
	public boolean isEmpty() {
		if(numberOfEntries == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Uses specified item and removes it from character's inventory
	//Returns text describing the action
	//Returns null if the inputed item doesn't exist
	public String use(String item, Character[] enemies) {
		Character target;
		//Health Potion - Restores 35 health to the user
		if(item.equals("Health Potion")) {
			drop("Health Potion");
			c.changeHealth(35);
			return "Restored 35 health!";
		}
		//Poison Vial - Poisons a target for 6 poison damage for 5 turns
		else if(item.equals("Poison Vial")) {
			target = c.targeting(enemies);
			if(target == null) {
				System.out.println("All targets are invalid.");
				return null;
			}
			drop("Poison Vial");
			target.setHasPoison(6);
			target.setPoison(6);
			return "Target poisoned for 5 turns!";
		}
		//Apple - Restores 10 health and clears status effects to the user
		else if(item.equals("Apple")) {
			drop("Apple");
			c.changeHealth(10);
			c.clearStatus();
			return "Restored 10 health and cleared all status effects!";
		}
		//Banana - Restores 15 health and adds a Banana Peel to your hand
		else if(item.equals("Banana")) {
			drop("Banana");
			c.changeHealth(15);
			add("Banana Peel");
			return "Restored 15 health and added a Banana Peel to your inventory!";
		}
		else if(item.equals("Banana Peel")) {
			target = c.targeting(enemies);
			if(target == null) {
				System.out.println("All targets are invalid.");
				return null;
			}
			drop("Banana Peel");
			target.setHasStun(target.getHasStun() + 1);
			return "Stunned your target for 1 turn!";
		}
		//Invigoration Potion - Restores 50% of the user's max resource amount
		else if(item.equals("Invigoration Potion")) {
			drop("Invigoration Potion");
			c.setResourceAmt(c.getResourceAmt() + (int)(c.getResourceMax()*0.5));
			return "Restored " + (int)(c.getResourceMax()*0.5) + " " + c.getResourceName() + "!";
		}
		//Rage Potion - Enrages the target for 3 turns
		else if(item.equals("Rage Potion")) {
			target = c.targeting(enemies);
			if(target == null) {
				System.out.println("All targets are invalid.");
				return null;
			}
			drop("Rage Potion");
			target.setHasEnrage(4);
			return "Target is enraged for 3 turns!";
		}
		//Smoke Bomb - (Work in Progress) Escape combat
		else if(item.equals("Smoke Bomb")) {
			drop("Smoke Bomb");
			//program running
			return "oops";
		}
		//Fire Potion - Deals 20 damage to the target
		else if(item.equals("Fire Potion")) {
			target = c.targeting(enemies);
			if(target == null) {
				System.out.println("All targets are invalid.");
				return null;
			}
			drop("Fire Potion");
			target.setHealth(target.getHealth() - 20);
			c.setDmgOutput(20);
			return "Threw the potion at your target!";
		}
		else {
			return null;
		}
	}

	public int getNumberOfEntries() {
		return numberOfEntries;
	}

	public void setNumberOfEntries(int numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Character getC() {
		return c;
	}

	public void setC(Character c) {
		this.c = c;
	}

	public String[] getBag() {
		return bag;
	}

	public void setBag(String[] bag) {
		this.bag = bag;
	}

	public String[] getAllItems() {
		return allItems;
	}

	public void setAllItems(String[] allItems) {
		this.allItems = allItems;
	}

	public Inventory(Character x) {
		bag = new String[DEFAULT_SIZE];
		c = x;
		gold = 10;
	}
	
}





