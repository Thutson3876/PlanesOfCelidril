public class ThickImpArcanicConsumption extends Ability{
	private int modifier;
	public ThickImpArcanicConsumption() {
		setCooldown(3);
		setCost(1);
		setDmg(0);
		setName("Arcanic Consumption");
		modifier = 6;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setDmgOutput(this.getDmg());
		caster.changeHealth(modifier*caster.getResourceAmt());
		System.out.println(caster.getUsername() + ":");
		System.out.println("Used ARCANIC CONSUMPTION! It devours its runes in order to sustain itself!");
		return true;
	}
}