# mining-telegram-notifier
 Telegram notifier for your mining rig. Can be used for mining Pools like etherminer.org.
 Will send notification over a Telegram BOT if Hashrate decrease or if rig goes offline.

## Setup
- PoolApi -> Pool API URL (ex. https://api.ethermine.org)
- HashrateGap -> Defines the GAP in % when the notification starts. If the reported Hashrate goes down (amount in %) a notification will be sent to telegram. 
- PollingInterval -> Polling interval for the Pool API.
- MiningAddress -> Mining Address. Dont use the prefix 0x for ETH Addresses.
- TelegramApi -> Telegram API URL.
- TelegramToken -> Telegram BOT Token. 
- TelegramChatId -> Your Chat ID where the BOT sents messages.

