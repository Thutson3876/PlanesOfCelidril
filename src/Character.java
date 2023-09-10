import java.util.Random;
import java.util.Scanner;

public class Character{
	private String username = "Player";
	private String hp;
	private String name;
	private boolean isPlayer = false;
	private boolean isAlive = true;
	private Combat currentCombat;
	//Abilities
	private Ability[] abilities;
	private String ability1Name;
	private String ability2Name;
	private String ability3Name;
	private int ability1Cost;
	private int ability2Cost;
	private int ability3Cost;
	private int ability1BaseCD;
	private int ability2BaseCD;
	private int ability3BaseCD;
	private int ability1CCD;
	private int ability2CCD;
	private int ability3CCD;
	//Basic Stats
	private Character prevTarget;
	private Inventory inv;
	private String resourceName;
	private int level;
	private int exp;
	private int resourceAmt;
	private int resourceMax;
	private int maxHealth;
	private int health;
	private int maxArmor;
	private int armor;
	private int attackDmg;
	private int dmgOutput;
	private int critChance;
	//Specific Stats
	private int trueShot = -1;
	private int steady = -1;
	private int rapidFire = -1;
	private int swordDuration = 0;
	private int swordDmg;
	private double reducedDmg = 0;
	private int hasArmorGain;
	//Status Effects
	private boolean stunned = false;
	private boolean frozen = false;
	private int hasFrozen;
	private int hasStun;
	private int hasBleed;
	private int hasPoison;
	private int invisible;
	private int hasWeakness;
	private int hasEnrage;
	//Damage character applies to target
	private int poisonDmg;
	private int bleedDmg;
	//Damage character takes per tick
	private int poison;
	private int bleed;
	
	public Character () {
		inv = new Inventory(this);
		bleedDmg = 7;
		poisonDmg = 6;
		bleed = 6;
		poison = 5;
	}
	//Allows player to choose between multiple targets and defaults if there is only possible target
	//Returns the chosen target
	//Returns null if no targets are available to attack
	public Character targeting(Character[] targets) {
		prevTarget = null;
		if(!isPlayer) {
			prevTarget = npcTargeting(targets);
			return prevTarget;
		}
		String output = "";
		int chosen;
		int invisCount = 0;
		boolean[] invis = new boolean[targets.length];
		if(targets.length == 1) {
			if(targets[0].invisible == 0) {
				return targets[0];
			}
			else {
				return null;
			}
		}
		for(int i = 0; i < targets.length; i++) {
			if(targets[i].invisible > 0) {
				invis[i] = true;
				invisCount++;
			}
		}
		if(invisCount == targets.length) {
			return null;
		}
		while(true) {
			for(int i = 0; i < targets.length; i++) {
				if(!invis[i]) {
					output += (i + 1) + ": " + targets[i].name + " ";
				}
			}
			chosen = Game.choice(targets.length, "Choose your target: " + output) - 1;
			if(targets[chosen].invisible == 0) {
				prevTarget = targets[chosen];
				return targets[chosen];
			}
		}
	}
	
	public Character npcTargeting(Character[] targets) {
		Random rng = new Random();
		int chosen;
		int invisCount = 0;
		boolean[] invis = new boolean[targets.length];
		if(targets.length == 1) {
			if(targets[0].invisible == 0) {
				return targets[0];
			}
			else {
				return null;
			}
		}
		for(int i = 0; i < targets.length; i++) {
			if(targets[i].invisible > 0) {
				invis[i] = true;
				invisCount++;
			}
		}
		if(invisCount == targets.length) {
			return null;
		}
		while(true) {
			chosen = rng.nextInt(targets.length);
			if(targets[chosen].invisible == 0) {
				return targets[chosen];
			}
		}
	}
	
