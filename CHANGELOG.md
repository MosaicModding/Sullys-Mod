# Changelog version 3.3.1-beta for 1.20.1:

> ## Changes/Updates:
> - Updated ru_ru @AnchorMcLovin
> - Updated de_de
> - Slightly increased the chance of spawning trash in suspicious gravel
>
> ## Fixes:
> - Fixed spawn modification configs not working by switching to double
> - Fixed suspicious gravel loot not spawning

# Changelog version 3.3.0-beta for 1.20.1:
**This version changes several mod features and the mod's config!**
**This will be the last version of Sully's Mod for 1.20.1!**

> ## Changes/Updates:
> - Amber has been rewritten to be more stable. This also changes some amber behaviors, please check the wiki for more information.
> - Primitive knife is now tagged as forge:tools/knives
> - Several artifacts can now be placed on the ground 
> - Artifact flowers can now be placed in a flower pot
> - Some artifacts can now be recycled (furnace, composter, crafting table)
> - Nametags can now be found inside the artifact gravel patches
> - Some broken artifacts can now be repaired
> - Gem Lanterns are now enabled by default
> - The configs for replacing some vanilla mobs with sullysmod variants is now percentage instead of a simple true/false
> - Carnivore behavior on the piranha or wolves now heal the entity slightly
> - You no longer get the jade block notification on world load
> - The jade shield now has a new model
> - Sully's Mod now has a dedicated creative tab and doesn't sort the items into vanilla's tabs anymore. This can be configured in the config
> - Updated ru_ru @anchor_kotvanov
> - Updated uk_ua @unroman
>
> ## Additions:
> - Added bug meat, a rare drop from cave critters and some other mobs.
> - Added new sounds for some jade blocks and the jade shield @JadenXgamer
> - Added several new artifacts
> - Added a new ancient skull
> - Added some new paintings
> - Added a new skeleton variant: The Mauled
> - Added ko_kr @sunbatheproductions
> 
> ## Fixes:
> - Ribbed skull can now be obtained
> - Make advancement for collecting all skulls completable
> - Piglins now try to pick up the golden goblet and golden belt buckle
> - Items on the itemstand now keep their previous state and don't reset enchantments or durability
> - A workaround has been added to prevent some items from not showing up in creative if quark was loaded
> - Fixed some stone cutter inconsistencies
> - Fixed some tag and loot table issues with petrified blocks
> - The throwing knife now rotates in the correct direction
> - Copper buttons are now tagged as pickaxe mineable
> - The rendered hitbox for ancient skulls is now the correct size
> - Botania's mana burst is now also blacklisted for bouncing off jade
> - Jungle Spider now drops spider eyes and string is affected by looting
> - Submerging mobs in amber no longer prevents entity shadows globally
> - Baby tortoise now has the correct size again
> - Fixed a duplication bug when using "Carry On"
> - Fixed crashes when using the Sounds mod
> - Throwing knives can no loner hit endermen
> - Fix a startup crash @evanbones
> - Death whistle now plays the same sound for all players
> - Fix missing texture particles when shift-polishing
> - Fix a deadlock on worldload when using dynamic resources in modern fix


# Changelog version 3.2.1-beta for 1.20.1:
> ## Changes/Updates:
> - update zh_cn translation thanks to @0Starocean0
> - rarity for artifacts can now be accessed by kubejs. The identifier is `sullysmod:ancient`
> - change color values for artifact rarity and artifact description
> - change artifact spawn rates slightly
> - item stands can now display block items
> - item stands no longer display armor items as their models, and instead render the item model
> - item stands now use the item's rarity color for rendering the item name
>
> ## Additions:
> - add quark chest textures
> 
> ## Fixes:
> - fix missing translation for petrified sign, hanging sign and amber fluid
> - fix server crash on startup
> - fix crash with amber
> - fix entities flickering through all variant textures added by quark when the entity is in amber
> - fix entities thinking they're in amber when picked up with carry on
> - fix some recipe advancements
> - fix potions being disabled by our selection system by default
> - fix not all amber blocks being able to fill cauldron

# Changelog version 3.2.0-beta for 1.20.1:
### IMPORTANT: This version removes the compat features we had and removes several jade blocks. Please back up your worlds.
### Also, this version is still not compatible with NeoForge!!! - NeoForge compat will come with 1.21

