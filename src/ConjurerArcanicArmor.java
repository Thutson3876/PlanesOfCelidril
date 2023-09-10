
public class ConjurerArcanicArmor extends Ability{
	private int base;
	private int modifier;
	public ConjurerArcanicArmor() {
		setCooldown(3);
		setCost(1);
		setDmg(0);
		setName("Arcanic Armor");
		base = 10;
		modifier = 12;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setDmgOutput(this.getDmg());
		caster.changeArmor((int)Math.round(base + modifier*caster.getResourceAmt()));
		caster.setResourceAmt(1);
		System.out.println(caster.getUsername() + ":");
		System.out.println("You spend all of your runes to shield yourself in arcanic armor.");
		return true;
	}
}
