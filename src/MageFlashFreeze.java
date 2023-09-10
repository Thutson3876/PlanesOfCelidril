
public class MageFlashFreeze extends Ability{
	private int freezeDuration;
	public MageFlashFreeze() {
		setCooldown(3);
		setCost(1);
		setDmg(6);
		setName("Flash Freeze");
		freezeDuration = 1;
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
		target.setHasFrozen(freezeDuration);
		
		System.out.println("You spend a rune to freeze your foe in their tracks.");
		return true;
	}
}
