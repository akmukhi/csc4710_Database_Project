public class NFT 
{
    protected int NFTid;
    protected String name;
    protected String description;
    protected String date;
    protected String link;
    protected int currentOwner;
    protected int price; 

    public NFT(){}

    public NFT(int NFTid)
    {
        this.NFTid = NFTid;
    }

    public NFT(int NFTid, String name, String description, String date, String link, int currentOwner)
    {
        this.name = name;
        this.description = description;
        this.link = link;
        this.currentOwner = currentOwner;
        this.NFTid = NFTid;
    }

    public NFT(String name, String description, String date, String link)
    {
        this.name = name;
        this.description = description;
        this.date = date;
        this.link = link;
    }

    public NFT(int NFTid, String name, String description, String link, int currentOwner, int price)
    {
        this.NFTid = NFTid;
        this.name = name;
        this.description = description;
        this.link = link;
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
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
    public String getLink()
    {
        return link;
    } 
    public void setLink(String link)
    {
        this.link = link;
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
