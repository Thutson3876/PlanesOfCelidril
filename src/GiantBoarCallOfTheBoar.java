
public class GiantBoarCallOfTheBoar extends Ability{
	private Character[] summon = new Character[1];
	public GiantBoarCallOfTheBoar() {
		setCooldown(6);
		setCost(12);
		setDmg(0);
		setName("Call of the Boar");
		summon[0] = new WeeBoar();
	}
	
	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		caster.setDmgOutput(this.getDmg());
		caster.getCurrentCombat().summon(caster, summon);
		System.out.println(caster.getUsername() + ":");
		System.out.println("Used CALL OF THE BOAR! A Wee Boar joins the fight!");
		return true;
	}
}
