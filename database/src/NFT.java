public class nft 
{
		protected int NFTid;
		protected String nftName;
		protected String nftDescription;
		protected int listingPrice;
		protected String listingTime;
		protected String uploadNFT;
		protected String nftOwner;
	 
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
	    
	    public nft(int NFTid, String nftName, String nftDescription,int listingPrice ,String uploadNFT , String listingTime, String nftOwner)
	    {
	    	this.NFTid = NFTid;
	    	this.nftName = nftName;
	    	this.nftDescription = nftDescription;
	    	this.listingPrice = listingPrice;
	    	this.uploadNFT = uploadNFT;
	    	this.listingTime = listingTime;
	    	this.nftOwner = nftOwner;   	
	    }
	    
	    
	    public nft(String nftName, String nftDescription,int listingPrice ,String uploadNFT , String listingTime, String nftOwner)
	    {
	    	this.nftName = nftName;
	    	this.nftDescription = nftDescription;
	    	this.listingPrice = listingPrice;
	    	this.uploadNFT = uploadNFT;
	    	this.listingTime = listingTime;
	    	this.nftOwner = nftOwner;   	
	    }
	    
	    
	    public nft(String nftDescription, int listingPrice,  String uploadNFT, String listingTime, String nftOwner)
	    {
	    	this.nftDescription = nftDescription;
	    	this.listingPrice = listingPrice;
	    	this.uploadNFT = uploadNFT;
	    	this.listingTime = listingTime;
	    	this.nftOwner = nftOwner;
	    	
	    }

	    public int getNFTid()
		{
			return NFTid;
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