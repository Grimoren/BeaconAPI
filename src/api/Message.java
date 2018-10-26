package api;

import java.util.Date;



public class Message
{
    private String userName = null;
    private Integer Id = null;
    
    private String text = null;
    private Date expirationDate = null;
    public Message(String userName,String text,int timeout,Integer Id )
    {
        this.text = text;
        this.userName=userName;
        expirationDate = new Date(new Date().getTime() + (timeout*1000));
        this.Id=Id;
        
    }

    public String getUserName()
    {
        return userName;
    }
    public Integer getId()
    {
        return Id;
    }
    public String getText()
    {
        return text;
    }
    public Date getExpirationDate()
    {
        return expirationDate;
    }
}
