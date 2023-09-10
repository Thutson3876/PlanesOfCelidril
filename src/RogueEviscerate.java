import java.util.Random;

public class RogueEviscerate extends Ability{
	private Random rng = new Random();
	private int modifier;
	private int bleedDmg;
	private int bleedDuration;
	public RogueEviscerate() {
		setCooldown(3);
		setCost(30);
		setDmg(10);
		setName("Eviscerate");
		bleedDmg = 8;
		bleedDuration = 3;
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
		target.applyBleed(bleedDuration, bleedDmg + rng.nextInt(modifier));
		
		System.out.println("You hack and slash at your target, causing them to bleed profusely.");
		return true;
	}
}
