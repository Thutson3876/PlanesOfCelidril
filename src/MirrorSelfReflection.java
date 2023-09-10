
public class MirrorSelfReflection extends Ability{
	private int modifier;
	public MirrorSelfReflection() {
		setCooldown(4);
		setCost(10);
		setDmg(0);
		setName("Self Reflection");
		modifier = 2;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		int temp = 0;
		for(int i = 0; i < enemies.length; i++) {
			temp += enemies[i].getDmgOutput()*modifier;
		}
		System.out.println(caster.getUsername() + ":");
		caster.changeArmor(temp);
		caster.setDmgOutput(this.getDmg());
		System.out.println("Used SELF REFLECTION! It gained armor based on the damage you dealt.");
		return true;
	}
}
