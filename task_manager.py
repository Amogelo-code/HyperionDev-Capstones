import datetime
# The reg_user function allows the user to enter their username and password into the system. After the prompts,
# there are two if statements. The first checks if the username being entered is matched by a previous username that
# has already been saved or not. The second checks if the password matches the password confirmation.
def reg_user():
    with open('user.txt', 'r+') as new_file:
        for line in new_file.readlines():
            username_checker = line.strip().split(', ')
            username = input("Please enter your username:\n")
            password = input("Enter your password:\n")
            confirmation = input("Confirm your password:\n")
            print(username)
            print(password)
            print(confirmation)
            # The conditional statements check if the user's confirmation matches the password.
            # If not, the user will have to re-try their username and password.
            if confirmation == password:
                print("Password successful")
            else:
                print("The password does not match. Please try again")
                password.strip(password)
                return reg_user()

            if username == username_checker[0]:
                print(username_checker[0])
                print("This username already exists! Please type another username")
                username.strip(username)
                return reg_user()
            elif username != username_checker[0]:
                new_file.write(username + ', ' + password + "\n")
                print('username entry successful')
                return option

def add_task():
    with open('tasks.txt', 'a') as add_file:
            date = "27 Feb 2023"
            name_of_user = input("Who is the task assigned to?\n")
            title = input("What is the title of the task?\n")
            description = input("Give a brief description of the task\n")
            assign_date = datetime.strptime(date, "%d %b %Y").strftime("%d %b %Y")
            due_date = input('Provide a due date for your task: ')
            completion = 'No'
            print(name_of_user)
            print(title)
            print(description)
            print(assign_date)
            print(due_date)
            print(completion)
            # the equation had to go onto the next line as it was too long for one line
            add_file.write(name_of_user + ', ' + title + ', ' + description + ', ' + assign_date
                            + ', ' + due_date + ', ' + completion + "\n")
            return option

def view_all():
    with open('tasks.txt', 'r+') as f:
        for line in f.readlines():
            all_tasks = line.strip().split(", ")
            user = all_tasks[0]
            task_title = all_tasks[1]
            task_outline = all_tasks[2]
            date_of_submission = all_tasks[3]
            complete_task = all_tasks[4]
            task_complete = all_tasks[5]
            details = f'''
                 User:               {user}
                 Title:              {task_title}
                 Description:        {task_outline}
                 Task assigned:      {complete_task}                
                 Due date:           {date_of_submission}
                 Task submitted:     {task_complete}
                       '''
            print(details)

def view_mine():
    users_tasks = []
    count = 0
    with open('tasks.txt', 'r+') as menu_option2:
        for line in menu_option2.readlines():
            task_indices = line.strip().split(', ')
            users_tasks.append(line.strip())
            if user == task_indices[0]:
                 count += 1
                 friendly_format = f'''
                    Task number:\t{count}
                    User: \t \t \t{task_indices[0]}
                    Title:\t \t \t{task_indices[1]}
                    Description:\t{task_indices[2]}
                    Task assigned:\t{task_indices[3]}
                    Due date:\t \t{task_indices[4]}
                    Task complete:\t{task_indices[5]}
                                    '''
                 print(friendly_format)
        # Instead of -1, I make the user enter number 4 as -1 printed out another users task
        task_no = int(input('''Select numbers 1,2 or 3 to access your task."To go back to the main menu,
                                select 4:'''))-1

        if task_no == 4:
            return option
        elif task_no < count:
            task_to_edit = users_tasks[task_no]
            print(task_to_edit)
            task_to_edit = task_to_edit.split(', ')
            task_query = input("Is the task complete? Yes/No\n")
            if task_query == "Yes":
                print('Task is complete, you can not edit. You will now return to the main menu')
                return option
            elif task_query == "No":
                task_edit = input('''Choose from the following to edit the task:"
                                  u - username
                                  d - due date 
                                  m - mark the task as complete
                                  : ''')
                if task_edit == 'u':
                    edit_username = input("Please edit your username: ")
                    task_to_edit[0] = edit_username
                    task_to_edit = ', '.join(task_to_edit)
                    users_tasks[task_no] = task_to_edit

                    with open('tasks.txt', 'w') as users_file:
                        for name in users_tasks:
                            users_file.write(name + '\n')
                    return option

                elif task_edit == 'd':
                    edit_date = input('Please edit your due date using this format (e.g 25 Dec 2023): ')
                    task_to_edit[4] = edit_date
                    task_to_edit = ', '.join(task_to_edit)
                    users_tasks[task_no] = task_to_edit

                    with open('tasks.txt', 'w') as users_file:
                        for name in users_tasks:
                            users_file.write(name + '\n')
                    return option

                elif task_edit == 'm':
                    task_complete = 'Yes'
                    task_to_edit[5] = task_complete

                    task_to_edit = ', '.join(task_to_edit)
                    users_tasks[task_no] = task_to_edit

                    with open('tasks.txt','w') as users_file:
                        for name in users_tasks:
                            users_file.write(name + '\n')
                    return option

