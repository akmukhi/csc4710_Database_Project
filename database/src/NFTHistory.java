import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class NFTHistory 
{
    protected int event;
    protected int user;
    protected int NFTid;
    protected String transactionDetails;
    protected String transaction;
    protected Timestamp date;
    public NFTHistory(){}

    public NFTHistory(int event, int user, int NFTid, String transactionDetails, String transaction, Timestamp date)
    {
        this.event = event;
        this.user = user;
        this.NFTid = NFTid;
        this.transactionDetails = transactionDetails;
        this.transaction = transaction;
        this.date=date;
    }
    public int getEvent()
    {
        return event;
    }
    public void setEvent(int event)
    {
        this.event = event;
    }
    public int getUser()
    {
        return user;
    }
    public void setUser(int user)
    {
        this.user = user;
    }
    public int getNFTId()
    {
        return NFTid;
    }
    public void setNFTId(int NFTid)
    {
        this.NFTid = NFTid;
    }
    public String getTransactionDetails()
    {
        return transactionDetails;
    }
    public void setTransactionDetials(String transactionDetails)
    {
        this.transactionDetails = transactionDetails;
    }
    public String getTransaction()
    {
        return transaction;
    }
    public void setTransaction(String transaction)
    {
        this.transaction = transaction;
    }
    public Timestamp getDate()
    {
        return date;
    }
    public void setDate(Timestamp date)
    {
        this.date = date;
    }


    
    
}
