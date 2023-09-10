
public class MindFlayerCorruptBlood extends Ability{
	private int bDuration;
	private int bleedDmg;
	private int pDuration;
	private int poisonDmg;
	public MindFlayerCorruptBlood() {
		setCooldown(4);
		setCost(30);
		setDmg(0);
		setName("Corrupt Blood");
		bDuration = 3;
		bleedDmg = 6;
		pDuration = 3;
		poisonDmg = 7;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		caster.setDmgOutput(this.getDmg());
		caster.setPrevTarget(target);
		System.out.println(caster.getUsername() + ":");
		target.applyBleed(bDuration, bleedDmg);
		target.applyPoison(pDuration, poisonDmg);
		System.out.println("Used CORRUPT BLOOD! Your veins pulse and run dark. You are poisoned and bleeding internally for 3 turns.");
		return true;
	}
}
