###build API using new model
 + connect to DB2 and create service for hearing
 + add persistence for hearing
 + add spring boot
 + build hearing API endpoint (resource) for hearing table
    + no documents
+ show in swagger (add to existing swagger)
 + add unit tests
 + debug the API (walkthrough with Shahid)
 + create same structure as legacy POC
 + advantages of having one big api , with different modules, or separate containers?
 + add business rule

###build json forms UI
+ build form for the hearing service
    + retrieve a hearing
    + add a new hearing
    + update an existing hearing
 + add redux
 + no json rules

###add cucumber
  + goal is to specify functinonal tests
  + explore ways to automate them 
	- QA person creates cucumber definitions
	- QA or Dev implements and automates them
	
	
### Questions
1. why do we have entities modeled in "interfaces"?
2. how will we get around shared code? No "api-core"