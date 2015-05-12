/**
 * Created by silvia on 06/05/15.
 */

angular.module('steamDataApp').controller('MainCtrl', function ($scope, $http, _, $location, SteamDataService) {

    $scope.searchText = '';

    $scope.games = [{appId: 1, name: 'Game 1' , getsUpdated : false}, {appId: 2, name: 'Game 2' , getsUpdated : true}];

    var init = function(){
        $scope.getGames();
    };

    //$scope.game = {
    //    "id": 9165,
    //    "created": 1431177305169,
    //    "appId": 226840,
    //    "getsUpdated": true,
    //    "name": "Age of Wonders III",
    //    "data": [
    //        {
    //            "id": 7,
    //            "created": 1431177325839,
    //            "price": 39.99,
    //            "tags": [
    //                "Great Soundtrack",
    //                "4X",
    //                "Tactical",
    //                "Strategy",
    //                "Anime",
    //                "Female Protagonist",
    //                "Grand Strategy",
    //                "Turn-Based Strategy",
    //                "Turn-Based",
    //                "Hex Grid",
    //                "Action",
    //                "Adventure",
    //                "Fantasy",
    //                "Classic",
    //                "RTS",
    //                "Multiplayer",
    //                "Co-op",
    //                "RPG",
    //                "Atmospheric",
    //                "Singleplayer"
    //            ],
    //            "reviews": [
    //                {
    //                    "id": 110,
    //                    "created": 1431177325840,
    //                    "authorId": 0,
    //                    "author": "2 scrakes 1 cup",
    //                    "content": "I didn't like this at first, played it for a few hours and was upset that i wasted more money on a ?????? game. Decided to give it another go and found out that I loved this game, great buy for the midweek madness that I got it on.",
    //                    "peopleSeen": 8,
    //                    "peopleHelpful": 6,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 118,
    //                    "created": 1431177325841,
    //                    "authorId": 0,
    //                    "author": "Nasarog",
    //                    "content": "Age of Wonders 3, what can I say that hasn't already been said. I am a late convert to the game. The original release didn't inspire me. The Golden Realms expansion, though great, didn't interest me. The hype of Eternal Lords got me to look and it's implementation got me to stay and play. A fantastic fantasy war game that reminds me a lot of Warhammer Fantasy Battle, AoW 3 is a fantastically fun battle simulator. Is it a perfect game? No. It has some shortcomings, but it's getting better all the time. My favorite Race are the Tigrans; fast, blood thirsty and feline. My favorite specilization: Arch Druid because of the summons and the cool units. Least favorite.. BONE DRAGONS!!!!! Anyways, to get more, come check out our official review at eXplorminate4x.com",
    //                    "peopleSeen": 9,
    //                    "peopleHelpful": 8,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 108,
    //                    "created": 1431177325840,
    //                    "authorId": 0,
    //                    "author": "Fancy Fish",
    //                    "content": "AGE OF WONDERS III - Good Turn based tactical combat, Hero RPG elements and Empire building 4X strategy || 8 || Score Comment Graphics 8 Nice graphics and detail on the strategic map and even better graphics for units, terrain and magical effects in combat. UI is nice, detailed and easy to use Audio 6 Music gets a little repetitive - maybe a little more variety needed, sound effects are good but nothing special. Gameplay 8 Age of Wonders III is a turn based, 4X strategy game based in a fantasy setting. The tactical combat view (when your army's battle) is excellent, you really feel like you are head to head with the AI (which seems to do a good job most of the time) in a battle of wits. Casting a well timed spell or outmanoeuvring your enemy can sometimes turn the tide of a battle, unfortunately usually the outcome of a battle is a forgone conclusion due to the relative strength of the units involved, but even when this is the case its still fun to crush your enemy and minimise your losses. The strength of the Units in your army really plays such a large part of the battles that you can actually choose to skip the tactical battle entirely and have the computer automatically calculate the results. Some of the units have somewhat of a balance issue with the higher level units often being able to take down whole armies by themselves with little damage or risk. This inevitably results in a race to upgrade your city buildings to create these higher level creatures. AI at the strategic level is a little predictable but there is still fun to be had placing your cities and grabbing as many resources as possible to fund your economy. One thing I noticed at the strategic level is there doesn't seem to be any downside to city spamming (covering the map in loads of your cities) as you gain resources (mana, gold, research) for each city. Ruins, shrines and artefacts are scattered throughout the maps and these will often require you to fight a monster to claim the reward for your Hero. Hero's are your army leaders you get a main one and then can hire more for gold. Hero's have a selection of spells they can use to aid you in battle, summon monsters or benefit your cities. Items you collect / make can be given to your hero's to increase their stats and they also get skills as they earn xp through battles. Story 7 The game contains a number of well written scenario's which are fairly fun to play through. Overall I think I preferred playing the random maps though. Replayability 7 If you enjoy the game you will probably want to play it again, random maps and steam workshop adds more community made content. Overall 8 A fan of this genre will probably get a lot of enjoyment out of this game. While not as complex or detailed as others of the same type what it does it does well. Hotseat mode (a mode I loved from Civilisation) is a great addition to this game and playing with friends is a lot of fun. More reviews by Fancy Fish Agree / Disagree? Please leave me a comment below If you liked this review or want to see more recommended games, be sure to follow our curator group: Follow Original Curator Group Check out the Original Network Groups. Win free games, make new friends on Steam, & more! Original Curators Group, Original Traders Group, & Original Giveaways Group",
    //                    "peopleSeen": 139,
    //                    "peopleHelpful": 113,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 121,
    //                    "created": 1431177325841,
    //                    "authorId": 0,
    //                    "author": "Infernal Machine [NGC]?",
    //                    "content": "A fantastic strategy game that will appeal to anyone who loves the genre. The game is a 4X similar to Civilization and Master of Magic, but it takes heavy cues from Heroes of Might and Magic in that the world is littered with goodies thrown about to plunder and neutral enemies guarding them, as well as customisable hero units that you can recruit and level. The best thing about it though, is the tactical battle system. This is nothing new, as Master of Magic, Heroes of Might and Magic, as well as the original Age of Wonders games, all did this a long time ago, but here it feels so refined. Terrain has a big impact, units have cool active and passive abilities, and the strategic choices that you make in battle all feel meaningful. On top of this, the game has great variety. Not only do you have a selection of races to choose, all with their own tech trees and units, but you also have character classes and traits to choose for your leader. Your leader's class and traits will determing what extra special units you can produce, as well as the spells you have access to. The combination of both race and class makes mixing and matching fun to do, and allows you to have much more variety if you get bored of a certain setup, yet still like certain elements of your previous games. The expansions are absolutely worth it if you enjoy the game already. The Necromancer class added by Eternal Lords is amazingly fun, and plays entirely differently than the other classes, with a much more agressive play style that emphasises growing your cities and armies through combat. Over all, Age of Wonders III is one of the best in the genre to date.",
    //                    "peopleSeen": 6,
    //                    "peopleHelpful": 5,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 116,
    //                    "created": 1431177325840,
    //                    "authorId": 0,
    //                    "author": "Jean-Luc",
    //                    "content": "Disclaimers: - I was a closed beta tester for the base game as well as its two expansions. However I'm not an employee of Triumph Studios, only a community volunteer. - This review was written post Eternal Lords and 1.5 patch releases. Age of Wonders III is a turn based hex based fantasy 4x game that is a lot more combat focused compared to something like the Civilization series but also quite a bit more strategically and tactically involved than let's say Heroes of Might & Magic which can be both good or bad depending on your personal preferences. Gameplay Compared to the previous games AoWIII retains most of the series' fundamentals but also iterates and adds to the formula. The most notable change is the way yor faction is presented via the class system. Your research tree and unit roster are not only determined by spell school and starting race picks but by your leader class (warlords, sorcerer, archdruid, dreadnaught, etc.), starting race as well as specializations which are akin to the previous game's spell spheres and leader skills. Your base unit list is thus determined by the race of the towns you own as well as your chosen class. Comparing it to AoWII the class system in a way separates the racial aspect of a faction from the gameplay theme of it. For example, in AoW2 Elves were always Druids with nature themed units, Archons were always Theocrats with their religious and holy theme, Dwarves were Dreadnoughts due to their technological prowes, Orcs were Warlords with strong physical combatants and so on. The AoWIII class system separates race and theme so Elves can be steampunk-ish technologists, Dwarves can wield druidic powers while Goblins can be religious crusaders. Thematically this allows you to create both stereotypical combinations (wizard elves) as well as some unusual ones (holy angelic goblins). Gameplay wise the trio of race, class and specialization (additional mini tech trees) allows for a myriad of different play styles and strategies and a very diverse meta game which encourages experimentation and greately enhances replayability. Each class comes with a unique tech tree which consists of special class units which can have their own racial variations as well as a large number of combat or strategic spells and passive upgrades that enhance your empire as well as units. Customization AoWIII features a leader creation process which allows for detailed aesthetic and gameplay customization. Visual options are akin to rpg games like Dragon Age allowing for changes to race, gender, clothing, accessories, hair style and color, skin color, eyes, hats masks, postures, background scenery, portrait poses, coat of arms, etc. Gameplay-wise the options include race, class and 3 slots for specializations that can be magic oriented or more mundane empire upgrades. Game settings include numerous tweaks to the random map generator like map type (land, continents, islands), map size (s, m, l, xl), geographical settings (types of terrain and climate, existence of the underground layer), starting town/army, amount of resources, treasure sites, independent monsters, etc. Furthermore various game parameters can be tweaked like the number of heroes, hero level cap, hero race, game pace, starting resources, turn progression (classic i-go-you-go or simultanious), empire quests, map events, victory conditions, type and number of opponents, preference for manual or auto-combat and so on. Much effort was made to cater to different preferences and play styles. Combat Like in Heroes of Might & Magic for example, combat in AoWIII happens on a separate tactical map that is derived from the participating units' position on the strategic map. If the units were standing on a snowy arctic hex the tactical map will also have snow on it for example. Same with forests, swamps, etc. Not only that but various sites like cities, farms, dungeons, mines, tombs, etc. are all represented on the tactical map and can have different effects like providing obstacles, choke points, magical effects and so on. Compared to AoWII combat in AoWIII has less randomness and is more dependent on the player making the right moves. Attacks will always hit for example (except under special circumstances) but the damage can still vary and there is the possibility of criticals or fumbles. There are many things to keep an eye on in combat like move points (usually the less you have the less attacks you get but not always), limited retaliations, defensive stances, attacks of opportunity, flanking attacks, distances, unit position and facing, range, the presence of obstacles or city walls, types of movement (walking, floating, flying) and so on. And this is without taking into account unit stats (physical attack vs. defence, magical/elemental attack vs. resistance), traits, special abilities, morale (which influences critical chance), combat spells, unit and battlefield enchantments, unit types, etc. The basics of combat are simple. Click on a unit, right click to attack an enemy unit. But is the enemy unit far enough to trigger the charge ability, can I flank it, what's the chance of my special effect triggering, what elemental protections does it have, how many retaliations does it have remaining, does it have first strike, is it a polearm unit and will my cavalry take extra damage, can I weaken it prior to attack and how, can I kill it, will my unit survive the next turn after it attacks...there are many questions you could end up asking yourself. Learning about the units, what abilities they have and how they can be used and combined for maximum effectiveness is necessary for mastering the game but learning can be achieved in gradual steps. AoWIII retains the adjacency rule of previous games so all armies that are adjacent to the hex being attacked will also participate in combat which can result in some pretty large battles. Thankfully animation speed in battle can be adjusted during combat. Units and heroes that perform well and survive will accumulate experience points and become stronger acquiring new abilities as they level up and grow into valued veterans. Strategy On the strategic level you build up your empire by settling or capturing new cities and expanding your borders in order to bring more and more resources under your control. There are resource sites to be captured, treasure sites to be explored, artifacts to be found, neutral creatures, cities and enemy playes to be subdued. All in all the world is a dangerous place and venturing forth requires combat worthy armies and leaders. Luckily there is a beautiful cloth map to be zoomed out to in order to get an overview of the situation. Resource gathering and exploration primarily serve the goal of building up armies that can defeat the dangers that lie beyond your borders. A new addition here compared to AoWII are the creature dwellings which function as minor races that offer unique units for hire as well as their own sets of buildings. These dwellings can expand your unit roster and provide unexpected tactical and strategic options. Both creature dwellings and independent cities can be outright conqured or negotiated with towards peaceful annexation by vooing them with money or performing quests. How you treat various towns and races can determine their attitude towards you as well as your alignment and diplomatic relations. Another addition is that clearing out and controlling certain treasure sites can unlock special unit or town upgrades in cities controlling those sites which can make some cities more valuable than others. All in all after 618 hrs of playing I have not yet experienced everything this game has to offer, so many untried combinations and strategies, which is probably the biggest endorsement I can give.",
    //                    "peopleSeen": 7,
    //                    "peopleHelpful": 6,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 115,
    //                    "created": 1431177325840,
    //                    "authorId": 0,
    //                    "author": "Strategikal",
    //                    "content": "Although Age of Wonders III features story based campaigns and scenarios (many of which are obviously balanced for multi-player), the real meat of the game IMO is the Random Map mode. This is equivalent to the default mode of most 4X games like Civilization, and that's what I'll talk about here. Unlike most 4X games which start you off with a single settler in a mostly empty undeveloped world, the default mode (normal game flow) of AoW3 starts you off in a populated world which already has roads, cities and armies. In these games you can get stuck into the action quickly and may not need to build any cities at all, just capture existing ones. These games can be a lot quicker to play than other 4X games. However, the random map games are extremely customisable, much more than Civ. There are three other preset modes - Battle, Empire Building and Adventure - which provide very different game experiences. Better still, go into the advanced setup screens and you can tweak the details to your heart's content. You can start with nothing more than a settler and a leader in a completely empty world, just like Civ, if that's what you want, or you can fill it with as much or as little of the different options that are available. You can also tweak the geography, altering the quantities of different terrain types that will appear on the map. The graphics are superb, they blow away the old AoW games and are better than most current 4X games IMO. The music is excellent too, one of the best soundtracks I've heard in a game, making it well worth getting the Deluxe version for the 2 hours of music, not to mention the huge 8 player scenario Dragon's Throne. The huge variety of units in the game, and the many different unit abilities and spells, provide for an awesome variety of strategy and tactics. The game is far more detailed than previous versions, and while the older games were great in their own right, AoW3 takes it to a whole new level. I can't go back to the old games anymore. It's a game you can get sucked into for many, many hours of your life. The two expansion packs - Golden Realms and Eternal Lords - are real expansions, not shallow cash grabbing DLCs like in many games these days. They add a lot of real content, not just new races and campaigns, but many new rules and game mechanics. I highly recommend this game, one of the best 4X games ever in my opinion.",
    //                    "peopleSeen": 41,
    //                    "peopleHelpful": 39,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 119,
    //                    "created": 1431177325841,
    //                    "authorId": 0,
    //                    "author": "lusciouslanguidity",
    //                    "content": "A welcome addition to the franchise, with some new additions and some drawbacks. Pros: +More strategic layers than previous Age of Wonders games with Race/Specialization/Class combinations +Unique balance of over-world construction, exploration, conquering/destruction, and tactical battles +Strong RPG elements for a strategy game with Heroes and Leaders' leveling and meaningful choices along the way +Interesting strategic variety with interaction of terrain and races +Many and meaningful strategic options/choices in units (skills, abilities, looks, interactions) and cities (city types, upgrades) and empire quests +Relatively easy to use map editor/maker Cons: -Less friendly fire (I enjoyed how friendly fire was handled in previous games) and height advantages -Less atmosphere than age of wonders games through slightly weaker lore, presence of magic, racial uniqueness, and music/sfx -Story is a bit weak -3D Models are a bit ugly -Less races (Currently) than in other age of wonders games Differences from other 4x Strategy-Fantasy Games: -More tactical battle focus -Less focus on construction The Future: The community is strong and kind enough. The developers are continuously working on the game and listening to the players. New races and classes are coming out. New race relations coming out. New balances and bonuses coming out. More modding coming out. Buy or No? If you are experienced with 4x strategy games... I would get this game. While the genre probably could use a game to revolutionize it a bit, this game does enough new things to keep your interest if you are captivated by 4x genre. You may be annoyed with some things here and there, but overall the balance is strong enough and they are working on improving the balance and adding more content to sink your teeth into. If you are interested in fantasy lore, role-playing/story-making, primarily and racial interactions... I would get it on sale or wait. Do some research on the game first; if you find the lore interesting, purchase it if you haven't played any game in the genre or wait to see how the updates improve and add to the game in a few months. Keep in mind you can also create some interesting quests with the map editor/maker. If you haven't really played much strategy 4x fantasy games and are curious... I would get this game now or on sale. Your enjoyment of the game will be higher if you have an open-mind and like intricate game systems. If you are looking for a fast-paced, mind-blowing, competitive game... do not purchase the game or do more research. The pacing is similar to other strategic games like Civilization. Also, there is nothing in here that will take your breath away, at least immediately. The engagement relies on the stories and histories that are created from the complex systems and events that occur. Overall... AOW3 is a quality game that could use some improvements. Fortunately, the developers care so improvements are on the way. There isn't too much else in the genre being made at the moment either and is better than its nearest fantasy-strategy competitors. But feel free to check out games like Warlock, Fallen Enchantress, and Endless Legend as they offer slightly different fare if tactical combat is not entirely for you.",
    //                    "peopleSeen": 32,
    //                    "peopleHelpful": 24,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 106,
    //                    "created": 1431177325839,
    //                    "authorId": 0,
    //                    "author": "asmodeus",
    //                    "content": "The sequel to one of the best fantasy turn-based strategies that ever hit the digital world. Still great, still addictive, still includes Dire Penguins (which are Dedicated to Evil). Makes me rather sad that the development is already coming to an end due to disappointing sale figures. Shame on you, people. Shame on you.",
    //                    "peopleSeen": 12,
    //                    "peopleHelpful": 8,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 124,
    //                    "created": 1431177325841,
    //                    "authorId": 0,
    //                    "author": "Graye",
    //                    "content": "It feels a lot like Heroes of Might & Magic had a baby with Master of Magic. I HIGHLY suggest reading some of the reviews here. The negatives aren't all bashers, the positives aren't all fanboi's. You'll get a good picture of what this game offers and where it is lacking. This is my take. Pro's: The hex-7 combat. You can flank prior to engaging, but you may leave an army vulnerable to attack (it affects placement in the battlefield.. except against a city) The hero \"Leader\" capabilities. Each army (group of 6 units including hero) can have a hero as a leader. Heros can pick from abilites to strenghten themselves or provide army-wide bonuses. The Dreadnaught Class (while overall not high on my personal list) is really neat. They are mech-based, rather than magic. If you use their higher special units, you need other special units to heal them. This matters most if your empire included multiple races. Healers can't heal machines, and workers can't heal normal units. I like that concept. :reusgreed: Spell options are \"random\" which helps split up the monogamy of game play (with a few exceptions). Underground & above ground, + teleporters, water tunnels, and 6v6 \"dungeons\" with unique item (Strong, Epic, Legendary, and Mythical difficulties. :ghlol: ) The graphics. Con's: The basic race & class units are carbon copies of each other. Slight differenced being pure damge vs a resistance-based damage type) The buildings are all exactly the same (except the T2 & T3 class buildings.. but the resource from them isn't a game changer). The spell system is lacking, in comparison to Master of Magic. There are obvious \"winners\" for large maps/games, which are to be avoided in smaller/shorter games. As above, there are several abilities/spells which only have true functionally in MP... thus wastes a spell slot. Again, as above, the spells are generated \"randomly\" based on your leader choices. While not entirely bad (MoM was like that also), the MultiPlayer-biased or large map-biased abilities aren't balanced out of the choices. Likewise, they don't recive more weight in those circumstances. So that's really just one big one, split into 3 parts and the last being a pro & con. -------- I've not gotten the xpac.. but that's supposed to add a few more nice things to the game. Worth the expansion price? Not sure yet. Next sale though.. ;) There are a few things they could do differently, from a deisgn standpoint, but given the origins and overal gameplay.. It's definitely a good $20-30.00 game. Not sure about $40-45 (not counting the expansion) Apparently there is a bug that can crash and lose all of your data. I haven't run into that one yet. It does have occasional \"lag\" on the large + maps. I defenitely enjoy it, so far, I'm just a bit picky about things. -------- What they could do to \"fix\" some of the holes in the game: Make a couple of \"difficult\" races, that are OP'd, but cost more. Make a couple of \"easy\" races, that are weak, but fast to max out. Mix up the complexity of the city structures. Maybe Orcs only get a shrine, not a grand temple, etc... then balance it out other ways. (can tie into above) Get rid of the \"Knowledge Points\" resource. It becomes 100% worthless at some point in the game. :angrytitan: Merge \"Mana Income\" and \"Knowledge Points\" and let the player balance the resource between the two. (mana income if dor spell upkeep, unit purchase, initial spell cast, etc.. Knowledge Points only go to how fast you research spells) Unbalance the spell system some more. Let a Mastery level always yield a really bad@$$ unit and global power. Weigh out some of the spells that favor different game maps and MP vs SP. There shouldn't be a clear \"winner\" for them, on that front. (These are in the Class-specific spells, regardless of your focuses, and the one focus realm of expansionisim.) If a balance can't be reached easily.. simply making a counterpart (with a note on each as to what they favor) that is equally beneficial in the opposite playstyle is fine.",
    //                    "peopleSeen": 18,
    //                    "peopleHelpful": 13,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 125,
    //                    "created": 1431177325842,
    //                    "authorId": 0,
    //                    "author": "Luna",
    //                    "content": "This game deserves a thumbs up for being a fun strategy based game. Very well thought out. Storyline is great and so are the graphics. Highly recommend as a single player game. However, I had every intention to play this game as coop. That's where the thumbs down comes into play. Even with good connection...we were unable to host our own server using multi-player mode. And there are really not a lot of servers to join. Most are password protected. The ones that aren't, which I have to say are very few...with more then 2 people playing on the server tends to get very laggy. So until the game makers fix this issue, I wouldn't rely on this game being multi-player. But playing solo. I still do recommend.",
    //                    "peopleSeen": 7,
    //                    "peopleHelpful": 5,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 114,
    //                    "created": 1431177325840,
    //                    "authorId": 0,
    //                    "author": "SlamCitySkates",
    //                    "content": "AOW3 is an empire building game/ combat game with a fantasy theme. On paper, it sounds great; but in reality it isnt massively interesting. The variety in gameplay comes through the tech trees in both what your cities are producing and on the special abilities that you can impose on your empire. But both trees are relatively short and you can reach the end of both a long way before you end a game. Like a lot of empire building games, the more cities you build, the more tedious it becomes. Sadly, the combat is also repetitive - the same maps with the same enemies in the same positions. It takes a short while to get used to, but once you do, you will be applying the same tactics over and over again. In short, there isnt much to recommend the game. Its not terribly programmed, but there just isnt enough depth to keep you interested.",
    //                    "peopleSeen": 20,
    //                    "peopleHelpful": 12,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 123,
    //                    "created": 1431177325841,
    //                    "authorId": 0,
    //                    "author": "CHIEF DRUID",
    //                    "content": "Just as I thought it's once again too HERO centric. If you lose your good hero it's all over might as well start over. Just put upgrade points into defense, hit points and melee strength and you wash all the other units off the map. Glad I waited until this POC was $13.59 I would have felt a waste of money at full retail price. Basically if you have just about any other 4x Fantasy strategy game even their Shadow Magic game you've pretty much got this one as well. Master of Magic is still the best and most fun. This just feels too much like a clone remake and should have been some DLC for another game. EDIT: Just as I suspected they are going to milk the donkey selling extra races in this game. Man if that ain't ???? pitiful. Of course they kept out the best race next to the death race itself. The Necromancers. Watch the DEATH race will be next and probably $19.99 by itself lol",
    //                    "peopleSeen": 7,
    //                    "peopleHelpful": 5,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 112,
    //                    "created": 1431177325840,
    //                    "authorId": 0,
    //                    "author": "Rando4069",
    //                    "content": "It has been almost one-year since release, I've been playing this game constantly for almost the full year. Incredibly addictive coupled with vast replayability and a veritable smorgasbord of options, units, spells, structures, and game mechanics... and the developers have only expanded on the deep content with Golden Realms and the upcoming release of Eternal Lords. I am not normally a review-writer or poster but this is hands down the best game of the genre I've ever played, including Master of Magic. Only a few fairly minor issues - mostly having to do with requested features such as added victory conditions, more customization options, and more gameplay options have been addressed by the developers or are in the process of being addresed. Kudos to Triumph. They've certainly earned the name. A long-time player of other 4 x games I would enthusiastically recommend this for any fan of the genre. I definitely encourage anyone on the fence to try it out!",
    //                    "peopleSeen": 21,
    //                    "peopleHelpful": 16,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 111,
    //                    "created": 1431177325840,
    //                    "authorId": 0,
    //                    "author": "Red Cyka",
    //                    "content": "This game is a true spiritual successor to Age of Wonders 2 and Shadow Magic that manages to remain true to and enhance on many things that make Shadow Magic such a timeless classic. One great improvement is the new and improved custom wizard creator, which allows you to make really interesting custom characters and save them to use or play against. One of the big changes is the addition of a class system, each wizard chooses one of seven classes, and some of the classes take over the function of some of the old races (Dark Elves, Undead, Archons, Nomads), for example, Rogues build Shadows and Succubi and can do so for any race, and Necromancers can summon or build a lot of the iconic Undead units, as well as ghoul forms of normal racial units. Each of the seven Wizard Classes contains a variety of unique units and spells and can provide unique upgrades to their armies, each of the nine races has several unique units and a few generic units and can build class units for any class that commands them, in addition wizards can have one of several masteries each of which allow their own upgrades and spells, some of which allow units to be summoned and global or city wide effects to be cast unless dispelled. With recent expansions there are too many specializations to list, and some are quite interesting. My one complaint is that there aren't enough terraforming spells. The game has a slightly greater focus on Hero Units than Shadow Magic did, but that can also be configured in settings if you don't like it. However your wizard doesn't have to sit around in a tower and gains levels like heroes so it's usually a good idea to take it into fights. The Building Selection is mostly just like in AoW 2, with the main focus of the game being on exploration and combat. Patches have added buildings based on resources within a city's domain but these are almost all battle improvements or slight improvements to units produced in them. A new racial governance system has been added that allows you to select one of two possible bonuses with each upgrade as you build your cities however. The Diplomacy system is improved in many ways over Age of Wonders 2, but compared to games like Civilization 4 it still falls short, it's impossible to threaten and coerce other Wizards, and the AI is usually unwilling to participate in diplomacy unless it fully sets the terms. There are a variety of game modes, including Play by Email. It's possible to play single player, and to my understanding local host multiplayer without creating a Triumph account, so it's not DRM, but be advised that in order to access all of the achievements and some multiplayer modes you may need to create an online account for some of the multiplayer. Multiplayer probably isn't the main reason you'd want to buy this anyway, but it's worth mentioning. Overall, I can't recommend this game enough, it's my all time favorite game,",
    //                    "peopleSeen": 7,
    //                    "peopleHelpful": 6,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 109,
    //                    "created": 1431177325840,
    //                    "authorId": 0,
    //                    "author": "BelovedMonster",
    //                    "content": "This game really captures the 'just one more turn!'-feeling. It is addicting and the game becomes better with each patch & DLC. Pros: A beautiful strategic map Combat is fast, brutal and entertaining It is fun to level your Heroes and Leader; it adds RPG to the game. Also quests that can be gained from independent cities or dwellings add to RPG-feel. Concept of Race & Class for your Leader provide much variety in play. For example, you can play as a Orc Sorcerer, Dwarven Warlord, Elven Rogue etc. Classes are really different from each other; this applies to gameplay, spells and units. See this wiki for an overview (click the class name for more info). Nice campaigns with different outcomes A pretty good Random Map generator with tons of options Users make custom scenarios and challenges with Map Editor Great music Triumph Studios listens really well to the community and has fixed (almost) all bugs/issues Cons: In general races could be more varied. The good news is that Triumph is working on this and it is expected to improve in the next patch. See this official dev journal (nov 2014) --> *EDIT april 2015* - this has been addressed in the latest patch 1.5!! Although the idea behind alignment is nice (good/neutral/evil is based on your actions and not on your race choice), it remains a bit shallow imo. --> *EDIT april 2015* - this has been addressed mainly in the DLC Eternal Lords! MOD-tools are not released at this moment (nov 2014). They most probably will in the future though. Other comments: For new-comers to the game it can be difficult to learn and to master. It is recommended to watch some videos on YouTube if you are new to the Age of Wonder-series. For example: How to play Age of Wonders 3-videos by iHunterKiller. Link: http://www.youtube.com/playlist?list=PLhAjLeSwzp3hLPeLqY2p-R7Ubbauwhxpe --> *EDIT april 2015* The developers made a Beginner's Strategy guide: see http://steamcommunity.com/sharedfiles/filedetails/?id=424872219 I cannot comment on Multiplayer as I don't have much experience on it. Overall I recommend this game to anyone who has an interest in Turn Based Strategy games.",
    //                    "peopleSeen": 103,
    //                    "peopleHelpful": 89,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 117,
    //                    "created": 1431177325841,
    //                    "authorId": 0,
    //                    "author": "Walrus",
    //                    "content": "Having not played an AoW game since the first one, way back in about 1995, I wasnt quite sure what to expect with this. What I found was a delightful cross between two of my other favourite franchises - Heroes of Might and Magic, and Civilization. Age of Wonders III features many elements from the above and adds in a few ideas of its own, the result is a very solid turned based strategy/RPG hybrid of sorts. Certainly one to watch out for on the Steam Sale if you are considering getting it.",
    //                    "peopleSeen": 38,
    //                    "peopleHelpful": 29,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 120,
    //                    "created": 1431177325841,
    //                    "authorId": 0,
    //                    "author": "MartyrA2J",
    //                    "content": "Age of Wonders III is already a very in depth and well made title. With Eternal Lords thrown into the mix. It just seems even better. This title has a laundry list of features, game modes, and more. Going through all the aspects of the game will take you easily more then a hundred hours to fully assimilate. To break it down, Age of Wonders III is a mixture of Civ meets Masters of Magic. You will bring your spell-flinging champion into the fray against other champion leaders. The empire aspects compliment your hero. This 4X title has more of a focus on individual heroes and tactical combat. Ordering your troops around the map isn't enough in this title. You must command them in battle as well. While the combat is optional, to fully experience this title I would suggest it. Simply put Age of Wonders III: Eternal Lords is an impressive accomplishment. For such a small studio. Graphics, music, and design are all top notch here. The only true complaint one could form is the price. While it can be a bit pricey. You will be playing one map for hundreds of turns and then there is there multiplayer, The level editor, and more. Bang for your buck is guaranteed. Along as you enjoy the genre. I made a video review to show off some of the features and gameplay of this game! https://youtu.be/Ub8KX1K2Vl0 I hope you enjoy! Martyr",
    //                    "peopleSeen": 5,
    //                    "peopleHelpful": 4,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 107,
    //                    "created": 1431177325839,
    //                    "authorId": 0,
    //                    "author": "Ryltair",
    //                    "content": "Long story short: Simply the best turn-based 4X game in years. The good bits: * Competent AI * Huge variation in races, classes, spells, abilities and units * Excellent tactical battles that keep you on your toes. * Great soundtrack * Vivid fantasy setting * Plenty of options in the robust random map generator to drastically alter every game you set up * Exploration is a blast The weaker bits: * Diplomacy is servicable, but it's nowhere near Civilization levels * No more naked Nymphs :( Extra bits: * Very dedicated support from the developers * New expansions bring crazy amounts of content * Regular free patches with new mechanics and additional free content Am looking forward to some PBEM games once the system goes live!",
    //                    "peopleSeen": 16,
    //                    "peopleHelpful": 11,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 122,
    //                    "created": 1431177325841,
    //                    "authorId": 0,
    //                    "author": "snowdogbilly",
    //                    "content": "I keep finding myself playing this game over and over again to see what I missed. I love the imagery and I cannot wait for the next DLC.",
    //                    "peopleSeen": 28,
    //                    "peopleHelpful": 18,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 113,
    //                    "created": 1431177325840,
    //                    "authorId": 0,
    //                    "author": "Stika",
    //                    "content": "\"When taken into account the content found in the original game and its expansion, Age of Wonders 3: Eternal Lords becomes an absolutely massive game. The wealth of military and magical options alone coupled with some truly impressive map sizes result in long, deeply strategic matches requiring up to several weeks for a meaningful resolution. Eternal Lords isn?t likely to turn nay-sayers around, but those who enjoy high fantasy turn based strategy games should waste no time in playing it.\" Full Review Here: http://www.tech-gaming.com/age-of-wonders-3-eternal-lords/",
    //                    "peopleSeen": 5,
    //                    "peopleHelpful": 4,
    //                    "posted": null
    //                }
    //            ]
    //        },
    //        {
    //            "id": 1,
    //            "created": 1431177316736,
    //            "price": 39.99,
    //            "tags": [
    //                "Great Soundtrack",
    //                "4X",
    //                "Tactical",
    //                "Strategy",
    //                "Anime",
    //                "Female Protagonist",
    //                "Grand Strategy",
    //                "Turn-Based Strategy",
    //                "Turn-Based",
    //                "Hex Grid",
    //                "Action",
    //                "Adventure",
    //                "Fantasy",
    //                "Classic",
    //                "RTS",
    //                "Multiplayer",
    //                "Co-op",
    //                "RPG",
    //                "Atmospheric",
    //                "Singleplayer"
    //            ],
    //            "reviews": [
    //                {
    //                    "id": 7,
    //                    "created": 1431177316739,
    //                    "authorId": 0,
    //                    "author": "Jean-Luc",
    //                    "content": "Disclaimers: - I was a closed beta tester for the base game as well as its two expansions. However I'm not an employee of Triumph Studios, only a community volunteer. - This review was written post Eternal Lords and 1.5 patch releases. Age of Wonders III is a turn based hex based fantasy 4x game that is a lot more combat focused compared to something like the Civilization series but also quite a bit more strategically and tactically involved than let's say Heroes of Might & Magic which can be both good or bad depending on your personal preferences. Gameplay Compared to the previous games AoWIII retains most of the series' fundamentals but also iterates and adds to the formula. The most notable change is the way yor faction is presented via the class system. Your research tree and unit roster are not only determined by spell school and starting race picks but by your leader class (warlords, sorcerer, archdruid, dreadnaught, etc.), starting race as well as specializations which are akin to the previous game's spell spheres and leader skills. Your base unit list is thus determined by the race of the towns you own as well as your chosen class. Comparing it to AoWII the class system in a way separates the racial aspect of a faction from the gameplay theme of it. For example, in AoW2 Elves were always Druids with nature themed units, Archons were always Theocrats with their religious and holy theme, Dwarves were Dreadnoughts due to their technological prowes, Orcs were Warlords with strong physical combatants and so on. The AoWIII class system separates race and theme so Elves can be steampunk-ish technologists, Dwarves can wield druidic powers while Goblins can be religious crusaders. Thematically this allows you to create both stereotypical combinations (wizard elves) as well as some unusual ones (holy angelic goblins). Gameplay wise the trio of race, class and specialization (additional mini tech trees) allows for a myriad of different play styles and strategies and a very diverse meta game which encourages experimentation and greately enhances replayability. Each class comes with a unique tech tree which consists of special class units which can have their own racial variations as well as a large number of combat or strategic spells and passive upgrades that enhance your empire as well as units. Customization AoWIII features a leader creation process which allows for detailed aesthetic and gameplay customization. Visual options are akin to rpg games like Dragon Age allowing for changes to race, gender, clothing, accessories, hair style and color, skin color, eyes, hats masks, postures, background scenery, portrait poses, coat of arms, etc. Gameplay-wise the options include race, class and 3 slots for specializations that can be magic oriented or more mundane empire upgrades. Game settings include numerous tweaks to the random map generator like map type (land, continents, islands), map size (s, m, l, xl), geographical settings (types of terrain and climate, existence of the underground layer), starting town/army, amount of resources, treasure sites, independent monsters, etc. Furthermore various game parameters can be tweaked like the number of heroes, hero level cap, hero race, game pace, starting resources, turn progression (classic i-go-you-go or simultanious), empire quests, map events, victory conditions, type and number of opponents, preference for manual or auto-combat and so on. Much effort was made to cater to different preferences and play styles. Combat Like in Heroes of Might & Magic for example, combat in AoWIII happens on a separate tactical map that is derived from the participating units' position on the strategic map. If the units were standing on a snowy arctic hex the tactical map will also have snow on it for example. Same with forests, swamps, etc. Not only that but various sites like cities, farms, dungeons, mines, tombs, etc. are all represented on the tactical map and can have different effects like providing obstacles, choke points, magical effects and so on. Compared to AoWII combat in AoWIII has less randomness and is more dependent on the player making the right moves. Attacks will always hit for example (except under special circumstances) but the damage can still vary and there is the possibility of criticals or fumbles. There are many things to keep an eye on in combat like move points (usually the less you have the less attacks you get but not always), limited retaliations, defensive stances, attacks of opportunity, flanking attacks, distances, unit position and facing, range, the presence of obstacles or city walls, types of movement (walking, floating, flying) and so on. And this is without taking into account unit stats (physical attack vs. defence, magical/elemental attack vs. resistance), traits, special abilities, morale (which influences critical chance), combat spells, unit and battlefield enchantments, unit types, etc. The basics of combat are simple. Click on a unit, right click to attack an enemy unit. But is the enemy unit far enough to trigger the charge ability, can I flank it, what's the chance of my special effect triggering, what elemental protections does it have, how many retaliations does it have remaining, does it have first strike, is it a polearm unit and will my cavalry take extra damage, can I weaken it prior to attack and how, can I kill it, will my unit survive the next turn after it attacks...there are many questions you could end up asking yourself. Learning about the units, what abilities they have and how they can be used and combined for maximum effectiveness is necessary for mastering the game but learning can be achieved in gradual steps. AoWIII retains the adjacency rule of previous games so all armies that are adjacent to the hex being attacked will also participate in combat which can result in some pretty large battles. Thankfully animation speed in battle can be adjusted during combat. Units and heroes that perform well and survive will accumulate experience points and become stronger acquiring new abilities as they level up and grow into valued veterans. Strategy On the strategic level you build up your empire by settling or capturing new cities and expanding your borders in order to bring more and more resources under your control. There are resource sites to be captured, treasure sites to be explored, artifacts to be found, neutral creatures, cities and enemy playes to be subdued. All in all the world is a dangerous place and venturing forth requires combat worthy armies and leaders. Luckily there is a beautiful cloth map to be zoomed out to in order to get an overview of the situation. Resource gathering and exploration primarily serve the goal of building up armies that can defeat the dangers that lie beyond your borders. A new addition here compared to AoWII are the creature dwellings which function as minor races that offer unique units for hire as well as their own sets of buildings. These dwellings can expand your unit roster and provide unexpected tactical and strategic options. Both creature dwellings and independent cities can be outright conqured or negotiated with towards peaceful annexation by vooing them with money or performing quests. How you treat various towns and races can determine their attitude towards you as well as your alignment and diplomatic relations. Another addition is that clearing out and controlling certain treasure sites can unlock special unit or town upgrades in cities controlling those sites which can make some cities more valuable than others. All in all after 618 hrs of playing I have not yet experienced everything this game has to offer, so many untried combinations and strategies, which is probably the biggest endorsement I can give.",
    //                    "peopleSeen": 7,
    //                    "peopleHelpful": 6,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 17,
    //                    "created": 1431177316740,
    //                    "authorId": 0,
    //                    "author": "Graye",
    //                    "content": "It feels a lot like Heroes of Might & Magic had a baby with Master of Magic. I HIGHLY suggest reading some of the reviews here. The negatives aren't all bashers, the positives aren't all fanboi's. You'll get a good picture of what this game offers and where it is lacking. This is my take. Pro's: The hex-7 combat. You can flank prior to engaging, but you may leave an army vulnerable to attack (it affects placement in the battlefield.. except against a city) The hero \"Leader\" capabilities. Each army (group of 6 units including hero) can have a hero as a leader. Heros can pick from abilites to strenghten themselves or provide army-wide bonuses. The Dreadnaught Class (while overall not high on my personal list) is really neat. They are mech-based, rather than magic. If you use their higher special units, you need other special units to heal them. This matters most if your empire included multiple races. Healers can't heal machines, and workers can't heal normal units. I like that concept. :reusgreed: Spell options are \"random\" which helps split up the monogamy of game play (with a few exceptions). Underground & above ground, + teleporters, water tunnels, and 6v6 \"dungeons\" with unique item (Strong, Epic, Legendary, and Mythical difficulties. :ghlol: ) The graphics. Con's: The basic race & class units are carbon copies of each other. Slight differenced being pure damge vs a resistance-based damage type) The buildings are all exactly the same (except the T2 & T3 class buildings.. but the resource from them isn't a game changer). The spell system is lacking, in comparison to Master of Magic. There are obvious \"winners\" for large maps/games, which are to be avoided in smaller/shorter games. As above, there are several abilities/spells which only have true functionally in MP... thus wastes a spell slot. Again, as above, the spells are generated \"randomly\" based on your leader choices. While not entirely bad (MoM was like that also), the MultiPlayer-biased or large map-biased abilities aren't balanced out of the choices. Likewise, they don't recive more weight in those circumstances. So that's really just one big one, split into 3 parts and the last being a pro & con. -------- I've not gotten the xpac.. but that's supposed to add a few more nice things to the game. Worth the expansion price? Not sure yet. Next sale though.. ;) There are a few things they could do differently, from a deisgn standpoint, but given the origins and overal gameplay.. It's definitely a good $20-30.00 game. Not sure about $40-45 (not counting the expansion) Apparently there is a bug that can crash and lose all of your data. I haven't run into that one yet. It does have occasional \"lag\" on the large + maps. I defenitely enjoy it, so far, I'm just a bit picky about things. -------- What they could do to \"fix\" some of the holes in the game: Make a couple of \"difficult\" races, that are OP'd, but cost more. Make a couple of \"easy\" races, that are weak, but fast to max out. Mix up the complexity of the city structures. Maybe Orcs only get a shrine, not a grand temple, etc... then balance it out other ways. (can tie into above) Get rid of the \"Knowledge Points\" resource. It becomes 100% worthless at some point in the game. :angrytitan: Merge \"Mana Income\" and \"Knowledge Points\" and let the player balance the resource between the two. (mana income if dor spell upkeep, unit purchase, initial spell cast, etc.. Knowledge Points only go to how fast you research spells) Unbalance the spell system some more. Let a Mastery level always yield a really bad@$$ unit and global power. Weigh out some of the spells that favor different game maps and MP vs SP. There shouldn't be a clear \"winner\" for them, on that front. (These are in the Class-specific spells, regardless of your focuses, and the one focus realm of expansionisim.) If a balance can't be reached easily.. simply making a counterpart (with a note on each as to what they favor) that is equally beneficial in the opposite playstyle is fine.",
    //                    "peopleSeen": 18,
    //                    "peopleHelpful": 13,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 12,
    //                    "created": 1431177316739,
    //                    "authorId": 0,
    //                    "author": "CHIEF DRUID",
    //                    "content": "Just as I thought it's once again too HERO centric. If you lose your good hero it's all over might as well start over. Just put upgrade points into defense, hit points and melee strength and you wash all the other units off the map. Glad I waited until this POC was $13.59 I would have felt a waste of money at full retail price. Basically if you have just about any other 4x Fantasy strategy game even their Shadow Magic game you've pretty much got this one as well. Master of Magic is still the best and most fun. This just feels too much like a clone remake and should have been some DLC for another game. EDIT: Just as I suspected they are going to milk the donkey selling extra races in this game. Man if that ain't ???? pitiful. Of course they kept out the best race next to the death race itself. The Necromancers. Watch the DEATH race will be next and probably $19.99 by itself lol",
    //                    "peopleSeen": 7,
    //                    "peopleHelpful": 5,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 16,
    //                    "created": 1431177316740,
    //                    "authorId": 0,
    //                    "author": "2 scrakes 1 cup",
    //                    "content": "I didn't like this at first, played it for a few hours and was upset that i wasted more money on a ?????? game. Decided to give it another go and found out that I loved this game, great buy for the midweek madness that I got it on.",
    //                    "peopleSeen": 8,
    //                    "peopleHelpful": 6,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 14,
    //                    "created": 1431177316740,
    //                    "authorId": 0,
    //                    "author": "Infernal Machine [NGC]?",
    //                    "content": "A fantastic strategy game that will appeal to anyone who loves the genre. The game is a 4X similar to Civilization and Master of Magic, but it takes heavy cues from Heroes of Might and Magic in that the world is littered with goodies thrown about to plunder and neutral enemies guarding them, as well as customisable hero units that you can recruit and level. The best thing about it though, is the tactical battle system. This is nothing new, as Master of Magic, Heroes of Might and Magic, as well as the original Age of Wonders games, all did this a long time ago, but here it feels so refined. Terrain has a big impact, units have cool active and passive abilities, and the strategic choices that you make in battle all feel meaningful. On top of this, the game has great variety. Not only do you have a selection of races to choose, all with their own tech trees and units, but you also have character classes and traits to choose for your leader. Your leader's class and traits will determing what extra special units you can produce, as well as the spells you have access to. The combination of both race and class makes mixing and matching fun to do, and allows you to have much more variety if you get bored of a certain setup, yet still like certain elements of your previous games. The expansions are absolutely worth it if you enjoy the game already. The Necromancer class added by Eternal Lords is amazingly fun, and plays entirely differently than the other classes, with a much more agressive play style that emphasises growing your cities and armies through combat. Over all, Age of Wonders III is one of the best in the genre to date.",
    //                    "peopleSeen": 6,
    //                    "peopleHelpful": 5,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 8,
    //                    "created": 1431177316739,
    //                    "authorId": 0,
    //                    "author": "Rando4069",
    //                    "content": "It has been almost one-year since release, I've been playing this game constantly for almost the full year. Incredibly addictive coupled with vast replayability and a veritable smorgasbord of options, units, spells, structures, and game mechanics... and the developers have only expanded on the deep content with Golden Realms and the upcoming release of Eternal Lords. I am not normally a review-writer or poster but this is hands down the best game of the genre I've ever played, including Master of Magic. Only a few fairly minor issues - mostly having to do with requested features such as added victory conditions, more customization options, and more gameplay options have been addressed by the developers or are in the process of being addresed. Kudos to Triumph. They've certainly earned the name. A long-time player of other 4 x games I would enthusiastically recommend this for any fan of the genre. I definitely encourage anyone on the fence to try it out!",
    //                    "peopleSeen": 21,
    //                    "peopleHelpful": 16,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 18,
    //                    "created": 1431177316740,
    //                    "authorId": 0,
    //                    "author": "BelovedMonster",
    //                    "content": "This game really captures the 'just one more turn!'-feeling. It is addicting and the game becomes better with each patch & DLC. Pros: A beautiful strategic map Combat is fast, brutal and entertaining It is fun to level your Heroes and Leader; it adds RPG to the game. Also quests that can be gained from independent cities or dwellings add to RPG-feel. Concept of Race & Class for your Leader provide much variety in play. For example, you can play as a Orc Sorcerer, Dwarven Warlord, Elven Rogue etc. Classes are really different from each other; this applies to gameplay, spells and units. See this wiki for an overview (click the class name for more info). Nice campaigns with different outcomes A pretty good Random Map generator with tons of options Users make custom scenarios and challenges with Map Editor Great music Triumph Studios listens really well to the community and has fixed (almost) all bugs/issues Cons: In general races could be more varied. The good news is that Triumph is working on this and it is expected to improve in the next patch. See this official dev journal (nov 2014) --> *EDIT april 2015* - this has been addressed in the latest patch 1.5!! Although the idea behind alignment is nice (good/neutral/evil is based on your actions and not on your race choice), it remains a bit shallow imo. --> *EDIT april 2015* - this has been addressed mainly in the DLC Eternal Lords! MOD-tools are not released at this moment (nov 2014). They most probably will in the future though. Other comments: For new-comers to the game it can be difficult to learn and to master. It is recommended to watch some videos on YouTube if you are new to the Age of Wonder-series. For example: How to play Age of Wonders 3-videos by iHunterKiller. Link: http://www.youtube.com/playlist?list=PLhAjLeSwzp3hLPeLqY2p-R7Ubbauwhxpe --> *EDIT april 2015* The developers made a Beginner's Strategy guide: see http://steamcommunity.com/sharedfiles/filedetails/?id=424872219 I cannot comment on Multiplayer as I don't have much experience on it. Overall I recommend this game to anyone who has an interest in Turn Based Strategy games.",
    //                    "peopleSeen": 103,
    //                    "peopleHelpful": 89,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 1,
    //                    "created": 1431177316737,
    //                    "authorId": 0,
    //                    "author": "Luna",
    //                    "content": "This game deserves a thumbs up for being a fun strategy based game. Very well thought out. Storyline is great and so are the graphics. Highly recommend as a single player game. However, I had every intention to play this game as coop. That's where the thumbs down comes into play. Even with good connection...we were unable to host our own server using multi-player mode. And there are really not a lot of servers to join. Most are password protected. The ones that aren't, which I have to say are very few...with more then 2 people playing on the server tends to get very laggy. So until the game makers fix this issue, I wouldn't rely on this game being multi-player. But playing solo. I still do recommend.",
    //                    "peopleSeen": 7,
    //                    "peopleHelpful": 5,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 15,
    //                    "created": 1431177316740,
    //                    "authorId": 0,
    //                    "author": "snowdogbilly",
    //                    "content": "I keep finding myself playing this game over and over again to see what I missed. I love the imagery and I cannot wait for the next DLC.",
    //                    "peopleSeen": 28,
    //                    "peopleHelpful": 18,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 9,
    //                    "created": 1431177316739,
    //                    "authorId": 0,
    //                    "author": "Fancy Fish",
    //                    "content": "AGE OF WONDERS III - Good Turn based tactical combat, Hero RPG elements and Empire building 4X strategy || 8 || Score Comment Graphics 8 Nice graphics and detail on the strategic map and even better graphics for units, terrain and magical effects in combat. UI is nice, detailed and easy to use Audio 6 Music gets a little repetitive - maybe a little more variety needed, sound effects are good but nothing special. Gameplay 8 Age of Wonders III is a turn based, 4X strategy game based in a fantasy setting. The tactical combat view (when your army's battle) is excellent, you really feel like you are head to head with the AI (which seems to do a good job most of the time) in a battle of wits. Casting a well timed spell or outmanoeuvring your enemy can sometimes turn the tide of a battle, unfortunately usually the outcome of a battle is a forgone conclusion due to the relative strength of the units involved, but even when this is the case its still fun to crush your enemy and minimise your losses. The strength of the Units in your army really plays such a large part of the battles that you can actually choose to skip the tactical battle entirely and have the computer automatically calculate the results. Some of the units have somewhat of a balance issue with the higher level units often being able to take down whole armies by themselves with little damage or risk. This inevitably results in a race to upgrade your city buildings to create these higher level creatures. AI at the strategic level is a little predictable but there is still fun to be had placing your cities and grabbing as many resources as possible to fund your economy. One thing I noticed at the strategic level is there doesn't seem to be any downside to city spamming (covering the map in loads of your cities) as you gain resources (mana, gold, research) for each city. Ruins, shrines and artefacts are scattered throughout the maps and these will often require you to fight a monster to claim the reward for your Hero. Hero's are your army leaders you get a main one and then can hire more for gold. Hero's have a selection of spells they can use to aid you in battle, summon monsters or benefit your cities. Items you collect / make can be given to your hero's to increase their stats and they also get skills as they earn xp through battles. Story 7 The game contains a number of well written scenario's which are fairly fun to play through. Overall I think I preferred playing the random maps though. Replayability 7 If you enjoy the game you will probably want to play it again, random maps and steam workshop adds more community made content. Overall 8 A fan of this genre will probably get a lot of enjoyment out of this game. While not as complex or detailed as others of the same type what it does it does well. Hotseat mode (a mode I loved from Civilisation) is a great addition to this game and playing with friends is a lot of fun. More reviews by Fancy Fish Agree / Disagree? Please leave me a comment below If you liked this review or want to see more recommended games, be sure to follow our curator group: Follow Original Curator Group Check out the Original Network Groups. Win free games, make new friends on Steam, & more! Original Curators Group, Original Traders Group, & Original Giveaways Group",
    //                    "peopleSeen": 139,
    //                    "peopleHelpful": 113,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 6,
    //                    "created": 1431177316739,
    //                    "authorId": 0,
    //                    "author": "Stika",
    //                    "content": "\"When taken into account the content found in the original game and its expansion, Age of Wonders 3: Eternal Lords becomes an absolutely massive game. The wealth of military and magical options alone coupled with some truly impressive map sizes result in long, deeply strategic matches requiring up to several weeks for a meaningful resolution. Eternal Lords isn?t likely to turn nay-sayers around, but those who enjoy high fantasy turn based strategy games should waste no time in playing it.\" Full Review Here: http://www.tech-gaming.com/age-of-wonders-3-eternal-lords/",
    //                    "peopleSeen": 5,
    //                    "peopleHelpful": 4,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 5,
    //                    "created": 1431177316738,
    //                    "authorId": 0,
    //                    "author": "lusciouslanguidity",
    //                    "content": "A welcome addition to the franchise, with some new additions and some drawbacks. Pros: +More strategic layers than previous Age of Wonders games with Race/Specialization/Class combinations +Unique balance of over-world construction, exploration, conquering/destruction, and tactical battles +Strong RPG elements for a strategy game with Heroes and Leaders' leveling and meaningful choices along the way +Interesting strategic variety with interaction of terrain and races +Many and meaningful strategic options/choices in units (skills, abilities, looks, interactions) and cities (city types, upgrades) and empire quests +Relatively easy to use map editor/maker Cons: -Less friendly fire (I enjoyed how friendly fire was handled in previous games) and height advantages -Less atmosphere than age of wonders games through slightly weaker lore, presence of magic, racial uniqueness, and music/sfx -Story is a bit weak -3D Models are a bit ugly -Less races (Currently) than in other age of wonders games Differences from other 4x Strategy-Fantasy Games: -More tactical battle focus -Less focus on construction The Future: The community is strong and kind enough. The developers are continuously working on the game and listening to the players. New races and classes are coming out. New race relations coming out. New balances and bonuses coming out. More modding coming out. Buy or No? If you are experienced with 4x strategy games... I would get this game. While the genre probably could use a game to revolutionize it a bit, this game does enough new things to keep your interest if you are captivated by 4x genre. You may be annoyed with some things here and there, but overall the balance is strong enough and they are working on improving the balance and adding more content to sink your teeth into. If you are interested in fantasy lore, role-playing/story-making, primarily and racial interactions... I would get it on sale or wait. Do some research on the game first; if you find the lore interesting, purchase it if you haven't played any game in the genre or wait to see how the updates improve and add to the game in a few months. Keep in mind you can also create some interesting quests with the map editor/maker. If you haven't really played much strategy 4x fantasy games and are curious... I would get this game now or on sale. Your enjoyment of the game will be higher if you have an open-mind and like intricate game systems. If you are looking for a fast-paced, mind-blowing, competitive game... do not purchase the game or do more research. The pacing is similar to other strategic games like Civilization. Also, there is nothing in here that will take your breath away, at least immediately. The engagement relies on the stories and histories that are created from the complex systems and events that occur. Overall... AOW3 is a quality game that could use some improvements. Fortunately, the developers care so improvements are on the way. There isn't too much else in the genre being made at the moment either and is better than its nearest fantasy-strategy competitors. But feel free to check out games like Warlock, Fallen Enchantress, and Endless Legend as they offer slightly different fare if tactical combat is not entirely for you.",
    //                    "peopleSeen": 32,
    //                    "peopleHelpful": 24,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 2,
    //                    "created": 1431177316738,
    //                    "authorId": 0,
    //                    "author": "asmodeus",
    //                    "content": "The sequel to one of the best fantasy turn-based strategies that ever hit the digital world. Still great, still addictive, still includes Dire Penguins (which are Dedicated to Evil). Makes me rather sad that the development is already coming to an end due to disappointing sale figures. Shame on you, people. Shame on you.",
    //                    "peopleSeen": 12,
    //                    "peopleHelpful": 8,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 3,
    //                    "created": 1431177316738,
    //                    "authorId": 0,
    //                    "author": "Red Cyka",
    //                    "content": "This game is a true spiritual successor to Age of Wonders 2 and Shadow Magic that manages to remain true to and enhance on many things that make Shadow Magic such a timeless classic. One great improvement is the new and improved custom wizard creator, which allows you to make really interesting custom characters and save them to use or play against. One of the big changes is the addition of a class system, each wizard chooses one of seven classes, and some of the classes take over the function of some of the old races (Dark Elves, Undead, Archons, Nomads), for example, Rogues build Shadows and Succubi and can do so for any race, and Necromancers can summon or build a lot of the iconic Undead units, as well as ghoul forms of normal racial units. Each of the seven Wizard Classes contains a variety of unique units and spells and can provide unique upgrades to their armies, each of the nine races has several unique units and a few generic units and can build class units for any class that commands them, in addition wizards can have one of several masteries each of which allow their own upgrades and spells, some of which allow units to be summoned and global or city wide effects to be cast unless dispelled. With recent expansions there are too many specializations to list, and some are quite interesting. My one complaint is that there aren't enough terraforming spells. The game has a slightly greater focus on Hero Units than Shadow Magic did, but that can also be configured in settings if you don't like it. However your wizard doesn't have to sit around in a tower and gains levels like heroes so it's usually a good idea to take it into fights. The Building Selection is mostly just like in AoW 2, with the main focus of the game being on exploration and combat. Patches have added buildings based on resources within a city's domain but these are almost all battle improvements or slight improvements to units produced in them. A new racial governance system has been added that allows you to select one of two possible bonuses with each upgrade as you build your cities however. The Diplomacy system is improved in many ways over Age of Wonders 2, but compared to games like Civilization 4 it still falls short, it's impossible to threaten and coerce other Wizards, and the AI is usually unwilling to participate in diplomacy unless it fully sets the terms. There are a variety of game modes, including Play by Email. It's possible to play single player, and to my understanding local host multiplayer without creating a Triumph account, so it's not DRM, but be advised that in order to access all of the achievements and some multiplayer modes you may need to create an online account for some of the multiplayer. Multiplayer probably isn't the main reason you'd want to buy this anyway, but it's worth mentioning. Overall, I can't recommend this game enough, it's my all time favorite game,",
    //                    "peopleSeen": 7,
    //                    "peopleHelpful": 6,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 10,
    //                    "created": 1431177316739,
    //                    "authorId": 0,
    //                    "author": "Walrus",
    //                    "content": "Having not played an AoW game since the first one, way back in about 1995, I wasnt quite sure what to expect with this. What I found was a delightful cross between two of my other favourite franchises - Heroes of Might and Magic, and Civilization. Age of Wonders III features many elements from the above and adds in a few ideas of its own, the result is a very solid turned based strategy/RPG hybrid of sorts. Certainly one to watch out for on the Steam Sale if you are considering getting it.",
    //                    "peopleSeen": 38,
    //                    "peopleHelpful": 29,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 20,
    //                    "created": 1431177316740,
    //                    "authorId": 0,
    //                    "author": "Nasarog",
    //                    "content": "Age of Wonders 3, what can I say that hasn't already been said. I am a late convert to the game. The original release didn't inspire me. The Golden Realms expansion, though great, didn't interest me. The hype of Eternal Lords got me to look and it's implementation got me to stay and play. A fantastic fantasy war game that reminds me a lot of Warhammer Fantasy Battle, AoW 3 is a fantastically fun battle simulator. Is it a perfect game? No. It has some shortcomings, but it's getting better all the time. My favorite Race are the Tigrans; fast, blood thirsty and feline. My favorite specilization: Arch Druid because of the summons and the cool units. Least favorite.. BONE DRAGONS!!!!! Anyways, to get more, come check out our official review at eXplorminate4x.com",
    //                    "peopleSeen": 9,
    //                    "peopleHelpful": 8,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 13,
    //                    "created": 1431177316739,
    //                    "authorId": 0,
    //                    "author": "SlamCitySkates",
    //                    "content": "AOW3 is an empire building game/ combat game with a fantasy theme. On paper, it sounds great; but in reality it isnt massively interesting. The variety in gameplay comes through the tech trees in both what your cities are producing and on the special abilities that you can impose on your empire. But both trees are relatively short and you can reach the end of both a long way before you end a game. Like a lot of empire building games, the more cities you build, the more tedious it becomes. Sadly, the combat is also repetitive - the same maps with the same enemies in the same positions. It takes a short while to get used to, but once you do, you will be applying the same tactics over and over again. In short, there isnt much to recommend the game. Its not terribly programmed, but there just isnt enough depth to keep you interested.",
    //                    "peopleSeen": 20,
    //                    "peopleHelpful": 12,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 11,
    //                    "created": 1431177316739,
    //                    "authorId": 0,
    //                    "author": "Ryltair",
    //                    "content": "Long story short: Simply the best turn-based 4X game in years. The good bits: * Competent AI * Huge variation in races, classes, spells, abilities and units * Excellent tactical battles that keep you on your toes. * Great soundtrack * Vivid fantasy setting * Plenty of options in the robust random map generator to drastically alter every game you set up * Exploration is a blast The weaker bits: * Diplomacy is servicable, but it's nowhere near Civilization levels * No more naked Nymphs :( Extra bits: * Very dedicated support from the developers * New expansions bring crazy amounts of content * Regular free patches with new mechanics and additional free content Am looking forward to some PBEM games once the system goes live!",
    //                    "peopleSeen": 16,
    //                    "peopleHelpful": 11,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 4,
    //                    "created": 1431177316738,
    //                    "authorId": 0,
    //                    "author": "Strategikal",
    //                    "content": "Although Age of Wonders III features story based campaigns and scenarios (many of which are obviously balanced for multi-player), the real meat of the game IMO is the Random Map mode. This is equivalent to the default mode of most 4X games like Civilization, and that's what I'll talk about here. Unlike most 4X games which start you off with a single settler in a mostly empty undeveloped world, the default mode (normal game flow) of AoW3 starts you off in a populated world which already has roads, cities and armies. In these games you can get stuck into the action quickly and may not need to build any cities at all, just capture existing ones. These games can be a lot quicker to play than other 4X games. However, the random map games are extremely customisable, much more than Civ. There are three other preset modes - Battle, Empire Building and Adventure - which provide very different game experiences. Better still, go into the advanced setup screens and you can tweak the details to your heart's content. You can start with nothing more than a settler and a leader in a completely empty world, just like Civ, if that's what you want, or you can fill it with as much or as little of the different options that are available. You can also tweak the geography, altering the quantities of different terrain types that will appear on the map. The graphics are superb, they blow away the old AoW games and are better than most current 4X games IMO. The music is excellent too, one of the best soundtracks I've heard in a game, making it well worth getting the Deluxe version for the 2 hours of music, not to mention the huge 8 player scenario Dragon's Throne. The huge variety of units in the game, and the many different unit abilities and spells, provide for an awesome variety of strategy and tactics. The game is far more detailed than previous versions, and while the older games were great in their own right, AoW3 takes it to a whole new level. I can't go back to the old games anymore. It's a game you can get sucked into for many, many hours of your life. The two expansion packs - Golden Realms and Eternal Lords - are real expansions, not shallow cash grabbing DLCs like in many games these days. They add a lot of real content, not just new races and campaigns, but many new rules and game mechanics. I highly recommend this game, one of the best 4X games ever in my opinion.",
    //                    "peopleSeen": 41,
    //                    "peopleHelpful": 39,
    //                    "posted": null
    //                },
    //                {
    //                    "id": 19,
    //                    "created": 1431177316740,
    //                    "authorId": 0,
    //                    "author": "MartyrA2J",
    //                    "content": "Age of Wonders III is already a very in depth and well made title. With Eternal Lords thrown into the mix. It just seems even better. This title has a laundry list of features, game modes, and more. Going through all the aspects of the game will take you easily more then a hundred hours to fully assimilate. To break it down, Age of Wonders III is a mixture of Civ meets Masters of Magic. You will bring your spell-flinging champion into the fray against other champion leaders. The empire aspects compliment your hero. This 4X title has more of a focus on individual heroes and tactical combat. Ordering your troops around the map isn't enough in this title. You must command them in battle as well. While the combat is optional, to fully experience this title I would suggest it. Simply put Age of Wonders III: Eternal Lords is an impressive accomplishment. For such a small studio. Graphics, music, and design are all top notch here. The only true complaint one could form is the price. While it can be a bit pricey. You will be playing one map for hundreds of turns and then there is there multiplayer, The level editor, and more. Bang for your buck is guaranteed. Along as you enjoy the genre. I made a video review to show off some of the features and gameplay of this game! https://youtu.be/Ub8KX1K2Vl0 I hope you enjoy! Martyr",
    //                    "peopleSeen": 5,
    //                    "peopleHelpful": 4,
    //                    "posted": null
    //                }
    //            ]
    //        }
    //    ],
    //    "versions": [
    //        {
    //            "id": 3,
    //            "created": 1431177316748,
    //            "title": "Age of Wonders III V1.5 Update now Available",
    //            "published": 1429022761000,
    //            "content": null
    //        },
    //        {
    //            "id": 2,
    //            "created": 1431177316748,
    //            "title": "Upcoming Beta Mac/Linux Update",
    //            "published": 1423851478000,
    //            "content": null
    //        },
    //        {
    //            "id": 1,
    //            "created": 1431177316748,
    //            "title": "Open Beta - incl. Mac Linux - for Update V1.5 Kicks Off!",
    //            "published": 1426183664000,
    //            "content": null
    //        }
    //    ],
    //    "dlcs": [
    //        {
    //            "id": 3,
    //            "created": 1431177316744,
    //            "dlcId": 329530,
    //            "name": "Age of Wonders III - Eternal Lords Expansion",
    //            "releaseDate": 1396213200000,
    //            "data": [
    //                {
    //                    "id": 4,
    //                    "created": 1431177316744,
    //                    "price": 19.99,
    //                    "tags": [
    //                        "Turn-Based Strategy",
    //                        "4X",
    //                        "Strategy",
    //                        "RPG"
    //                    ],
    //                    "reviews": [
    //                        {
    //                            "id": 48,
    //                            "created": 1431177316745,
    //                            "authorId": 0,
    //                            "author": "Lowen",
    //                            "content": "The new Necromancer class owns bones, Frostlings are really cool, the wonderful thing about Tigrans is Tigrans are wonderful things. But seriously this expansion is even better than the first one, and the first one was really good.",
    //                            "peopleSeen": 1,
    //                            "peopleHelpful": 1,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 56,
    //                            "created": 1431177316746,
    //                            "authorId": 0,
    //                            "author": "Strategikal",
    //                            "content": "The Eternal Lords Expansion for Age of Wonders III is special. Not only do we get a new player class and two new races, but a whole host of new features which add even more richness to this already very rich and detailed game. https://youtu.be/CVdCVtqYCBY The new Necromancer class is in a class of its own. No matter which race you choose to play, you can now play a DEAD version of that race! When you capture cities you can kill everyone to turn them into DEAD cities! This is so cool, I love the new death mechanics. For example, \"fertile fields\" are now actually graveyards, effectively feeding your population with corpses. When you kill units in battle, they are added to the population of your cities, so killing is actually good for economic growth! There are downsides of course, dead units don't heal naturally like living units, unless they are stacked with leaders that have the Healers of the Dead ability, or Reanimators. In cities you need the Embalmer's Guild building to restore health to your deadites garrisoned there. As for the two new races, Frostlings are cool, quite literally, having lots of ice based attacks (some of which can freeze units), but are vulnerable to spirit magic and fire, and hate hot terrain. Tigrans are pretty much the opposite, loving hot terrain but hating the cold. They are fast, can pounce over walls, and are resistant to many mind control effects. As usual, along with a new campaign and scenarios, there's a load of new features in the game, such as a new victory condition, cosmic happenings, map locations, items and more. One very interesting new feature is race relations. Now the game doesn't just track relations with other AI players, but the individual races too, so if you have elves in your empire and then declare war on an independent elven city, the elves in your civilisation won't like it! I'm having a lot of fun with this new expansion and I highly recommend it. Even though it's not on sale at the moment, if you don't own the base game yet, buying the entire collection as a single pack will save you more money compared to buying them separately, which is sort of like getting a discount on the new DLC too.",
    //                            "peopleSeen": 41,
    //                            "peopleHelpful": 37,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 59,
    //                            "created": 1431177316747,
    //                            "authorId": 0,
    //                            "author": "bludaemon",
    //                            "content": "An excellent edition to the game. The new races are fun and well made, and the necromancer class and shadowborn spheres add a nice touch of evil. The new diplomacy system with the neutral cities is nicely improved and the new race relation system is fantastic. I would definitely recommend this DLC to anyone who owns the original game!",
    //                            "peopleSeen": 10,
    //                            "peopleHelpful": 10,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 50,
    //                            "created": 1431177316745,
    //                            "authorId": 0,
    //                            "author": "Kusaha",
    //                            "content": "Totally recommend it, finally a game where the expansions are REALLY meaningful. New races, new class tons of new content which also alters how the game can be played. Brilliant.",
    //                            "peopleSeen": 30,
    //                            "peopleHelpful": 21,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 64,
    //                            "created": 1431177316747,
    //                            "authorId": 0,
    //                            "author": "dwiggs",
    //                            "content": "This is truly EXCELLENT! Full of content that changes the nature of the game (for the better) - it's really an expansion in the old-school sense of the word, not just a \"DLC pack\" of new characters/skins/maps. Out of all of the improvements I've encountered so far, I think that the most important and game-revitalizing are race governance, the enhanced racial distinctions and re-balancing, and the various new systems that allow you to work towards victory/domination through methods other than plain military might (e.g. vassalship + unifier victory + playstyle of the new specializations). The cosmic events are a great touch too, they seem to have struck a good level of randomized impact without making things frustrating. I'd also note that I'm enjoying this expansion's campaign more than the Golden Realms campaign, and far more than the original base campaign. I think this has to do with a good implementation of the necromancer class - it hits many of the themes you'd expect from a necromancer, but without strictly following the easy route of obvious reanimation = attrition. There's enough subtlety and mechanic interaction to make it really feel appropriate to AoW3. Throw in Frostlings and Tigrans, new items/locations/monsters/spells, and some flavorful new abilities (Fishing is great!), and you end up with one hell of a great expansion pack.",
    //                            "peopleSeen": 14,
    //                            "peopleHelpful": 14,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 65,
    //                            "created": 1431177316747,
    //                            "authorId": 0,
    //                            "author": "orangespottydragon",
    //                            "content": "I have been looking forward to this DLC since it was announced especially the necromancer. When I bought it I thought I would mostly use necromancer and that the cosmic thingies were kind of cool but I would still stick with draconian. I booted it up and made a tigrin necromancer so I could show of some of the new stuff for youtube then I realised how much better it made it. If you like the standard game this does nothing but improve it.",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 4,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 55,
    //                            "created": 1431177316746,
    //                            "authorId": 0,
    //                            "author": "Highlander Gaming",
    //                            "content": "I can't recall another expansion in the last two decades that has added so much content to a game and improved it so immeasurably. Age of Wonders III was already a rare and precious gem in this modern era of PC gaming, and now it's even better. Anyone who enjoyed one of the best 4X games in recent years should not hesitate to purchase Eternal Lords.",
    //                            "peopleSeen": 56,
    //                            "peopleHelpful": 45,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 58,
    //                            "created": 1431177316747,
    //                            "authorId": 0,
    //                            "author": "Maximus (Legend of Erthia DEV)",
    //                            "content": "First of all, I would like to say I have been a huge fan of the Age of Wonders series since the beginning. The entire series has always been my favorite for turn based strategy games. I purchased AOW III right on release day, but I wanted to wait for some more expansions to get the most out of it. The one thing I missed from Age of Wonders II was the angel and fallen angel units. It didn?t ruin the game, I still loved AOW III, but I really did miss them. After finding out that the angels and fallen angels were making a return in Eternal Lords I literally jumped out of my chair in joy! It just shows that the Devs/Triumph Studios really care about and listen to the fans because myself, I have been requesting the return of these units since the beginning. I have spent a couple hours in-game now with Eternal Lords and I have to say I am 150% happy with my purchase. There is just so much new content in this expansion pack that will keep you playing for hours and hours. The new skills, units, races, and class makes this expansion pack well worth the asking price. I would strongly recommend Age of Wonders III and the awesome Eternal Lords expansion pack to anyone who loves 4x fantasy strategy games. There is a lot of depth in this game including challenging tactical battles, great diplomacy, and just overall character. I love the fact that you can make alliances with the neutrals! So, do not hesitate! Pick this baby up, it is worth the money! 10/10 good job Triumph Studios! I am really looking forward to more expansions and DLC in the future. Keep it coming!",
    //                            "peopleSeen": 39,
    //                            "peopleHelpful": 34,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 57,
    //                            "created": 1431177316746,
    //                            "authorId": 0,
    //                            "author": "Thariorn",
    //                            "content": "Still WIP review, but feel free to read thorugh already! Before I start I gotta disclaim that I've been a Beta-tester for this game since pre-release (As in prior to the release of vanilla AoW3). So, of course, I'm biased towards the game. Now, I'll try to only post 'facts' about the Expansion but nonetheless, take this review with a grain of salt. Anyway, have fun reading through this bloody heap of words :D Well, what's there to say about the second Expansion of Age of Wonders III, Eternal Lords? First of all, we finally got Necromancers. I can't really say whether this is a case of \"Fans asked and Devs delivered\" or not, but much love/work/time was poured into the proper designing of the Necromancer (And I should know, being in the Beta and all). And with the Necromancers we also got back, at least in some ways, the race of the Undead. The Necromancers in Eternal Lords aren't quite your run-of-the-mill Badies tho. Yes, they do come with skeletons called Cadavers, vampire-esque Ladies in tight leather outfits (As far as PEGI 13 allows that ;P) called Death Bringers and the one definitve thing any good Necromancer caring for his reputation should employ, their very own little Pocket-Death called Dread Reaper....among other ones of course. The main 'gimmick' (I hate how this word kinda means \"some bad gameplayfeature which isn't up to anything one would desire out of it\" nowadays) of the Necromancer is him and his folks being Undead. What this means in essence is that they're immune/highly resistant (Changes with race) to Blight & Cold aswell as (highly) vunerable to Spirit & Fire damage and effects, immune to most convential Mind Controll effects (Control Undead is pretty much the one exception here) aswell as unable to be healed by ordinary means (Heal Undead & dedicated Skeletonhospital do heal them tho), which includes standard Healing on the strategic Map. So unlike e.g. living Dwarven Units, undead ones (Called Ghouls) do not regen their 6 Hp per turn. That's a pretty major drawback already. To partially counter this, Necromancer Leaders will start with 'Heal Undead' right of the bat, as the attrition your Ghouls'd suffer from is extremly high compared to other races (And yes, we've been at that point once in the Beta). In addition, Undead & Ghoul units are immune/not affected by most morale (de-)buffs (Apart from an Mepire Upgrade and Halfling Shenanigans there's no way to up morale, but there are some more ways to debuff it). They also get a skill to raise the corpses of the dead as Cadavers, which are weak meatshield boneshield-units that persist after a battle is won. Also they can be used as combat-rations to buff the Bone Collector, a huge crab-like skeletal Beast(It's body is entirely made up from skeletons and random creatures' bones). Now, those sound quite strong, eh? Well, they'd allow you to swarm the environment/your enemies if not for the fact that they decay. What this means is them losing 8 HP each strategic round. So without constant healing they just die off after a couple of turns. Another part of the Necromancer class are the Ghouls. Ghouls are just like regular units, except they're dead and come with all the strenghts and weakness of being Undead. In addition, they also suffer a flat penality to Defense & Resistance, which can be countered, if you build the right Building(s) in your cities. This seperates Ghouls you yourself produce in your cities from those accquired through combat/other means. Which...is kinda needed, as having the possibility to 'recruit' enemy units for free (Given you've infected & killed them) is quite the boon already and it'd be quite over the top if those units were just as good (Or better because of Undead traits) as the ones your enemies just lost. I've mentioned Deathbringers earlier, and those ladies seem to be the new vampires of Athla(The continent the game takes place on). In meleecombat they can infect their targets with the Ghoul-Curse, which raises those units as undead Ghouls if they die and You win the fight. However, once the Ghoul-Curse proccs (or fails) on a unit, said unit will be immune to the curse for the remainder of the battle. Of course you can improve your chances to infect somebody by lowering their Defense/Resistance against the procc-check (This shouldbe Spirit). Once those nasty foes are Ghouled they will require payment tho. Can't have mindless Ghouls without paying them for being those, right? :) Now, the unit Line-Up the Necromancer may field is rather varied. First we have Ghouls....which may include any (normally) living unit (So no Elementals, Machines, Undead and Incorporeal units aswell as Dragons). They make up the main army of a Necromancer, as any unit you'll produce will be either a Ghoul variant of regular racial units or your Undead Class-Units. The produceable Class-Units are the Reanimator, the Bone Collector and the Death Bringer. In addition to the produceable units, the Necro may also field one of threefour Summons, which are the Cadaver, the Lost Soul, theBanshee and the Dread Reaper. Then we have the two new races, the Frostlings and the Tigrans. Both of them come with their own take on the racial unit line-up (Duh), but unlike the other 7 races, the Frosties and the Tiggers require and offer much more synergy play.",
    //                            "peopleSeen": 26,
    //                            "peopleHelpful": 22,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 54,
    //                            "created": 1431177316746,
    //                            "authorId": 0,
    //                            "author": "Astns",
    //                            "content": "Two new races, two new magic specializations, a new class and a boatload of new abilties, locations, monsters and so on make this DLC extremely feature-dense. In terms of the amount of content it reminds me of the old 'expansion pack model', very good deal in terms of price I feel. All the new features seem well realised and polished and they really enhance what was already a great game for me. If you enjoy the base game, this is a must buy. Recommend 100%.",
    //                            "peopleSeen": 27,
    //                            "peopleHelpful": 25,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 49,
    //                            "created": 1431177316745,
    //                            "authorId": 0,
    //                            "author": "Kris Magi",
    //                            "content": "I have already logged up 572 hours on playing the core game and the first DLC (Goldem Realms). This DLC together with Update 1.5 has added extra depth and increased the gameplay. Together with the beauty of the graphics and the UI, this is a 'must have' strategy game. All credit to Triumph and the design team. Cannot recommend this game highly enough. Excellent.",
    //                            "peopleSeen": 28,
    //                            "peopleHelpful": 24,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 60,
    //                            "created": 1431177316747,
    //                            "authorId": 0,
    //                            "author": "Richard Brown",
    //                            "content": "Well worth the money, i allways like DLC for age of wonders, the dev have done a good job to add new stuff hope to see more DLC to come allway like the new stuff, Hope other people buy the DLC",
    //                            "peopleSeen": 42,
    //                            "peopleHelpful": 30,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 47,
    //                            "created": 1431177316745,
    //                            "authorId": 0,
    //                            "author": "MattStriker",
    //                            "content": "This isn't your average one-or-two-maps-and-an-extra-item DLC. This is a pretty decently-sized expansion. It doesn't just add Necromancers, Frostlings and Tigrans but also a few entirely new game mechanics, three new specialization spheres, a bunch of new map buildings, unique heroes... I'd definitely call it worth the price.",
    //                            "peopleSeen": 26,
    //                            "peopleHelpful": 23,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 51,
    //                            "created": 1431177316746,
    //                            "authorId": 0,
    //                            "author": "jaccovandorp[Gloweye]",
    //                            "content": "Having been closer to the Developers than most of us after I got invited to the Closed Beta, I want to elaborate one how and why this game just got a lot better. Therefore, this review will be mostly aimed at those who own the original game, and to inform them why they need to buy. First of all, the obvious stuff. Necromancer, Frostlings and Tigrans are great additions to the game, and make the world feel much more complete. The intricate synergies are fun to discover and make sure you'll spend many hours discovering others. One of my personal favorites is Frostling Necromancer - enhance your strengths(Frost AND Blight immunity on all your units), but suffer harsher weaknesses. The combinations between Inflict Chilling and Inflict Despair(the only two stacking debuffs) as well as good access to Frost Damage supported by Blight and Spirit, makes sure that noone is immune to your forces. On the other hand, you have to use all you got in order to compensate for the 60% Fire weakness you suffer - primary among them, the Embalmers Guild and Grant Frozen Flames. (be sure to visit the dedicated Wiki, which we are updating as fast as possible! Kudo's to the Dev's for helping us with it!)",
    //                            "peopleSeen": 106,
    //                            "peopleHelpful": 77,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 62,
    //                            "created": 1431177316747,
    //                            "authorId": 0,
    //                            "author": "DadouXIII",
    //                            "content": "Make no mistake, this is a fully fleshed out expansion pack, not a simple DLC. - 2 new races - 1 new class - 1 new 3 endings campaign - New units - New heroes - New items - New locations to visit - New mechanics - New spells / skills - A rebalance of the whole game What else could anyone ask for? 10/10, must buy for anyone who owns the game!",
    //                            "peopleSeen": 110,
    //                            "peopleHelpful": 103,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 63,
    //                            "created": 1431177316747,
    //                            "authorId": 0,
    //                            "author": "Ghevd",
    //                            "content": "If you haven't played Age of Wonders 3 than you are missing out. It is definately different from its previous incarnations. They have added a race and class system to replace the wizards. Regardless of the change, the game is still an excellent 4X. Once you play the game you'll want all the expansions. Not because the base game is so bad but because it is so good. You'll want more. CRAVE MORE! Eternal Lords has to be the best one yet but I am bias for it adds Necromancer class and the undead. The undead are different as they are not a race of their own but are every race and any race. If you like turn based strategy games then I don't know what you're waiting for. Get this game today!",
    //                            "peopleSeen": 9,
    //                            "peopleHelpful": 8,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 46,
    //                            "created": 1431177316745,
    //                            "authorId": 0,
    //                            "author": "Ukrop",
    //                            "content": "This is a proper expansion, like we used to have them long time ago. It's also much better than Golden Realms. Just read its description - it all in there and improves the game greatly. And thanks to 3 new specializations and a new leader class you can expand the gameplay with base races too, not just Frostlings and Tigrans making their return to AoW3. Plus there's also patch 1.5 released alongside Eternal Lords that affects previously available races as well - so they become way more unique than before.",
    //                            "peopleSeen": 16,
    //                            "peopleHelpful": 14,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 61,
    //                            "created": 1431177316747,
    //                            "authorId": 0,
    //                            "author": "Palinchron",
    //                            "content": "This fully fledged expansion - not a mere \"DLC\" - turns an already excellent game into a true classic with a plethora of new features and a thick layer of polish. AoW III is now one of the best turn based games ever made. Most highly recommended, even more so since the developers behind this game are such passionate, nice and honest guys and thus deserve all the success they can get. 10/10",
    //                            "peopleSeen": 65,
    //                            "peopleHelpful": 51,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 52,
    //                            "created": 1431177316746,
    //                            "authorId": 0,
    //                            "author": "Tetzer",
    //                            "content": "Beautiful. I spent waaaaay more time than I should have playing yesterday, but this expansion, like the way expansions USED to be, was just too amazing. Last night I said one more turn a grand total of 59 times until I finally had to have a internal struggle within myself to stop. Dont judge me",
    //                            "peopleSeen": 11,
    //                            "peopleHelpful": 10,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 53,
    //                            "created": 1431177316746,
    //                            "authorId": 0,
    //                            "author": "Leichenfresser",
    //                            "content": "YOU CAN PLAY AS A NECROMANCER KITTY. WHAT IS NOT TO LOVE?!",
    //                            "peopleSeen": 20,
    //                            "peopleHelpful": 14,
    //                            "posted": null
    //                        }
    //                    ]
    //                },
    //                {
    //                    "id": 10,
    //                    "created": 1431177325854,
    //                    "price": 19.99,
    //                    "tags": [
    //                        "Turn-Based Strategy",
    //                        "4X",
    //                        "Strategy",
    //                        "RPG"
    //                    ],
    //                    "reviews": [
    //                        {
    //                            "id": 165,
    //                            "created": 1431177325856,
    //                            "authorId": 0,
    //                            "author": "Palinchron",
    //                            "content": "This fully fledged expansion - not a mere \"DLC\" - turns an already excellent game into a true classic with a plethora of new features and a thick layer of polish. AoW III is now one of the best turn based games ever made. Most highly recommended, even more so since the developers behind this game are such passionate, nice and honest guys and thus deserve all the success they can get. 10/10",
    //                            "peopleSeen": 65,
    //                            "peopleHelpful": 51,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 158,
    //                            "created": 1431177325855,
    //                            "authorId": 0,
    //                            "author": "Richard Brown",
    //                            "content": "Well worth the money, i allways like DLC for age of wonders, the dev have done a good job to add new stuff hope to see more DLC to come allway like the new stuff, Hope other people buy the DLC",
    //                            "peopleSeen": 42,
    //                            "peopleHelpful": 30,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 153,
    //                            "created": 1431177325854,
    //                            "authorId": 0,
    //                            "author": "Maximus (Legend of Erthia DEV)",
    //                            "content": "First of all, I would like to say I have been a huge fan of the Age of Wonders series since the beginning. The entire series has always been my favorite for turn based strategy games. I purchased AOW III right on release day, but I wanted to wait for some more expansions to get the most out of it. The one thing I missed from Age of Wonders II was the angel and fallen angel units. It didn?t ruin the game, I still loved AOW III, but I really did miss them. After finding out that the angels and fallen angels were making a return in Eternal Lords I literally jumped out of my chair in joy! It just shows that the Devs/Triumph Studios really care about and listen to the fans because myself, I have been requesting the return of these units since the beginning. I have spent a couple hours in-game now with Eternal Lords and I have to say I am 150% happy with my purchase. There is just so much new content in this expansion pack that will keep you playing for hours and hours. The new skills, units, races, and class makes this expansion pack well worth the asking price. I would strongly recommend Age of Wonders III and the awesome Eternal Lords expansion pack to anyone who loves 4x fantasy strategy games. There is a lot of depth in this game including challenging tactical battles, great diplomacy, and just overall character. I love the fact that you can make alliances with the neutrals! So, do not hesitate! Pick this baby up, it is worth the money! 10/10 good job Triumph Studios! I am really looking forward to more expansions and DLC in the future. Keep it coming!",
    //                            "peopleSeen": 39,
    //                            "peopleHelpful": 34,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 167,
    //                            "created": 1431177325856,
    //                            "authorId": 0,
    //                            "author": "MattStriker",
    //                            "content": "This isn't your average one-or-two-maps-and-an-extra-item DLC. This is a pretty decently-sized expansion. It doesn't just add Necromancers, Frostlings and Tigrans but also a few entirely new game mechanics, three new specialization spheres, a bunch of new map buildings, unique heroes... I'd definitely call it worth the price.",
    //                            "peopleSeen": 26,
    //                            "peopleHelpful": 23,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 160,
    //                            "created": 1431177325855,
    //                            "authorId": 0,
    //                            "author": "Lowen",
    //                            "content": "The new Necromancer class owns bones, Frostlings are really cool, the wonderful thing about Tigrans is Tigrans are wonderful things. But seriously this expansion is even better than the first one, and the first one was really good.",
    //                            "peopleSeen": 1,
    //                            "peopleHelpful": 1,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 159,
    //                            "created": 1431177325855,
    //                            "authorId": 0,
    //                            "author": "jaccovandorp[Gloweye]",
    //                            "content": "Having been closer to the Developers than most of us after I got invited to the Closed Beta, I want to elaborate one how and why this game just got a lot better. Therefore, this review will be mostly aimed at those who own the original game, and to inform them why they need to buy. First of all, the obvious stuff. Necromancer, Frostlings and Tigrans are great additions to the game, and make the world feel much more complete. The intricate synergies are fun to discover and make sure you'll spend many hours discovering others. One of my personal favorites is Frostling Necromancer - enhance your strengths(Frost AND Blight immunity on all your units), but suffer harsher weaknesses. The combinations between Inflict Chilling and Inflict Despair(the only two stacking debuffs) as well as good access to Frost Damage supported by Blight and Spirit, makes sure that noone is immune to your forces. On the other hand, you have to use all you got in order to compensate for the 60% Fire weakness you suffer - primary among them, the Embalmers Guild and Grant Frozen Flames. (be sure to visit the dedicated Wiki, which we are updating as fast as possible! Kudo's to the Dev's for helping us with it!)",
    //                            "peopleSeen": 106,
    //                            "peopleHelpful": 77,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 152,
    //                            "created": 1431177325854,
    //                            "authorId": 0,
    //                            "author": "orangespottydragon",
    //                            "content": "I have been looking forward to this DLC since it was announced especially the necromancer. When I bought it I thought I would mostly use necromancer and that the cosmic thingies were kind of cool but I would still stick with draconian. I booted it up and made a tigrin necromancer so I could show of some of the new stuff for youtube then I realised how much better it made it. If you like the standard game this does nothing but improve it.",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 4,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 164,
    //                            "created": 1431177325856,
    //                            "authorId": 0,
    //                            "author": "Astns",
    //                            "content": "Two new races, two new magic specializations, a new class and a boatload of new abilties, locations, monsters and so on make this DLC extremely feature-dense. In terms of the amount of content it reminds me of the old 'expansion pack model', very good deal in terms of price I feel. All the new features seem well realised and polished and they really enhance what was already a great game for me. If you enjoy the base game, this is a must buy. Recommend 100%.",
    //                            "peopleSeen": 27,
    //                            "peopleHelpful": 25,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 161,
    //                            "created": 1431177325855,
    //                            "authorId": 0,
    //                            "author": "Ghevd",
    //                            "content": "If you haven't played Age of Wonders 3 than you are missing out. It is definately different from its previous incarnations. They have added a race and class system to replace the wizards. Regardless of the change, the game is still an excellent 4X. Once you play the game you'll want all the expansions. Not because the base game is so bad but because it is so good. You'll want more. CRAVE MORE! Eternal Lords has to be the best one yet but I am bias for it adds Necromancer class and the undead. The undead are different as they are not a race of their own but are every race and any race. If you like turn based strategy games then I don't know what you're waiting for. Get this game today!",
    //                            "peopleSeen": 9,
    //                            "peopleHelpful": 8,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 157,
    //                            "created": 1431177325855,
    //                            "authorId": 0,
    //                            "author": "DadouXIII",
    //                            "content": "Make no mistake, this is a fully fleshed out expansion pack, not a simple DLC. - 2 new races - 1 new class - 1 new 3 endings campaign - New units - New heroes - New items - New locations to visit - New mechanics - New spells / skills - A rebalance of the whole game What else could anyone ask for? 10/10, must buy for anyone who owns the game!",
    //                            "peopleSeen": 110,
    //                            "peopleHelpful": 103,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 163,
    //                            "created": 1431177325855,
    //                            "authorId": 0,
    //                            "author": "Thariorn",
    //                            "content": "Still WIP review, but feel free to read thorugh already! Before I start I gotta disclaim that I've been a Beta-tester for this game since pre-release (As in prior to the release of vanilla AoW3). So, of course, I'm biased towards the game. Now, I'll try to only post 'facts' about the Expansion but nonetheless, take this review with a grain of salt. Anyway, have fun reading through this bloody heap of words :D Well, what's there to say about the second Expansion of Age of Wonders III, Eternal Lords? First of all, we finally got Necromancers. I can't really say whether this is a case of \"Fans asked and Devs delivered\" or not, but much love/work/time was poured into the proper designing of the Necromancer (And I should know, being in the Beta and all). And with the Necromancers we also got back, at least in some ways, the race of the Undead. The Necromancers in Eternal Lords aren't quite your run-of-the-mill Badies tho. Yes, they do come with skeletons called Cadavers, vampire-esque Ladies in tight leather outfits (As far as PEGI 13 allows that ;P) called Death Bringers and the one definitve thing any good Necromancer caring for his reputation should employ, their very own little Pocket-Death called Dread Reaper....among other ones of course. The main 'gimmick' (I hate how this word kinda means \"some bad gameplayfeature which isn't up to anything one would desire out of it\" nowadays) of the Necromancer is him and his folks being Undead. What this means in essence is that they're immune/highly resistant (Changes with race) to Blight & Cold aswell as (highly) vunerable to Spirit & Fire damage and effects, immune to most convential Mind Controll effects (Control Undead is pretty much the one exception here) aswell as unable to be healed by ordinary means (Heal Undead & dedicated Skeletonhospital do heal them tho), which includes standard Healing on the strategic Map. So unlike e.g. living Dwarven Units, undead ones (Called Ghouls) do not regen their 6 Hp per turn. That's a pretty major drawback already. To partially counter this, Necromancer Leaders will start with 'Heal Undead' right of the bat, as the attrition your Ghouls'd suffer from is extremly high compared to other races (And yes, we've been at that point once in the Beta). In addition, Undead & Ghoul units are immune/not affected by most morale (de-)buffs (Apart from an Mepire Upgrade and Halfling Shenanigans there's no way to up morale, but there are some more ways to debuff it). They also get a skill to raise the corpses of the dead as Cadavers, which are weak meatshield boneshield-units that persist after a battle is won. Also they can be used as combat-rations to buff the Bone Collector, a huge crab-like skeletal Beast(It's body is entirely made up from skeletons and random creatures' bones). Now, those sound quite strong, eh? Well, they'd allow you to swarm the environment/your enemies if not for the fact that they decay. What this means is them losing 8 HP each strategic round. So without constant healing they just die off after a couple of turns. Another part of the Necromancer class are the Ghouls. Ghouls are just like regular units, except they're dead and come with all the strenghts and weakness of being Undead. In addition, they also suffer a flat penality to Defense & Resistance, which can be countered, if you build the right Building(s) in your cities. This seperates Ghouls you yourself produce in your cities from those accquired through combat/other means. Which...is kinda needed, as having the possibility to 'recruit' enemy units for free (Given you've infected & killed them) is quite the boon already and it'd be quite over the top if those units were just as good (Or better because of Undead traits) as the ones your enemies just lost. I've mentioned Deathbringers earlier, and those ladies seem to be the new vampires of Athla(The continent the game takes place on). In meleecombat they can infect their targets with the Ghoul-Curse, which raises those units as undead Ghouls if they die and You win the fight. However, once the Ghoul-Curse proccs (or fails) on a unit, said unit will be immune to the curse for the remainder of the battle. Of course you can improve your chances to infect somebody by lowering their Defense/Resistance against the procc-check (This shouldbe Spirit). Once those nasty foes are Ghouled they will require payment tho. Can't have mindless Ghouls without paying them for being those, right? :) Now, the unit Line-Up the Necromancer may field is rather varied. First we have Ghouls....which may include any (normally) living unit (So no Elementals, Machines, Undead and Incorporeal units aswell as Dragons). They make up the main army of a Necromancer, as any unit you'll produce will be either a Ghoul variant of regular racial units or your Undead Class-Units. The produceable Class-Units are the Reanimator, the Bone Collector and the Death Bringer. In addition to the produceable units, the Necro may also field one of threefour Summons, which are the Cadaver, the Lost Soul, theBanshee and the Dread Reaper. Then we have the two new races, the Frostlings and the Tigrans. Both of them come with their own take on the racial unit line-up (Duh), but unlike the other 7 races, the Frosties and the Tiggers require and offer much more synergy play.",
    //                            "peopleSeen": 26,
    //                            "peopleHelpful": 22,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 156,
    //                            "created": 1431177325855,
    //                            "authorId": 0,
    //                            "author": "Kris Magi",
    //                            "content": "I have already logged up 572 hours on playing the core game and the first DLC (Goldem Realms). This DLC together with Update 1.5 has added extra depth and increased the gameplay. Together with the beauty of the graphics and the UI, this is a 'must have' strategy game. All credit to Triumph and the design team. Cannot recommend this game highly enough. Excellent.",
    //                            "peopleSeen": 28,
    //                            "peopleHelpful": 24,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 154,
    //                            "created": 1431177325854,
    //                            "authorId": 0,
    //                            "author": "dwiggs",
    //                            "content": "This is truly EXCELLENT! Full of content that changes the nature of the game (for the better) - it's really an expansion in the old-school sense of the word, not just a \"DLC pack\" of new characters/skins/maps. Out of all of the improvements I've encountered so far, I think that the most important and game-revitalizing are race governance, the enhanced racial distinctions and re-balancing, and the various new systems that allow you to work towards victory/domination through methods other than plain military might (e.g. vassalship + unifier victory + playstyle of the new specializations). The cosmic events are a great touch too, they seem to have struck a good level of randomized impact without making things frustrating. I'd also note that I'm enjoying this expansion's campaign more than the Golden Realms campaign, and far more than the original base campaign. I think this has to do with a good implementation of the necromancer class - it hits many of the themes you'd expect from a necromancer, but without strictly following the easy route of obvious reanimation = attrition. There's enough subtlety and mechanic interaction to make it really feel appropriate to AoW3. Throw in Frostlings and Tigrans, new items/locations/monsters/spells, and some flavorful new abilities (Fishing is great!), and you end up with one hell of a great expansion pack.",
    //                            "peopleSeen": 14,
    //                            "peopleHelpful": 14,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 166,
    //                            "created": 1431177325856,
    //                            "authorId": 0,
    //                            "author": "bludaemon",
    //                            "content": "An excellent edition to the game. The new races are fun and well made, and the necromancer class and shadowborn spheres add a nice touch of evil. The new diplomacy system with the neutral cities is nicely improved and the new race relation system is fantastic. I would definitely recommend this DLC to anyone who owns the original game!",
    //                            "peopleSeen": 10,
    //                            "peopleHelpful": 10,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 155,
    //                            "created": 1431177325855,
    //                            "authorId": 0,
    //                            "author": "Highlander Gaming",
    //                            "content": "I can't recall another expansion in the last two decades that has added so much content to a game and improved it so immeasurably. Age of Wonders III was already a rare and precious gem in this modern era of PC gaming, and now it's even better. Anyone who enjoyed one of the best 4X games in recent years should not hesitate to purchase Eternal Lords.",
    //                            "peopleSeen": 56,
    //                            "peopleHelpful": 45,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 168,
    //                            "created": 1431177325856,
    //                            "authorId": 0,
    //                            "author": "Leichenfresser",
    //                            "content": "YOU CAN PLAY AS A NECROMANCER KITTY. WHAT IS NOT TO LOVE?!",
    //                            "peopleSeen": 20,
    //                            "peopleHelpful": 14,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 169,
    //                            "created": 1431177325856,
    //                            "authorId": 0,
    //                            "author": "Ukrop",
    //                            "content": "This is a proper expansion, like we used to have them long time ago. It's also much better than Golden Realms. Just read its description - it all in there and improves the game greatly. And thanks to 3 new specializations and a new leader class you can expand the gameplay with base races too, not just Frostlings and Tigrans making their return to AoW3. Plus there's also patch 1.5 released alongside Eternal Lords that affects previously available races as well - so they become way more unique than before.",
    //                            "peopleSeen": 16,
    //                            "peopleHelpful": 14,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 151,
    //                            "created": 1431177325854,
    //                            "authorId": 0,
    //                            "author": "Kusaha",
    //                            "content": "Totally recommend it, finally a game where the expansions are REALLY meaningful. New races, new class tons of new content which also alters how the game can be played. Brilliant.",
    //                            "peopleSeen": 30,
    //                            "peopleHelpful": 21,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 170,
    //                            "created": 1431177325856,
    //                            "authorId": 0,
    //                            "author": "Strategikal",
    //                            "content": "The Eternal Lords Expansion for Age of Wonders III is special. Not only do we get a new player class and two new races, but a whole host of new features which add even more richness to this already very rich and detailed game. https://youtu.be/CVdCVtqYCBY The new Necromancer class is in a class of its own. No matter which race you choose to play, you can now play a DEAD version of that race! When you capture cities you can kill everyone to turn them into DEAD cities! This is so cool, I love the new death mechanics. For example, \"fertile fields\" are now actually graveyards, effectively feeding your population with corpses. When you kill units in battle, they are added to the population of your cities, so killing is actually good for economic growth! There are downsides of course, dead units don't heal naturally like living units, unless they are stacked with leaders that have the Healers of the Dead ability, or Reanimators. In cities you need the Embalmer's Guild building to restore health to your deadites garrisoned there. As for the two new races, Frostlings are cool, quite literally, having lots of ice based attacks (some of which can freeze units), but are vulnerable to spirit magic and fire, and hate hot terrain. Tigrans are pretty much the opposite, loving hot terrain but hating the cold. They are fast, can pounce over walls, and are resistant to many mind control effects. As usual, along with a new campaign and scenarios, there's a load of new features in the game, such as a new victory condition, cosmic happenings, map locations, items and more. One very interesting new feature is race relations. Now the game doesn't just track relations with other AI players, but the individual races too, so if you have elves in your empire and then declare war on an independent elven city, the elves in your civilisation won't like it! I'm having a lot of fun with this new expansion and I highly recommend it. Even though it's not on sale at the moment, if you don't own the base game yet, buying the entire collection as a single pack will save you more money compared to buying them separately, which is sort of like getting a discount on the new DLC too.",
    //                            "peopleSeen": 41,
    //                            "peopleHelpful": 37,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 162,
    //                            "created": 1431177325855,
    //                            "authorId": 0,
    //                            "author": "Tetzer",
    //                            "content": "Beautiful. I spent waaaaay more time than I should have playing yesterday, but this expansion, like the way expansions USED to be, was just too amazing. Last night I said one more turn a grand total of 59 times until I finally had to have a internal struggle within myself to stop. Dont judge me",
    //                            "peopleSeen": 11,
    //                            "peopleHelpful": 10,
    //                            "posted": null
    //                        }
    //                    ]
    //                }
    //            ]
    //        },
    //        {
    //            "id": 2,
    //            "created": 1431177316742,
    //            "dlcId": 299230,
    //            "name": "Age of Wonders III - Golden Realms Expansion",
    //            "releaseDate": 1396213200000,
    //            "data": [
    //                {
    //                    "id": 8,
    //                    "created": 1431177325845,
    //                    "price": 11.99,
    //                    "tags": [
    //                        "Turn-Based Strategy",
    //                        "Fantasy",
    //                        "Strategy",
    //                        "RPG"
    //                    ],
    //                    "reviews": [
    //                        {
    //                            "id": 143,
    //                            "created": 1431177325847,
    //                            "authorId": 0,
    //                            "author": "Wendersnaven",
    //                            "content": "Unless you are a hard-core multiplayer, in which case I believe the magic items and new races will make this worth it for you, I wouldn't fret this X-pac until it is on a super-sale. It was well done. Humorous halflings and their chicken chasing, but there wasn't a lot of material to play. The scenarios ended far too quickly, and there were only a few of those. So if it's on sale, it's worth a shot.",
    //                            "peopleSeen": 12,
    //                            "peopleHelpful": 12,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 134,
    //                            "created": 1431177325846,
    //                            "authorId": 0,
    //                            "author": "Karnil Vark Khaitan",
    //                            "content": "Really great DLC",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 132,
    //                            "created": 1431177325845,
    //                            "authorId": 0,
    //                            "author": "Heinhaim",
    //                            "content": "Good game become even better, its like adding sugar to your sour life!",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 135,
    //                            "created": 1431177325846,
    //                            "authorId": 0,
    //                            "author": "Sethfcm",
    //                            "content": "I waited a long time to review AoW3, and really quite awhile to review golden realms. After alot of time playing it I have to say it's worth it. Golden Realms may only add Halfings as a race, something many people like myself aren't really interested in, but it also adds a great deal of other content, from lairs/encounters to units, to magic, and all manner of other things, many of these things were included in the official updates for free, but that doesn't mean that this DLC isn't still worth it for what wasn't. That, and isn't it worth it to support the company if you're enjoying free content that only came about due to this DLC? Not only all of that, but the campaign is actually well done, and fun even if you don't enjoy halfings too much it's still a well done campaign and deserves praise for that. In addition to all my previous points this DLC/Expansion is both cheap, and content rich not something that can be said about most dlc these days which can't even qualify for the title 'expansion' if they tried. I am sure this review isnt' as detailed as others, but it's how I feel. I'd give this dlc 8.5/10 as it doesn't have a new class (like the upcoming eternal lords) which means it doesn't add alot strategically to my games, but it definitely adds more variety, and Nagas are a pretty cool dwelling. As a last note, it's quite low in price for the sheer amount of content, many games charge 20-40 dollars for a new campaign, and all manner of other side content, or just split it into many small dlcs, this is a full scale expansion pack like the old days (when they were practically a new game/added alot to games) and it's only 12 bucks. Support a company that does business right/well, listens to it's community, and has good pricing policies. Edit: I forgot to even mention that it adds a new speciality, and sphere of magic adding alot more to the game I completely forgot even came from this expansion. I'd say it's more of a 9 out of 10 given the fun I've add with partisans, and wild magic shennanigans.",
    //                            "peopleSeen": 3,
    //                            "peopleHelpful": 2,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 128,
    //                            "created": 1431177325845,
    //                            "authorId": 0,
    //                            "author": "Kaleido",
    //                            "content": "Golden Realms is the first expansion for Age of Wonders 3. It introduces a number of new featurers to the game and adds a lot of new content as well as some unique mechanics to the (already very good) base game. You are in for a treat with these new features: A new race: The Halflings (with their own campaign) A new neutral dwelling: The Naga Two new specializations An alternate victory condition: Seals Victory Empire Quests, Mystical City Upgrades and racial Defenses Two new scenarios The RaceThe Halfings are back, and they play quite different! The halflings are available for any class and of course also have their own (rather unique-looking) racial units (ever wanted to throw chickens at the enemy? Well, now you can!) They also come with their own campaign, making use of most of the new features added with Golden Realms. The \"Lucky\" mechanic Being rather small fellows and not heavily armoured either all halfling units suffer a 20% physical weakness. To counter that they all have the brand new innate trait \"lucky\". Lucky units have a chance to completely dodge attacks (both physical and magical). The luckier you are, the higher your chance to avoid being stabbed to death. The lucky trait is in direct relation to the unit morale: happier units = luckier units; units with negative morale will no longer be lucky. While other races have some means to get the lucky trait too (mainly by visiting the new \"Lucky Cloverfield\" structure) it is a central mechanic for the Halflings. With their physical weakness halflings need to uphold high morale for the chance to avoid hits. This makes for some very interesting battles, especially on terrain they don't like much. Many existing and new abilities have also been revamped and given moral modifiers. Severely poisoned units now suffer a morale penalty for a few turns, while the new \"Halfling Brewbrother\" unit can prepare a feast for a friend, increasing their maximum HP and morale for the fight, for example. The Neutrals: NagaThese slithering guys are the new neutral faction that you can encounter on random maps, adding to the existing dragons, giants, fairies and archon revenants. They come packed with 5 units (4 of those are new additions to the game, the other one is a Baby Reed Serpent) and a unique-looking city (which should look familiar to Age of Wonders 1 veterans). They are powerful melee fighters, but their matriachs also know how to weave a spell or two. Their scariest unit by far is the gigantic \"Glutton\". The \"Swallow Whole\" ability gives the glutton a chance to simply devour its target, using it as a snack and to regain health. Fret not though, as - more often than not - you will be able to \"retrieve\" your most recently devoured unit if you kill the glutton. All naga units (except the glutton and the reed serpent) have the \"Regrowth\" ability, allowing them to heal 20% of their HP every turn in battle and 100% of their health on the world map. New SpecializationsThe Partisan and Wild Magic Adept + Master specializations have been added with this expansion. Partisan focuses on guerilla-warfare and stealth. The spells you get with Partisan are mainly focused on concealing your units and lessening the cost of hiring neutral units/buying neutral cities. Wild Magic Adept and Wild Magic Master give you access to highly powerful albeit very random spells. For example, \"Spontaneous Mutation\" gives all your units 2 \"mostly beneficial\" mutations for the duration of the combat, while \"Unstable Transformation\" allows you to transform a friendly target into a random unit of equal or higher power for the rest of the battle, afterwards the affected unit will die. Alternate Victory Condition: Seals\"Seals of Power\" are a new optional victory condition available both on random maps and the 2 scenarios that come with the Golden Realms expansion. The goal is to find and conquer the seal structures on the map. You control a seal by standing on top of it, and for every turn you control a seal you get a charge. You will win the game after accumulating a certain amount of charges (depending on map size and your user-settings when you generate a random map). Be careful when capturing a seal though, as capturing a seal will periodically attract all kinds of elementals. Empire Quests, Mystical City Upgrades and racial DefensesEmpire Quests will reward the first player (or AI) to complete them with a set of appropriate rewards. These quests usually center around your city or empire. For example, being the first player to achieve a \"Pure Good\" or \"Pure Evil\" alignment will reward you with a set of alignment-appropriate units, whereas the first player who grows a city to a metropolis will get a permanent bonus to all gold, mana and research income for that city. Mystical City Upgrades become available when you have cleared treasure sites and they become part of one of your cities' domain. This allows you to build a special structure in that city that will bestow you with unique bonuses. For example, having a \"Crystal Tree\" in your domain will allow you to build an \"Enchanted Armory\", giving all future armored units bonuses to defense and resistance, whereas owning the new \"Lost City\" structure allows you to produce the new \"Feathered Serpent\" unit in your city. Race-appropriate defensive city upgrades are a relatively minor thing, but they add a lot to the individual style of all the races. This allows you to build a racial defense upgrade in your cities. Halflings get to build rabbit burrows, sending out vicious rabbits to defend your city if it gets attacked, whereas the human's bell tower upgrade makes your citizen shoot arrows at enemies every turn during combat. Two new scenariosThese should be rather self-explanatory, two new hand-crafted maps by the developers. One of them is a large 6-player map while the other one is a medium 4-player map. Both scenarios utilize many of the new units, structures and other features of the expansion as well as the Seal Victory. Keep in mind that, while the additions listed above are the main features of the expansion many new heroes, units and items have also been added to the game.",
    //                            "peopleSeen": 86,
    //                            "peopleHelpful": 79,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 127,
    //                            "created": 1431177325845,
    //                            "authorId": 0,
    //                            "author": "Sharky",
    //                            "content": "I am always a sucker for knights riding donkeys or ponies. :P There is just something awesome about seeing a horrific beasty or eldritch horror get taken down by one of the wee folk on a wee lil pony. :P",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 4,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 142,
    //                            "created": 1431177325847,
    //                            "authorId": 0,
    //                            "author": "coderbob0101",
    //                            "content": "I am extremely pleased with Age of Wonders 3. It is the most fun I have had with a game in years. I think the game strikes the right balance of empire building, exploring, and combat. Playing mutliplayer is a blast and the AI in almost all circumstances is the best I have experienced in a game. The lone exception in the excellent AI performance is handling random summons during combat (it over reacts big time). As opposed to Elemental Enchantess, AoW3 has soul and is a very fun experience.",
    //                            "peopleSeen": 39,
    //                            "peopleHelpful": 36,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 131,
    //                            "created": 1431177325845,
    //                            "authorId": 0,
    //                            "author": "Feldis",
    //                            "content": "A great meaty expansion filled with lots of new great content. Lots of new units, a new race and dwelling. Items, campaigns, structures. music etc, everthing you wish for in a good expansion. It expands the Age of wonders world in a great way, if you love AOW3 and want more of it, then this is a must have!",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 4,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 136,
    //                            "created": 1431177325846,
    //                            "authorId": 0,
    //                            "author": "BelovedMonster",
    //                            "content": "Halflings are adorable. They have the unique Luck mechanism - giving them a chance (based on their morale) of dodging attacks in battle. Nothing beats it when this mechanism is triggered in battle!! Apart from the new race, the expansion includes many other things like a campaign, a new dwelling type, many new items and two specializations with new spells. This DLC should not be missed if you like the base Age of Wonders III...",
    //                            "peopleSeen": 7,
    //                            "peopleHelpful": 6,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 129,
    //                            "created": 1431177325845,
    //                            "authorId": 0,
    //                            "author": "Angsthas3",
    //                            "content": "It´s an awesome experience and a nice addition to the basic Game, We always play AoW 3 with friends in the Hot-Seat Mode, but now i bought it to experience the campaign. Must have for every turn-based strategy fan!",
    //                            "peopleSeen": 12,
    //                            "peopleHelpful": 11,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 145,
    //                            "created": 1431177325847,
    //                            "authorId": 0,
    //                            "author": "Dark Knight",
    //                            "content": "very good add-on for the price you're paying. Even more so if it's on a sales. halflings are a very unique race, relying heavily on \"luck\" mechanics and other nice features like \"throwing chickens\", or \"fireworks\". Reminds me much of LOTR lol.. The 4 missions campaign does help a lot to add further depth for the Halflings race.. and the new mini Naga race too.",
    //                            "peopleSeen": 1,
    //                            "peopleHelpful": 0,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 141,
    //                            "created": 1431177325847,
    //                            "authorId": 0,
    //                            "author": "Irish whiskey for breakfast",
    //                            "content": "If you have the game and like it all, this expansion is a must have. The added race, mystical upgrades and new specializations make a good game better. Once you play with the expansion you won't know how you could have played without it.",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 130,
    //                            "created": 1431177325845,
    //                            "authorId": 0,
    //                            "author": "Kusaha",
    //                            "content": "A great expansion, with a lot of content.",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 137,
    //                            "created": 1431177325846,
    //                            "authorId": 0,
    //                            "author": "Steel*Faith",
    //                            "content": "This is the best 4x Strategy game there is. If you love the idea of \"Heroes of Might and Magic\" perfectly blended with \"Civilization\", then you're in for a treat. Also worth noting, the Devs for this game are fantastic and deserve your support. Very active, open, and helpful with the community, and they do a fantastic job of supporting their game and community.",
    //                            "peopleSeen": 3,
    //                            "peopleHelpful": 2,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 126,
    //                            "created": 1431177325845,
    //                            "authorId": 0,
    //                            "author": "Ravenholme",
    //                            "content": "The Golden Realms is the first expansion for Age of Wonders 3, adding the Halfling race, a new campaign and a plethora of new features which after you play your first few Random Map Games with, you'll wonder how you lived without. As you might've guessed, this will be a positive review. Firstly, the feature list added by this expansion: - The Halfling Race, a fully fledged race with a line up of interesting and unique race units and a few special abilities on their class units, and all benefiting from the new 'Luck' mechanic, which allows them a percentage chance to dodge attacks depending on their morale (Something that is needed due to them being physically weaker than their larger counterparts) - The Naga Dwelling, a new Dwelling of independent units, from the foot soldiers of the Slither and Guardian, to their lightning slinging Matriarchs and troop-devouring Gluttons. - New Treasure Sites - The Mystical City Upgrades, which means that certain treasure sites, when inside a city's domain, can be used to produce new, unique structures that confer benefits to the troops produced there, or unlock the production of new units such as Mermaids or flying serpents. - New Specialisations, the Wild Magic spec focuses on summoning lesser elementals of a random elemental type, and bringing chaos to bear on the battlefield, buffing troops randomly or debuffing enemies in various ways, and the Partizan specialisation gives new ways to be stealthy on the strategic map (and generally make a pain of yourself to your enemies) - Empire Quests, competitive quests that can be completed by a player and then by no other, that confer various benefits such as a stack of new troops, random artifacts, or unlocking whole new spheres of magic (randomly). - The Seals of Power, a King of the Hill style objective to provide a new way to win maps. - Defensive City Structures, upon building a Siege Workshop each race unlocks a defensive structure that contributes random strikes upon the enemy for each turn of siege combat, making a well defended city an even tougher prospect to fight at. And a succinct review of those features: The Halflings: Well, they fit right into the game as if they had been there from the start, their physical weakness counterbalanced by a small buff to ranged damage and their \"Lucky\" mechanic, which gives them a small percentage chance to dodge damage that increases the higher their morale. Their units have a sense of humour, something that has always been present in the Halfling lore in the AoW-verse - They are pranksters, jokers and generally embrace a joie de vivre in the face of all the dark and grim things on Athla. Some might feel them a little too unique when compared to the races as they were in base AoW3, but each race was diversified by the patch that accompanied the release of the Golden Realms, and a patch touted to come alongside the next expansion seeks to continue that work. The Naga Dwelling: A dwelling filled with interesting units, the primary aspect of which is their amphibious nature, affording them great mobility and a handful of interesting abilities on their higher tier units. Not too much to say about these guys, except that they are a great addition to the RMG, as always. New Treasure Sites: As above, there isn't too much to say about these - New dungeons and monster spawners, with new and old creatures defending them/being spawned from them, and a slew of new items to be earned from them. But they also tie heavily into the next feature which is.. The Mystical City Upgrades: This is, probably, my favourite feature of the expansion. Certain treasure sites, when cleared of enemies and within a city's domain, allow a new structure to be built in that city. This structure either confers a bonus to a certain unit class or units with a certain trait (Say... +1 armour to all armoured units, or giving Pike Units a Spirit Damage Channel and other abilities), or allows the production of new units in that city. What this results in is that each city can become, with a little forethought, a powerhouse for producing certain types of unit, to get the absolute best from them, and adds much needed importance to individual cities, and, if you really want to min-max from them, to the races that you force to inhabit those cities. New Specialisations: Not too much to say about these, the diversity is always good. Wild Magic focuses on providing random benefits or debuffing your enemies, chaotically terraforming city domains, swapping unit locations, summoning the (new) lesser elemental units and so on. Partisan is a specialisation that allows a player to perform hit and run attacks, hide units and get discounts on buying troops from Inns. Empire Quests: Possibly my second most favourite generalised feature of the expansion. These are competitive quests that are available from the start, can only be completed by one player, and confer benefits upon the player who completes them - either generally, or restricted to the city that completed it - depending on the player who actually completed it. This can be a stack of new units for reaching Full Good/Evil alignment, or randomly recieving two new spheres of magic to research after being the first to research all available spells and abilities in your research book. Seals of Power: The strength of these lie in providing a new way to complete a level. When they are present, the map can be won the traditional way (by defeating all the enemies), or by holding these for a (customisable, in the RMG settings) set length of time. However, as you hold a seal, it will periodically spawn new defenders who will try to reclaim them from you, meaning that you have to defend them against both your enemies and the seal itself. Especially good for L+ maps which you are worried about taking too long to finish. Defensive City Upgrades: Make besieging a city a slightly more risky proposition than it had been before, is basically the AoW3 equivalent of the Magical Guard Towers from AoW2, but with each race getting their own, individual form of it. Not game-breaking, and a feature you will wonder how you ever survived without. All this ties together to plug seamlessly into the base game and make you wonder how you ever played without it. It highlights Triumph's Strategy of \"Improve, and Add More\", and that they actually mean it. That feature list should really convey all you need to know, it expands the game, adds new ways to play it, a new race and a whole slew of features that enhance the game greatly. If you like Age of Wonders 3, you really should buy this expansion pack, as it is a lot of bang for the buck, and if you're staring at this during a sale, then... buy this already.",
    //                            "peopleSeen": 10,
    //                            "peopleHelpful": 8,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 138,
    //                            "created": 1431177325846,
    //                            "authorId": 0,
    //                            "author": "come[back]home",
    //                            "content": "Amazing game, you will not regret buying. Anyone considering this should buy because it is a worthy investment both for your money and for your time spent as a gamer on this top quality product/company.",
    //                            "peopleSeen": 4,
    //                            "peopleHelpful": 2,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 139,
    //                            "created": 1431177325846,
    //                            "authorId": 0,
    //                            "author": "Night_Weaver",
    //                            "content": "Halfling that tosses chickens. All you need to know.",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 133,
    //                            "created": 1431177325846,
    //                            "authorId": 0,
    //                            "author": "NicolloJ",
    //                            "content": "A great expansion for a great game! I fall in love with halflings and these beautiful landscapes <3 Just buy it!",
    //                            "peopleSeen": 14,
    //                            "peopleHelpful": 9,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 140,
    //                            "created": 1431177325846,
    //                            "authorId": 0,
    //                            "author": "Strategikal",
    //                            "content": "While the Golden Realms Expansion for Age of Wonders III only offers one new race, this expansion adds a lot of new features to the game - units, items, spells and skills - all of which are usable in the random map mode, as well as a new campaign and a couple of new scenarios. The halflings (effectively hobbits, if you prefer) are the main feature of this expansion though. While they may seem a bit weak and wimpy at first, they do have one very useful thing on their side - luck! This helps them to survive dangerous encounters more effectively than you might expect them too. Although I've never been a fan of halflings myself, I have to admit that I've had a lot of fun playing them, much more than I expected to. Even if you're not a fan of halflings, this is an essential addon in my opinion, if only for all of the other features it includes.",
    //                            "peopleSeen": 28,
    //                            "peopleHelpful": 27,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 144,
    //                            "created": 1431177325847,
    //                            "authorId": 0,
    //                            "author": "Red Cyka",
    //                            "content": "Well worth it especially if you ? Halflings. Includes a fully fleshed out Halfling faction. New Monsters, Dwellings, and Units. New School of Magic w/ Spells. New Specializations, and Customization Items. New Victory Conditions. New maps. 3 Part Halfling Campaign. And more...",
    //                            "peopleSeen": 28,
    //                            "peopleHelpful": 25,
    //                            "posted": null
    //                        }
    //                    ]
    //                },
    //                {
    //                    "id": 3,
    //                    "created": 1431177316742,
    //                    "price": 11.99,
    //                    "tags": [
    //                        "Turn-Based Strategy",
    //                        "Fantasy",
    //                        "Strategy",
    //                        "RPG"
    //                    ],
    //                    "reviews": [
    //                        {
    //                            "id": 30,
    //                            "created": 1431177316742,
    //                            "authorId": 0,
    //                            "author": "Irish whiskey for breakfast",
    //                            "content": "If you have the game and like it all, this expansion is a must have. The added race, mystical upgrades and new specializations make a good game better. Once you play with the expansion you won't know how you could have played without it.",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 40,
    //                            "created": 1431177316743,
    //                            "authorId": 0,
    //                            "author": "Karnil Vark Khaitan",
    //                            "content": "Really great DLC",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 42,
    //                            "created": 1431177316744,
    //                            "authorId": 0,
    //                            "author": "Angsthas3",
    //                            "content": "It´s an awesome experience and a nice addition to the basic Game, We always play AoW 3 with friends in the Hot-Seat Mode, but now i bought it to experience the campaign. Must have for every turn-based strategy fan!",
    //                            "peopleSeen": 12,
    //                            "peopleHelpful": 11,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 39,
    //                            "created": 1431177316743,
    //                            "authorId": 0,
    //                            "author": "Kusaha",
    //                            "content": "A great expansion, with a lot of content.",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 33,
    //                            "created": 1431177316743,
    //                            "authorId": 0,
    //                            "author": "Kaleido",
    //                            "content": "Golden Realms is the first expansion for Age of Wonders 3. It introduces a number of new featurers to the game and adds a lot of new content as well as some unique mechanics to the (already very good) base game. You are in for a treat with these new features: A new race: The Halflings (with their own campaign) A new neutral dwelling: The Naga Two new specializations An alternate victory condition: Seals Victory Empire Quests, Mystical City Upgrades and racial Defenses Two new scenarios The RaceThe Halfings are back, and they play quite different! The halflings are available for any class and of course also have their own (rather unique-looking) racial units (ever wanted to throw chickens at the enemy? Well, now you can!) They also come with their own campaign, making use of most of the new features added with Golden Realms. The \"Lucky\" mechanic Being rather small fellows and not heavily armoured either all halfling units suffer a 20% physical weakness. To counter that they all have the brand new innate trait \"lucky\". Lucky units have a chance to completely dodge attacks (both physical and magical). The luckier you are, the higher your chance to avoid being stabbed to death. The lucky trait is in direct relation to the unit morale: happier units = luckier units; units with negative morale will no longer be lucky. While other races have some means to get the lucky trait too (mainly by visiting the new \"Lucky Cloverfield\" structure) it is a central mechanic for the Halflings. With their physical weakness halflings need to uphold high morale for the chance to avoid hits. This makes for some very interesting battles, especially on terrain they don't like much. Many existing and new abilities have also been revamped and given moral modifiers. Severely poisoned units now suffer a morale penalty for a few turns, while the new \"Halfling Brewbrother\" unit can prepare a feast for a friend, increasing their maximum HP and morale for the fight, for example. The Neutrals: NagaThese slithering guys are the new neutral faction that you can encounter on random maps, adding to the existing dragons, giants, fairies and archon revenants. They come packed with 5 units (4 of those are new additions to the game, the other one is a Baby Reed Serpent) and a unique-looking city (which should look familiar to Age of Wonders 1 veterans). They are powerful melee fighters, but their matriachs also know how to weave a spell or two. Their scariest unit by far is the gigantic \"Glutton\". The \"Swallow Whole\" ability gives the glutton a chance to simply devour its target, using it as a snack and to regain health. Fret not though, as - more often than not - you will be able to \"retrieve\" your most recently devoured unit if you kill the glutton. All naga units (except the glutton and the reed serpent) have the \"Regrowth\" ability, allowing them to heal 20% of their HP every turn in battle and 100% of their health on the world map. New SpecializationsThe Partisan and Wild Magic Adept + Master specializations have been added with this expansion. Partisan focuses on guerilla-warfare and stealth. The spells you get with Partisan are mainly focused on concealing your units and lessening the cost of hiring neutral units/buying neutral cities. Wild Magic Adept and Wild Magic Master give you access to highly powerful albeit very random spells. For example, \"Spontaneous Mutation\" gives all your units 2 \"mostly beneficial\" mutations for the duration of the combat, while \"Unstable Transformation\" allows you to transform a friendly target into a random unit of equal or higher power for the rest of the battle, afterwards the affected unit will die. Alternate Victory Condition: Seals\"Seals of Power\" are a new optional victory condition available both on random maps and the 2 scenarios that come with the Golden Realms expansion. The goal is to find and conquer the seal structures on the map. You control a seal by standing on top of it, and for every turn you control a seal you get a charge. You will win the game after accumulating a certain amount of charges (depending on map size and your user-settings when you generate a random map). Be careful when capturing a seal though, as capturing a seal will periodically attract all kinds of elementals. Empire Quests, Mystical City Upgrades and racial DefensesEmpire Quests will reward the first player (or AI) to complete them with a set of appropriate rewards. These quests usually center around your city or empire. For example, being the first player to achieve a \"Pure Good\" or \"Pure Evil\" alignment will reward you with a set of alignment-appropriate units, whereas the first player who grows a city to a metropolis will get a permanent bonus to all gold, mana and research income for that city. Mystical City Upgrades become available when you have cleared treasure sites and they become part of one of your cities' domain. This allows you to build a special structure in that city that will bestow you with unique bonuses. For example, having a \"Crystal Tree\" in your domain will allow you to build an \"Enchanted Armory\", giving all future armored units bonuses to defense and resistance, whereas owning the new \"Lost City\" structure allows you to produce the new \"Feathered Serpent\" unit in your city. Race-appropriate defensive city upgrades are a relatively minor thing, but they add a lot to the individual style of all the races. This allows you to build a racial defense upgrade in your cities. Halflings get to build rabbit burrows, sending out vicious rabbits to defend your city if it gets attacked, whereas the human's bell tower upgrade makes your citizen shoot arrows at enemies every turn during combat. Two new scenariosThese should be rather self-explanatory, two new hand-crafted maps by the developers. One of them is a large 6-player map while the other one is a medium 4-player map. Both scenarios utilize many of the new units, structures and other features of the expansion as well as the Seal Victory. Keep in mind that, while the additions listed above are the main features of the expansion many new heroes, units and items have also been added to the game.",
    //                            "peopleSeen": 86,
    //                            "peopleHelpful": 79,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 32,
    //                            "created": 1431177316743,
    //                            "authorId": 0,
    //                            "author": "BelovedMonster",
    //                            "content": "Halflings are adorable. They have the unique Luck mechanism - giving them a chance (based on their morale) of dodging attacks in battle. Nothing beats it when this mechanism is triggered in battle!! Apart from the new race, the expansion includes many other things like a campaign, a new dwelling type, many new items and two specializations with new spells. This DLC should not be missed if you like the base Age of Wonders III...",
    //                            "peopleSeen": 7,
    //                            "peopleHelpful": 6,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 43,
    //                            "created": 1431177316744,
    //                            "authorId": 0,
    //                            "author": "come[back]home",
    //                            "content": "Amazing game, you will not regret buying. Anyone considering this should buy because it is a worthy investment both for your money and for your time spent as a gamer on this top quality product/company.",
    //                            "peopleSeen": 4,
    //                            "peopleHelpful": 2,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 28,
    //                            "created": 1431177316742,
    //                            "authorId": 0,
    //                            "author": "Feldis",
    //                            "content": "A great meaty expansion filled with lots of new great content. Lots of new units, a new race and dwelling. Items, campaigns, structures. music etc, everthing you wish for in a good expansion. It expands the Age of wonders world in a great way, if you love AOW3 and want more of it, then this is a must have!",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 4,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 41,
    //                            "created": 1431177316744,
    //                            "authorId": 0,
    //                            "author": "coderbob0101",
    //                            "content": "I am extremely pleased with Age of Wonders 3. It is the most fun I have had with a game in years. I think the game strikes the right balance of empire building, exploring, and combat. Playing mutliplayer is a blast and the AI in almost all circumstances is the best I have experienced in a game. The lone exception in the excellent AI performance is handling random summons during combat (it over reacts big time). As opposed to Elemental Enchantess, AoW3 has soul and is a very fun experience.",
    //                            "peopleSeen": 39,
    //                            "peopleHelpful": 36,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 29,
    //                            "created": 1431177316742,
    //                            "authorId": 0,
    //                            "author": "Steel*Faith",
    //                            "content": "This is the best 4x Strategy game there is. If you love the idea of \"Heroes of Might and Magic\" perfectly blended with \"Civilization\", then you're in for a treat. Also worth noting, the Devs for this game are fantastic and deserve your support. Very active, open, and helpful with the community, and they do a fantastic job of supporting their game and community.",
    //                            "peopleSeen": 3,
    //                            "peopleHelpful": 2,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 26,
    //                            "created": 1431177316742,
    //                            "authorId": 0,
    //                            "author": "Ravenholme",
    //                            "content": "The Golden Realms is the first expansion for Age of Wonders 3, adding the Halfling race, a new campaign and a plethora of new features which after you play your first few Random Map Games with, you'll wonder how you lived without. As you might've guessed, this will be a positive review. Firstly, the feature list added by this expansion: - The Halfling Race, a fully fledged race with a line up of interesting and unique race units and a few special abilities on their class units, and all benefiting from the new 'Luck' mechanic, which allows them a percentage chance to dodge attacks depending on their morale (Something that is needed due to them being physically weaker than their larger counterparts) - The Naga Dwelling, a new Dwelling of independent units, from the foot soldiers of the Slither and Guardian, to their lightning slinging Matriarchs and troop-devouring Gluttons. - New Treasure Sites - The Mystical City Upgrades, which means that certain treasure sites, when inside a city's domain, can be used to produce new, unique structures that confer benefits to the troops produced there, or unlock the production of new units such as Mermaids or flying serpents. - New Specialisations, the Wild Magic spec focuses on summoning lesser elementals of a random elemental type, and bringing chaos to bear on the battlefield, buffing troops randomly or debuffing enemies in various ways, and the Partizan specialisation gives new ways to be stealthy on the strategic map (and generally make a pain of yourself to your enemies) - Empire Quests, competitive quests that can be completed by a player and then by no other, that confer various benefits such as a stack of new troops, random artifacts, or unlocking whole new spheres of magic (randomly). - The Seals of Power, a King of the Hill style objective to provide a new way to win maps. - Defensive City Structures, upon building a Siege Workshop each race unlocks a defensive structure that contributes random strikes upon the enemy for each turn of siege combat, making a well defended city an even tougher prospect to fight at. And a succinct review of those features: The Halflings: Well, they fit right into the game as if they had been there from the start, their physical weakness counterbalanced by a small buff to ranged damage and their \"Lucky\" mechanic, which gives them a small percentage chance to dodge damage that increases the higher their morale. Their units have a sense of humour, something that has always been present in the Halfling lore in the AoW-verse - They are pranksters, jokers and generally embrace a joie de vivre in the face of all the dark and grim things on Athla. Some might feel them a little too unique when compared to the races as they were in base AoW3, but each race was diversified by the patch that accompanied the release of the Golden Realms, and a patch touted to come alongside the next expansion seeks to continue that work. The Naga Dwelling: A dwelling filled with interesting units, the primary aspect of which is their amphibious nature, affording them great mobility and a handful of interesting abilities on their higher tier units. Not too much to say about these guys, except that they are a great addition to the RMG, as always. New Treasure Sites: As above, there isn't too much to say about these - New dungeons and monster spawners, with new and old creatures defending them/being spawned from them, and a slew of new items to be earned from them. But they also tie heavily into the next feature which is.. The Mystical City Upgrades: This is, probably, my favourite feature of the expansion. Certain treasure sites, when cleared of enemies and within a city's domain, allow a new structure to be built in that city. This structure either confers a bonus to a certain unit class or units with a certain trait (Say... +1 armour to all armoured units, or giving Pike Units a Spirit Damage Channel and other abilities), or allows the production of new units in that city. What this results in is that each city can become, with a little forethought, a powerhouse for producing certain types of unit, to get the absolute best from them, and adds much needed importance to individual cities, and, if you really want to min-max from them, to the races that you force to inhabit those cities. New Specialisations: Not too much to say about these, the diversity is always good. Wild Magic focuses on providing random benefits or debuffing your enemies, chaotically terraforming city domains, swapping unit locations, summoning the (new) lesser elemental units and so on. Partisan is a specialisation that allows a player to perform hit and run attacks, hide units and get discounts on buying troops from Inns. Empire Quests: Possibly my second most favourite generalised feature of the expansion. These are competitive quests that are available from the start, can only be completed by one player, and confer benefits upon the player who completes them - either generally, or restricted to the city that completed it - depending on the player who actually completed it. This can be a stack of new units for reaching Full Good/Evil alignment, or randomly recieving two new spheres of magic to research after being the first to research all available spells and abilities in your research book. Seals of Power: The strength of these lie in providing a new way to complete a level. When they are present, the map can be won the traditional way (by defeating all the enemies), or by holding these for a (customisable, in the RMG settings) set length of time. However, as you hold a seal, it will periodically spawn new defenders who will try to reclaim them from you, meaning that you have to defend them against both your enemies and the seal itself. Especially good for L+ maps which you are worried about taking too long to finish. Defensive City Upgrades: Make besieging a city a slightly more risky proposition than it had been before, is basically the AoW3 equivalent of the Magical Guard Towers from AoW2, but with each race getting their own, individual form of it. Not game-breaking, and a feature you will wonder how you ever survived without. All this ties together to plug seamlessly into the base game and make you wonder how you ever played without it. It highlights Triumph's Strategy of \"Improve, and Add More\", and that they actually mean it. That feature list should really convey all you need to know, it expands the game, adds new ways to play it, a new race and a whole slew of features that enhance the game greatly. If you like Age of Wonders 3, you really should buy this expansion pack, as it is a lot of bang for the buck, and if you're staring at this during a sale, then... buy this already.",
    //                            "peopleSeen": 10,
    //                            "peopleHelpful": 8,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 35,
    //                            "created": 1431177316743,
    //                            "authorId": 0,
    //                            "author": "Sethfcm",
    //                            "content": "I waited a long time to review AoW3, and really quite awhile to review golden realms. After alot of time playing it I have to say it's worth it. Golden Realms may only add Halfings as a race, something many people like myself aren't really interested in, but it also adds a great deal of other content, from lairs/encounters to units, to magic, and all manner of other things, many of these things were included in the official updates for free, but that doesn't mean that this DLC isn't still worth it for what wasn't. That, and isn't it worth it to support the company if you're enjoying free content that only came about due to this DLC? Not only all of that, but the campaign is actually well done, and fun even if you don't enjoy halfings too much it's still a well done campaign and deserves praise for that. In addition to all my previous points this DLC/Expansion is both cheap, and content rich not something that can be said about most dlc these days which can't even qualify for the title 'expansion' if they tried. I am sure this review isnt' as detailed as others, but it's how I feel. I'd give this dlc 8.5/10 as it doesn't have a new class (like the upcoming eternal lords) which means it doesn't add alot strategically to my games, but it definitely adds more variety, and Nagas are a pretty cool dwelling. As a last note, it's quite low in price for the sheer amount of content, many games charge 20-40 dollars for a new campaign, and all manner of other side content, or just split it into many small dlcs, this is a full scale expansion pack like the old days (when they were practically a new game/added alot to games) and it's only 12 bucks. Support a company that does business right/well, listens to it's community, and has good pricing policies. Edit: I forgot to even mention that it adds a new speciality, and sphere of magic adding alot more to the game I completely forgot even came from this expansion. I'd say it's more of a 9 out of 10 given the fun I've add with partisans, and wild magic shennanigans.",
    //                            "peopleSeen": 3,
    //                            "peopleHelpful": 2,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 45,
    //                            "created": 1431177316744,
    //                            "authorId": 0,
    //                            "author": "Red Cyka",
    //                            "content": "Well worth it especially if you ? Halflings. Includes a fully fleshed out Halfling faction. New Monsters, Dwellings, and Units. New School of Magic w/ Spells. New Specializations, and Customization Items. New Victory Conditions. New maps. 3 Part Halfling Campaign. And more...",
    //                            "peopleSeen": 28,
    //                            "peopleHelpful": 25,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 44,
    //                            "created": 1431177316744,
    //                            "authorId": 0,
    //                            "author": "Night_Weaver",
    //                            "content": "Halfling that tosses chickens. All you need to know.",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 38,
    //                            "created": 1431177316743,
    //                            "authorId": 0,
    //                            "author": "Heinhaim",
    //                            "content": "Good game become even better, its like adding sugar to your sour life!",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 3,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 34,
    //                            "created": 1431177316743,
    //                            "authorId": 0,
    //                            "author": "Sharky",
    //                            "content": "I am always a sucker for knights riding donkeys or ponies. :P There is just something awesome about seeing a horrific beasty or eldritch horror get taken down by one of the wee folk on a wee lil pony. :P",
    //                            "peopleSeen": 5,
    //                            "peopleHelpful": 4,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 36,
    //                            "created": 1431177316743,
    //                            "authorId": 0,
    //                            "author": "Dark Knight",
    //                            "content": "very good add-on for the price you're paying. Even more so if it's on a sales. halflings are a very unique race, relying heavily on \"luck\" mechanics and other nice features like \"throwing chickens\", or \"fireworks\". Reminds me much of LOTR lol.. The 4 missions campaign does help a lot to add further depth for the Halflings race.. and the new mini Naga race too.",
    //                            "peopleSeen": 1,
    //                            "peopleHelpful": 0,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 27,
    //                            "created": 1431177316742,
    //                            "authorId": 0,
    //                            "author": "Strategikal",
    //                            "content": "While the Golden Realms Expansion for Age of Wonders III only offers one new race, this expansion adds a lot of new features to the game - units, items, spells and skills - all of which are usable in the random map mode, as well as a new campaign and a couple of new scenarios. The halflings (effectively hobbits, if you prefer) are the main feature of this expansion though. While they may seem a bit weak and wimpy at first, they do have one very useful thing on their side - luck! This helps them to survive dangerous encounters more effectively than you might expect them too. Although I've never been a fan of halflings myself, I have to admit that I've had a lot of fun playing them, much more than I expected to. Even if you're not a fan of halflings, this is an essential addon in my opinion, if only for all of the other features it includes.",
    //                            "peopleSeen": 28,
    //                            "peopleHelpful": 27,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 31,
    //                            "created": 1431177316742,
    //                            "authorId": 0,
    //                            "author": "NicolloJ",
    //                            "content": "A great expansion for a great game! I fall in love with halflings and these beautiful landscapes <3 Just buy it!",
    //                            "peopleSeen": 14,
    //                            "peopleHelpful": 9,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 37,
    //                            "created": 1431177316743,
    //                            "authorId": 0,
    //                            "author": "Wendersnaven",
    //                            "content": "Unless you are a hard-core multiplayer, in which case I believe the magic items and new races will make this worth it for you, I wouldn't fret this X-pac until it is on a super-sale. It was well done. Humorous halflings and their chicken chasing, but there wasn't a lot of material to play. The scenarios ended far too quickly, and there were only a few of those. So if it's on sale, it's worth a shot.",
    //                            "peopleSeen": 12,
    //                            "peopleHelpful": 12,
    //                            "posted": null
    //                        }
    //                    ]
    //                }
    //            ]
    //        },
    //        {
    //            "id": 1,
    //            "created": 1431177316741,
    //            "dlcId": 275560,
    //            "name": "Age of Wonders III - Deluxe Edition DLC",
    //            "releaseDate": 1396213200000,
    //            "data": [
    //                {
    //                    "id": 2,
    //                    "created": 1431177316741,
    //                    "price": 6.99,
    //                    "tags": [
    //                        "Strategy",
    //                        "RPG"
    //                    ],
    //                    "reviews": [
    //                        {
    //                            "id": 23,
    //                            "created": 1431177316741,
    //                            "authorId": 0,
    //                            "author": "come[back]home",
    //                            "content": "Amazing game, you will not regret buying. Anyone considering this should buy because it is a worthy investment both for your money and for your time spent as a gamer on this top quality product/company.",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 5,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 25,
    //                            "created": 1431177316741,
    //                            "authorId": 0,
    //                            "author": "Temp",
    //                            "content": "If you love civilization you should love this, i bought it a few days ago and i'm always playing it! Loads of content, the graphics and music are phenominal and great range of classes and races. AWESOME GAME",
    //                            "peopleSeen": 4,
    //                            "peopleHelpful": 4,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 21,
    //                            "created": 1431177316741,
    //                            "authorId": 0,
    //                            "author": "Tymeus",
    //                            "content": "This game is wonderful. Play is fun, Character creation is immersive. One down side is the Editor, I wish there was a more solid tutorial or help feature in explaining how to script and set up customized games, however basic editing features are easy and you can whip up a map in a short time and play. Another quality game in a series which I have never been dissapointed with. Kudos. Peeves - No Death race per se or frostlings. definitely a 9.5 out of 10 for me though. For the price it is a steal. You will not regret it.",
    //                            "peopleSeen": 1,
    //                            "peopleHelpful": 1,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 22,
    //                            "created": 1431177316741,
    //                            "authorId": 0,
    //                            "author": "Strategikal",
    //                            "content": "I'll be honest, I think the Age of Wonders III Deluxe Edition DLC is overpriced for just the soundtrack and one scenario, however... The music in this game is brilliant, one of the best game soundtracks I've heard, and if you enjoy this music as much as I do, it's great to be able to listen to it separately from the game, as well as five bonus tracks that aren't in the game. The scenario, even though it's only one scenario, is the largest in the game, a very large map with 8 players. If you like huge scenarios, then this is a no brainer. I do recommend this DLC, but with reservations. If you haven't bought the game yet, I recommend getting the Deluxe Edition - it's much cheaper than buying this DLC separately later. If you didn't buy the Deluxe Edition, then I'd only recommend this for completionists and music lovers. Or just wait for a sale.",
    //                            "peopleSeen": 28,
    //                            "peopleHelpful": 28,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 24,
    //                            "created": 1431177316741,
    //                            "authorId": 0,
    //                            "author": "DadouXIII",
    //                            "content": "7 euros for both an exclusive scenario and the official soundtrack (which is incredible by the way). A good deal, but mostly for fans.",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 5,
    //                            "posted": null
    //                        }
    //                    ]
    //                },
    //                {
    //                    "id": 9,
    //                    "created": 1431177325850,
    //                    "price": 6.99,
    //                    "tags": [
    //                        "Strategy",
    //                        "RPG"
    //                    ],
    //                    "reviews": [
    //                        {
    //                            "id": 148,
    //                            "created": 1431177325850,
    //                            "authorId": 0,
    //                            "author": "Strategikal",
    //                            "content": "I'll be honest, I think the Age of Wonders III Deluxe Edition DLC is overpriced for just the soundtrack and one scenario, however... The music in this game is brilliant, one of the best game soundtracks I've heard, and if you enjoy this music as much as I do, it's great to be able to listen to it separately from the game, as well as five bonus tracks that aren't in the game. The scenario, even though it's only one scenario, is the largest in the game, a very large map with 8 players. If you like huge scenarios, then this is a no brainer. I do recommend this DLC, but with reservations. If you haven't bought the game yet, I recommend getting the Deluxe Edition - it's much cheaper than buying this DLC separately later. If you didn't buy the Deluxe Edition, then I'd only recommend this for completionists and music lovers. Or just wait for a sale.",
    //                            "peopleSeen": 28,
    //                            "peopleHelpful": 28,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 146,
    //                            "created": 1431177325850,
    //                            "authorId": 0,
    //                            "author": "Tymeus",
    //                            "content": "This game is wonderful. Play is fun, Character creation is immersive. One down side is the Editor, I wish there was a more solid tutorial or help feature in explaining how to script and set up customized games, however basic editing features are easy and you can whip up a map in a short time and play. Another quality game in a series which I have never been dissapointed with. Kudos. Peeves - No Death race per se or frostlings. definitely a 9.5 out of 10 for me though. For the price it is a steal. You will not regret it.",
    //                            "peopleSeen": 1,
    //                            "peopleHelpful": 1,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 149,
    //                            "created": 1431177325850,
    //                            "authorId": 0,
    //                            "author": "DadouXIII",
    //                            "content": "7 euros for both an exclusive scenario and the official soundtrack (which is incredible by the way). A good deal, but mostly for fans.",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 5,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 147,
    //                            "created": 1431177325850,
    //                            "authorId": 0,
    //                            "author": "come[back]home",
    //                            "content": "Amazing game, you will not regret buying. Anyone considering this should buy because it is a worthy investment both for your money and for your time spent as a gamer on this top quality product/company.",
    //                            "peopleSeen": 6,
    //                            "peopleHelpful": 5,
    //                            "posted": null
    //                        },
    //                        {
    //                            "id": 150,
    //                            "created": 1431177325851,
    //                            "authorId": 0,
    //                            "author": "Temp",
    //                            "content": "If you love civilization you should love this, i bought it a few days ago and i'm always playing it! Loads of content, the graphics and music are phenominal and great range of classes and races. AWESOME GAME",
    //                            "peopleSeen": 4,
    //                            "peopleHelpful": 4,
    //                            "posted": null
    //                        }
    //                    ]
    //                }
    //            ]
    //        }
    //    ]
    //};

    $scope.getGames = function(){

        SteamDataService.getAllApps()
            .then(function(games){
                $scope.games = _.first(games, 200);
        });

    };

    $scope.getDataSets = function(appId){

    };

    $scope.addToWatchlist = function(appId){
        console.log('adding');
        SteamDataService.addToWatchlist(appId)
            .then(function(games){
                updateGetsUpdated(appId, true);
            });
    };

    $scope.removeFromWatchlist = function(appId){
        SteamDataService.removeFromWatchlist(appId)
            .then(function(games){
                updateGetsUpdated(appId, false);
            });
    };

    $scope.showGameData = function(appId){
        console.log('redirecting '+ appId);
        $location.path('#/datasets/' + appId);
    };

    var updateGetsUpdated = function(appId, value){

        var gameIndex = _.findIndex($scope.games, function(game){
            return game.appId === appId;
        });

        $scope.games[gameIndex].getsUpdated = value;
    };

    //init();

    //$scope.getGames = function(){
    //
    //    $http.get('/SteamDataHarvestingWebServices/service/app/getAllApps').
    //        success(function(data, status, headers, config) {
    //            console.log('success');
    //            console.log(data);
    //            $scope.games = _.first(data, 200);
    //
    //        }).
    //        error(function(data, status, headers, config) {
    //            console.log('error');
    //        });
    //};
    //
    //$scope.addToWatchlist = function(appId){
    //    $http.post('/SteamDataHarvestingWebServices/service/app/addToWatchList', appId).
    //        success(function(data, status, headers, config) {
    //            console.log('post success');
    //
    //            updateGetsUpdated(appId, true);
    //        }).
    //        error(function(data, status, headers, config) {
    //            console.log('post error');
    //        });
    //};
    //
    //$scope.removeFromWatchlist = function(appId){
    //    $http.post('/SteamDataHarvestingWebServices/service/app/removeFromWatchList', appId).
    //        success(function(data, status, headers, config) {
    //            console.log('post success');
    //
    //            updateGetsUpdated(appId, false);
    //        }).
    //        error(function(data, status, headers, config) {
    //            console.log('post error');
    //        });
    //};
    //

});