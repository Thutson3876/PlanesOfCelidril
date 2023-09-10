
public class Ability {
	private int cooldown;
	private int ccd = 0;
	private int cost;
	private int dmg;
	private String name;
	public Ability() {
		
	}
	
	public int getCooldown() {
		return cooldown;
	}
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	public int getCcd() {
		return ccd;
	}
	public void setCcd(int ccd) {
		this.ccd = ccd;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive(Character caster) {
		if(ccd == 0 && caster.getResourceAmt() >= cost) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean cast(Character caster, Character[] allies, Character[] enemies) {
		return false;
	}
}