	//Character deals attackDmg to chosen target
	//Returns true if target is chosen
	//Returns false if no targets can be attacked
	public boolean autoAttack(Character target) {
		int critical = 0;
		Random rng = new Random();
		prevTarget = target;
		if(target.invisible > 0) {
			dmgOutput = 0;
			return false;
		}
		invisible = 0;
		dmgOutput = attackDmg;
		autoAttackPassive(target);
		if (swordDuration > 0) {
			dmgOutput = swordDmg;
		}
		if (name.contentEquals("Mage")) {
			dmgOutput = (int)Math.round(attackDmg + attackDmg*.33*resourceAmt);
		}
		else if (resourceName.contentEquals("Rage")) {
			if (hasEnrage > 0) {
				dmgOutput += 4;
				resourceAmt += dmgOutput;
			}
			else {
				resourceAmt += attackDmg;
			}
		}
		else if (name.contentEquals("Archer")) {
			if (rapidFire > 0) {
				dmgOutput += (int)Math.round(.66*dmgOutput);
			}
			if (steady > 0) {
				resourceAmt += 15;
			}
			trueShot++;
		}
		critical = rng.nextInt(100);
		if (critical < critChance) {
			dmgOutput = (int)Math.round(dmgOutput * 1.5);
			System.out.println(username + " CRITICAL STRIKE!");
		}
		if(hasWeakness > 0) {
			dmgOutput -= (int)Math.round(dmgOutput/4);
		}
		if(target.reducedDmg != 0) {
			dmgOutput = (int)Math.round(dmgOutput*(1 - target.reducedDmg));
		}
		
		if (target.armor > 0) {
			target.armor -= dmgOutput;
			if (target.armor < 0) {
				target.health += target.armor;
				target.armor = 0;
			}
		}
		else {
			target.health -= dmgOutput;
		}
		
		prevTarget = target;
		return true;
	}
	
	//Casts character's first ability
	//Returns true if ability is successful
	//Returns false if ability isn't successful
	public boolean action1 (Character[] allies, Character[] enemies) {
		prevTarget = null;
		Random rng = new Random();
		int critical = 0;
		invisible = 0;
		
		if(!ability1(allies, enemies)) {
			return false;
		}
		
		if(dmgOutput != 0) {
			critical = rng.nextInt(100);
			if (critical < critChance) {
				dmgOutput = (int)Math.round(dmgOutput * 1.5);
				System.out.println(username + " CRITICAL STRIKE!");
			}
		}
		
		if(hasWeakness > 0) {
			dmgOutput -= (int)Math.round(dmgOutput/4);
		}
		if(prevTarget != null && prevTarget.reducedDmg != 0) {
			dmgOutput = (int)Math.round(dmgOutput*(1 - prevTarget.reducedDmg));
		}
		
		if(prevTarget != null && dmgOutput > 0) {
			prevTarget.dmgTakenPassive(this, enemies, allies);
			dmgDealtPassive(prevTarget, allies, enemies);
		}
		
		ability1CCD = ability1BaseCD;
		resourceAmt -= ability1Cost;
		
		if(prevTarget != null) {
			if (prevTarget.armor > 0) {
				prevTarget.armor -= dmgOutput;
				if (prevTarget.armor < 0) {
					prevTarget.health += prevTarget.armor;
					prevTarget.armor = 0;
				}
			}
			else {
				prevTarget.health -= dmgOutput;
			}
		}
		return true;
	}
	
	//Casts character's second ability
	//Returns true if ability is successful
	//Returns false if ability isn't successful
	public boolean action2 (Character[] allies, Character[] enemies) {
		prevTarget = null;
		Random rng = new Random();
		int critical = 0;
		invisible = 0;
		
		if(!ability2(allies, enemies)) {
			return false;
		}
		
		if(dmgOutput != 0) {
			critical = rng.nextInt(100);
			if (critical < critChance) {
				dmgOutput = (int)Math.round(dmgOutput * 1.5);
				System.out.println(username + " CRITICAL STRIKE!");
			}
		}
		
		if(hasWeakness > 0) {
			dmgOutput -= (int)Math.round(dmgOutput/4);
		}
		if(prevTarget != null && prevTarget.reducedDmg != 0) {
			dmgOutput = (int)Math.round(dmgOutput*(1 - prevTarget.reducedDmg));
		}
		ability2CCD = ability2BaseCD;
		resourceAmt -= ability2Cost;
		
		if(prevTarget != null && dmgOutput > 0) {
			prevTarget.dmgTakenPassive(this, enemies, allies);
			dmgDealtPassive(prevTarget, allies, enemies);
		}
		
		if(prevTarget != null) {
			if (prevTarget.armor > 0) {
				prevTarget.armor -= dmgOutput;
				if (prevTarget.armor < 0) {
					prevTarget.health += prevTarget.armor;
					prevTarget.armor = 0;
				}
			}
			else {
				prevTarget.health -= dmgOutput;
			}
		}
		return true;
	}
	
