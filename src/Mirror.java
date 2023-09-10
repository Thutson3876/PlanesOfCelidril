
public class Mirror extends Character {
	public Mirror() {
		setName("Mirror");
		setMaxHealth(200);
		setHealth(200);
		setArmor(100);
		setMaxArmor(300);
		setAttackDmg(7);
		setCritChance(5);
		setResourceName("Mana");
		setResourceAmt(10);
		setResourceMax(100);
		setAbility1Name("Shatter");
		setAbility1BaseCD(6);
		setAbility1Cost(15);
		setAbility2Name("Self Reflection");
		setAbility2BaseCD(4);
		setAbility2Cost(10);
		setAbility3Name("Loathful Reflection");
		setAbility3BaseCD(3);
		setAbility3Cost(10);
		setUsername(getName());
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		setDmgOutput(0);
		for(int i = 0; i < enemies.length; i++) {
			setDmgOutput(enemies[i].getDmgOutput() + enemies[i].getArmor());
			enemies[i].setArmor(0);
		}
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(getDmgOutput() + getArmor());
		setArmor(0);
		System.out.println("Used SHATTER! It destroys both your armor, and its own, dealing damage based on armor lost.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		int temp = 0;
		for(int i = 0; i < enemies.length; i++) {
			temp += enemies[i].getDmgOutput()*2;
		}
		System.out.println(getUsername() + ":");
		setArmor(getArmor() + temp);
		setDmgOutput(0);
		System.out.println("Used SELF REFLECTION! It gained armor based on the damage you dealt.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		setDmgOutput(0);
		for(int i = 0; i < enemies.length; i++) {
			setDmgOutput(getDmgOutput() + enemies[i].getDmgOutput());
		}
		System.out.println(getUsername() + ":");
		System.out.println("Used LOATHFUL REFLECTION! It damages you based on the damage that you dealt to it.");
		return true;
	}
}