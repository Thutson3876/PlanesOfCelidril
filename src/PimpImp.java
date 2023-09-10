
public class PimpImp extends Character {
	private int impC = 0;
	public PimpImp() {
		setName("Pimp Imp");
		setMaxHealth(25);
		setHealth(25);
		setArmor(0);
		setMaxArmor(20);
		setAttackDmg(6);
		setCritChance(7);
		setResourceName("Rage");
		setResourceAmt(10);
		setResourceMax(100);
		setAbility1Name("Imp Slap");
		setAbility1BaseCD(4);
		setAbility1Cost(10);
		setAbility2Name("Imp Cane");
		setAbility2BaseCD(2);
		setAbility2Cost(6);
		setAbility3Name("");
		setAbility3BaseCD(0);
		setAbility3Cost(0);
		setUsername(getName());
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(3);
		target.setHasStun(1);
		
		System.out.println("Used IMP SLAP! It slaps you like it owns you and you are stunned by its audacity.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		setDmgOutput(3+3*impC);
		if(impC == 3) {
			setResourceAmt(getResourceAmt() + Math.round(getAbility2Cost()/2));
		}
		
		System.out.println(getUsername() + ":");
		if(impC == 3) {
			System.out.println("Used IMP CANE! It smacks you with its sleek cane and puts on an even sleeker bowler hat.");
		}
		else {
			System.out.println("Used IMP CANE! It smacks you with its sleek cane.");
		}
		
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		return false;
	}
	
	public void endPassive(Character[] allies, Character[] enemies) {
		impC++;
		if(impC > 3) {
			impC = 3;
		}
	}

}