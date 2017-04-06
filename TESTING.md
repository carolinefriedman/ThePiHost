Who: Joshua Griffiths, Austin Griffith, Caroline Friedman, Galen Pogoncheff

Title: The Pi Host

Vision: Bringing the retro 90's games back to life for everyone to play anywhere anytime because the best games are free.


Screenshots of Automated Test Results for SpaceDefender game:

https://cloud.githubusercontent.com/assets/24575285/24729030/a608392e-1a19-11e7-8346-00dd8deb15e5.png

https://cloud.githubusercontent.com/assets/24575285/24729031/a7756c46-1a19-11e7-8ca4-e7a4ef6766c5.png

https://cloud.githubusercontent.com/assets/24575285/24729038/aa0ffad4-1a19-11e7-9d49-a0b6e9e36848.png

https://cloud.githubusercontent.com/assets/24575285/24729044/ac85a91c-1a19-11e7-8020-6e2dd8b6b1c4.png

https://cloud.githubusercontent.com/assets/24575285/24729050/af6ae9c6-1a19-11e7-990a-befcc7ec7310.png

https://cloud.githubusercontent.com/assets/24575285/24729053/b1a20224-1a19-11e7-9023-d0ec1b86c21b.png

Screenshots of Automated Test Results for SpaceDefender game:

https://github.com/carolinefriedman/ThePiHost/blob/AutomatedTesting/tennisTestingScreenshots/TennisClassTesting_Screenshot.png?raw=true

https://github.com/carolinefriedman/ThePiHost/blob/AutomatedTesting/tennisTestingScreenshots/PlayerPaddleClassTesting_Screenshot.png?raw=true

https://github.com/carolinefriedman/ThePiHost/blob/AutomatedTesting/tennisTestingScreenshots/ComputerPassleClassTesting_Screenshot.png?raw=true

https://github.com/carolinefriedman/ThePiHost/blob/AutomatedTesting/tennisTestingScreenshots/BallClassTesting_Screenshot.png?raw=true


Tool used for Automated Testing: Cucumber - JVM (https://cucumber.io/)
    
  The automated tests we wrote are to be run with the cucumber jvm framework.  Feature files for these tests can be found in the 'AutomatedTesting' branch.  Each feature is to be run as a cucumber feature to initiate the automated testing.  The feature files that test the classes and methods of the spaceDefenders game can be located in AutomatedTesting/cucumberTesting/src/{class_name}.  The feature files that test the classes and methods of the tennis game can be located in AutomatedTesting/AutomatedTennisTesting/src/{class_name}.  For more information about running the tests, contact Galen.


User Accpetance Tests :

1)
Use case name :
    Verify website functionality and hosted files are able to be downloaded with no error.
    
Description :
    Testing the home website for ThePiHost.
    
Pre-conditions :
    Rasberry Pi is working and hosting files sucessfully.
    
Test steps :
    1. Go to The Pi Host url.
    2. Navigate through provided pages on website to download page.
    3. Click Download link to get package.
    4. Sucessfully see zip file in downloads folder on user machine.
    
Expected result :
    User should be able to successfully navigate through website pages of wesite and download and open provided package.
    
Actual result :
    User is able to navigate through website locally. Although, download link is not active.
    
Status (Pass/Fail) :
    Fail.
    
Notes:
    Need to fix hosting from raspberry pi.
    Download link will be active once newest games are finished.
    
Post-conditions :
    User obtains a copy of game package.
    After download link is click, user is directed to installation instructions on website.
   
   
2)
Use case name :
    SpaceDefender game testing.
    
Description :
    Testing that game functionality is fully opperational and that all graphics work without glitching or errors.
    
Pre-conditions :
    User has game downloaded locally.
    
Test steps :
    1. Launch game (manually or through main menu).
    2. click on game screen.
    3. Press space-bar to play.
    4. Play Game (directions provided during game startup).
    
Expected result :
    User should be able to play game, without any glitches
    
Actual result :
    User is able to play game.  Although, slight glitches in "level-up" system.
    
Status (Pass/Fail) :
    Pass.
    
Notes :
    We must find the bugs that give rise to the "level-up" glitches.
    
Post-conditions :
    N/A.
    
3)
Use case name :
    tennis game testing.
    
Description :
    Testing that game functionality is fully opperational and that all graphics work without glitching or errors.
    
Pre-conditions :
    User has game downloaded locally via the complete download from website as a zip file.
    
Test steps :
    1. Launch game (manually or through main menu).
    2. click on game screen.
    3. Play Game (directions provided during game startup).
    
Expected result :
    User should be able to play game, without any glitches.
    
Actual result :
    User is able to play game.
    
Status (Pass/Fail) :
    Pass.
    
Notes:
    N/A.
    
Post-conditions :
    N/A.
    
    
4)
Use case name :
    Emulator testing and game functionality.
    
Description :
    Testing that the emulator successfully downloads and installs on user computer with provided directions from website.  Testing that all provided game files and directories are playable with no faults.
    
Pre-conditions :
    User has successfully followed directions to install emulator before launching it from main menu system.
    
Test steps :
    1. User downloads emulator locally.
    2. Follow instructions provided to install emulator and associated game files.
    3. Boot emulator and follows menu screen to desired game.
    4. Plays game of choice.
    
Expected result:
    User should be able play any of the games included in the download on emulator without any issues.
    
Actual result :
    User is able play games on emulator without any issues.
    Emulator and game files not yet downloadable from website.
    Directions have not yet been provided on website.
    
Status (Pass/Fail) :
    Pass.
    
Notes :
    N/A.
    
Post-conditions :
    N/A.
