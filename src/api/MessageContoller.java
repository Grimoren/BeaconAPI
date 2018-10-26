package api;

import static spark.Spark.*;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MessageContoller
{
    public MessageContoller(final MessageService messageService)
    {
        get("/chat/:id", (req, res) -> {

            String id = req.params(":id");
            Message message = messageService.getMessageById(Integer.parseInt(id));
            if (message != null)
            {
                JsonObject json = new JsonObject();
                json.addProperty("username", message.getUserName());
                json.addProperty("text", message.getText());
                json.addProperty("expiration_date", message.getExpirationDate().toString());
                return json;
            }
            res.status(400);
            return "No Message with id:"+ id + " found.";
        });
        get("/chats/:username", (req, res) -> {

            String username = req.params(":username");
            List<Message> messages = messageService.getMessageByUserName(username);
            JsonArray array = new JsonArray();
            if (messages != null)
            {
                for (Message message : messages)
                {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("id", message.getId());
                    obj.addProperty("text", message.getText());
                    array.add(obj);
                }

                return array;
            }
            res.status(400);
            return "No Chats found for username:" + username;
        });
        post("/chat", (req, res) -> {
            int timeout = 60;
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(req.body()).getAsJsonObject();
            if(json.get("timeout") !=null )
            {
                try
                {
                    timeout = json.get("timeout").getAsInt();
                }
                catch(ClassCastException ex)
                {
                    timeout = 60;
                }
            }
            Message message = messageService.createMessage(json.get("username").getAsString(),
                    json.get("text").getAsString(), timeout);

            JsonObject json2 = new JsonObject();
            json2.addProperty("id", message.getId());
            res.status(201);
            return json2;
        });
    }
}
