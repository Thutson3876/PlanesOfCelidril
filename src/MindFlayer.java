import java.util.Random;

public class MindFlayer extends Character {
	public MindFlayer() {
		setName("Mind Flayer");
		setMaxHealth(30);
		setHealth(30);
		setArmor(0);
		setMaxArmor(40);
		setAttackDmg(10);
		setCritChance(12);
		setResourceName("Mana");
		setResourceAmt(100);
		setResourceMax(100);
		setAbility1Name("Mind Shatter");
		setAbility1BaseCD(3);
		setAbility1Cost(25);
		setAbility2Name("Corrupt Blood");
		setAbility2BaseCD(4);
		setAbility2Cost(30);
		setAbility3Name("Void Assimilation");
		setAbility3BaseCD(4);
		setAbility3Cost(40);
		setBleedDmg(6);
		setPoisonDmg(7);
		setUsername(getName());
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		Random rng = new Random();
		Character target = null;
		int temp = 0;
		target = targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		temp = rng.nextInt(5);
		setDmgOutput(0);
		if(temp == 0) {
			setDmgOutput(18);
			System.out.println("Used MIND SHATTER! It blasts your brain with forbidden knowledge.");
		}
		else if(temp == 1) {
			target.setHasWeakness(4);
			System.out.println("Used MIND SHATTER! Your mind is filled with doubts and visions of dark magic as you are weakened for 3 turns.");
		}
		else if(temp == 2) {
			target.setHasEnrage(3);
			System.out.println("Used MIND SHATTER! Your deepest, darkest pains are brought to the surface of your mind enraging you for 2 turns.");
		}
		else if(temp == 3) {
			target.setHasStun(1);
			System.out.println("Used MIND SHATTER! Your mind is blasted to shambles and you are stunned for a turn.");
		}
		else if(temp == 4) {
			target.setResourceAmt((int)Math.round(0.5*target.getResourceAmt()));
			setResourceAmt(getResourceAmt() + 15);
			System.out.println("Used MIND SHATTER! It drains you of your power in order to fuel its own.");
		}
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		target.setHasBleed(3);
		target.setBleed(getBleedDmg());
		target.setHasPoison(3);
		target.setPoison(getPoisonDmg());
		System.out.println("Used CORRUPT BLOOD! Your veins pulse and run dark. You are poisoned and bleeding internally for 3 turns.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(0);
		setInvisible(2);
		if(target.getHealth() <= (int)Math.round(target.getMaxHealth()*0.25)) {
			target.setHealth(0);
			System.out.println("Your surroundings meld with the shadows around you until your vision fades, and you become one with the void...");
		}
		else {
			System.out.println("Used VOID ASSIMILATION! You can't damage it for a turn.");
		}
		return true;
	}
}