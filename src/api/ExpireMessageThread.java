package api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpireMessageThread extends Thread
{
    public ExpireMessageThread()
    {
    }

    public void run() {

        if (MessageService.hotStorage.size() > 0)
        {
            for (List<Message> messages : MessageService.hotStorage.values())
            {
                for (int i = 0; i < messages.size(); i++)
                {
                    Message message = messages.get(i);
                    if (messages.get(i).getExpirationDate().before(new Date()))
                    {
                        messages.remove(message);
                        if (messages.size() == 0)
                        {
                            MessageService.hotStorage.remove(message.getUserName());
                        }
                        else
                        {
                            MessageService.hotStorage.put(message.getUserName(), messages);
                        }
                        if (MessageService.coldStorage.containsKey(message.getUserName()))
                        {
                            List<Message> messageList = MessageService.coldStorage.get(message.getUserName());
                            messageList.add(message);
                            MessageService.coldStorage.put(message.getUserName(),messageList);
                        }
                        else
                        {
                            List<Message> messageList = new ArrayList<Message>();
                            messageList.add(message);
                            MessageService.coldStorage.put(message.getUserName(),messageList);
                        }
                        i--;// since this message was removed a new message will be on the same index
                    }
                }
            }
        }
    }
}
