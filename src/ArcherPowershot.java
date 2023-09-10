
public class ArcherPowershot extends Ability{
	private int dmgModifier;
	public ArcherPowershot() {
		setCooldown(0);
		setCost(50);
		setDmg(12);
		setName("Powershot");
		dmgModifier = 12;
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
		caster.setDmgOutput(dmgModifier*caster.getTrueShot()+this.getDmg());
		if(caster.getTrueShot() > 0) {
			caster.setTrueShot(caster.getTrueShot()-1);
		}
		
		System.out.println("You charge your bow and release a precise shot at your target's weakest point.");
		return true;
	}
}
