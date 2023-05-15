# Creating an empty list that will be used in the opening of the text files
empty = []

# Opening the text files using the with block so that they can go through the conditional statement
with open('user.txt', 'r+') as f:
    for line in f.readlines():
        line_splitter1 = line.split(", ")
        empty += line_splitter1

with open('tasks.txt', 'r+') as f2:
    for line in f2.readlines():
        line_splitter2 = line.split(", ")
        empty += line_splitter2

# The login process that will allow the user to access the menu once their
# details have been entered into the system.
# The user can choose from any of the codes below to do/access their work or just exit
while True:
    with open('user.txt', 'r+') as login_file:
        for line in login_file.readlines():
            line_splitter3 = line.strip().split(", ")
            old_user = []
            old_password = []
            user = input("Enter your username:\n")
            password = input("Enter your password:\n")

            if user == line_splitter3[0] and password == line_splitter3[1]:
                print("You are now logged in")
                break
            elif user != line_splitter3[0] and password != line_splitter3[1]:
                print("You can not log in")

    option = input('''Please select one of the following:
r - register user
a - add task
va - view all
vm - view my tasks
tt - total tasks
tu - total users
e - exit
:''')

# Creating conditional statements so that the user can easily access their work
# using empty lists to store the new information that is being inputted.
# In the following conditional statements, each letter has its own purposes
# which will display different results based on the users choice.
# Option r allows the user to register a new user. They will have to input their
# details
    if option == 'r':
        with open('user.txt', 'a') as new_file:
            username = []
            password = []

            username = input("Please enter your new username:\n")
            password = input("Enter your new password:\n")
            confirmation = input("Confirm your password:\n")
            print(username)
            print(password)
            print(confirmation)
            new_file.write("\n" + username + ', ' + password)

        if confirmation == password:
            print("Password successful")
        else:
            print("The password is incorrect. Please try again")


    elif option == 'a':
        with open('tasks.txt', 'r+') as add_file:
            for line in add_file.readlines():
                username = input("Who is the task assigned to?\n")
                title = input("What is the title of the task?\n")
                description = input("Give a brief description of the task\n")
                assign_date = input("When was the task given?\n")
                due_date = input("When should the task be handed in?\n")
                completion = input("Has the task been completed?\n")
                print(username)
                print(title)
                print(description)
                print(assign_date)
                print(due_date)
                print(completion)
                # the equation had to go onto the next line as it was too long for one line
                add_file.write("\n" + username + ', ' + title + ', ' + description + ', ' + assign_date
                               + ', ' + due_date + ', ' + completion)
                break

    elif option == 'va':
        with open('tasks.txt', 'r+') as f:
            for line in f.readlines():
                task_remove = line.strip().split(", ")
                user = task_remove[0]
                task_title = task_remove[1]
                task_outline = task_remove[2]
                date_of_submission = task_remove[3]
                complete_task = task_remove[4]
                task_complete = task_remove[5]
                details = f'''
                User:               {user}
                Title:              {task_title}
                Description:        {task_outline}
                Task complete:      {complete_task}                
                Due date:           {date_of_submission}'''
                print(details)


    elif option == 'vm':
        with open('tasks.txt', 'r+') as f:
            for line in f.readlines():
                task_remove2 = line.strip().split(", ")
                if username == task_remove2[0]:
                    user = task_remove2[0]
                    task_title = task_remove2[1]
                    task_outline = task_remove2[2]
                    date_of_submission = task_remove2[3]
                    complete_task = task_remove2[4]
                    task_complete = task_remove2[5]
                    task = f'''
                    User: \t \t \t{user}
                    Title:\t \t \t{task_title}
                    Description:\t{task_outline}
                    Task complete:\t{complete_task}
                    Due date:\t \t{date_of_submission}'''
                    print(task)

    elif option == 'tt':
        with open('tasks.txt', 'r') as f3:
            for line in f3.readlines():
                manip = line.strip().split(", ")
                edit = [manip[0]]
                print(manip)
                print(len(edit))
                # tu(total users) displays the total amount of users
    elif option == 'tu':
        with open('user.txt', 'r') as file:
            for line in file.readlines():
                manip2 = line.strip().split(", ")
                edit2 = [manip2[0]]
                print(manip2)
                print(len(edit2))


    elif option == 'e':
        print("You have left the menu")
        exit()

    else:
        print("You have entered the wrong letter. Please enter the letters r,a,va,vm or e to proceed")


    print(option)