def total_tasks():
    with open('tasks.txt', 'r') as f3:
        for line in f3.readlines():
            manip = line.strip().split(", ")
            edit = [manip[0]]
            print(manip)
            print(len(edit))

def total_users():
    with open('user.txt', 'r') as file:
        for line in file.readlines():
            manip2 = line.strip().split(", ")
            edit2 = [manip2[0]]
            print(manip2)
            print(len(edit2))

def generate_reports():
    total_tasks = 0
    completed_tasks = 0
    incomplete_tasks = 0
    overdue_tasks = 0

    with open('tasks.txt', 'r') as task_cal:
        for line in task_cal.readlines():
            total_tasks += 1
            task_info = line.strip().split(', ')
            # calculating the amount of tasks in the text file
            task_amount = len(task_info[1])
            # Counting the amount of tasks that have been completed, the tasks that are not and those
            # that are overdue
            if task_info[5] == "Yes":
                completed_tasks += 1
            elif task_info[5] == "No":
                incomplete_tasks += 1
            # finding out how many tasks have been overdue and are incomplete
            todays_date = datetime.datetime.strptime(task_info[4], "%d %b %Y")
            if task_info[5] == "No" and todays_date > datetime.datetime.now():
                overdue_tasks += 1

        # creating calculations that will be added to the Task_Overview Report
        try:
            total_tasks_generated = total_tasks
            tasks_completed = completed_tasks
            tasks_incomplete = incomplete_tasks
            incomplete_and_overdue = overdue_tasks
            percentage_incomplete = incomplete_tasks * 100 / total_tasks
            percentage_overdue = overdue_tasks * 100 / total_tasks
            print(total_tasks_generated)
            print(tasks_completed)
            print(tasks_incomplete)
            print(incomplete_and_overdue)
            print(percentage_incomplete)
            print(percentage_overdue)

        except ZeroDivisionError:
            percentage_incomplete = 0
            percentage_overdue = 0

        # the user overview report below
        task_report = f''' 
                    -----------------------------------------------------------------------------------------------
                                             Task_Overview Report
                    -----------------------------------------------------------------------------------------------
    
                      The total amount of tasks generated:{total_tasks}
                      The total amount of completed tasks:{tasks_completed}
                      The total amount of incomplete tasks:{tasks_incomplete}
                      The total amount of that haven’t been completed and that are overdue:{incomplete_and_overdue}
                      The percentage of tasks that are incomplete:{percentage_incomplete}
                      The percentage of tasks that are overdue:{percentage_overdue}
                    -----------------------------------------------------------------------------------------------'''
        print(task_report)
        with open('task_overview.txt', 'w') as t_overview:
            t_overview.write(task_report)

        # The calculations for the user_overview.txt
        # The first with open block calculates the amount of users
        with open('user.txt', 'r+') as user_cal:
            for line in user_cal.readlines():
                user_manip = line.strip().split(', ')
                user_amount = len(user_manip[0])
                print(user_amount)
        # calculating the total amount of tasks
        with open('tasks.txt', 'r') as task_info:
            tasks_generated = task_info.readlines()
            task_manip2 = line.strip().split(', ')
            task_quantity = len(task_manip2[0])

        # Appending all the usernames
        all_users = []
        with open('user.txt', 'r') as users_file:
            for line in users_file:
                details = line.split(', ')
                all_users.append(details[0])
        with open('tasks.txt', 'r+') as tasks_file:
            raw_data = tasks_file.readlines()
        # creating four different counts for each calculation
        for user in all_users:
            task_amount2 = 0
            finished_task = 0
            unfinished_task = 0
            overdue = 0
            # Checking if the user's task is completed, incomplete or incomplete and overdue
            # so that we can acquire the percentages.
            for task in raw_data:
                if user in task:
                    task_amount2 += 1
                    info_manip = task.strip().split(', ')

                    if info_manip[5] == "Yes":
                        finished_task += 1
                    elif info_manip[5] == "No":
                        unfinished_task += 1
                    elif info_manip[5] == "No" and info_manip[4] > date.today():
                        overdue += 1
        # Getting the total number of tasks from the task text file and users from the user text file
        with open('tasks.txt', 'r+') as task_gen, open('user.txt', 'r+') as user_gen:
            for user in user_gen.readlines():
                user_tasks = 0
                user_complete = 0
                user_incomplete = 0
                user_overdue = 0
                user_manip = user.strip().split(', ')
                for line in task_gen.readlines():
                    task_manip3 = line.strip().split(', ')
                    # Creating conditional statements for each username and their task information
                    if user_manip[0] == task_manip3[0]:
                        user_tasks += 1
                    if task_manip3[5] == "Yes":
                        user_complete += 1
                    elif task_manip3[5] == "No":
                        user_incomplete += 1
                    # Finding out how many tasks have been overdue and are incomplete. The date format is
                    # in a short format e.g. 12 Nov 2022
                    todays_date2 = datetime.datetime.strptime(task_manip3[4], "%d %b %Y")
                    if task_manip3[5] == "No" and todays_date2 > datetime.datetime.now():
                        user_overdue += 1
                try:
                    user_tasks_generated = task_amount2
                    user_percentage = task_amount2 * 100 / task_amount2
                    user_completed = finished_task * 100 / task_amount2
                    user_not_done = unfinished_task * 100 / task_amount2
                    user_incomplete_overdue = user_overdue * 100 / task_amount2
                    print(user_tasks_generated)
                    print(user_percentage)
                    print(user_completed)
                    print(user_not_done)
                    print(user_incomplete_overdue)
                except ZeroDivisionError:
                    user_percentage = 0
                    user_completed = 0
                    user_not_done = 0
                    user_incomplete_overdue = 0

        # The user report below and writing it to the user_overview text file
        user_report = f'''
    
                    -----------------------------------------------------------------------------------------------------
                                                 User_Overview Reports
                    -----------------------------------------------------------------------------------------------------
    
                    The total amount of tasks generated:{total_tasks_generated}
                    The total amount of tasks assigned to user:{user_tasks_generated}
                    The percentage of total amount of tasks from user:{user_percentage}    
                    The percentage of completed tasks from user:{user_completed}
                    The percentage of incomplete tasks from user:{user_not_done}
                    The percentage of the tasks that are incomplete and overdue from user:{user_incomplete_overdue}
    
                    ----------------------------------------------------------------------------------------------------- '''
        print(user_report)
        with open('user_overview.txt', 'r+') as u_overview:
            u_overview.write(user_report)

