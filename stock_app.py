
import requests

apiKey = "b31fcc2718da460a83388bca924328c7"


print("Welcome to the stock app!")

while True:
    symbol = input("\nEnter a ticker to search or type 'quit' at anytime to exit:")
    if symbol.lower() == "quit":
        exit("\nThank you for using stock app....Exiting Application)")
    
    URL = f"https://api.twelvedata.com/time_series?symbol={symbol},EUR/USD,ETH/BTC:Huobi,TRP:TSX&interval=1min&apikey={apiKey}"

    try:
        fetchInfo = requests.get(URL).json()

        open_price = fetchInfo[symbol]["values"][0]["open"]
        high_price = fetchInfo[symbol]["values"][0]["high"]
        low_price = fetchInfo[symbol]["values"][0]["low"]
        close_price = fetchInfo[symbol]["values"][0]["close"]

        print(f"\nThe opening price for {symbol} is {open_price}")
        print(f"The high price for {symbol} is {high_price}")
        print(f"The low price for {symbol} is {low_price}")
        print(f"The closing price for {symbol} is {close_price}\n")
    except:
        print(f"\nError {symbol} not found.. please enter another ticker.....")



