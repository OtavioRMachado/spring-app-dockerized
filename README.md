# Readme

## Running the Application
I created two different applications using Java 8, Springboot, Gradle and MongoDB, mainly out of familiarity with this tech stack.

First of all, you need to run the build to generate the Jar files. To run the build, you will need `Gradle` installed in your machine.
After you install it, you should enter each project specific root directory `survey-service` and `dashboard-service` and run on each

        gradle build

Then, if you have docker installed in your machine, you can run the application using 

	docker-compose up dashboard-service
	
Dashboard service should be running on port `8090`. Survey Service should be running on port `8080`

–––––––––––––––––––

### Dashboard service has the following endpoints:

#### /fetch (GET)
Goes to all survey systems defined in the properties and calls their `/export` endpoint, expecting to receive a collection of Surveys. Then, persists everything inside mongo, in the **dashboarddb** document.

#### /find (GET)
Gets all surveys saved in the database.

–––––––––––––––––––

### Survey service has the following endpoints:

#### /new (POST)
Receives a JSON representing a Survey and persists it in its database, in the **surveydb** document.

#### /export (GET)
Fetches all Surveys inside its database and returns it as a list.


–––––––––––––––––––

### The survey representation object follows the example below:

```
{
        "name": "Otávio",
        "age": 27,
        "city": "Porto Alegre",
        "platform": "MacOS",
        "rating": 5
}
```

### Running the Tests
To run the tests, you should have `Gradle` installed in your machine. 

After you install it, you should enter each project specific root directory `survey-service` or `dashboard-service` and run

	gradle test
	
The test suite contains both unit and integration tests separated from each other. Integration tests start the Spring Context whereas Unit tests do not. Both of them mock external dependencies.


## Main considerations and thought process
I felt that the code challenge was meant to take more than six hours. I had to simplify the challenge, in a way that is not filtering anything in dashboard service, and only runs one instance of survey service (even though it would be easy to add more instances to that) and I had to simplify my decision making in order to be able to deliver something that, at least, worked.

In an ideal world, I would be developing the Survey Service with a language like Clojure, that is optimal for data processing. My first thought for the Survey Service persistence layer was to use an in-memory database such as Redis, but I got stuck with a problem trying to use its Java Driver, Jedis, so I switched to MongoDB, that I feel works fine for this situation, and it would work best in a real world situation. 

As for the Dashboard service, I was thinking of using Golang – where you can run goroutines in parallel –, but I feel JavaRX would help here as well. For the database, I tried using ElasticSearch for the first time, as I felt it would fit best for this situation – where we want to filter data as fast as we can –, but as I started looking for how to integrate that, it felt that it would take at least a couple of hours to do it, so I switched to mongo, as well, just as a way to deliver in the defined timeframe (6 hours of work).

I developed both application following an Evolutionary Architecture mindset, separating input and output from the business logic and trying to integrate between those layers with the help of interfaces, in a way that would be easy to change any input or output source.

I created the whole configurations from the scratch, and, As I have been a while away from creating Java Services, it took me a while set up the baseline for the projects. 

For the dockerization, I found an image that already dealed with boostraping a Spring App. The docker-compose was made from scratch as well.

## Next Steps
As I started giving up on some things in order to deliver something usable, I took notes on everything that I could do better, if I had the time. They are:

- Running the build and tests inside a docker container so people don't need to have anything (well, besides docker) installed in their machine – and it would be easier to integrate with a CI pipeline and move to Continuous Delivery;
- Cleaning the survey-service database after /export has been called, or adding an information on all the surveys that have already been served before.
- Better error handling: I feel that I used the most of what Spring brings out of the box, but I would love some more readable error handling;
- Creating some sort of Circuit breaker for the /fetch, so we can handle possible temporary failures from the third party services;
- Configuring an in-memory database instead of mocking it for integration tests;
- Make the Survey object more flexible (accept partial information or ignore not used information);
- Separate classes that are used in both apps in a library;
- Integrate Dashboard-service with ElasticSearch;

As for if these applications would grow, I would also add:

- Integrate with a profiler or a monitoring tool, to understand if there is a bottleneck, or if there are many requests failing for a specific third party survey service;
- Start using logging to make debug easier;
- Add different properties files for different environments;
- Separate integration tests run from unit tests;
- Create performance and journey tests;
- Think about having a microservice to fetch data from all third party systems, translate them to an agnostic language and send them via Messaging
- Grow into a more DDD oriented format. Standard MVC organization works great, but as soon as we involve different business contexts and logic, it can get messy.