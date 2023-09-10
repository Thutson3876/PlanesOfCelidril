import java.util.Arrays;

public class Combat {
	Character[] allies;
	Character[] enemies;
	
	public Combat(Character[] good, Character[] bad) {
		allies = good;
		enemies = bad;
		for(int i = 0; i < allies.length; i++) {
			allies[i].setCurrentCombat(this);
		}
		for(int i = 0; i < enemies.length; i++) {
			enemies[i].setCurrentCombat(this);
		}
	}
		
	//2 player - Initiates combat against another player
	public void combat2() {
			
			while (allies[0].getIsAlive() && enemies[0].getIsAlive()) {
				multiStartRound();
				
				if(!allies[0].getIsAlive() || !enemies[0].getIsAlive()) {
					break;
				}
				
				allies[0].multiPlayerTurn(allies, enemies);
				if(allies.length > 1) {
					for(int i = 1; i < allies.length; i++) {
						allies[i].multiNpcTurn(allies, enemies);
					}
				}
				enemies[0].multiPlayerTurn(enemies, allies);
				if(enemies.length > 1) {
					for(int i = 1; i < enemies.length; i++) {
						enemies[i].multiNpcTurn(enemies, allies);
					}
				}
				
				if(!allies[0].getIsAlive() || !enemies[0].getIsAlive()) {
					break;
				}
				
				multiEndRound();
			}
			
			if(!allies[0].getIsAlive() && !enemies[0].getIsAlive()) {
				System.out.println("Tie!");
			}
			else if (!allies[0].getIsAlive()) {
				System.out.println(enemies[0].getUsername() + " wins!");
			}
			else if (!enemies[0].getIsAlive()) {
				System.out.println(allies[0].getUsername() + " wins!");
			}
		}
		
