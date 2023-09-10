
public class GiantBoarVisciousBite extends Ability{
	private int heal;
	public GiantBoarVisciousBite() {
		setCooldown(3);
		setCost(9);
		setDmg(9);
		setName("Viscious Bite");
		heal = 5;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		caster.setPrevTarget(target);
		caster.setDmgOutput(this.getDmg());
		caster.changeHealth(heal);
		
		System.out.println(caster.getUsername() + ":");
		System.out.println("Used VICIOUS BITE! It takes a meaty bite of your flesh feeding its bloodlust and healing it.");
		return true;
	}
}
