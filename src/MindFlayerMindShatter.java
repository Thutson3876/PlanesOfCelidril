import java.util.Random;

public class MindFlayerMindShatter extends Ability{
	private Random rng = new Random();
	private int dmg;
	private int wDuration;
	private int eDuration;
	private int sDuration;
	private double resourceDrain;
	private int resourceGain;
	public MindFlayerMindShatter() {
		setCooldown(3);
		setCost(25);
		setDmg(0);
		setName("Mind Shatter");
		dmg = 18;
		wDuration = 4;
		eDuration = 3;
		sDuration = 1;
		resourceDrain = 0.5;
		resourceGain = 15;
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		Character target = null;
		int temp = 0;
		target = caster.targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		caster.setPrevTarget(target);
		System.out.println(caster.getUsername() + ":");
		temp = rng.nextInt(5);
		caster.setDmgOutput(this.getDmg());
		if(temp == 0) {
			caster.setDmgOutput(dmg);
			System.out.println("Used MIND SHATTER! It blasts your brain with forbidden knowledge.");
		}
		else if(temp == 1) {
			target.setHasWeakness(wDuration);
			System.out.println("Used MIND SHATTER! Your mind is filled with doubts and visions of dark magic as you are weakened for 3 turns.");
		}
		else if(temp == 2) {
			target.setHasEnrage(eDuration);
			System.out.println("Used MIND SHATTER! Your deepest, darkest pains are brought to the surface of your mind enraging you for 2 turns.");
		}
		else if(temp == 3) {
			target.setHasStun(sDuration);
			System.out.println("Used MIND SHATTER! Your mind is blasted to shambles and you are stunned for a turn.");
		}
		else if(temp == 4) {
			target.changeResource(-(int)(resourceDrain*target.getResourceAmt()));
			caster.changeResource(resourceGain);
			System.out.println("Used MIND SHATTER! It drains you of your power in order to fuel its own.");
		}
		return true;
	}
}