	//Multiple enemies - DOTs tick and displays HUD
	private void multiStartRound() {
			String format = "%1$-22s ";
			String line = "----------------------------------------------------------------------------------------------------------------";
			boolean isArcher = false;
			boolean sD = false;
			boolean F = false;
			boolean S = false;
			boolean W = false;
			boolean E = false;
			boolean I = false;
			boolean P = false;
			boolean B = false;
			String allyHp = "";
			String allyR = "";
			String allyTs = "";
			String allySd = "";
			String allyF = "";
			String allyS = "";
			String allyW = "";
			String allyE = "";
			String allyI = "";
			String allyP = "";
			String allyB = "";
			
			String enemyHp = "";
			String enemyR = "";
			String enemyTs = "";
			String enemySd = "";
			String enemyF = "";
			String enemyS = "";
			String enemyW = "";
			String enemyE = "";
			String enemyI = "";
			String enemyP = "";
			String enemyB = "";
			int enemyCount = 0;
			
			for(int i = 0; i < allies.length; i++) {
				allies[i].startPassive(allies, enemies);
				
				if(allies[i].getReducedDmg() != 0) {
					allies[i].setReducedDmg(0);
				}
				if (allies[i].getHasBleed() > 0) {
					allies[i].setHasBleed(allies[i].getHasBleed()-1);
					allies[i].changeHealth(-allies[i].getBleed());
				}
				if (allies[i].getHasPoison() > 0) {
					allies[i].setHasPoison(allies[i].getHasPoison()-1);
					allies[i].changeHealth(-allies[i].getPoison());
				}
				
				/*if (allies[i].trueShot > 3) {
					allies[i].trueShot = 3;
				}*/
				
				if(allies[i].isAlive()) {
					allyHp += String.format(format, allies[i].getHp());
					allyR += String.format(format, allies[i].getResourceName() + ": " + allies[i].getResourceAmt());
					if(allies[i].getName().contentEquals("Archer")) {
						allyTs += String.format(format, "True Shot: " + allies[i].getTrueShot());
						isArcher = true;
					}
					else {
						allyTs += String.format(format, " ");
					}
					if(allies[i].getSwordDuration() > 0) {
						allySd += String.format(format, "Sword Duration: " + allies[i].getSwordDuration());
						sD = true;
					}
					else {
						allySd += String.format(format, " ");
					}
					if(allies[i].isFrozen()) {
						allyF += String.format(format, "Frozen");
						F = true;
					}
					else {
						allyF += String.format(format, " ");
					}
					if(allies[i].isStunned()) {
						allyS += String.format(format, "Stunned");
						S = true;
					}
					else {
						allyS += String.format(format, " ");
					}
					if(allies[i].getHasWeakness() > 0) {
						allyW += String.format(format, "Weakened");
						W = true;
					}
					else {
						allyW += String.format(format, " ");
					}
					if(allies[i].getHasEnrage() > 0) {
						allyE += String.format(format, "Enraged");
						E = true;
					}
					else {
						allyE += String.format(format, " ");
					}
					if(allies[i].getInvisible() > 0) {
						allyI += String.format(format, "Invisible");
						I = true;
					}
					else {
						allyI += String.format(format, " ");
					}
					if(allies[i].getHasPoison() > 0) {
						allyP += String.format(format, "Poisoned: " + allies[i].getPoison());
						P = true;
					}
					else {
						allyP += String.format(format, " ");
					}
					if(allies[i].getHasBleed() > 0) {
						allyB += String.format(format, "Bleeding: " + allies[i].getBleed());
						B = true;
					}
					else {
						allyB += String.format(format, " ");
					}
				}
			}
			
			for(int i = 0; i < enemies.length; i++) {
				enemies[i].startPassive(enemies, allies);
				
				if(enemies[i].getReducedDmg() != 0) {
					enemies[i].setReducedDmg(0);
				}
				if (enemies[i].getHasBleed() > 0) {
					enemies[i].setHasBleed(enemies[i].getHasBleed() - 1);
					enemies[i].changeHealth(-enemies[i].getBleed());
				}
				if (enemies[i].getHasPoison() > 0) {
					enemies[i].setHasPoison(enemies[i].getPoison() - 1);
					enemies[i].changeHealth(-enemies[i].getPoison());
				}

				/*if (enemies[i].trueShot > 3) {
					enemies[i].trueShot = 3;
				}*/
				
				if(enemies[i].isAlive()) {
					enemyCount++;
					enemyHp += String.format(format, enemies[i].getHp());
					enemyR += String.format(format, enemies[i].getResourceName() + ": " + enemies[i].getResourceAmt());
					if(enemies[i].getName().contentEquals("Archer")) {
						enemyTs += String.format(format, "True Shot: " + enemies[i].getTrueShot());
						isArcher = true;
					}
					else {
						enemyTs += String.format(format, " ");
					}
					if(enemies[i].getSwordDuration() > 0) {
						enemySd += String.format(format, "Sword Duration: " + enemies[i].getSwordDuration());
						sD = true;
					}
					else {
						enemySd += String.format(format, " ");
					}
					if(enemies[i].isFrozen()) {
						enemyF += String.format(format, "Frozen");
						F = true;
					}
					else {
						enemyF += String.format(format, " ");
					}
					if(enemies[i].isStunned()) {
						enemyS += String.format(format, "Stunned");
						S = true;
					}
					else {
						enemyS += String.format(format, " ");
					}
					if(enemies[i].getHasWeakness() > 0) {
						enemyW += String.format(format, "Weakened");
						W = true;
					}
					else {
						enemyW += String.format(format, " ");
					}
					if(enemies[i].getHasEnrage() > 0) {
						enemyE += String.format(format, "Enraged");
						E = true;
					}
					else {
						enemyE += String.format(format, " ");
					}
					if(enemies[i].getInvisible() > 0) {
						enemyI += String.format(format, "Invisible");
						I = true;
					}
					else {
						enemyI += String.format(format, " ");
					}
					if(enemies[i].getHasPoison() > 0) {
						enemyP += String.format(format, "Poisoned: " + enemies[i].getPoison());
						P = true;
					}
					else {
						enemyP += String.format(format, " ");
					}
					if(enemies[i].getHasBleed() > 0) {
						enemyB += String.format(format, "Bleeding: " + enemies[i].getBleed());
						B = true;
					}
					else {
						enemyB += String.format(format, " ");
					}
				}
			}
			
			if(enemyCount == 0) {
				return;
			}
			if(!allies[0].getIsAlive()) {
				return;
			}
			
			System.out.println(line);
			System.out.println(allyHp + " Enemies: " + enemyHp);
			System.out.println(allyR + " Enemies: " + enemyR);
			if (isArcher) {
				System.out.println(allyTs + "          " + enemyTs);
			}
			if (sD) {
				System.out.println(allySd + "          " + enemySd);
			}
			System.out.println(line);
			if (F) {
				System.out.println(allyF + "          " +enemyF);
			}
			if (S) {
				System.out.println(allyS + "          " +enemyS);
			}
			if (W) {
				System.out.println(allyW + "          " +enemyW);
			}
			if (E) {
				System.out.println(allyE + "          " +enemyE);
			}
			if (I) {
				System.out.println(allyI + "          " +enemyI);
			}
			if (P) {
				System.out.println(allyP + "          " +enemyP);
			}
			if (B) {
				System.out.println(allyB + "          " +enemyB);
			}
			//System.out.println(line);
		}
		
