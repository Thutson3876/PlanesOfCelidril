
public class WarriorShieldBash extends Ability{
	private int stunDuration;
	public WarriorShieldBash() {
		setCooldown(3);
		setCost(12);
		setDmg(6);
		setName("Fury");
		stunDuration = 1;
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
		target.setHasStun(stunDuration);
		
		System.out.println("You slam your shield into your enemy, briefly stunning them.");
		return true;
	}
}
