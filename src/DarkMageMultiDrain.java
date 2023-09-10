
public class DarkMageMultiDrain extends Ability{
	private int resourceGain;
	public DarkMageMultiDrain() {
		setCooldown(3);
		setCost(0);
		setDmg(7);
		setName("Multi Drain");
		resourceGain = 1;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character[] targets = Character.allButOne(caster, allies, enemies);
		caster.changeResource(resourceGain*targets.length);
		caster.setDmgOutput(this.getDmg());
		caster.aoe(targets, allies, enemies);
		
		System.out.println(caster.getUsername() + ":");
		System.out.println("Used MULTI DRAIN! Dealt damage to all other characters and fueled its magic.");
		return true;
	}
}
