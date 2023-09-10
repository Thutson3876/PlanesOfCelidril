
public class MindFlayerVoidAssimilation extends Ability{
	private int duration;
	private double kill;
	public MindFlayerVoidAssimilation() {
		setCooldown(4);
		setCost(40);
		setDmg(0);
		setName("Void Assimilation");
		duration = 2;
		kill = 0.25;
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
		caster.setDmgOutput(this.getDmg());
		caster.setInvisible(duration);
		if(target.getHealth() <= (int)Math.round(target.getMaxHealth()*kill)) {
			target.changeHealth(-target.getHealth());
			System.out.println("Your surroundings meld with the shadows around you until your vision fades, and you become one with the void...");
		}
		else {
			System.out.println("Used VOID ASSIMILATION! You can't damage it for a turn.");
		}
		return true;
	}
}
