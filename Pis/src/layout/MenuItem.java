package layout;

public class MenuItem {
	private String name;
	private String url;
	
	public MenuItem(String name, String url){
		this.name = name;
		this.url = url;
	}
	public String getName(){ return this.name; }
	public String getUrl(){ return this.url; }
}
