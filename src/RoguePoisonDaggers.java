import java.util.Random;

public class RoguePoisonDaggers extends Ability{
	private Random rng = new Random();
	private int poisonDmg;
	private int modifier;
	private int poisonDuration;
	public RoguePoisonDaggers() {
		setCooldown(3);
		setCost(30);
		setDmg(7);
		setName("Poison Daggers");
		poisonDmg = 7;
		poisonDuration = 4;
		modifier = 2;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		caster.setPrevTarget(target);
		System.out.println(caster.getUsername() + ":");
		caster.setDmgOutput(this.getDmg());
		target.applyPoison(poisonDuration, poisonDmg + rng.nextInt(modifier));
		target.setHasWeakness(3);
		System.out.println("You coat your blades in weakening poison and stab your opponent.");
		return true;
	}
}
