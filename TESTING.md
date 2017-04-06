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
    Verify website functionality.
Description:
    Testing the home website for ThePiHost.
Pre-conditions:
    Rasberry Pi is working and hosting.
Test steps:
    1. Go to The Pi Host url.
    2. Navigate through provided pages on website.
    3. Click Download link to get package.
Expected result:
    User should be able to successfully navigate through website pages of wesite and download and open provides package.
Actual result:
    User is able to navigate through website locally. Although, download link is not active.
Status (Pass/Fail):
    Fail.
Notes:
    Need to fix hosting from raspberry pi.
    Download link will be active once newest games are finished.
Post-conditions:
    User obtains a copy of game package.
    After download link is click, user is directed to installation instructions on website.
   
   
2)
Use case name:
    SpaceDefender game testing.
    
Description:
    Testing that game functionality and graphics work.
    
Pre-conditions:
    User has game downloaded locally.
    
Test steps:
    1. Launch game (maually or through main menu).
    2. click on game screen.
    3. Press space-bar to play.
    4. Play Game (directions provided during game startup).
    
Expected result:
    User should be able to play game, without any glitches
    
Actual result:
    User is able to play game.  Although, slight glitches in "level-up" system.
    
Status (Pass/Fail):
    Pass.
    
Notes:
    We must find the bugs that give rise to the "level-up" glitches.
    
Post-conditions:
    N/A.
    
 3)
Use case name:
    SpaceDefender game testing.
    
Description:
    Testing that game functionality and graphics work.
    
Pre-conditions:
    User has game downloaded locally.
    
Test steps:
    1. Launch game (maually or through main menu).
    2. click on game screen.
    3. Press space-bar to play.
    4. Play Game (directions provided during game startup).
    
Expected result:
    User should be able to play game, without any glitches.
    
Actual result:
    User is able to play game.  Although, slight glitches in "level-up" system.
    
Status (Pass/Fail):
    Pass.
    
Notes:
    We must find the bugs that give rise to the "level-up" glitches.
    
Post-conditions:
    N/A.
    
    
3)
Use case name:
    Emulator testing and game functionality.
    
Description:
    Testing that the emulator successfully downloads and installs on user computer.  Testing that all provided game files are playable.
    
Pre-conditions:
    User has successfully followed directions to install emulator before launching it from main menu system.
    
Test steps:
    1. User downloads emulator locally.
    2. Follow instructions provided to install emulator and associated game files.
    3. Boot emulator and follows menu screen to desired game.
    4. Plays game of choice.
    
Expected result:
    User should be able play games on emulator without any issues.
    
Actual result:
    User is able play games on emulator without any issues.
    Emulator and game files not yet downloadable from website.
    
Status (Pass/Fail):
    Pass.
    
Notes:
    N/A.
    
Post-conditions:
    N/A.
