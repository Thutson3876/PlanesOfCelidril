
public class DruidTreantForm extends Character{
	public DruidTreantForm() {
		setName("Druid Treant");
		setMaxHealth(80);
		setHealth(80);
		setArmor(0);
		setMaxArmor(50);
		setAttackDmg(6);
		setCritChance(5);
		setResourceName("Mana");
		setResourceAmt(100);
		setResourceMax(100);
		setAbility1Name("Healing Touch");
		setAbility1BaseCD(0);
		setAbility1Cost(20);
		setAbility2Name("Barkskin");
		setAbility2BaseCD(4);
		setAbility2Cost(50);
		setAbility3Name("Revert");
		setAbility3BaseCD(0);
		setAbility3Cost(0);
		setAbility1CCD(0);
		setAbility2CCD(0);
		setAbility3CCD(0);
		setBleedDmg(8);
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(allies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(0);
		target.setHealth(target.getHealth() + 25);
		
		System.out.println("You use the power of nature to mend your target's wounds.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		for(int i = 0; i < allies.length; i++) {
			allies[i].setArmor(getArmor() + 15);
		}
		
		System.out.println(getUsername() + ":");
		System.out.println("You strengthen your allies' defenses by shielding them in thick bark.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		return false;
	}
}
