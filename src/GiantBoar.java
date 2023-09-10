
public class GiantBoar extends Character {
	private int stampede = 0;
	public GiantBoar() {
		setName("Giant Boar");
		setMaxHealth(40);
		setHealth(40);
		setArmor(20);
		setMaxArmor(60);
		setAttackDmg(5);
		setCritChance(7);
		setResourceName("Rage");
		setResourceAmt(10);
		setResourceMax(100);
		setAbility1Name("Primal Charge");
		setAbility1BaseCD(2);
		setAbility1Cost(6);
		setAbility2Name("Vicious Bite");
		setAbility2BaseCD(4);
		setAbility2Cost(9);
		setAbility3Name("Call of the Boar");
		setAbility3BaseCD(6);
		setAbility3Cost(12);
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
		setDmgOutput(6 + (3*stampede));
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
		setDmgOutput(9);
		setHealth(getHealth() + 5);
		
		System.out.println(getUsername() + ":");
		System.out.println("Used VICIOUS BITE! It takes a meaty bite of your flesh feeding its bloodlust and healing it.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		Character[] summon = new Character[1];
		summon[0] = new WeeBoar();
		setDmgOutput(0);
		getCurrentCombat().summon(this, summon);
		System.out.println(getUsername() + ":");
		System.out.println("Used CALL OF THE BOAR! A Wee Boar joins the fight!");
		return true;
	}
	
	public void dmgTakenPassive(Character attacker, Character[] allies, Character[] enemies) {
		attacker.setDmgOutput(attacker.getDmgOutput() - 5);
		if(attacker.getDmgOutput() < 0) {
			attacker.setDmgOutput(0);
		}
	}

}