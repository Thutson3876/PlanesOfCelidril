
public class Warrior extends Character {
	public Warrior() {
		setName("Warrior");
		setMaxHealth(130);
		setHealth(130);
		setArmor(50);
		setMaxArmor(100);
		setAttackDmg(7);
		setCritChance(5);
		setResourceName("Rage");
		setResourceAmt(0);
		setResourceMax(100);
		setAbility1Name("Fury");
		setAbility1BaseCD(3);
		setAbility1Cost(0);
		setAbility2Name("Shield Bash");
		setAbility2BaseCD(3);
		setAbility2Cost(12);
		setAbility3Name("Armor Up");
		setAbility3BaseCD(0);
		setAbility3Cost(1);
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(10);
		setResourceAmt(getResourceAmt() + 10);
		setHasEnrage(3);
		
		System.out.println("You become enraged and unleash your fury upon your foe to generate extra rage.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(6);
		target.setHasStun(1);
		
		System.out.println("You slam your shield into your enemy, briefly stunning them.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		setArmor(getArmor() + getResourceAmt());
		System.out.println(getUsername() + ":");
		System.out.println("You convert your rage into shielding and gain " + getResourceAmt() + " armor.");
		setResourceAmt(1);
		return true;
	}
}