# Software Studio Assignment 6


## Explanation of the Design
### Operation
+Clicking on the button "Add All": users can add all the characters into network to be analyzed.
+Clicking on the button "Clear": users can remove all the characters from network.
+Hovering on the character: the name of the character will be revealed.
+By dragging the little circle and drop it in the big circle, the character will be added to the network.
+By pressing the previous and next episode button, users can switch between episodes.


### Visualization
The width of each link is visualized based on the value of the link.

## Implementation
+The implementation is similar to lab8.
+In the beginning, all characters will be construct and given a anchor position.
+When mouse point to a character, the character's method inRegion() will judge if the mouse is in itself and show the name tag. 
+When mouse is pressed, MainApplet's method mouseDragged() will decide which character is being drag. And let the character go with the mouse.
+When mouse release, nework's method insideJudge() will decide which characters are inside the big circle. If it is in the big circle, arrange it in the big circle by cosine and sine. If it is not, go back to its anchor position.
+When the characters are in the circle, draw connection line to its own targets.
+When buttons add all and clear all is pressed, add all characters into the big circle or remove all characters out of the big circle.
+When episode switched, change file path and reload again.




 