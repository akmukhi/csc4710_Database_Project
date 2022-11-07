public class NFT 
{
    protected int NFTid;
    protected String name;
    protected String description;
    protected String date;
    protected String link;
    protected String author;

    public NFT()
    {

    }

    public NFT(int NFTid)
    {
        this.NFTid = NFTid;
    }

    public NFT(int NFTid, String name, String description, String date, String link)
    {
        this.(name, description, date, link);
        this.NFTid = NFTid;
    }

    public NFT(String name, String description, String date, String link)
    {
        this.name = name;
        this.description = description;
        this.date = date;
        this.link = link;
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
    
}
