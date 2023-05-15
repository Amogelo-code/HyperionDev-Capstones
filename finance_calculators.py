import math

financial_decision = input("Choose either 'investment' or 'bond' from the menu below to proceed\n:")
financial_decision = financial_decision.upper().lower()

"investment - to calculate the amount of interest you'll earn on interest"
"bond - to calculate the amount you'll have to pay on a home loan"

# the if statement outputs the customers choice and the error message if they did not type anything
if financial_decision == 'investment':
    print("Please answer the following questions related to investment")
elif financial_decision == 'bond':
    print("Request that you answer questions related to bond")
else:
    print("Error. Please choose between investment or bond")

# if the customer selected investment, he/she will be required to enter their own values
# with the type of interest, the customer will be given formulas to fill in with the values they've entered
if financial_decision == 'investment':
    amount = int(input("How much money will you be depositing?"))
    rate = float(input("What would you like your interest rate to be?"))
    years = int(input("Please enter the amount of years you plan on investing:"))
    interest = input("Select between simple or compound interest:")
    print(amount)
    print(rate)
    print(years)
    print(interest)
if financial_decision == 'investment' and interest == 'simple':
    simple = amount * (1 + rate/100 * years)
    print(simple)
elif financial_decision == 'investment' and interest == 'compound':
    compound = amount * math.pow((1+rate/100),years)
    print(compound)

# similar to the investment statement, the customer can enter their respective values
if financial_decision == 'bond':
    house_value = int(input("Enter the current value of your home:"))
    interest_rate = int(input("How much is the interest rate?"))
    months = int(input("How many months will you need to repay the bond?"))
    repayment = ((interest_rate/100/12) * house_value)/(1 - (1+interest_rate) ** (-months))
    print(house_value)
    print(interest_rate)
    print(months)
    print(repayment)

print(financial_decision)


