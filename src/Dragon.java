
public class Dragon extends Character {
	public Dragon() {
		setName("Dragon");
		setMaxHealth(200);
		setHealth(200);
		setArmor(0);
		setMaxArmor(50);
		setAttackDmg(5);
		setCritChance(3);
		setResourceName("Rage");
		setResourceAmt(10);
		setResourceMax(100);
		setAbility1Name("Fire Breath");
		setAbility1BaseCD(4);
		setAbility1Cost(35);
		setAbility2Name("Ferocious Bite");
		setAbility2BaseCD(4);
		setAbility2Cost(35);
		setAbility3Name("Flying High");
		setAbility3BaseCD(4);
		setAbility3Cost(35);
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
		setDmgOutput(20);
		target.setHasWeakness(3);
		
		System.out.println("Used FIRE BREATH! You are weakened for 2 turns.");
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
		setDmgOutput(12);
		target.setHasBleed(3);
		target.setBleed(getBleedDmg());
		
		System.out.println("Used FEROCIOUS BITE! You are bleeding for 3 turns.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		setInvisible(2);
		System.out.println(getUsername() + ":");
		System.out.println("Used FLYING HIGH! You can't damage it for a turn.");
		return true;
	}
}