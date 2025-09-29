# Current Problems
- Performance problems with amber fluid
- Fluid doesn't stop flowing when placed high up, causing mass reproduction of amber blocks
- too many BE's can cause performance problems
- problems saving the entity correctly
- saving & unsaving bigger entities
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
