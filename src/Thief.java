
public class Thief extends Character {
	public Thief() {
		setName("Thief");
		setMaxHealth(30);
		setHealth(30);
		setArmor(5);
		setMaxArmor(30);
		setAttackDmg(6);
		setCritChance(10);
		setResourceName("Energy");
		setResourceAmt(100);
		setResourceMax(100);
		setAbility1Name("Throw Sand");
		setAbility1BaseCD(5);
		setAbility1Cost(70);
		setAbility2Name("Apply Poison");
		setAbility2BaseCD(4);
		setAbility2Cost(50);
		setAbility3Name("Sinister Strike");
		setAbility3BaseCD(5);
		setAbility3Cost(60);
		setPoisonDmg(3);
		setBleedDmg(4);
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
		
		System.out.println("Used THROW SAND! You are stunned for a turn.");
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
		System.out.println(getUsername() + ":");
		setDmgOutput(3);
		target.setHasPoison(2);
		setPoisonDmg(5);
		target.setPoison(getPoisonDmg());
		target.setHasWeakness(2);
		
		System.out.println("Used APPLY POISON! You are poisoned and weakened for 2 turns.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(15);
		System.out.println("Used SINISTER STRIKE!");
		return true;
	}
}