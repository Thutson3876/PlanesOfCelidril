
public class ArcherRapidFire extends Ability{
	private int duration;
	public ArcherRapidFire() {
		setCooldown(4);
		setCost(40);
		setDmg(0);
		setName("Rapid Fire");
		duration = 5;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setRapidFire(duration);
		caster.setDmgOutput(this.getDmg());
		
		System.out.println(caster.getUsername() + ":");
		System.out.println("You empower your bow with Elune's Blessing allowing you to fire more frequently.");
		return true;
	}
}
