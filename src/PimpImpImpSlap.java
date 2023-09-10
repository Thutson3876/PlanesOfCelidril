public class PimpImpImpSlap extends Ability{
	private int duration;
	public PimpImpImpSlap() {
		setCooldown(4);
		setCost(10);
		setDmg(3);
		setName("Imp Slap");
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
		target.setHasStun(duration);
		
		System.out.println("Used IMP SLAP! It slaps you like it owns you and you are stunned by its audacity.");
		return true;
	}
}