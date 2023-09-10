public class SicklyImpImpVomit extends Ability{
	private int duration;
	private int poisonDmg;
	public SicklyImpImpVomit() {
		setCooldown(4);
		setCost(10);
		setDmg(6);
		setName("Imp Vomit");
		duration = 2;
		poisonDmg = 4;
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
		target.applyPoison(duration, poisonDmg);
		
		System.out.println("Used IMP VOMIT! It pukes all over you inflicting you with whatever diseases it carries.");
		return true;
	}
}