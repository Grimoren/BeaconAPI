# BeaconAPI
Ephemeral Chat function using Java Spark API end points
To run:
Either run executable jar: BeaconApi.jar (java - jar BeaconApi.jar) and run a post request either through eclipse like in step 4

1.Import project into eclipse as a maven project
2.Add all files in the libs directory  to  the build path
3. Run Main.java
4. To run a Post request to insert new chats run SendPostRequest.java
5. To do a get request:
    a.open the browser.
    b.navigate http://localhost:4567/chat/1(or which ever function you would like to test)

Implementations decisions:
I used sparkjava framework in order to implement all the api endpoints.
The framework is small and rather effective to implement something this small
I used rest-assured in order to send post request (the decision was arbitrary, I just picked the first functional framework).
Storage for the messages is in HashMaps of either username,message or id,message.

The limitations:
Once the application is terminated all messages are deleted.
A thread is run to move messages from hotStorage to coldStorage which may cause issues if multiple calls chats/username is called.

What I would do if I had more time:
Most likely convert the hashmap into less volatile storage like a database. 
Change the host name to have a more meaningful name instead of localhost:4567
Possible design and implement a front end to make it look much better than raw JSON.

Scaling:
Move Messages to a Database
Run on a dedicated server(like an aws ec2 instance)
Improve expiration  process of messages by making it more thread safe

Add features like sending it to other users, a login(authentication) system.

