# ChattingApplication

#1. Introduction
The objective of this project is to give you experience in developing a network application based on the
client/server architecture. Using sockets API, you are required to design a chat application that is
composed of a network of two interconnected chat servers. Once users are connected to one of the chat
servers, they can converse with other users connected to the other chat server.
#2. System Model
The system consists of 4 chat servers connected together. Each chat server can handle multiple clients and
is connected to the other servers.
You should consider two different scenarios:

- Two users connected to the same server chatting with each other. In this case the message will be
directed through their local chat server, no routing is needed.

- Two users connected to two different chat servers chat with each other. In this case, the server of
the initiator client must find a route to the destination server. A routing protocol should be
implemented and used to route the chat message across the network to reach the destination
server.

#2.1 Example
Consider the network below in figure 1 consisting of 4 chat servers. Consider the case if A wants to send
a chat message to B. In this case A will send its message to server S1. S1 will check the destination of this
message (B) and compares it with the list of clients it has. Since B and A are connected to the same server
(S1), thus the message will be forwarded through the server to B directly.
Now let’s consider the case where A wants to chat with C. In this case A will send its message to server
S1. S1 will check the destination of this message (C) and compares it with the list of clients it has. Since
C will not be found at the list of S1, thus the server S1 will forward the message to the server directly
connected to it which is S2 (considered as its default router).The same scenario will happen at S2 and it
will forward the message to S4 where C is found so S4 will deliver the message to C.

#3. Client-Server Functionalities
To be able to understand the application here are the functionalities of the client and the server:
#3.1 Client Functionalities
The client should be able to perform the following functions:

1. Join(name): The client uses this message to log on to the server (i.e., initiate a chat session).The client
must send this message first, before sending any other messages. The member name is any name the
client chooses to be identified by.

2. GetMemberList(): This message asks for a list of all members on the network.

3. Chat (Source, Destination, TTL, Message): The chat message that the user will send to another user.
Any chat message consists of three header fields and a body:

- Source: The id of the sender.

- Destination: The id of the receiver.

- Time To Live (TTL): TTL is a counter which is decremented at each chat server, when it reaches zero the
message should be discarded and an error message should be sent to the sender. The purpose of TTL is
to prevent the message from looping infinitely in the network. The default value for the TTL is set to 2.

4. Quit: This message is used to log off.
#3.2 Server Functionalities
The server should be able to perform the following functions:

1. JoinResponse: This is the response to the join request of the client whether accepted or not. (Note: the
client is only rejected when he registers with a used name).

2. MemberListResponse: This sends the list of all members to the client upon request.

3. Route (Message, Destination): This method sends the message to the targeted destination. The server
should check whether the destination is directly connected to it or not. In the case where the destination
is directly connected to it, then it will directly forward the message. However if the destination is not
found in the list of clients connected to the server, then it will send the message to the other chat server.

#4. Graphical User Interface
The project should be implemented with a graphical user interface allowing users to use your application
with all the functions specified above.
