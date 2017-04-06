Who: Joshua Griffiths, Austin Griffith, Caroline Friedman, Galen Pogoncheff

Title: The Pi Host

Vision: Bringing the retro 90's games back to life for everyone to play anywhere anytime because the best games are free.

Automated Tests:

https://cloud.githubusercontent.com/assets/24575285/24729030/a608392e-1a19-11e7-8346-00dd8deb15e5.png

https://cloud.githubusercontent.com/assets/24575285/24729031/a7756c46-1a19-11e7-8ca4-e7a4ef6766c5.png

https://cloud.githubusercontent.com/assets/24575285/24729038/aa0ffad4-1a19-11e7-9d49-a0b6e9e36848.png

https://cloud.githubusercontent.com/assets/24575285/24729044/ac85a91c-1a19-11e7-8020-6e2dd8b6b1c4.png

https://cloud.githubusercontent.com/assets/24575285/24729050/af6ae9c6-1a19-11e7-990a-befcc7ec7310.png

https://cloud.githubusercontent.com/assets/24575285/24729053/b1a20224-1a19-11e7-9023-d0ec1b86c21b.png

User Accpetance Tests:

1)
Use case name:
    Launching N64 games.
Description:
    Launching N64 games from main menu brings you to alternative menu to choose various games that can all be played.
Pre-conditions:
    Rasberry Pi is working and hosting.
Test steps:
    1. Start Rasberry Pi
    2. Provide valid user name
    3. Provide valid password
    4. Click login button
Expected result:
    User should be able to login.
Actual result:
    User is navigated to dashboard with successful login.
Status (Pass/Fail):
    Pass.
Notes:
    N/A.
Post-conditions:
    User is validated with database and successfully signed into their account.
    The account session details are logged in database.
   
   
2)
Use case name:
    Verify login with valid user name and password.
Description:
    Test the Google login page.
Pre-conditions:
    User has valid user name and password.
Test steps:
    1. Navigate to login page
    2. Provide valid user name
    3. Provide valid password
    4. Click login button
Expected result:
    User should be able to login.
Actual result:
    User is navigated to dashboard with successful login.
Status (Pass/Fail):
    Pass.
Notes:
    N/A.
Post-conditions:
    User is validated with database and successfully signed into their account.
    The account session details are logged in database.
    
    
3)
Use case name:
    Verify login with valid user name and password.
Description:
    Test the Google login page.
Pre-conditions:
    User has valid user name and password.
Test steps:
    1. Navigate to login page.
    2. Provide valid user name.
    3. Provide valid password.
    4. Click login button.
Expected result:
    User should be able to login.
Actual result:
    User is navigated to dashboard with successful login.
Status (Pass/Fail):
    Pass.
Notes:
    N/A.
Post-conditions:
    User is validated with database and successfully signed into their account.
    The account session details are logged in database.
