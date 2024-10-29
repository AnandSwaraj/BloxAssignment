//Files are inside    src/main/java/org/example      folder

Q.1. Mandatory : Elaborate what your internship or academic projects were?
a. What did the system do?
b. What other systems have you seen in the wild like that?
c. How do you approach the development problem?
d. What were interesting aspects where you copied code from Stack Overflow?
e. What did you learn from some very specific copy paste? Mention explicitly some
of them.

Ans:  In internship , I was trained on the basic flows in preTUPS application. 
I was expected to write basic manual test cases for the different scenarios and was asked to run them and ensure that everything works fine. 
Also, I was given tasks (mainly to debugs and code fix small issues (In frontend CSS and roles based issues , and some backend bugs , some simple apis which will fetch lists))

A. preTUPS stands for prepaid TopUP. But its not just a recharging platform. It had several different modules and hierarchy , for eg, Commission Profile module for defining commissions,  transfer rules for defining the rules of a transaction, card group for defining the denominations for transfer . Also it maintained the hierarchy for different type of users in market(Eg. Operator users like SuperDistributor, network admin, staff users) and (Channel users like distributor, retailer, agent.)
It had certain modes of transactions like(O2C - Operator to channel , C2C - Channel to channel ,C2S - Channel to subscriber.)

B. There a Mitra app of Airtel which does the same thing

C. UI :- first I see the FIGMA design, and analyse which kind of FORMCONTROL, FORMARRAYS etc are needed and which api needs to be integrated . Then I design a basic page with the main flow working(mocking the api data if required) . Then I start adding css, side functionalities like reset button, back button , and each and every field that is required.

Backend :  First I read the requirements from the story very clearly , then make a basic request(sample payload) and response(which fields are required) . Then I make a sample query which will fetch those details . Once query is made , then I start making the controller, service and implementation layer code along with exception handling.
After the api is made , I test several scenarios (like response is correct for a valid payload or not) . Also try to implement java 8 features while making apis

D.   I have gone to stackoverflow most of the time , when I was stuck (due to some error, or environment issues)
Eg : Recently I was facing a issue, with heap size while compiling our angular app wit ng serve command.
So I learnt from stackOverFlow, to increase the heap size with the following command
Node --max_old_space_size=8192 node_modules/@angular/cli/bin/ng serve

E. Learning 1 :  finally block is not always executed
I copy pasted a piece of code in which there was a logic to hide the spinner in the finally block. Later I realised that it's not necessary that a finally block will get executed

Learning 2 : String is a Immutable class in java
When I copy pasted and tried to modify that string, I came to know about immutability of String class

Learning 3 : difference between == and .equals


===================================================
Q.2 There is one database. Let’s say it is hosted locally and one of the team members migrates it
to AWS or GCP. How can one confirm that the copied data is the same as the original data ?
What would be the check points ?
Imagine that data from table is of the form : List<Map<String,String>>

Ans : To confirm that the copied data is same as the original data , we can implement multiple checks
1. Counting total no. of rows.
2. Using Random data samples :- We can verify random samples to check for accuracy
3. Matching the Schema and data types:- Checking wether the keys are same 
Eg query :-  SELECT colum_name, data_type,character_max_length, is_nullable FROM information_schema.columns WHERE table_name = 'erp';
4. Hash comparison : 
5. Retrieving and compare data from both databases
Sample query :-SELECT * FROM original_database.users EXCEPT SELECT * FROM copied_database.users;
EXCEPT is used to find any records that exist in the original database but not in the copied database. If the result set is empty, it means both databases have identical data.


==========================================================
Q.3 Write a function to parse any valid json string into a corresponding Object, List, or Map object. You can use C,C++, Java, Scala, Kotlin, Python, Node. Note that the integer and
floating point should be arbitrary precision.

Ans :-I have used Jackson’s ObjectMapper to parse JSON strings into Java objects. For arbitrary precision, Jackson automatically converts JSON numbers to BigInteger and BigDecimal when configured with USE_BIG_DECIMAL_FOR_FLOATS.

For using Jackson, we have add the dependency in pom.xml

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
</dependency>
