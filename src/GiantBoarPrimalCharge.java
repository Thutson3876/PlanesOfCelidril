
public class GiantBoarPrimalCharge extends Ability{
	private int stampede;
	private int modifier;
	public GiantBoarPrimalCharge() {
		setCooldown(2);
		setCost(6);
		setDmg(6);
		setName("Primal Charge");
		modifier = 3;
		stampede = 0;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		caster.setPrevTarget(target);
		System.out.println(caster.getUsername() + ":");
		caster.setDmgOutput(this.getDmg() + (modifier*stampede));
		stampede++;
		
		System.out.println("Used PRIMAL CHARGE! It rushes towards and is getting more primal.");
		return true;
	}
}
