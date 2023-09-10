
public class Treant extends Character {
	public Treant() {
		setName("Treant");
		setMaxHealth(30);
		setHealth(30);
		setArmor(0);
		setMaxArmor(40);
		setAttackDmg(5);
		setCritChance(3);
		setResourceName("Mana");
		setResourceAmt(100);
		setResourceMax(100);
		setAbility1Name("Viscious Vines");
		setAbility1BaseCD(4);
		setAbility1Cost(20);
		setAbility2Name("Healing Touch");
		setAbility2BaseCD(4);
		setAbility2Cost(40);
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
		setDmgOutput(8);
		target.setHasWeakness(3);
		System.out.println("Used VISCIOUS VINES! You are weakened for 2 turns.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		System.out.println(getUsername() + ":");
		setDmgOutput(0);
		setHealth(getHealth() + 12);
		System.out.println("Used HEALING TOUCH! Restored 12 health to itself.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		return false;
	}
}