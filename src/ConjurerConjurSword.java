
public class ConjurerConjurSword extends Ability{
	private int swordDmg;
	private int duration;
	public ConjurerConjurSword() {
		setCooldown(3);
		setCost(2);
		setDmg(0);
		setName("Conjur Sword");
		swordDmg = 12;
		duration = 5;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setSwordDmg((int)Math.round(swordDmg + swordDmg*0.33*caster.getResourceAmt()));
		caster.setDmgOutput(this.getDmg());
		caster.setSwordDuration(duration);
		
		System.out.println(caster.getUsername() + ":");
		System.out.println("You spend 2 runes to forge an arcanic weapon that boosts your attack damage.");
		return true;
	}
}