	public void aoe(Character[] targets, Character[] allies, Character[] enemies) {
		Random rng = new Random();
		int critical = 0;
		
		for(int i = 0; i < targets.length; i++) {
			if(dmgOutput != 0) {
				critical = rng.nextInt(100);
				if (critical < critChance) {
					dmgOutput = (int)Math.round(dmgOutput * 1.5);
					System.out.println(username + " CRITICAL STRIKE!");
				}
			}
			
			if(hasWeakness > 0) {
				dmgOutput -= (int)Math.round(dmgOutput/4);
			}
			if(targets[i] != null && targets[i].reducedDmg != 0) {
				dmgOutput = (int)Math.round(dmgOutput*(1 - targets[i].reducedDmg));
			}
			
			if(dmgOutput > 0) {
				targets[i].dmgTakenPassive(this, enemies, allies);
				dmgDealtPassive(targets[i], allies, enemies);
			}
			
			if(targets[i] != null) {
				if (targets[i].armor > 0) {
					targets[i].armor -= dmgOutput;
					if (targets[i].armor < 0) {
						targets[i].health += targets[i].armor;
						targets[i].armor = 0;
					}
				}
				else {
					targets[i].health -= dmgOutput;
				}
			}
		}
		
	}
	
	//Casts character's third ability
	//Returns true if ability is successful
	//Returns false if ability isn't successful
	public boolean action3 (Character allies[], Character[] enemies) {
		prevTarget = null;
		Random rng = new Random();
		int critical = 0;
		invisible = 0;
		
		if(!ability3(allies, enemies)) {
			return false;
		}
		
		if(dmgOutput != 0) {
			critical = rng.nextInt(100);
			if (critical < critChance) {
				dmgOutput = (int)Math.round(dmgOutput * 1.5);
				System.out.println(username + " CRITICAL STRIKE!");
			}
		}
		
		if(hasWeakness > 0) {
			dmgOutput -= (int)Math.round(dmgOutput/4);
		}
		if(prevTarget != null && prevTarget.reducedDmg != 0) {
			dmgOutput = (int)Math.round(dmgOutput*(1 - prevTarget.reducedDmg));
		}
		ability3CCD = ability3BaseCD;
		resourceAmt -= ability3Cost;
		
		if(prevTarget != null && dmgOutput > 0) {
			prevTarget.dmgTakenPassive(this, enemies, allies);
			dmgDealtPassive(prevTarget, allies, enemies);
		}
		
		if(prevTarget != null) {
			if (prevTarget.armor > 0) {
				prevTarget.armor -= dmgOutput;
				if (prevTarget.armor < 0) {
					prevTarget.health += prevTarget.armor;
					prevTarget.armor = 0;
				}
			}
			else {
				prevTarget.health -= dmgOutput;
			}
		}
		return true;
	}
	
	//Their only purpose is to be replaced
	public boolean ability1 (Character allies[], Character[] enemies) {
		return false;
	}
	public boolean ability2 (Character allies[], Character[] enemies) {
		return false;
	}
	public boolean ability3 (Character allies[], Character[] enemies) {
		return false;
	}
	
	public void dmgDealtPassive(Character victim, Character allies[], Character[] enemies) {
		
	}
	public void dmgTakenPassive(Character attacker, Character allies[], Character[] enemies) {
		
	}
	public void startPassive(Character allies[], Character[] enemies) {
		
	}
	public void endPassive(Character allies[], Character[] enemies) {
		
	}
	public void deathPassive(Character victim, Character killer, Character allies[], Character[] enemies) {
		
	}
	public void autoAttackPassive(Character target) {
		
	}
	public void abilityPassive(Character[] allies, Character[] enemies) {
		
	}
	
