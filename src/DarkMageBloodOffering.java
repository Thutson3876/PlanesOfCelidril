
public class DarkMageBloodOffering extends Ability{
	Character[] summoned;
	public DarkMageBloodOffering() {
		setCooldown(2);
		setCost(1);
		setDmg(0);
		setName("Blood Offering");
		summoned[0] = new SicklyImp();
		summoned[1] = new PimpImp();
		summoned[2] = new ThickImp();
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character[] summon = new Character[1];
		
		System.out.println(caster.getUsername() + ":");
		caster.setDmgOutput(this.getDmg());
		caster.changeHealth(-5);
		if(caster.getResourceAmt() == 1) {
			summon[0] = summoned[0];
		}
		else if(caster.getResourceAmt() == 2) {
			summon[0] = summoned[1];
		}
		else if(caster.getResourceAmt() == 3) {
			summon[0] = summoned[2];
		}
		caster.getCurrentCombat().summon(caster, summon);
		caster.setResourceAmt(1);
		System.out.println("Used BLOOD OFFERING! Summoned a " +  summon[0].getUsername() + ".");
		return true;
	}
}