	//Multiple enemies - Cooldowns, statuses tick down, and passives trigger
	private void multiEndRound() {
			int etotal = 0;
			int atotal = 0;
			for(int i = 0; i < enemies.length; i++) {
				etotal += enemies[i].getDmgOutput();
			}
			if(allies.length > 1) {
				for(int i = 1; i < allies.length; i++) {
					atotal += allies[i].getDmgOutput();
				}
			}
			
			for(int i = 0; i < allies.length; i++) {
				if (allies[i].getAbility1CCD() > 0) {
					allies[i].setAbility1CCD(allies[i].getAbility1CCD()-1);
				}
				if (allies[i].getAbility2CCD() > 0) {
					allies[i].setAbility2CCD(allies[i].getAbility2CCD()-1);
				}
				if (allies[i].getAbility3CCD() > 0) {
					allies[i].setAbility3CCD(allies[i].getAbility3CCD()-1);
				}
				if(allies[i].isFrozen()) {
					allies[i].setFrozen(false);
				}
				if(allies[i].isStunned()) {
					allies[i].setStunned(false);
				}
				if (allies[i].getHasFrozen() > 0) {
					allies[i].setHasFrozen(allies[i].getHasFrozen() - 1);
					allies[i].setFrozen(true);
				}
				if (allies[i].getHasStun() > 0) {
					allies[i].setHasStun(allies[i].getHasFrozen() - 1);
					allies[i].setStunned(true);
				}
				if (allies[i].getHasWeakness() > 0) {
					allies[i].setHasWeakness(allies[i].getHasWeakness() - 1);
				}
				if (allies[i].getHasEnrage() > 0) {
					allies[i].setHasEnrage(allies[i].getHasEnrage() - 1);
				}
				if (allies[i].getRapidFire() > 0) {
					allies[i].setRapidFire(allies[i].getRapidFire() - 1);
				}
				if (allies[i].getSteady() > 0) {
					allies[i].setSteady(allies[i].getSteady() - 1);
				}
				if (allies[i].getSwordDuration() > 0) {
					allies[i].setSwordDuration(allies[i].getSwordDuration() - 1);
				}
				if (allies[i].getInvisible() > 0) {
					allies[i].setInvisible(allies[i].getInvisible() - 1);
				}
				if (allies[i].getResourceName().contentEquals("Energy")) {
					allies[i].changeResource(15);
				}
				if(allies[i].getResourceName().contentEquals("Mana")) {
					allies[i].changeResource(5);
				}

				allies[i].endPassive(allies, enemies);
			}
			
			for(int i = 0; i < enemies.length; i++) {
				if (enemies[i].getAbility1CCD() > 0) {
					enemies[i].setAbility1CCD(enemies[i].getAbility1CCD()-1);
				}
				if (enemies[i].getAbility2CCD() > 0) {
					enemies[i].setAbility2CCD(enemies[i].getAbility2CCD()-1);
				}
				if (enemies[i].getAbility3CCD() > 0) {
					enemies[i].setAbility3CCD(enemies[i].getAbility3CCD()-1);
				}
				if(enemies[i].isFrozen()) {
					enemies[i].setFrozen(false);
				}
				if(enemies[i].isStunned()) {
					enemies[i].setStunned(false);
				}
				if (enemies[i].getHasFrozen() > 0) {
					enemies[i].setHasFrozen(enemies[i].getHasFrozen() - 1);
					enemies[i].setFrozen(true);
				}
				if (enemies[i].getHasStun() > 0) {
					enemies[i].setHasStun(enemies[i].getHasFrozen() - 1);
					enemies[i].setStunned(true);
				}
				if (enemies[i].getHasWeakness() > 0) {
					enemies[i].setHasWeakness(enemies[i].getHasWeakness() - 1);
				}
				if (enemies[i].getHasEnrage() > 0) {
					enemies[i].setHasEnrage(enemies[i].getHasEnrage() - 1);
				}
				if (enemies[i].getRapidFire() > 0) {
					enemies[i].setRapidFire(enemies[i].getRapidFire() - 1);
				}
				if (enemies[i].getSteady() > 0) {
					enemies[i].setSteady(enemies[i].getSteady() - 1);
				}
				if (enemies[i].getSwordDuration() > 0) {
					enemies[i].setSwordDuration(enemies[i].getSwordDuration() - 1);
				}
				if (enemies[i].getInvisible() > 0) {
					enemies[i].setInvisible(enemies[i].getInvisible() - 1);
				}
				if (enemies[i].getResourceName().contentEquals("Energy")) {
					enemies[i].changeResource(15);
				}
				if(enemies[i].getResourceName().contentEquals("Mana")) {
					enemies[i].changeResource(5);
				}

				enemies[i].endPassive(enemies, allies);
			}
			
			if(allies.length > 1) {
				System.out.println("You dealt " + allies[0].getDmgOutput() + " damage, your allies dealt " + atotal + ", and enemies dealt " + etotal + ".");
			}
			else {
				System.out.println("You dealt " + allies[0].getDmgOutput() + " damage to and took " + etotal + " damage.");
			}
		}
		
