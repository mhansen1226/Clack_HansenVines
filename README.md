Project Part 2
Matt Hansen and Matt Vines

Output from TestClackClient

(Added >> to denote user input)

------------------------------

/Library/Java/JavaVirtualMachines/jdk1.8.0_301.jdk/Contents/Home/bin/java - … Clack_HansenVines test.TestClackClient

userName cannot be null
hostName cannot be null
port cannot be less than 1024
--- CC1 --- 
port: 7000
UserName: USERNAME
HostName: HOSTNAME
Close connection? false

getUserName(): USERNAME
getHostName(): HOSTNAME
getPort(): 7000
hashCode(): 55817138

>> SENDFILE src/test/Part2_document.txt
USERNAME:
	A digital computer can usually be regarded as consisting of three parts: (i) Store. (ii) Executive unit. (iii) Control. ...The executive unit is the part which carries out the various individual operations involved in a calculation. ...It is the duty of the control to see that...[the table of] instructions are obeyed correctly and in the right order. ...A typical instruction might say—"Add the number stored in position 6809 to that in 4302 and put the result back into the latter storage position." Needless to say it would not occur in the machine expressed in English. It would more likely be coded in a form such as 6809430217. Here 17 says which of various possible operations [add] is to be performed on the two numbers. ...It will be noticed that the instruction takes up 10 digits and so forms one packet of information...

>> Hello friend
USERNAME:
	Hello friend
>> DONE
USERNAME:
	Hello friend
--- CC2 --- 
port: 7000
UserName: USERNAME
HostName: HOSTNAME
Close connection? false

getUserName(): USERNAME
getHostName(): HOSTNAME
getPort(): 7000
hashCode(): 55817138

>> SENDFILE src/test/Part2_document.txt
USERNAME:
	A digital computer can usually be regarded as consisting of three parts: (i) Store. (ii) Executive unit. (iii) Control. ...The executive unit is the part which carries out the various individual operations involved in a calculation. ...It is the duty of the control to see that...[the table of] instructions are obeyed correctly and in the right order. ...A typical instruction might say—"Add the number stored in position 6809 to that in 4302 and put the result back into the latter storage position." Needless to say it would not occur in the machine expressed in English. It would more likely be coded in a form such as 6809430217. Here 17 says which of various possible operations [add] is to be performed on the two numbers. ...It will be noticed that the instruction takes up 10 digits and so forms one packet of information...

>> Hello again
USERNAME:
	Hello again
>> DONE
USERNAME:
	Hello again
--- CC3 --- 
port: 7000
UserName: USERNAME
HostName: localHost
Close connection? false

getUserName(): USERNAME
getHostName(): localHost
getPort(): 7000
hashCode(): 2010132786

>> SENDFILE src/test/Part2_document.txt
USERNAME:
	A digital computer can usually be regarded as consisting of three parts: (i) Store. (ii) Executive unit. (iii) Control. ...The executive unit is the part which carries out the various individual operations involved in a calculation. ...It is the duty of the control to see that...[the table of] instructions are obeyed correctly and in the right order. ...A typical instruction might say—"Add the number stored in position 6809 to that in 4302 and put the result back into the latter storage position." Needless to say it would not occur in the machine expressed in English. It would more likely be coded in a form such as 6809430217. Here 17 says which of various possible operations [add] is to be performed on the two numbers. ...It will be noticed that the instruction takes up 10 digits and so forms one packet of information...

>> Whats up
USERNAME:
	Whats up
>> DONE
USERNAME:
	Whats up
--- CC4 --- 
port: 7000
UserName: Anon
HostName: localHost
Close connection? false

getUserName(): Anon
getHostName(): localHost
getPort(): 7000
hashCode(): 1530901720

>> SENDFILE src/test/Part2_document.txt
Anon:
	A digital computer can usually be regarded as consisting of three parts: (i) Store. (ii) Executive unit. (iii) Control. ...The executive unit is the part which carries out the various individual operations involved in a calculation. ...It is the duty of the control to see that...[the table of] instructions are obeyed correctly and in the right order. ...A typical instruction might say—"Add the number stored in position 6809 to that in 4302 and put the result back into the latter storage position." Needless to say it would not occur in the machine expressed in English. It would more likely be coded in a form such as 6809430217. Here 17 says which of various possible operations [add] is to be performed on the two numbers. ...It will be noticed that the instruction takes up 10 digits and so forms one packet of information...

>> Hows it going
Anon:
	Hows it going
>> DONE
Anon:
	Hows it going
false
false

Process finished with exit code 0
