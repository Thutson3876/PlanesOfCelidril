public class PimpImpImpCane extends Ability{
	private int impC;
	private int max;
	public PimpImpImpCane() {
		setCooldown(4);
		setCost(10);
		setDmg(3);
		setName("Imp Cane");
		impC = 0;
		max = 3;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		System.out.println(caster.getUsername() + ":");
		caster.setPrevTarget(target);
		caster.setDmgOutput(this.getDmg()*(impC+1));
		if(impC == max) {
			caster.changeResource(Math.round(this.getCost()/2));
			System.out.println("Used IMP CANE! It smacks you with its sleek cane and puts on an even sleeker bowler hat.");
		}
		else {
			System.out.println("Used IMP CANE! It smacks you with its sleek cane.");
		}
		
		return true;
	}
}