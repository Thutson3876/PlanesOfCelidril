
public class ThickImp extends Character {
	public ThickImp() {
		setName("Thick Imp");
		setMaxHealth(50);
		setHealth(50);
		setArmor(10);
		setMaxArmor(60);
		setAttackDmg(5);
		setCritChance(7);
		setResourceName("Runes");
		setResourceAmt(0);
		setResourceMax(3);
		setAbility1Name("Belly Slam");
		setAbility1BaseCD(2);
		setAbility1Cost(1);
		setAbility2Name("Feeding Time");
		setAbility2BaseCD(0);
		setAbility2Cost(0);
		setAbility3Name("Arcanic Consumption");
		setAbility3BaseCD(3);
		setAbility3Cost(1);
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
		if(getResourceAmt() > 0) {
			setDmgOutput(Math.round(getHealth()/(3/getResourceAmt())));
		}
		else {
			setDmgOutput(Math.round(getHealth()/3));
		}
		setResourceAmt(1);
		
		System.out.println("Used BELLY SLAM! It slams into you dealing heavy damage.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		
		setDmgOutput(0);
		setHealth(getHealth() - 5);
		setResourceAmt(getResourceAmt() + 1);
		System.out.println(getUsername() + ":");
		System.out.println("Used FEEDING TIME! It takes a meaty bite of its own flesh in order to fuel its dark magic.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {

		setDmgOutput(0);
		setHealth(getHealth() + 6*getResourceAmt());
		System.out.println(getUsername() + ":");
		System.out.println("Used ARCANIC CONSUMPTION! It devours its runes in order to sustain itself!");
		return true;
	}
	
	public void endPassive(Character[] allies, Character[] enemies) {
		setHealth(getHealth() - 4);
	}

}