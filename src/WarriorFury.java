
public class WarriorFury extends Ability{
	private int enrageDuration;
	private int resourceGain;
	public WarriorFury() {
		setCooldown(3);
		setCost(0);
		setDmg(10);
		setName("Fury");
		enrageDuration = 3;
		resourceGain = getDmg();
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
		caster.setDmgOutput(getDmg());
		caster.setResourceAmt(caster.getResourceAmt() + resourceGain);
		caster.setHasEnrage(enrageDuration);
		
		System.out.println("You become enraged and unleash your fury upon your foe to generate extra rage.");
		return true;
	}
}