def display_statistics():
    # The display statistics takes information from the generate reports option
    # and displays it in a user-friendly format.
    total_tasks = 0
    completed_tasks = 0
    incomplete_tasks = 0
    overdue_tasks = 0

    with open('tasks.txt', 'r') as task_cal:
        for line in task_cal.readlines():
            total_tasks += 1
            task_info = line.strip().split(', ')
            # calculating the amount of tasks in the text file
            task_amount = len(task_info[1])
            # Counting the amount of tasks that have been completed, the tasks that are not and those
            # that are overdue
            if task_info[5] == "Yes":
                completed_tasks += 1
            elif task_info[5] == "No":
                incomplete_tasks += 1
            # finding out how many tasks have been overdue and are incomplete
            todays_date = datetime.datetime.strptime(task_info[4], "%d %b %Y")
            if task_info[5] == "No" and todays_date > datetime.datetime.now():
                overdue_tasks += 1

        # creating calculations that will be added to the Task_Overview Report
        try:
            total_tasks_generated = total_tasks
            tasks_completed = completed_tasks
            tasks_incomplete = incomplete_tasks
            incomplete_and_overdue = overdue_tasks
            percentage_incomplete = incomplete_tasks * 100 / total_tasks
            percentage_overdue = overdue_tasks * 100 / total_tasks
            print(total_tasks_generated)
            print(tasks_completed)
            print(tasks_incomplete)
            print(incomplete_and_overdue)
            print(percentage_incomplete)
            print(percentage_overdue)

        except ZeroDivisionError:
            percentage_incomplete = 0
            percentage_overdue = 0
    # Displaying the statistics in a user-friendly format
    with open('task_overview.txt', 'w') as task_overview:
        user_friendly = f'''

                    -----------------------------------------------------------------------------------------------
                                         Task_Overview Report
                    -----------------------------------------------------------------------------------------------
                      The total amount of tasks generated:{total_tasks}
                      The total amount of completed tasks:{tasks_completed}
                      The total amount of incomplete tasks:{tasks_incomplete}
                      The total amount of that haven’t been completed and that are overdue:{incomplete_and_overdue}
                      The percentage of tasks that are incomplete:{percentage_incomplete}
                      The percentage of tasks that are overdue:{percentage_overdue}
                    -----------------------------------------------------------------------------------------------'''
        print(user_friendly)
        task_overview.write(user_friendly)

        # The calculations for the user_overview.txt
        # The first with open block calculates the amount of users
        with open('user.txt', 'r+') as user_cal:
            for line in user_cal.readlines():
                user_manip = line.strip().split(', ')
                user_amount = len(user_manip[0])
        # calculating the total amount of tasks
        with open('tasks.txt', 'r') as task_info:
            tasks_generated = task_info.readlines()
            task_manip2 = line.strip().split(', ')
            task_quantity = len(task_manip2[0])

        # Appending all the usernames
        all_users = []
        with open('user.txt', 'r') as users_file:
            for line in users_file:
                details = line.split(', ')
                all_users.append(details[0])
        with open('tasks.txt', 'r+') as tasks_file:
            raw_data = tasks_file.readlines()
        # creating four different counts for each calculation
        for user in all_users:
            task_amount2 = 0
            finished_task = 0
            unfinished_task = 0
            overdue = 0
            # Checking if the user's task is completed, incomplete or incomplete and overdue
            # so that we can acquire the percentages.
            for task in raw_data:
                if user in task:
                    task_amount2 += 1
                    info_manip = task.strip().split(', ')

                    if info_manip[5] == "Yes":
                        finished_task += 1
                    elif info_manip[5] == "No":
                        unfinished_task += 1
                    elif info_manip[5] == "No" and info_manip[4] > date.today():
                        overdue += 1
        # Getting the total number of tasks from the task text file and users from the user text file
        with open('tasks.txt', 'r+') as task_gen, open('user.txt', 'r+') as user_gen:
            for user in user_gen.readlines():
                user_tasks = 0
                user_complete = 0
                user_incomplete = 0
                user_overdue = 0
                user_manip = user.strip().split(', ')
                for line in task_gen.readlines():
                    task_manip3 = line.strip().split(', ')
                    # Creating conditional statements for each username and their task information
                    if user_manip[0] == task_manip3[0]:
                        user_tasks += 1
                    if task_manip3[5] == "Yes":
                        user_complete += 1
                    elif task_manip3[5] == "No":
                        user_incomplete += 1
                    # Finding out how many tasks have been overdue and are incomplete. The date format is
                    # in a short format e.g. 12 Nov 2022
                    todays_date2 = datetime.datetime.strptime(task_manip3[4], "%d %b %Y")
                    if task_manip3[5] == "No" and todays_date2 > datetime.datetime.now():
                        user_overdue += 1
                try:
                    user_tasks_generated = task_amount2
                    user_percentage = task_amount2 * 100 / task_amount2
                    user_completed = finished_task * 100 / task_amount2
                    user_not_done = unfinished_task * 100 / task_amount2
                    user_incomplete_overdue = user_overdue * 100 / task_amount2
                    print(user_tasks_generated)
                    print(user_percentage)
                    print(user_completed)
                    print(user_not_done)
                    print(user_incomplete_overdue)
                except ZeroDivisionError:
                    user_percentage = 0
                    user_completed = 0
                    user_not_done = 0
                    user_incomplete_overdue = 0
    with open('user_overview.txt', 'w') as user_overview:
        user_friendly2 = f'''
                    -----------------------------------------------------------------------------------------------
                                                 User_Overview Reports
                    -----------------------------------------------------------------------------------------------

                    The total amount of tasks assigned to user:{user_tasks_generated}
                    The percentage of total amount of tasks from user:{user_percentage}    
                    The percentage of completed tasks from user:{user_completed}
                    The percentage of incomplete tasks from user:{user_not_done}
                    The percentage of the tasks that are incomplete and overdue from user:{user_incomplete_overdue}

                    -----------------------------------------------------------------------------------------------'''
        print(user_friendly2)
        user_overview.write(user_friendly2)


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
run = True
while run:
    with open('user.txt', 'r') as users_file:
        user_list = []
        for line in users_file.readlines():
            line_splitter3 = line.strip().split(", ")
            user_list.append(line_splitter3[0])

    with open('user.txt', 'r') as users_file:
        password_list = []
        for line in users_file.readlines():
            line_splitter3 = line.strip().split(", ")
            password_list.append(line_splitter3[1])

    user = input('Username:\n')
    if user in user_list:
        user_index = user_list.index(user)
        # print statement
        password = input('Password:\n')
        if password in password_list:
            password_index = password_list.index(password)
        if password_index == user_index:
            print("Welcome")
            break

while True:
    option = input('''Please select one of the following to continue:
    r - register user
    a - add task
    va - view all
    vm - view mine
    tt - total tasks
    tu - total users
    gr - generate reports
    ds - display statistics
    e - exit
    :''')

    # As shown, the large amounts of code have been replaced by the functions being called in
    # the conditional statements.
    if option == 'r':
        reg_user()
    elif option == 'a':
        add_task()
    elif option == 'va':
        view_all()
    elif option == 'vm':
        view_mine()
    elif option == 'tt':
        total_tasks()
    elif option == 'tu':
        total_users()
    elif option == 'gr':
        generate_reports()
    elif option == 'ds':
        display_statistics()
    elif option == 'e':
        exit()
    else:
        print("You have entered the wrong letter. Please enter the letters r,a,va,vm or e to proceed")

    print(option)