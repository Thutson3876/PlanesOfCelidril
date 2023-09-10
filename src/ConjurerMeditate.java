
public class ConjurerMeditate extends Ability{
	private double reducedDmgBase;
	private double reducedDmgModifier;
	public ConjurerMeditate() {
		setCooldown(2);
		setCost(0);
		setDmg(0);
		setName("Meditate");
		reducedDmgBase = 0.1;
		reducedDmgModifier = 0.2;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setDmgOutput(this.getDmg());
		caster.setReducedDmg(reducedDmgBase + reducedDmgModifier*caster.getResourceAmt());
		caster.changeResource(2);
		System.out.println(caster.getUsername() + ":");
		System.out.println("Focus your energy to create 2 new magical runes.");
		return true;
	}
}
