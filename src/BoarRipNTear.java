import java.util.Random;

public class BoarRipNTear extends Ability{
	private Random rng = new Random();
	private int modifier;
	private int bleedDmg;
	public BoarRipNTear() {
		setCooldown(3);
		setCost(1);
		setDmg(6);
		setName("Rip n' Tear");
		bleedDmg = 2;
		modifier = 2;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		caster.setPrevTarget(target);
		System.out.println(caster.getUsername() + ":");
		caster.setDmgOutput(this.getDmg());
		target.applyBleed(3, rng.nextInt(modifier) + bleedDmg);
		System.out.println("Used RIP N' TEAR! You are bleeding for 2 turns.");
		return true;
	}
}
