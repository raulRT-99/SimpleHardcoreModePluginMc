
# Simple Hardcore Mode
This is my very first plugin for Minecraft, I hope you enjoy it!  
I inspired in another discontinued plugin named <a> [Hardcore Beta](https://www.spigotmc.org/resources/hardcore.118580/) </a> from <a>[Dianelito](https://github.com/Dianelito)</a>
------------------------------------------------------------------------------
With this plugin you can:

- Manage how many lives you and all the player who join the server can have before get kicked
- See in any moment how many lives each player have with a simple command
- Add X lives to any player in game
- Reset someone to default lives
- If you want to do permaban or just for a certain duration you can configure that on config file

--------------------------------------------------------------------------

Config.yml

#Language of commands message (only the player who execute commands can see this)   
#eng - English  
#es - Español  
    `lang: "eng"`

**************************************************************************************************
#Messages to be shown on each event  
#To show the player name use %player%  
#To show the default lives use %defaultLives%  
#To show current lives of the player use %lives%  
#To show the remaining ban time use %banTime%  
#--------------------------------------------------------------------------------------------------------  
#You can modify the text color using the following pattern before the text you want to change color  
| Code   | Color         |
| ------ | ------------- |
| §0     | Black         |
| §1     | Dark blue     |
| §2     | Dark green    |
| §3     | Dark aqua     |
| §4     | Dark red      |
| §5     | Dark purple   |
| §6     | Gold          |
| §7     | Gray          |
| §8     | Dark gray     |
| §9     | Blue          |
| §a     | Green         |
| §b     | Aqua          |
| §c     | Red           |
| §d     | Light purple  |
| §e     | Yellow        |
| §f     | White         |

#This message only appears the first time a player join the server  
`welcome-message: "§aWelcome to the hardcore mode server. Your only have §6%defaultLives%§a lives"`  
  
#Message to be shown when a player lost all of his lives (only the player can see this)  
`kick-message: "§2You lost all of your lives"`  
  
#Message to be shown when a banned player tries to join the server  
`ban-message: "§4You can not enter the server because you lost all of your lives"`  
  
#This message is sent to all the server when a player get banned by lost of his lives  
`broadcast-message: "§aThe player §e%player%§a has lost all of his lives"`  
  
#This message appears each time a player joins the server (only the player can see this)  
`enter-message: "§aHello again §e%player%§a, you have §e%lives%§a lives left"`  
  
#Message to be shown to the player that loose a life (if it has more than 1 life)  
`lost-life-message: "§aYou lost a life, your new life's count is: §e%lives%"`  
  
#**************************************************************************************************  
#Ban config  
  
#Lives to have each player by default at the time joins the server  
`default-lives: 3`  

#If true there are not ban time, the ban is permanent, otherwise set to false
`permaban: true`

#Ban time if permaban option is set to false. Set to -1 to omit
`ban-time-minutes: -1`
`ban-time-hours: -1`
`ban-time-days: -1`