	//Clears character's negative status effects
	public void clearStatus() {
		if (hasFrozen > 0) {
			hasFrozen = 0;
		}
		if (hasStun > 0) {
			hasStun = 0;
		}
		if (hasBleed > 0) {
			hasBleed = 0;
		}
		if (hasPoison > 0) {
			hasPoison = 0;
		}
		if (hasWeakness > 0) {
			hasWeakness = 0;
		}
		if (hasEnrage > 0) {
			hasEnrage = 0;
		}
		if (invisible > 0) {
			invisible = 0;
		}
		if (rapidFire > 0) {
			rapidFire = 0;
		}
		if (steady > 0) {
			steady = 0;
		}
		if (swordDuration > 0) {
			swordDuration = 0;
		}
	}
	
	//Sets character's cooldowns to 0
	public void clearCDs() {
		ability1CCD = 0;
		ability2CCD = 0;
		ability3CCD = 0;
	}
	
	//Sets character's resource amount to default
	public void clearResource() {
		if (resourceName.contentEquals("Energy")) {
			resourceAmt = resourceMax;
		}
		else if (resourceName.equals("Rage")) {
			resourceAmt = 0;
		}
		else if(resourceName.equals("Mana")) {
			if(!name.contentEquals("Mirror")) {
				resourceAmt = resourceMax;
			}
		}
		else if (resourceName.equals("Runes")) {
			resourceAmt = 0;
		}
		if(trueShot > 0) {
			trueShot = 0;
		}
		if(armor > maxArmor) {
			armor = maxArmor;
		}
	}
	
	//Casts the 3 methods above
	public void reset() {
		clearStatus();
		clearResource();
		clearCDs();
	}
	
	//Multiple Enemies - Player takes their turn and outputs interactive UI
	public void multiPlayerTurn(Character[] allies, Character[] enemies) {
		Character target;
		Scanner in = new Scanner(System.in);
		boolean loop = false;
		boolean isInt = false;
		int input = -1;
		
		do {
			if (frozen || stunned) {
				System.out.println("You are unable to attack or use abilities.");
			}
			System.out.println("1: Basic Attack 2: " + ability1Name + " 3: " + ability2Name + " 4: " + ability3Name + " 5: Inventory 6: Pass");
			System.out.println("Cost: 0 CCD: 0 | Cost: " + ability1Cost + " CCD: " + ability1CCD + " | Cost: " + ability2Cost + " CCD: " + ability2CCD + " | Cost: " + ability3Cost + " CCD: " + ability3CCD);
			if(in.hasNextInt()) {
				input = in.nextInt();
				in.nextLine();
				isInt = true;
			}
			else {
				in.nextLine();
				isInt = false;
			}

			if (!isInt || input <= 0 || input > 6) {
				loop = true;
				System.out.println("You didn't enter a valid input. Please try again.");
			}
			else {
				loop = false;
			}
		
			if (input == 1 && !stunned && !frozen) {
				target = targeting(enemies);
				if(target == null) {
					System.out.println("All targets are invalid.");
					loop = true;
				}
				else {
					autoAttack(target);
				}
			}
			else if (input == 2 && ability1CCD == 0 && resourceAmt >= ability1Cost && !stunned && !frozen && hasEnrage == 0) {
				if(!action1(allies, enemies)) {
					loop = true;
				}
			}
			else if (input == 3 && ability2CCD == 0 && resourceAmt >= ability2Cost && !stunned && !frozen && hasEnrage == 0) {
				if(!action2(allies, enemies)) {
					loop = true;
				}
			}
			else if (input == 4 && ability3CCD == 0 && resourceAmt >= ability3Cost && !stunned && !frozen && hasEnrage == 0) {
				if(!action3(allies, enemies)) {
					loop = true;
				}
			}
			else if(input == 5) {
				dmgOutput = 0;
				if(inv.display(enemies)) {
					invisible = 0;
				}
				else {
					loop = true;
				}
			}
			else if(input == 6) {
				dmgOutput = 0;
			}
			else {
				if (input < 5 && input > 0) {
					System.out.println("You can't use that right now.");
				}
				loop = true;
			}
		} while(loop);
	}
	
