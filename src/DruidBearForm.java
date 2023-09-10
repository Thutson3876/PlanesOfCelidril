
public class DruidBearForm extends Character{
	public DruidBearForm() {
		setName("Druid Bear");
		setMaxHealth(100);
		setHealth(100);
		setArmor(50);
		setMaxArmor(100);
		setAttackDmg(7);
		setCritChance(5);
		setResourceName("Rage");
		setResourceAmt(10);
		setResourceMax(100);
		setAbility1Name("Maul");
		setAbility1BaseCD(2);
		setAbility1Cost(15);
		setAbility2Name("Fearsome Roar");
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
		target = targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(8);
		target.setHasBleed(2);
		target.setBleed(getBleedDmg());
		
		System.out.println("You slash at your target visciously causing it to bleed.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		for(int i = 0; i < enemies.length; i++) {
			enemies[i].setHasWeakness(5);
			enemies[i].setHasStun(0);
		}
		
		System.out.println(getUsername() + ":");
		System.out.println("You release a ground-shaking roar that sends chills down the spines of your enemies.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		return false;
	}
	
	public void dmgTakenPassive(Character attacker, Character allies, Character enemies) {
		setResourceAmt(getResourceAmt() + attacker.getDmgOutput());
	}
}
