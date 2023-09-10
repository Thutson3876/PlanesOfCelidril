
public class Mage extends Character {
	public Mage() {
		setName("Mage");
		setMaxHealth(100);
		setHealth(100);
		setArmor(0);
		setMaxArmor(30);
		setAttackDmg(12);
		setCritChance(7);
		setResourceName("Runes");
		setResourceMax(3);
		setResourceAmt(0);
		setAbility1Name("Siphon Magic");
		setAbility1BaseCD(0);
		setAbility1Cost(0);
		setAbility2Name("Flash Freeze");
		setAbility2BaseCD(3);
		setAbility2Cost(1);
		setAbility3Name("Runic Pulse");
		setAbility3BaseCD(0);
		setAbility3Cost(0);
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
		setDmgOutput((int)Math.round(10 + 10*.33*getResourceAmt()));
		setResourceAmt(getResourceAmt() + 2);
		
		System.out.println("You siphon the life force from your enemy to forge 2 new runes.");
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
		setDmgOutput((int)Math.round(6 + 6*.33*getResourceAmt()));
		target.setHasFrozen(1);
		
		System.out.println("You spend a rune to freeze your foe in their tracks.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput((int)Math.round(18*getResourceAmt()));
		setResourceAmt(0);
		System.out.println("You send your runes forth and overload them with energy until they explode around your enemy.");
		return true;
	}
}