	//Multiple enemies - Initiates combat against a single npc enemy
	public void multicombat() {
			boolean run = false;
			
			allies[0].reset();
			
			while (allies[0].getIsAlive() && enemies != null && !run) {
				
				multiStartRound();
				
				//Checks how many enemies are alive and removes dead enemies from the array
				enemies = Character.multiIsAlive(enemies);
				allies = Character.multiIsAlive(allies);
				if(enemies == null) {
					break;
				}
				
				allies[0].multiPlayerTurn(allies, enemies);
				for(int i = 1; i < allies.length; i++) {
					enemies = Character.multiIsAlive(enemies);
					if(enemies == null) {
						break;
					}
					allies[i].multiNpcTurn(allies, enemies);
				}
				
				enemies = Character.multiIsAlive(enemies);
				if(enemies == null) {
					break;
				}
				
				for(int i = 0; i < enemies.length; i++) {
					enemies[i].multiNpcTurn(enemies, allies);
					allies = Character.multiIsAlive(allies);
				}
				
				if(allies == null) {
					break;
				}
				
				multiEndRound();
			}
			
			if (allies[0] == null || !allies[0].getIsAlive()) {
				System.out.println("You lose!");
			}
			else if (enemies == null) {
				System.out.println("You win!");
			}
			
		}
		
	public void summon(Character summoner, Character[] arr) {
		int side = 0;
		Character[] newArr;
		side = checkSide(summoner);
		if(side == 1) {
			newArr = Arrays.copyOf(allies, allies.length+arr.length);
			for(int i = 0; i < arr.length; i++) {
				newArr[i+allies.length] = arr[i];
				newArr[i+allies.length].setCurrentCombat(this);
			}
			allies = newArr;
		}
		else {
			newArr = Arrays.copyOf(enemies, enemies.length+arr.length);
			for(int i = 0; i < arr.length; i++) {
				newArr[i+enemies.length] = arr[i];
				newArr[i+enemies.length].setCurrentCombat(this);
			}
			enemies = newArr;
		}
	}
	
	public int checkSide(Character x) {
		for(int i = 0; i < allies.length; i++) {
			if(x.equals(allies[i])) {
				//System.out.println("Good");
				return 1;
			}
		}
		for(int i = 0; i < enemies.length; i++) {
			if(x.equals(enemies[i])) {
				//System.out.println("Bad");
				return 2;
			}
		}
		return 0;
	}

}
