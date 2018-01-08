package demo5;

public class Cazako {
	private String name;
	private String phrase;
	private String series;
	
	protected Cazako(){}
	public Cazako(String name, String phrase, String series){
		this.name = name;
		this.phrase = phrase;
		this.series = series;
	}
	
	public String  toString(){
		return this.name + " is from the series " + this.series;
	}
	
	public String getName(){
		return this.name;
	}
	public String getPhrase(){
		return this.phrase;
	}
	public String getSeries(){
		return this.series;
	}
	
	protected void setName(String name)
	{
		this.name = name;
	}
	protected void setPhrase(String phrase)
	{
		
		this.phrase = phrase;
	}
	protected void setSeries(String series)
	{
		this.series = series;
	}
	
	
	
}
