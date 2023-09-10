
public class Conjurer extends Character {
	public Conjurer() {
		setName("Conjurer");
		setMaxHealth(100);
		setHealth(100);
		setArmor(10);
		setMaxArmor(60);
		setAttackDmg(3);
		setCritChance(5);
		setResourceName("Runes");
		setResourceAmt(0);
		setResourceMax(3);
		setAbility1Name("Meditate");
		setAbility1BaseCD(2);
		setAbility1Cost(0);
		setAbility2Name("Conjur Sword");
		setAbility2BaseCD(3);
		setAbility2Cost(2);
		setAbility3Name("Arcanic Armor");
		setAbility3BaseCD(3);
		setAbility3Cost(1);
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		System.out.println(getUsername() + ":");
		setDmgOutput(0);
		setReducedDmg(0.25 + 0.1*getResourceAmt());
		setResourceAmt(getResourceAmt() + 2);
		
		System.out.println("Focus your energy to create 2 new magical runes.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		setSwordDmg((int)Math.round(14 + 12*0.33*getResourceAmt()));
		setDmgOutput(0);
		setSwordDuration(4);
		
		System.out.println(getUsername() + ":");
		System.out.println("You spend 2 runes to forge an arcanic weapon that boosts your attack damage.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		setArmor(getArmor() + (int)Math.round(10 + 12*getResourceAmt()));
		setResourceAmt(1);
		System.out.println(getUsername() + ":");
		System.out.println("You spend all of your runes to shield yourself in arcanic armor.");
		return true;
	}
}