	public void multiNpcTurn(Character[] allies, Character[] enemies) {
		Random rng = new Random();
		Character target = null;
		int enemyChoice;
		int isInvis = 0;
		boolean[] enemyPass = new boolean[4];
		boolean enemyReRoll;
		
		for(int i = 0; i < enemyPass.length; i++) {
			enemyPass[i] = false;
		}
		enemyChoice = -1;
		enemyReRoll = true;
		if(enemies[0] == null) {
			return;
		}
		for(int i = 0; i < enemies.length; i++) {
			if(enemies[i].invisible > 0) {
				isInvis++;
			}
		}
		if(isInvis == enemies.length) {
			dmgOutput = 0;
			return;
		}
		
		while (enemyReRoll) {
			enemyChoice = rng.nextInt(4);
			if(enemyPass[0] && enemyPass[1] && enemyPass[2] && enemyPass[3]) {
				enemyChoice = 20;
				break;
			}
			if (frozen || stunned) {
				enemyChoice = 20;
				break;
			}
			if (enemyChoice == 0 && !stunned && !frozen) {
				target = npcTargeting(enemies);
				if(!autoAttack(target)) {
					enemyReRoll = true;
					enemyPass[0] = true;
				}
				else {
					break;
				}
			}
			else if (enemyChoice == 1 && ability1CCD == 0 && resourceAmt >= ability1Cost && !stunned && !frozen && hasEnrage == 0) {
				if(!action1(allies, enemies)) {
					enemyReRoll = true;
					enemyPass[1] = true;
				}
				else {
					break;
				}
			}
			else if (enemyChoice == 2 && ability2CCD == 0 && resourceAmt >= ability2Cost && !stunned && !frozen && hasEnrage == 0) {
				if(!action2(allies, enemies)) {
					enemyReRoll = true;
					enemyPass[2] = true;
				}
				else {
					break;
				}
			}
			else if (enemyChoice == 3 && ability3CCD == 0 && resourceAmt >= ability3Cost && !stunned && !frozen && hasEnrage == 0) {
				if(!action3(allies, enemies)) {
					enemyReRoll = true;
					enemyPass[3] = true;
				}
				else {
					break;
				}
			}
			else {
				enemyReRoll = true;
				//System.out.println("potato");
			}
		}
		if (enemyChoice == 20) {
			dmgOutput = 0;
		}
	}
	
