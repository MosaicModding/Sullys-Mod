# Current Problems
- Performance problems with amber fluid
- Fluid doesn't stop flowing when placed high up, causing mass reproduction of amber blocks
- too many BE's can cause performance problems (done I think (at least there are less BEs now))
- problems saving the entity correctly (done)
- saving & unsaving bigger entities (done)
- Entities only rotated 90 degrees -> should be better randomized

# Solving the Problems

## Fluid
There was one idea that suggested entirely removing the fluid and only having the raw normal spawning amber.
Then the behavior would be like this:
1. Find raw amber (tree or cave etc)
2. Since you can't pick up the block with a bucket you need to put a cauldron underneath as usual
3. When filled you can pick it up with a bucket as usual, but placing it doesn't place a fluid but a raw amber block instead.

It basically works like powder snow, but with the difference that you can't pickup a placed raw amber block with a bucket again, you can only pick it up from the cauldron
Breaking raw amber would still cause it to drop rough amber ofc.

If we wanted to we could make the cauldron require a heat source underneath it in order to collect the amber drops.

Changing the system to work like this would make amber so much better from a code perspective, because 1. a ticking fluid is a performance hell 
and 2. the amber fluid overall is a pretty flawed system rn.


## Amber Blocks
Now there is the Amber block (transparent) and the solid amber block (also transparent). There still is raw amber with it's building blocks, but we ignore that for now.
The regular amber block is the one you get from the bucket and it's the one you can heat up, so that you can freeze mobs in there. It is only a block no BE!

When an entity is frozen in the amber block, the block turns to solid amber. 
It looks the same, but this one has no collision behavior (is as the name suggests always solid) and this one is a BE.
Breaking solid amber causes all other solid amber blocks (only the ones for this entity) to break as well and the entity is respawned.
As previously, amber in which mobs are frozen (now called solid amber) does not produce liquid particles. --> However this does mean, that you can only get more amber by going back to the caves and build a little amber farm there
Breaking amber or solid amber drops raw amber, even with silk touch.

These changes also mean that the amber features now generate with normal amber and sometimes with solid amber for the frozen entities.
