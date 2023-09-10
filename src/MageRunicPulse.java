
public class MageRunicPulse extends Ability{
	public MageRunicPulse() {
		setCooldown(0);
		setCost(1);
		setDmg(18);
		setName("Runic Pulse");
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		caster.setPrevTarget(target);
		System.out.println(caster.getUsername() + ":");
		caster.setDmgOutput((int)Math.round(this.getDmg()*caster.getResourceAmt()));
		caster.setResourceAmt(1);
		System.out.println("You send your runes forth and overload them with energy until they explode around your enemy.");
		return true;
	}
}
