
public class WarriorArmorUp extends Ability{
	private double armorGainModifier;
	public WarriorArmorUp() {
		setCooldown(0);
		setCost(1);
		setDmg(0);
		setName("Fury");
		armorGainModifier = 1;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setDmgOutput(this.getDmg());
		caster.changeArmor((int)(armorGainModifier*caster.getResourceAmt()));
		System.out.println(caster.getUsername() + ":");
		System.out.println("You convert your rage into shielding and gain " + (int)(armorGainModifier*caster.getResourceAmt()) + " armor.");
		caster.setResourceAmt(1);
		return true;
	}
}
