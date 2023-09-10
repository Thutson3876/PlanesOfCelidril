public class ThickImpBellySlam extends Ability{
	private int modifier;
	public ThickImpBellySlam() {
		setCooldown(2);
		setCost(1);
		setDmg(0);
		setName("Belly Slam");
		modifier = 3;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		this.setDmg(caster.getHealth()/(modifier/caster.getResourceAmt()));
		caster.setPrevTarget(target);
		System.out.println(caster.getUsername() + ":");
		if(caster.getResourceAmt() > 0) {
			caster.setDmgOutput(Math.round(caster.getHealth()/(modifier/caster.getResourceAmt())));
		}
		caster.setResourceAmt(1);
		
		System.out.println("Used BELLY SLAM! It slams into you dealing heavy damage.");
		return true;
	}
}