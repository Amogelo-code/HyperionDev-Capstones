from tabulate import tabulate
# the Shoes class that consists of a constructor and three methods
class Shoes:
    def __init__(self,country,code,product,cost,quantity):
        self.country = country
        self.code = code
        self.product = product
        self.cost = float(cost)
        self.quantity = int(quantity)

    def get_cost(self):
        return self.cost

    def get_quantity(self):
        return self.quantity

    def __str__(self):
        print(f'the value of the shoe is, {self.cost}.The amount of stock we have is {self.quantity}')


# An empty list that will be used for the next four functions
shoe_list = []


# The read_shoes_data function uses the try and except block. It will therefore append the
def read_shoes_data():
    temp_list = []
    try:
        new_file = open('inventory.txt','r+')
    except FileNotFoundError:
        print('The file does not exist')
        new_file.close()

    for line in new_file:
        data = line.split(',')
        shoe_object = [data[0],data[1],data[2],data[3],data[4]]
        temp_list.append(shoe_object)

    del temp_list[0]

    for item in temp_list:
        new_shoe = Shoes(item[0], item[1], item[2], item[3], item[4])
        shoe_list.append(new_shoe)


# The capture_shoes function will intake data and append it to the shoe list variable
def capture_shoes():
    country = input('Which country is the shoe from?\n')
    code = input('Enter the code of the shoe:\n')
    product = input('What is the name of the shoe?\n')
    cost = float(input('Enter the price of the shoe:\n'))
    quantity = int(input('How much stock is left of the product?\n'))
    shoe_data = Shoes(country=country,code=code,product=product,cost=cost,quantity=quantity)
    shoe_list.append(shoe_data)


# the view all function allows the user to see all information about the shoes
def view_all():
    table_list = []

    with open('inventory.txt','r+') as file:
        for line in file.readlines():
            line_splitter = line.strip().split(',')
            table_list.append([line_splitter[0], line_splitter[1], line_splitter[2], line_splitter[3], line_splitter[4]])
    print(tabulate(table_list, headers='firstrow', tablefmt='fancy_grid'))


# The re-stock function takes the shoe with the lowest quantity and allows for
# the user to update the quantity of the shoe.
def re_stock():
    lowest_quantity = shoe_list[0]
    # print(lowest_quantity)
    for shoe_data in shoe_list:
        if shoe_data.quantity < lowest_quantity.quantity:
            lowest_quantity = shoe_data
    print(lowest_quantity.product, lowest_quantity.quantity)
    update_query = input('Would you like to update stock?')
    if update_query == 'Yes':
        try:
            update_amount = int(input('Enter the stock that will be added:'))
            lowest_quantity.quantity += update_amount
            print(shoe_data.product, shoe_data.quantity)
            old_list = ''
            for text in shoe_list:
                old_list += f'{text.country},{text.code},{text.product},{text.cost},{text.quantity}\n'
            print(old_list.strip())
            with open('inventory.txt','w') as file:
                file.write(old_list)
        except ValueError:
            print('please type a positive number to continue')
    elif update_query == 'No':
        return main_menu


# The user will be able to search for the shoe using the code
def search_shoe():
    shoe_code = input('Enter the code of the shoe:')

    for item in shoe_list:
        if item.code == shoe_code:
            print(item.product,item.code)


# The value per item function checks the total price for all shoes in the text file.
def value_per_item():
    for price in shoe_list:
        value = int(price.cost) * int(price.quantity)
        print(price.product, price.cost, price.quantity, value)


# This function checks for the shoe with the largest stock and putting it on sale
def highest_quantity():
    highest_qty = shoe_list[0]
    for shoe_data in shoe_list:
        if shoe_data.quantity > highest_qty.quantity:
            highest_qty = shoe_data
            print(f'The {shoe_data.product} shoe is on sale!')


# Creating a while loop and within it lies a menu for all the functions regarding shoe information
while True:

    main_menu = int(input('''Please choose from the following to continue:
1. read shoes data
2. capture shoes
3. view all
4. re-stock
5. search shoe
6. value per item
7. highest quantity
:'''))
    # the main menu consists of the functions being called as the functions are above the menu
    read_shoes_data()
    if main_menu == 1:
        read_shoes_data()
    elif main_menu == 2:
        capture_shoes()
    elif main_menu == 3:
        view_all()
    elif main_menu == 4:
        re_stock()
    elif main_menu == 5:
        search_shoe()
    elif main_menu == 6:
        value_per_item()
    elif main_menu == 7:
        highest_quantity()