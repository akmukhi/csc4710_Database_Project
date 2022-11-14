public class NFT{
	protected int NFTid;
	protected String name;
	protected String description;
	protected String nft; 
	protected int currentOwner;
	protected int price;
	public NFT() {}
	public NFT(int currentOwner)
	{
		this.currentOwner = currentOwner;
	}
	public NFT(String name, String description, String nft, int currentOwner)
	{
		this.name = name;
		this.description = description;
		this.nft = nft;
		this.currentOwner = currentOwner;
	}
	public NFT(int NFTid, String name, String description, String nft, int currentOwner)
	{
		this.NFTid = NFTid;
		this.description = description;
		this.name = name;
		this.nft = nft;
		this.currentOwner = currentOwner;
	}
	public NFT(int NFTid, String name, String description, String nft, int currentOwner, int price)
	{
		this.NFTid = NFTid;
		this.name = name;
		this.description = description;
		this.nft = nft;
		this.currentOwner = currentOwner;
		this.price = price;
	}
	public int getNFTid()
	{
		return NFTid;
	}
	public void setNFTid(int NFTid)
	{
		this.NFTid = NFTid;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getNft()
	{
		return nft;
	}
	public void setNft(String nft)
	{
		this.nft = nft;
	}
	public int getCurrentOwner()
	{
		return currentOwner;
	}
	public void setCurrentOwner(int currentOwner)
	{
		this.currentOwner = currentOwner;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	
}