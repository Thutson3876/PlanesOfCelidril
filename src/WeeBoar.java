
public class WeeBoar extends Character {
	private int stampede = 0;
	public WeeBoar() {
		setName("Wee Boar");
		setMaxHealth(15);
		setHealth(15);
		setArmor(0);
		setMaxArmor(20);
		setAttackDmg(3);
		setCritChance(7);
		setResourceName("Rage");
		setResourceAmt(5);
		setResourceMax(100);
		setAbility1Name("Primal Charge");
		setAbility1BaseCD(2);
		setAbility1Cost(6);
		setAbility2Name("Vicious Bite");
		setAbility2BaseCD(4);
		setAbility2Cost(9);
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
		setDmgOutput(6 + (2*stampede));
		stampede++;
		
		System.out.println("Used PRIMAL CHARGE! It rushes towards and is getting more primal.");
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
		setDmgOutput(6);
		setHealth(getHealth() + 3);
		
		System.out.println(getUsername() + ":");
		System.out.println("Used VICIOUS BITE! It takes a meaty bite of your flesh feeding its bloodlust and healing it.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		return false;
	}
	
	public void dmgTakenPassive(Character attacker, Character[] allies, Character[] enemies) {
		attacker.setDmgOutput(attacker.getDmgOutput() - 1);
		if(attacker.getDmgOutput() < 0) {
			attacker.setDmgOutput(0);
		}
	}
}