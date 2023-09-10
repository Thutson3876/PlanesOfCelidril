import java.util.Random;

public class SicklyImp extends Character {
	public SicklyImp() {
		setName("Sickly Imp");
		setMaxHealth(15);
		setHealth(15);
		setArmor(0);
		setMaxArmor(10);
		setAttackDmg(3);
		setCritChance(5);
		setResourceName("Energy");
		setResourceAmt(70);
		setResourceMax(100);
		setAbility1Name("Imp Vomit");
		setAbility1BaseCD(3);
		setAbility1Cost(50);
		setAbility2Name("");
		setAbility2BaseCD(0);
		setAbility2Cost(0);
		setAbility3Name("");
		setAbility3BaseCD(0);
		setAbility3Cost(0);
		setPoisonDmg(4);
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
		setDmgOutput(6);
		target.setHasPoison(2);
		target.setPoison(getPoisonDmg());
		
		System.out.println("Used IMP VOMIT! It pukes all over you inflicting you with whatever diseases it carries.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		return false;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		return false;
	}
	
	public void endPassive(Character[] allies, Character[] enemies) {
		Random rng = new Random();
		if(rng.nextInt(100) < 3) {
			setHealth(0);
			System.out.println("The Sick Imp choked on its own vomit and has now stopped breathing.");
		}
	}
	
}