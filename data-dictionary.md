<h1>data dictionary:</h1>

1. coin : it represent a cryptocurrency coin like 'BTCB' or 'USDT'
2. market : as a String , market represent a pair coin like 'BTC/USDT' or 'DOGE/USDT'
3. symbol : as a String , symbol can refere to two thing 1- iso of a coin like 'BTCB' or 'USDT' 2- also like market, it can refere to a pair coin like 'BTCB/USDT' or 'DOGE/USDT'
4. exchangeCoin : as an entity, it represents the entity of a market for example the market of 'BTCB/USDT' or 'DOGE/USDT'
5. coin : as an entity, it represents the entity of a cryptocurrency coin like 'BTC' or 'USDT'
6. memberWallet: as an entity, it represents the wallet of a specific user on a specific coin, for example an instance of this entity represent the wallet of member with id 2 on 'BTC' coin
7. exchangeOrder: as an entity, it represents the one order of a specific user on a specific market (exchangeCoin), for example  an instance of this entity represents one order of member with id 2 on 'BTCB/USDT' market
8. (order)direction: which is a property of exchangeOrder. this field show the fact that an order is buy order or a sell one
9. (order)type: which is a property of exchangeOrder. this field show the fact that an order is limit_price order or a market_price (fast_matching_order) one