	//Checks if character's health is above 0
	//returns true if they're alive
	//returns false if they're dead
	public boolean isAlive() {
		if(health > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public static Character[] multiIsAlive(Character[] arr) {
		Character[] newArr = null;
		int count = 0;
		int j = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].isAlive()) {
				count++;
			}
		}
		if(count == 0) {
			return null;
		}
		if(count != arr.length) {
			newArr = new Character[count];
			for(int i = 0; i < arr.length; i++) {
				if(arr[i].isAlive()) {
					newArr[j] = arr[i];
					j++;
				}
			}
			arr = newArr;
		}
		else {
			return arr;
		}
		return newArr;
	}

	public static Character[] allButOne(Character remove, Character[] allies, Character[] enemies) {
		Character[] newArr = new Character[allies.length+enemies.length-1];
		boolean notFound = true;
		int length = allies.length;
		if(allies.length == 1) {
			return enemies;
		}
		for(int i = 0; i < length; i++) {
			if(!allies[i].equals(remove) && notFound) {
				newArr[i] = allies[i];
			}
			else {
				length = allies.length - 1;
				notFound = false;
				newArr[i] = allies[i+1];
			}
		}
		for(int i = 0; i < enemies.length; i++) {
			newArr[allies.length-1+i] = enemies[i];
		}
		
		return newArr;
	}
	
	public void replace(Character x) {
		clearStatus();
		name = x.name;
		maxHealth = x.maxHealth;
		health = x.health;
		armor = x.armor;
		maxArmor = x.maxArmor;
		attackDmg = x.attackDmg;
		critChance = x.critChance;
		resourceName = x.resourceName;
		resourceMax = x.resourceMax;
		resourceAmt = x.resourceAmt;
		ability1Name = x.ability1Name;
		ability1Cost = x.ability1Cost;
		ability1BaseCD = x.ability1BaseCD;
		ability1CCD = x.ability1CCD;
		ability2Name = x.ability2Name;
		ability2Cost = x.ability2Cost;
		ability2BaseCD = x.ability2BaseCD;
		ability2CCD = x.ability2CCD;
		ability3Name = x.ability3Name;
		ability3Cost = x.ability3Cost;
		ability3BaseCD = x.ability3BaseCD;
		ability3CCD = x.ability3CCD;
		bleedDmg = x.bleedDmg;
		poisonDmg = x.poisonDmg;
	}
	
	public String getHp() {
		if(armor > 0) {
			hp = name + " HP: " + health + " |" + armor + "|";
		}
		else {
			hp = name + " HP: " + health;
		}
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPlayer() {
		return isPlayer;
	}
	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}
	public Combat getCurrentCombat() {
		return currentCombat;
	}
	public void setCurrentCombat(Combat currentCombat) {
		this.currentCombat = currentCombat;
	}
	public String getAbility1Name() {
		return ability1Name;
	}
	public void setAbility1Name(String ability1Name) {
		this.ability1Name = ability1Name;
	}
	public String getAbility2Name() {
		return ability2Name;
	}
	public void setAbility2Name(String ability2Name) {
		this.ability2Name = ability2Name;
	}
	public String getAbility3Name() {
		return ability3Name;
	}
	public void setAbility3Name(String ability3Name) {
		this.ability3Name = ability3Name;
	}
	public int getAbility1Cost() {
		return ability1Cost;
	}
	public void setAbility1Cost(int ability1Cost) {
		this.ability1Cost = ability1Cost;
	}
	public int getAbility2Cost() {
		return ability2Cost;
	}
	public void setAbility2Cost(int ability2Cost) {
		this.ability2Cost = ability2Cost;
	}
	public int getAbility3Cost() {
		return ability3Cost;
	}
	public void setAbility3Cost(int ability3Cost) {
		this.ability3Cost = ability3Cost;
	}
	public int getAbility1BaseCD() {
		return ability1BaseCD;
	}
	public void setAbility1BaseCD(int ability1BaseCD) {
		this.ability1BaseCD = ability1BaseCD;
	}
	public int getAbility2BaseCD() {
		return ability2BaseCD;
	}
	public void setAbility2BaseCD(int ability2BaseCD) {
		this.ability2BaseCD = ability2BaseCD;
	}
	public int getAbility3BaseCD() {
		return ability3BaseCD;
	}
	public void setAbility3BaseCD(int ability3BaseCD) {
		this.ability3BaseCD = ability3BaseCD;
	}
	public int getAbility1CCD() {
		return ability1CCD;
	}
	public void setAbility1CCD(int ability1ccd) {
		ability1CCD = ability1ccd;
	}
	public int getAbility2CCD() {
		return ability2CCD;
	}
	public void setAbility2CCD(int ability2ccd) {
		ability2CCD = ability2ccd;
	}
	public int getAbility3CCD() {
		return ability3CCD;
	}
	public void setAbility3CCD(int ability3ccd) {
		ability3CCD = ability3ccd;
	}
	public Character getPrevTarget() {
		return prevTarget;
	}
	public void setPrevTarget(Character prevTarget) {
		this.prevTarget = prevTarget;
	}
	public Inventory getInv() {
		return inv;
	}
	public void setInv(Inventory inv) {
		this.inv = inv;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getResourceAmt() {
		return resourceAmt;
	}
	public void setResourceAmt(int resourceAmt) {
		if(resourceAmt > resourceMax) {
			this.resourceAmt = resourceMax;
		}else {
			this.resourceAmt = resourceAmt;
		}
		
	}
	public int getResourceMax() {
		return resourceMax;
	}
	public void setResourceMax(int resourceMax) {
		this.resourceMax = resourceMax;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMaxArmor() {
		return maxArmor;
	}
	public void setMaxArmor(int maxArmor) {
		this.maxArmor = maxArmor;
	}
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public int getAttackDmg() {
		return attackDmg;
	}
	public void setAttackDmg(int attackDmg) {
		this.attackDmg = attackDmg;
	}
	public int getDmgOutput() {
		return dmgOutput;
	}
	public void setDmgOutput(int dmgOutput) {
		this.dmgOutput = dmgOutput;
	}
	public int getCritChance() {
		return critChance;
	}
	public void setCritChance(int critChance) {
		this.critChance = critChance;
	}
	public int getTrueShot() {
		return trueShot;
	}
	public void setTrueShot(int trueShot) {
		this.trueShot = trueShot;
	}
	public int getSteady() {
		return steady;
	}
	public void setSteady(int steady) {
		this.steady = steady;
	}
	public int getRapidFire() {
		return rapidFire;
	}
	public void setRapidFire(int rapidFire) {
		this.rapidFire = rapidFire;
	}
	public int getSwordDuration() {
		return swordDuration;
	}
	public void setSwordDuration(int swordDuration) {
		this.swordDuration = swordDuration;
	}
	public int getSwordDmg() {
		return swordDmg;
	}
	public void setSwordDmg(int swordDmg) {
		this.swordDmg = swordDmg;
	}
	public double getReducedDmg() {
		return reducedDmg;
	}
	public void setReducedDmg(double reducedDmg) {
		this.reducedDmg = reducedDmg;
	}
	public int getHasArmorGain() {
		return hasArmorGain;
	}
	public void setHasArmorGain(int hasArmorGain) {
		this.hasArmorGain = hasArmorGain;
	}
	public boolean isStunned() {
		return stunned;
	}
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	public boolean isFrozen() {
		return frozen;
	}
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}
	public int getHasFrozen() {
		return hasFrozen;
	}
	public void setHasFrozen(int hasFrozen) {
		this.hasFrozen = hasFrozen;
	}
	public int getHasStun() {
		return hasStun;
	}
	public void setHasStun(int hasStun) {
		this.hasStun = hasStun;
	}
	public int getHasBleed() {
		return hasBleed;
	}
	public void setHasBleed(int hasBleed) {
		this.hasBleed = hasBleed;
	}
	public int getHasPoison() {
		return hasPoison;
	}
	public void setHasPoison(int hasPoison) {
		this.hasPoison = hasPoison;
	}
	public int getInvisible() {
		return invisible;
	}
	public void setInvisible(int invisible) {
		this.invisible = invisible;
	}
	public int getHasWeakness() {
		return hasWeakness;
	}
	public void setHasWeakness(int hasWeakness) {
		this.hasWeakness = hasWeakness;
	}
	public int getHasEnrage() {
		return hasEnrage;
	}
	public void setHasEnrage(int hasEnrage) {
		this.hasEnrage = hasEnrage;
	}
	public int getPoisonDmg() {
		return poisonDmg;
	}
	public void setPoisonDmg(int poisonDmg) {
		this.poisonDmg = poisonDmg;
	}
	public int getBleedDmg() {
		return bleedDmg;
	}
	public void setBleedDmg(int bleedDmg) {
		this.bleedDmg = bleedDmg;
	}
	public int getPoison() {
		return poison;
	}
	public void setPoison(int poison) {
		this.poison = poison;
	}
	public int getBleed() {
		return bleed;
	}
	public void setBleed(int bleed) {
		this.bleed = bleed;
	}
	public boolean getIsAlive() {
		return this.isAlive;
	}
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public Ability getAbility(int index) {
		return abilities[index];
	}
	public void setAbility(int index, Ability newAbility) {
		abilities[index] = newAbility;
	}
	public void applyPoison(int duration, int dmg) {
		poison = dmg;
		hasPoison = duration;
	}
	public void applyBleed(int duration, int dmg) {
		bleed = dmg;
		hasBleed = duration;
	}
	public void setHealthAndMax(int health) {
		this.health = health;
		this.maxHealth = health;
	}
	public void changeHealth(int amount) {
		health += amount;
		if(health > maxHealth) {
			health = maxHealth;
		}
		if(health <= 0) {
			isAlive = false;
		}
	}
	public void changeArmor(int amount) {
		armor += amount;
		if(armor < 0) {
			changeHealth(armor);
			armor = 0;
		}
		if(armor > maxArmor) {
			armor = maxArmor;
		}
	}
	public void changeResource(int amount) {
		resourceAmt += amount;
		if(resourceAmt > resourceMax) {
			resourceAmt = resourceMax;
		}
		if(resourceAmt < 0) {
			resourceAmt = 0;
		}
	}
	public boolean criticalStrike() {
		Random rng = new Random();
		int critical = 0;
		critical = rng.nextInt(100);
		if (critical < critChance) {
			System.out.println(username + " CRITICAL STRIKE!");
			return true;
		}
		return false;
	}

}





