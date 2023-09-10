
public class MageSiphonMagic extends Ability{
	private int resourceGain;
	public MageSiphonMagic() {
		setCooldown(0);
		setCost(0);
		setDmg(10);
		setName("Siphon Magic");
		resourceGain = 2;
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
		
		System.out.println("You siphon the life force from your enemy to forge 2 new runes.");
		return true;
	}
}
