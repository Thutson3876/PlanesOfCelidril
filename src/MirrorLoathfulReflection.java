
public class MirrorLoathfulReflection extends Ability{
	private int modifier;
	public MirrorLoathfulReflection() {
		setCooldown(3);
		setCost(10);
		setDmg(0);
		setName("Self Reflection");
		modifier = 1;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		target = caster.targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		caster.setPrevTarget(target);
		for(int i = 0; i < enemies.length; i++) {
			setDmg(getDmg() + enemies[i].getDmgOutput());
		}
		caster.setDmgOutput(this.getDmg()*modifier);
		System.out.println(caster.getUsername() + ":");
		System.out.println("Used LOATHFUL REFLECTION! It damages you based on the damage that you dealt to it.");
		return true;
	}
}
