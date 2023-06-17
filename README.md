# hands_on_test_by_spring

this project is here to show two important things :<br> 
1- the first one is  how to write test<br>
> this project demonstartes how to write test using Mockito and how to write test using spring annotations
> beside that you will get familiar with annotations like @SpringBootTest and @ExtendsWith(SpringExtentions.class)  and also you would know the differences between them 


2- the second one is the differences between integration test and unit test<br> 
> Many people always ask me that: is unit test needed ? can we have unit test for service layer ? (because the nature of service layer is integration), beside that many people get confused that is test for repository layer a unit test or a integration test ? 

so let's go.<br>

## project dictionary
The first thing that every project needs is a dictionary which explain names which are used ini project, so let's have one. <br>
1. coin : it represent a cryptocurrency coin like 'BTCB' or 'USDT'
2. market : as a String , market represent a pair coin like 'BTC/USDT' or 'DOGE/USDT' 
3. symbol : as a String , symbol can refere to two thing 1- iso of a coin like 'BTCB' or 'USDT' 2- also like market, it can refere to a pair coin like 'BTCB/USDT' or 'DOGE/USDT'
4. exchangeCoin : as an entity, it represents the entity of a market for example the market of 'BTCB/USDT' or 'DOGE/USDT'
5. coin : as an entity, it represents the entity of a cryptocurrency coin like 'BTC' or 'USDT'
6. memberWallet: as an entity, it represents the wallet of a specific user on a specific coin, for example an instance of this entity represent the wallet of member with id 2 on 'BTC' coin
7. exchangeOrder: as an entity, it represents the one order of a specific user on a specific market (exchangeCoin), for example  an instance of this entity represents one order of member with id 2 on 'BTCB/USDT' market
8. (order)direction: which is a property of exchangeOrder. this field show the fact that an order is buy order or a sell one
9. (order)type: which is a property of exchangeOrder. this field show the fact that an order is limit_price order or a market_price (fast_matching_order) one
