# hands_on_test_by_spring

## Goal
This project is here to show two important things :<br> 
1- the first one is  how to write test<br>
> this project demonstartes how to write test using Mockito and how to write test using spring annotations
> beside that you will get familiar with annotations like @SpringBootTest and @ExtendsWith(SpringExtentions.class)  and also you would know the differences between them 


2- the second one is the differences between integration test and unit test<br> 
> Many people always ask me that: is unit test needed ? can we have unit test for service layer ? (because the nature of service layer is integration), beside that many people get confused that is test for repository layer a unit test or a integration test ? 

so let's go.<br>

## project dictionary
The first thing that every project needs is a dictionary which explain names which are used ini project, so let's have one <a href="https://github.com/sajadShahsavari1377/hands_on_test_by_spring/blob/master/data-dictionary.md">here</a>. <br>


## hands on unit testing and integration testing
Let's ask some important questions first then answer it: <br>
1. what is unit test definition?
2. what is integration test definition?
3. can we have unit test for a layer which is integrated by the nature (like service layer)?
4. if we have many integration tests, is unit test needed (people ask this question because they think integration test has unit test in itself) ? if yes, then why?

Now follow me to answer these questions:<br>
### 1-Unit test is a test for smallest testable unit of project:<br>
<p>
 Not a bad definition but is not complete. Service layer is not the smallest testable unit of my project,
 but I still need unit test for that.<br>

 so then, what is the complete definition? In my opinion, unit test is a test which you need for every unit of your project
 but independent of other components (what do you mean by that ?!)<br>.
 Suppose you have an ExchangeOrderService which is a service layer class.
 A process like adding an ExchangeOrder has many dependency to other components or services of our project, as a good example,
 to check the balance of the member, it is depended on MemberWalletService.<br>
 So now how can you how unit test for this class?
 what is the solution? is that needed to have unit test for such a component? why ?
 to address this problem, we get help from a concept which is Mocking, which help us to test our project units independent of other units.
 now let's imagine our 
 
</p>


