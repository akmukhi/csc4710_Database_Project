

public class nft 
{
		protected int NFTid;
		protected String nftName;
		protected String nftDescription;
		protected int listingPrice;
		protected String listingTime;
		protected String uploadNFT;
		protected String nftOwner;
		protected int active;
	 
	    //constructors
	    public nft() {
	    	
	    }
	    
	    
	    public nft(String nftName)
	    {
	    	this.nftName = nftName;
	    }
	    
	    public nft(String nftName, String nftOwner)
	    {
	    	this.nftName = nftName;
	    	this.nftOwner = nftOwner;
	    }
	    
	    public nft(int NFTid, String nftName, String nftDescription,int listingPrice ,String uploadNFT , String listingTime, String nftOwner, int active)
	    {
	    	this.NFTid = NFTid;
	    	this.nftName = nftName;
	    	this.nftDescription = nftDescription;
	    	this.listingPrice = listingPrice;
	    	this.uploadNFT = uploadNFT;
	    	this.listingTime = listingTime;
	    	this.nftOwner = nftOwner;   	
	    	this.active = active;
	    }
	    
	    
	    public nft(String nftName, String nftDescription,int listingPrice ,String uploadNFT , String listingTime, String nftOwner)
	    {
	    	this.nftName = nftName;
	    	this.nftDescription = nftDescription;
	    	this.listingPrice = listingPrice;
	    	this.uploadNFT = uploadNFT;
	    	this.listingTime = listingTime;
	    	this.nftOwner = nftOwner;   	
	    	this.active = active;
	    }
	    
	    
	    public nft(String nftDescription, int listingPrice,  String uploadNFT, String listingTime, String nftOwner, int active)
	    {
	    	this.nftDescription = nftDescription;
	    	this.listingPrice = listingPrice;
	    	this.uploadNFT = uploadNFT;
	    	this.listingTime = listingTime;
	    	this.nftOwner = nftOwner;
	    	this.active = active;
	    	
	    }

	    public int getNFTid()
		{
			return NFTid;
		}
	    public int getactive()
	  		{
	  			return active;
	  		}
	    public String getnftName() {
	    	return nftName;
	    }
	    public String getNftDescription() {
	    	return nftDescription;
	    }
	    public int getListingPrice() {
	    	return listingPrice;
	    }
	    public String getUploadNFT() {
	    	return uploadNFT;
	    }
	    public String getListingTime() {
	    	return listingTime;
	    }
	    public String getnftOwner() {
	    	return nftOwner;
	    }
	    public void setNFTid(int NFTid)
		{
			this.NFTid = NFTid;
		}
	    public void setactive(int active) {
	    	this.active = active;
	    }
	    public void setnftOwner(String nftOwner) {
	    	this.nftOwner = nftOwner;
	    }
	    public void setnftName(String nftName)
	    {
	    	this.nftName = nftName;
	    }
	    public void setDescription(String nftDescription) {
	    	this.nftDescription = nftDescription;
	    }
	    public void setListingPrice(int listingPrice) {
	    	this.listingPrice = listingPrice;
	    }
	    public void setUploadNFT(String uploadNFT) {
	    	this.uploadNFT = uploadNFT;
	    }
	    public void setListingTime(String listingTime) {
	    	this.listingTime = listingTime;
	    }



	   //getter and setter methods
	   
	}
