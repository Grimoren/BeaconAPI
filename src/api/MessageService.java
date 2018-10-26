package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageService
{
    static Integer id = 0;
    static HashMap<String,List<Message>> hotStorage = new HashMap<String,List<Message>>();
    static HashMap<String,List<Message>> coldStorage = new HashMap<String,List<Message>>();
    static HashMap<Integer,Message> allMessage = new HashMap<Integer,Message>();
    private static final Thread expireMessageThread = new ExpireMessageThread();

    public Message createMessage(String userName,String text,Integer timeout)
    {
        
        id++;
        Message message = new Message(userName,text,timeout,id);
        allMessage.put(id,message);
        if(hotStorage.containsKey(message.getUserName()))
        {
            List<Message> userMessages = hotStorage.get(message.getUserName());
            userMessages.add(message);
            hotStorage.put(message.getUserName(),userMessages);
        }
        else
        {
            List<Message> userMessages = new ArrayList<Message>();
            userMessages.add(message);
            hotStorage.put(message.getUserName(), userMessages);
        }
        return message;
    }
    
    public Message getMessageById(int id)
    {
        return allMessage.get(id);
        
    }
    public List<Message> getMessageByUserName(String userName)
    {
        expireMessageThread.run();
        return hotStorage.get(userName);
    }
    
}
