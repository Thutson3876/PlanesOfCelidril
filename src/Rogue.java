import java.util.Random;

public class Rogue extends Character {
	public Rogue() {
		setName("Rogue");
		setMaxHealth(100);
		setHealth(100);
		setArmor(0);
		setMaxArmor(30);
		setAttackDmg(10);
		setCritChance(15);
		setResourceName("Energy");
		setResourceAmt(100);
		setResourceMax(100);
		setAbility1Name("Eviscerate");
		setAbility1BaseCD(3);
		setAbility1Cost(30);
		setAbility2Name("Poison Daggers");
		setAbility2BaseCD(3);
		setAbility2Cost(30);
		setAbility3Name("Shadowdance");
		setAbility3BaseCD(5);
		setAbility3Cost(60);
		setBleedDmg(8);
		setPoisonDmg(7);
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		Random rng = new Random();
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(10);
		target.setHasBleed(3);
		setBleedDmg(rng.nextInt(2) + 8);
		target.setBleed(getBleedDmg());
		
		System.out.println("You hack and slash at your target, causing them to bleed profusely.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		Random rng = new Random();
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(7);
		target.setHasPoison(4);
		setPoisonDmg(rng.nextInt(2) + 7);
		target.setPoison(getPoisonDmg());
		target.setHasWeakness(3);
		System.out.println("You coat your blades in weakening poison and stab your opponent.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		setInvisible(4);
		System.out.println(getUsername() + ":");
		System.out.println("You swiftly sheathe your blades and disappear, hiding in the shadows.");
		return true;
	}
}