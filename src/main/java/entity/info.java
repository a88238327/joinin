package entity;

public class info {

	private String name;
	private String jihuoshu;
	private String guanzhushu;
	public info(String name,String jihuoshu,String guanzhushu) {
		this.name=name;
		this.jihuoshu=jihuoshu;
		this.guanzhushu=guanzhushu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJihuoshu() {
		return jihuoshu;
	}
	public void setJihuoshu(String jihuoshu) {
		this.jihuoshu = jihuoshu;
	}
	public String getGuanzhushu() {
		return guanzhushu;
	}
	public void setGuanzhushu(String guanzhushu) {
		this.guanzhushu = guanzhushu;
	}
	
			
}