> ## Changes/Updates:
> - Removed some Jade Blocks
> - Renamed all remaining Jade Blocks/Items
> - Updated Jade and Lanternfish textures
> - Updated the builtin mob_retextures pack. (now includes skeleton horse and spider)
> - Several language files were updated
> - Removed geckolib as a dependency
>
> ## Additions:
> - **Added Selection System:**
>   - _Almost all features can be enabled or disabled through our config now. Please reload the game after changing the config!_
> - **Added Amber:** 
>   - _Amber is found in Petrified Trees and can be used to trap entities or items inside when heated up._
>   - _When amber is broken it drops rough amber, which can be used to craft several decoration blocks. These blocks can also be heated up, but you cannot freeze entities inside them._
>   - _When amber is heated up, it will spawn amber particles, which can be collected in a cauldron. When the cauldron is full, you can take the fluid out with a bucket and place it in the world._
>   - _As long as the amber fluid is not being heated it will slowly start to turn into the normal amber blocks (the ones that can be used to trap mobs)_
> - **Added Petrified Trees:** 
>   - _They spawn underground together with patches of amber._
>   - _They can only be mined and stripped with a pickaxe and suspicious gravel can be found around it, which can contain several items one of which is a petrifed sapling._
>   - _This sapling can only be placed on gravel and can grow without light._
>   - _The wood can be crafted into their wood variants, but you cannot craft a boat with it._
> - something echoes from up here
> - **Added Piranha:** 
>   - _The Piranha is a new fish spawning in Jungles and Mangrove Swamps._
>   - _It attacks in groups and is only aggressive to small mobs and mobs that were hurt (only organic mobs count). Additionally, it can attack boats._
>   - _When the piranha kills a mob that would drop meat, the meat is consumed and only the other loot will drop._
>   - _Zombies that are killed by the piranha will turn into skeletons._
>   - _When killed it can sometimes drop a piranha tooth, which can be crafted into throwing knifes._
> - **Added Throwing knife:** 
>   - _The throwing knife is a new stackable weapon that can be thrown. It deals extra damage to any mob that is currently in the air._
> - **Added Tortoise Workstations:** 
>   - _It is now possible to place a crafting table or a jukebox on top of a tortoise or a tortoise shell._
> - **Added Item Stand:** 
>   - _Can be used to display items in your world. It is similar to an armor stand. When looking at an item stand it will also display the name of the item it's holding._
> - **Added Artifacts:**
>   - _Artifacts can be found all around the world buried in suspicious gravel._
>   - _Different artifacts have different rarities_
>   - _They can be collected and displayed, or they can be sold the Wandering Trader for a good amount of emeralds._
>   - _We also added several advancements for collecting the artifacts._
> - **Added Ancient Skulls:**
>   - _Ancient Skulls are a specific type of artifact. They are big skulls of partly extinct animals which can be worn._
>   - _Some skulls can be placed on a note block to make the note block play a sound of this animal._
>   - _Ancient Skulls count as Skeleton heads so they reduce detection range for skeletons._
> - **Added Gem Lanterns: (experimental)**
>   - _Gem Lanterns are lantern blocks made from gems like diamonds or emeralds. They are purely for decoration._
>   - _This feature is currently disabled by default and needs to be enabled in the config to use. Please tell us if you like this feature._
> - Added config option to make wolves consume the meat that would drop when they kill an entity (disabled by default)
> - something watches from below
> - Added a new music disc called 'Sunken Past' which can drop from the Elder Guardian
> - Added a bunch of new paintings. (Some were made in a community event)
> - Added the ability to brew resistance potions with a Tortoise Scute
> - Added sound for clicking copper buttons
> - Added Dev and Contributor Capes as part of MosaicCosmetics 
>
> ## Fixes:
> - Fixed projectile impact actions being processed when hitting a Flinger Totem (e.g. egg spawns chicken, splash potions splash)
> - Fixed Crash with the Bouldering Zombie Spawner
> - Fixed Jade not being tagged as gemstone
> - Fixed Bouldering Zombie not rendering armor or items
> - Fixed Bouldering Zombie spawning on mushroom island
> - Fixed audio files