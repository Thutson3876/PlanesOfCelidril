
public class Archer extends Character {
	public Archer() {
		setName("Archer");
		setMaxHealth(120);
		setHealth(120);
		setArmor(0);
		setMaxArmor(50);
		setAttackDmg(12);
		setCritChance(5);
		setResourceName("Energy");
		setResourceAmt(100);
		setResourceMax(100);
		setAbility1Name("Powershot");
		setAbility1BaseCD(0);
		setAbility1Cost(50);
		setAbility2Name("Rapid Fire");
		setAbility2BaseCD(4);
		setAbility2Cost(30);
		setAbility3Name("Steady Shot");
		setAbility3BaseCD(3);
		setAbility3Cost(30);
		setTrueShot(0);
		setSteady(0);
		setRapidFire(0);
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
		setDmgOutput(12 + 8*getTrueShot());
		if (getTrueShot() > 3) {
			setDmgOutput(2*getDmgOutput());
		}
		if(getTrueShot() > 0) {
			setTrueShot(getTrueShot()-1);
		}
		
		System.out.println("You charge your bow and release a precise shot at your target's weakest point.");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		setRapidFire(5);
		setDmgOutput(0);
		
		System.out.println(getUsername() + ":");
		System.out.println("You empower your bow with Elune's Blessing allowing you to fire more frequently.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		setResourceAmt(getResourceAmt()+25);
		setSteady(3);
		System.out.println(getUsername() + ":");
		System.out.println("You prepare your next shots with perfect focus and conserve your energy.");
		return true;
	}
}