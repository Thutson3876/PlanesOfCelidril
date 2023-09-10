
public class DruidFeralForm extends Character{
	public DruidFeralForm() {
		setName("Druid Feral");
		setMaxHealth(70);
		setHealth(70);
		setArmor(0);
		setMaxArmor(50);
		setAttackDmg(12);
		setCritChance(15);
		setResourceName("Energy");
		setResourceAmt(100);
		setResourceMax(100);
		setAbility1Name("Piercing Bite");
		setAbility1BaseCD(3);
		setAbility1CCD(0);
		setAbility1Cost(30);
		setAbility2Name("Amongst the Bushes");
		setAbility2BaseCD(4);
		setAbility2CCD(0);
		setAbility2Cost(40);
		setAbility3Name("Revert");
		setAbility3BaseCD(0);
		setAbility3CCD(0);
		setAbility3Cost(0);
		setBleedDmg(8);
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
		setDmgOutput(12);
		target.setHasBleed(2);
		target.setBleed(getBleedDmg());
		
		System.out.println("You leap towards your target and bite into its flesh causing it to bleed profusely.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		setInvisible(3);
		
		System.out.println(getUsername() + ":");
		System.out.println("You hide amongst the shadows and prepare to strike.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		return false;
	}
}
