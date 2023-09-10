
public class DarkMageBloodletting extends Ability{
	private int resourceGain;
	public DarkMageBloodletting() {
		setCooldown(2);
		setCost(0);
		setDmg(5);
		setName("Multi Drain");
		resourceGain = 2;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character[] arr = new Character[0];
		Character[] targets = Character.allButOne(caster, allies, arr);
		Character target = null;
		target = caster.targeting(targets);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		caster.setPrevTarget(target);
		caster.setDmgOutput(this.getDmg());
		System.out.println(caster.getUsername() + ":");
		caster.changeResource(resourceGain);
		System.out.println("Used BLOODLETTING! Dealt damage to an ally and gained more runes.");
		return true;
	}
}
