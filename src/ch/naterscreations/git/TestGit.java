package ch.naterscreations.git;

public class TestGit
{
	@SuppressWarnings("unused")
	private String gitFromScratch;
	
	public TestGit(String parametre)
	{
		this.gitFromScratch = parametre;		
	}
	
	public String returnString()
	{
		return this.gitFromScratch;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Hello");
	}
	
}
