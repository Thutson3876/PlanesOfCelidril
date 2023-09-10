
public class DarkMage extends Character {
	public DarkMage() {
		setName("Dark Mage");
		setMaxHealth(100);
		setHealth(100);
		setArmor(30);
		setMaxArmor(60);
		setAttackDmg(12);
		setCritChance(7);
		setResourceName("Runes");
		setResourceAmt(1);
		setResourceMax(3);
		setAbility1Name("Blood Offering");
		setAbility1BaseCD(2);
		setAbility1Cost(1);
		setAbility2Name("Multi Drain");
		setAbility2BaseCD(3);
		setAbility2Cost(0);
		setAbility3Name("Bloodletting");
		setAbility3BaseCD(2);
		setAbility3Cost(0);
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		Character[] summon = new Character[1];
		
		System.out.println(getUsername() + ":");
		setDmgOutput(0);
		setHealth(getHealth() - 5);
		if(getResourceAmt() == 1) {
			summon[0] = new SicklyImp();
		}
		else if(getResourceAmt() == 2) {
			summon[0] = new PimpImp();
		}
		else if(getResourceAmt() == 3) {
			summon[0] = new ThickImp();
		}
		getCurrentCombat().summon(this, summon);
		setResourceAmt(1);
		System.out.println("Used BLOOD OFFERING! Summoned a " +  summon[0].getUsername() + ".");
		return true;
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		Character[] targets = allButOne(this, allies, enemies);
		setResourceAmt(getResourceAmt() + targets.length);
		setDmgOutput(7);
		aoe(targets, allies, enemies);
		
		System.out.println(getUsername() + ":");
		System.out.println("Used MULTI DRAIN! Dealt 7 damage to all other characters and fueled its magic.");
		return true;
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		Character[] arr = new Character[0];
		Character[] targets = allButOne(this, allies, arr);
		Character target = null;
		target = targeting(targets);
		if(target == null) {
			//System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		setDmgOutput(5);
		System.out.println(getUsername() + ":");
		setResourceAmt(getResourceAmt() + 2);
		System.out.println("Used BLOODLETTING! Dealt 5 damage to an ally and gained 2 runes.");
		return true;
	}
	
	public void dmgDealtPassive(Character victim, Character allies[], Character[] enemies) {
		setHealth(getHealth() + Math.round(getDmgOutput()/2));
	}
}