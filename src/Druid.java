
public class Druid extends Character {
	Character humanForm;
	Character feralForm;
	Character bearForm;
	Character treantForm;
	public Druid() {
		setName("Druid");
		setMaxHealth(100);
		setHealth(100);
		setArmor(0);
		setMaxArmor(50);
		setAttackDmg(6);
		setCritChance(5);
		setResourceName("Mana");
		setResourceAmt(100);
		setResourceMax(100);
		setAbility1Name("Feral Form");
		setAbility1BaseCD(2);
		setAbility1Cost(30);
		setAbility2Name("Bear Form");
		setAbility2BaseCD(2);
		setAbility2Cost(30);
		setAbility3Name("Treant Form");
		setAbility3BaseCD(2);
		setAbility3Cost(30);
		feralForm = new DruidFeralForm();
		bearForm = new DruidBearForm();
		treantForm = new DruidTreantForm();
	}
	
	public boolean ability1(Character[] allies, Character[] enemies) {
		if(getName().contentEquals("Druid treant")) {
			return healingTouch(allies, enemies);
		}
		else if(getName().contentEquals("Druid Feral")) {
			return piercingBite(allies, enemies);
		}
		else if(getName().contentEquals("Druid Bear")) {
			return maul(allies, enemies);
		}
		else {
			setDmgOutput(0);
			setResourceAmt(getResourceAmt() - getAbility1Cost());
			setAbility1CCD(getAbility1BaseCD());
			humanForm = this;
			
			System.out.println("You shapeshift into a great tiger.");
			replace(feralForm);
			setResourceAmt(getResourceAmt() + getAbility1Cost());
			setAbility1CCD(-getAbility1BaseCD());
			
			return true;
		}
		
	}
	
	public boolean ability2(Character[] allies, Character[] enemies) {
		if(getName().contentEquals("Druid treant")) {
			return barkskin(allies, enemies);
		}
		else if(getName().contentEquals("Druid Feral")) {
			return amongstTheBushes(allies, enemies);
		}
		else if(getName().contentEquals("Druid Bear")) {
			return fearsomeRoar(allies, enemies);
		}
		else {
			setDmgOutput(0);
			setResourceAmt(getResourceAmt() - getAbility2Cost());
			setAbility2CCD(getAbility2BaseCD());
			humanForm = this;
			
			System.out.println("You shapeshift into a large bear.");
			replace(bearForm);
			setResourceAmt(getResourceAmt() + getAbility2Cost());
			setAbility2CCD(-getAbility2BaseCD());
			
			return true;
		}
		
	}
	
	public boolean ability3(Character[] allies, Character[] enemies) {
		if(getName().contentEquals("Druid")) {
			setDmgOutput(0);
			setResourceAmt(getResourceAmt() - getAbility1Cost());
			setAbility1CCD(getAbility1BaseCD());
			humanForm = this;
	
			System.out.println("You shapeshift into a treant");
			replace(treantForm);
			setResourceAmt(getResourceAmt() + getAbility1Cost());
			setAbility1CCD(-getAbility1BaseCD());
			
			return true;
		}
		else {
			return revert();
		}
	}
	
	public boolean piercingBite(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(12);
		target.setHasBleed(2);
		target.setBleed(getBleedDmg());
		
		System.out.println("You leap towards your target and bite into its flesh causing it to bleed profusely.");
		return true;
	}
	public boolean amongstTheBushes(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		setInvisible(3);
		
		System.out.println(getUsername() + ":");
		System.out.println("You hide amongst the shadows and prepare to strike.");
		return true;
	}
	
	public boolean maul(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(enemies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(8);
		target.setHasBleed(2);
		target.setBleed(getBleedDmg());
		
		System.out.println("You slash at your target visciously causing it to bleed.");
		return true;
	}
	public boolean fearsomeRoar(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		for(int i = 0; i < enemies.length; i++) {
			enemies[i].setHasWeakness(5);
			enemies[i].setHasStun(1);
		}
		
		System.out.println(getUsername() + ":");
		System.out.println("You release a ground-shaking roar that sends chills down the spines of your enemies.");
		return true;
	}
	
	public boolean healingTouch(Character[] allies, Character[] enemies) {
		Character target = null;
		target = targeting(allies);
		if(target == null) {
			System.out.println("All targets are invalid.");
			return false;
		}
		
		setPrevTarget(target);
		System.out.println(getUsername() + ":");
		setDmgOutput(0);
		target.setHealth(target.getHealth() + 25);
		
		System.out.println("You use the power of nature to mend your target's wounds.");
		return true;
	}
	public boolean barkskin(Character[] allies, Character[] enemies) {
		setDmgOutput(0);
		for(int i = 0; i < allies.length; i++) {
			allies[i].setArmor(allies[i].getArmor() + 15);
		}
		
		System.out.println(getUsername() + ":");
		System.out.println("You strengthen your allies' defenses by shielding them in thick bark.");
		return true;
	}
	
	public boolean revert() {
			setDmgOutput(0);
			System.out.println(getUsername() + ": ");
			replace(humanForm);
			System.out.println("You shapeshift back into your regular form.");
			return true;
		}
	
	public void dmgTakenPassive(Character attacker, Character[] allies, Character[] enemies) {
		if(getName().contentEquals("Druid Bear")) {
			setResourceAmt(getResourceAmt() + attacker.getDmgOutput());
		}
	}
}