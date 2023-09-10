
public class DragonFlyingHigh extends Ability{
	private int duration;
	public DragonFlyingHigh() {
		setCooldown(4);
		setCost(35);
		setDmg(0);
		setName("Flying High");
		duration = 2;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		System.out.println(caster.getUsername() + ":");
		caster.setDmgOutput(this.getDmg());
		caster.setInvisible(duration);
		System.out.println("Used FLYING HIGH! You can't damage it for a turn.");
		return true;
	}
}
