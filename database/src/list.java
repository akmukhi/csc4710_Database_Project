import java.sql.Time;
import java.sql.Timestamp;

public class list 
{
    protected int transactionID;
    protected Timestamp date;
    protected int price;
    protected int NFTid;
    protected String name;
    protected String link;

    public list(){}
    public list(int transactionID, Timestamp date, int price, int NFTid)
    {
        this.transactionID = transactionID;
        this.date = date;
        this.price = price;
        this.NFTid = NFTid;
    }

    public list(int transactionID, Timestamp date, int price, int NFTid, String name, String link)
    {
        this.transactionID = transactionID;
        this.date = date;
        this.price = price;
        this.NFTid = NFTid;
        this.name = name;
        this.link = link;
    }
    public list(int transactionID, Timestamp date, int price, int NFTid, String name)
    {
        this.transactionID = transactionID;
        this.date = date;
        this.price = price;
        this.NFTid = NFTid;
        this.name = name;
    }

    public int getTransactionID()
    {
        return transactionID;
    }

    public void setTransactionID(int transactionID)
    {
        this.transactionID = transactionID;
    }

    public Timestamp getDate()
    {
        return date;
    }

    public void setDate(Timestamp date)
    {
        long time = date.getTime();
        date = new Timestamp(time);
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
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

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }
}