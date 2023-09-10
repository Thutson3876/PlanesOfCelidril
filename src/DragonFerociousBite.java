
public class DragonFerociousBite extends Ability{
	private int duration;
	private int bleedDmg;
	public DragonFerociousBite() {
		setCooldown(4);
		setCost(35);
		setDmg(18);
		setName("Ferocious Bite");
		duration = 3;
		bleedDmg = 7;
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
		target.applyBleed(duration, bleedDmg);
		
		System.out.println("Used FEROCIOUS BITE! You are bleeding for 3 turns.");
		return true;
	}
}
