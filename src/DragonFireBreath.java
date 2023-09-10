
public class DragonFireBreath extends Ability{
	private int duration;
	public DragonFireBreath() {
		setCooldown(4);
		setCost(35);
		setDmg(20);
		setName("Fire Breath");
		duration = 3;
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
		target.setHasWeakness(duration);
		
		System.out.println("Used FIRE BREATH! You are weakened for 2 turns.");
		return true;
	}
}
