import java.sql.Time;
import java.sql.Timestamp;

public class statistics 
{
 protected int Buys;
 protected int Transfers;
 protected int Sells;
 protected int NFTs;

    public statistics(){}
    public statistics( int Buys, int Transfers, int Sells, int NFTs)
    {
        this.Buys = Buys;
        this.Transfers = Transfers;
        this.Sells = Sells;
        this.NFTs = NFTs;

    }

    public int getBuys()
    {
        return Buys;
    }

    public int getTransfers()
    {
        return Transfers;
    }



    public int getSells()
    {
        return Sells;
    }

    public int getNFTs()
    {
        return NFTs;
    }



}
