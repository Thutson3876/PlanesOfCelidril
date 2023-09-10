
public class RogueShadowdance extends Ability{
	public RogueShadowdance() {
		setCooldown(5);
		setCost(50);
		setDmg(0);
		setName("Shadowdance");
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setDmgOutput(this.getDmg());
		caster.setInvisible(4);
		System.out.println(caster.getUsername() + ":");
		System.out.println("You swiftly sheathe your blades and disappear, hiding in the shadows.");
		return true;
	}
}
