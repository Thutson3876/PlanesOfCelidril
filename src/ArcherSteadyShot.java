
public class ArcherSteadyShot extends Ability{
	private int duration;
	public ArcherSteadyShot() {
		setCooldown(4);
		setCost(20);
		setDmg(0);
		setName("Rapid Fire");
		duration = 3;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setDmgOutput(this.getDmg());
		caster.changeResource(20);
		caster.setSteady(duration);
		System.out.println(caster.getUsername() + ":");
		System.out.println("You prepare your next shots with perfect focus and conserve your energy.");
		return true;
	}
}
