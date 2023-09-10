
public class MirrorShatter extends Ability{
	public MirrorShatter() {
		setCooldown(6);
		setCost(15);
		setDmg(0);
		setName("Shatter");
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		for(int i = 0; i < enemies.length; i++) {
			setDmg(getDmg() + enemies[i].getDmgOutput() + enemies[i].getArmor());
			enemies[i].setArmor(0);
		}
		caster.setPrevTarget(target);
		System.out.println(caster.getUsername() + ":");
		caster.setDmgOutput(this.getDmg() + caster.getArmor());
		caster.setArmor(0);
		System.out.println("Used SHATTER! It destroys both your armor, and its own, dealing damage based on armor lost.");
		return true;
	}
}
