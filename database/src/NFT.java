import java.sql.Timestamp;

public class nft 
{
		protected int NFTid;
		protected String nftName;
		protected String nftDescription;
		protected int listingPrice;
		protected Timestamp listingTime;
		protected String uploadNFT;
		protected String nftOwner;
		protected String url;
	 
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
	    
	    
	    public nft(int NFTid, String nftName, String nftDescription,int listingPrice ,String uploadNFT , Timestamp listingTime, String nftOwner, String url)
	    {
	    	this(nftDescription,listingPrice,uploadNFT,listingTime,nftOwner, url);   	
	    	this.nftName = nftName;
	    }
	    
	    public nft(String nftName, String nftDescription,int listingPrice ,String uploadNFT , Timestamp listingTime, String nftOwner, String url)
	    {
	    	this(nftDescription,listingPrice,uploadNFT,listingTime,nftOwner, url);   	
	    	this.nftName = nftName;
	    }
	    
	    public nft(String nftDescription, int listingPrice,  String uploadNFT, Timestamp listingTime, String nftOwner, String url)
	    {
	    	this.nftName = nftName;
	    	this.nftDescription = nftDescription;
	    	this.listingPrice = listingPrice;
	    	this.uploadNFT = uploadNFT;
	    	this.listingTime = listingTime;
	    	this.nftOwner = nftOwner;
	    	this.url = url;
	    	
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
	    public String getURL() {
	    	return url;
	    }
	    public Timestamp getListingTime() {
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
	    public void setListingTime(Timestamp listingTime) {
	    	long time = listingTime.getTime();
	        listingTime = new Timestamp(time);
	    }
	    public void setURL (String url)
	    {
	    	this.url = url;
	    }



	   //getter and setter methods
	   
	}