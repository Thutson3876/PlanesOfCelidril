import java.util.Random;

public class Boar extends Character {
	public Boar() {
		setName("Boar");
		setMaxHealth(20);
		setHealth(20);
		setArmor(0);
		setMaxArmor(20);
		setAttackDmg(3);
		setCritChance(7);
		setResourceName("Rage");
		setResourceAmt(5);
		setResourceMax(100);
		setAbility1Name("Rip n' Tear");
		setAbility1BaseCD(3);
		setAbility1Cost(6);
		setAbility2Name("");
		setAbility2BaseCD(0);
		setAbility2Cost(0);
		setAbility3Name("");
		setAbility3BaseCD(0);
		setAbility3Cost(0);
		setBleedDmg(4);
		setUsername(getName());
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		Random rng = new Random();
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(6);
		target.setHasBleed(3);
		setBleedDmg(rng.nextInt(2) + 2);
		target.setBleed(getBleedDmg());
		System.out.println("Used RIP N' TEAR! You are bleeding for 2 turns.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		return false;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		return false;
	}

}