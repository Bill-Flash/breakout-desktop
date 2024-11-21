# breakout-desktop
Personal assignment of JAVA OOP

Author: Bill-Flash

Time: December 2020

## game description   
Breakout is a classic arcade game. The objective of the game is to destroy all of the bricks at the top of the screen by hitting them with the ball. The player has a limited number of lives and a life is lost whenever the ball (or last ball) drops off the screen. The game can include some bonuses that can change the gameplay. These may be dropped when a brick is destroyed (approximately 1% chance) and will have different effects on the game. When dropped, the bonus will fall down the screen and must be collected by the paddle to be activated. Here are typical examples of the bonuses;

- Multi-ball - Every ball is split into three moving off at slightly different angles from each other
- Wide-Paddle - The paddle doubles in width for a period of time
- Sticky-Paddle - When the ball hits the paddle it does not bounce but sticks and can be started again
by the player
- Laser - The paddle can fire lasers for a short period of time to destroy bricks directly above the paddle

## Gameplay
### Movement
The only action that the player can take is to move the paddle (green rectangle at the bottom of the screen)
to bounce the ball back up towards the bricks at the top of the screen. As a ball is kept in play, over time the
speed of the ball will increase. If a life is lost and we start again with another ball, the speed returns to normal.
You can implement user input either using the keyboard or by using the mouse.
Bounce Angle
When the ball bounces off a brick or wall it bounces off at an angle. This angle is shown in the figure 1a for
when the ball bounces off a wall or the side of a brick and figure 1b for when the ball is hitting a brick from
above or below or hitting the roof.

The calculations that you will have to perform to represent this behaviour will depend on how you represent
the motion of the ball. If you represent the motion using an heading and speed, then you will have to use
functions like tan, atan, atan2, cos and sin to calculate the correct angles and how much the ball should move
in the x and y directions. Your calculations should ensure that the angles represented as a in the diagrams are
the same.
If you represent the motion of the ball as components of a vector (how much it changes in the x direction
and how much it changes in the y direction), then calculating the change in direction from a bounce requires
only changing the sign of one of the components of the vector. If the ball hits something from the side, then
the sign of the x part should change, if the ball hits something from the top or bottom, then the sign of the y
part should change.
Example if motion of the ball can be described by the vector d~ = (3; 4), this means that every step of the
game the ball will change position by x in the x-axis and 4 in the y-axis. If the ball hits a wall (or brick from
the side), then the vector would be changed to d~ = (−3; 4) and so would not be moving left. The ball should
keep moving up or down at the same rate as before the collision.

### Bouncing on Paddle
When the ball hits the paddle things can happen a little differently, the different results are shown in figure 2.
If the ball hits the middle third of the paddle, then the calculations are the same as hitting the top or bottom
of a brick or the ceiling.
If the ball hits the left or right thirds of the angle the ball makes on the way out is change by a random
amount r. The effect of this can be seen in figures 2a, 2d, 2e and 2f.
The effect of this should be that when the ball hits the left side of the paddle, it bounces a little more to
the left and when it hits the right side of the paddle it bounces a little more to the right. The difference (r)
should be a random number between 10 and 30 degrees.
The diagrams show how this random amount is added or subtracted from the angle depending on the
direction the ball is travelling from.

### Levels
When the player completes a level (by destroying all of the bricks) the game is started with a new level with
bricks in a different configuration or shape.
