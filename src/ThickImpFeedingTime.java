public class ThickImpFeedingTime extends Ability{
	private int healthCost;
	private int resourceGain;
	public ThickImpFeedingTime() {
		setCooldown(2);
		setCost(0);
		setDmg(0);
		setName("Feeding Time");
		healthCost = 5;
		resourceGain = 1;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setDmgOutput(this.getDmg());
		caster.changeHealth(-healthCost);
		caster.changeResource(resourceGain);
		System.out.println(caster.getUsername() + ":");
		System.out.println("Used FEEDING TIME! It takes a meaty bite of its own flesh in order to fuel its dark magic.");
		return true;
	}
}