SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS;
SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION;
SET NAMES utf8;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0; 

DROP SCHEMA IF EXISTS books;
CREATE SCHEMA books;
USE books;

CREATE TABLE franchises (
	franchiseID INT UNSIGNED AUTO_INCREMENT,
    franName VARCHAR(255),
    PRIMARY KEY (franchiseID)
    );

CREATE TABLE series (
	seriesID INT UNSIGNED AUTO_INCREMENT,
    seriesName VARCHAR(100),
    serLength INT UNSIGNED,
    finished BOOLEAN,
    franchiseID INT UNSIGNED,
    PRIMARY KEY (seriesID),
    FOREIGN KEY(franchiseID) REFERENCES franchises(franchiseID)
    );

CREATE TABLE books (
	bookID INT UNSIGNED AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    authorName VARCHAR(100),
    publisher VARCHAR(255),
    pageCount INT UNSIGNED,
    genre VARCHAR(100),
    seriesID INT UNSIGNED,
    seriesOrder FLOAT,
    released BOOLEAN,
    franchiseID INT UNSIGNED,
    coverURL VARCHAR(255),
	description TEXT(65535),
    PRIMARY KEY (bookID),
    FOREIGN KEY (seriesID) REFERENCES series(seriesID),
    FOREIGN KEY(franchiseID) REFERENCES franchises(franchiseID)
    );
    
CREATE TABLE users (
	userID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (userID)
    );
    
CREATE TABLE userBooks (
	userID INT UNSIGNED,
    bookID INT UNSIGNED,
    currentPage INT UNSIGNED,

    currStatus char(1),
    rating INT,
    FOREIGN KEY (userID) references users(userID),
    FOREIGN KEY (bookID) references books(bookID),
    CHECK(currStatus IN('p', 's', 'f')),
	CHECK((((rating >= 0 AND rating <= 10) AND currStatus = 'f') OR (currStatus IN('p', 's') AND rating = -1))) -- not being able to review a book you haven't finished was intentional. There was an issue last year of certain books(books with lgbtq or antiracist themes) getting review bombed by users who didn't read, but disagreed with the content. There are other cases of authors being extorted with threats of one star reviews. This isn't a substantial solution, but its a start https://bookriot.com/book-banning-review-bombing/ https://time.com/6078993/goodreads-review-bombing/
    
  );



DELIMITER //

CREATE PROCEDURE userReport(IN idParam INT)
BEGIN
	SELECT users.username, currStatus, count(currStatus)
	FROM userBooks join users
	ON users.userID = userBooks.userID
	WHERE userBooks.userID = idParam
	GROUP BY currStatus;
END //

CREATE PROCEDURE bookReport(IN bookParam INT)
BEGIN 
	SELECT title, currStatus, count(currStatus)
	FROM userBooks join books
    ON userBooks.bookID = books.bookID
    WHERE userBooks.bookID = bookParam
    GROUP BY currStatus;
END //

CREATE PROCEDURE userBooksReport() 
BEGIN 
	SELECT userID, count(bookID) as 'Books Read ', count(currStatus) 'Completion Status'
	FROM userBooks
	GROUP BY userID, currStatus;
END //

CREATE FUNCTION averageRating(bookParam INT) 
RETURNS INT 
DETERMINISTIC
BEGIN
	DECLARE retVal INT;
    SET retVal = (SELECT AVG(rating)
	FROM userBooks join books
    ON userBooks.bookID = books.bookID
    WHERE userBooks.bookID = bookParam and currStatus = 'f'
    GROUP BY userBooks.bookID);
    Return retVal;
END //
DELIMITER ;


SET AUTOCOMMIT=0;
INSERT INTO franchises VALUES 
(1, 'Cosmere'),
(2, 'Star Wars'),
(3, 'Riordan Verse');
COMMIT;


INSERT INTO series VALUES
(1, 'The Masquerade', 4, false, NULL),
(2, 'Stormlight Archive', 12, false, 1),
(3, 'Percy Jackson and the Olympians', 5, true, 3),
(4, 'Original Thrawn Trilogy', 3, true, 2),
(5, 'The Kane Chronicles', 3, true, 3),
(6, 'The Locked Tomb', 4, false, NULL),
(7, 'Mistborn', 3, true, 1),
(8, 'The Han Solo Trilogy', 3, true, 2);
COMMIT;


INSERT INTO books VALUES
(1, 'Way Of Kings', 'Brandon Sanderson', 'TOR', 1280, 'Fantasy', 2, 1, true, 1, 'https://images-na.ssl-images-amazon.com/images/I/91KzZWpgmyL.jpg', '"The result of over ten years of planning, writing, and world-building, The Way of Kings is but the opening movement of the Stormlight Archive, a bold masterpiece in the making.'),
(2, 'Words of Radiance', 'Brandon Sanderson', 'TOR', 1328, 'Fantasy', 2, 2, true, 1, 'https://images-na.ssl-images-amazon.com/images/I/81o27Vl468L.jpg', '"Expected by his enemies to die the miserable death of a military slave, Kaladin survived to be given command of the royal bodyguards, a controversial first for a low-status "darkeyes." Now he must protect the king and Dalinar from every common peril as well as the distinctly uncommon threat of the Assassin, all while secretly struggling to master remarkable new powers that are somehow linked to his honorspren, Syl."'),
(3, 'The Traitor Baru Cormorant', 'Seth Dickinson', 'TOR', 399, 'Fantasy', 1, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/71BpO2pC-KL.jpg', 'The Empire of Masks is coming, armed with coin and ink, doctrine and compass, soap and lies. They\'ll conquer Baru’s island, rewrite her culture, criminalize her customs, and dispose of one of her fathers. But Baru is patient. She\'ll swallow her hate, prove her talent, and join the Masquerade. She will learn the secrets of empire. She’ll be exactly what they need. And she\'ll claw her way high enough up the rungs of power to set her people free.'),
(4, 'The Monster Baru Cormorant', 'Seth Dickinson', 'TOR', 464, 'Fantasy', 1, 2, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/81yTlh4VmYL.jpg', 'A breathtaking geopolitical fantasy as fraught as Game of Thrones, The Monster Baru Cormorant is the long-anticipated sequel to Seth Dickinson gut-wrenching debut, The Traitor Baru Cormorant.Baru Cormormant\'s world was shattered by the Empire of Masks. To exact her revenge, she has clawed her way up razor-edged rungs of betrayal, sacrifice, and compromise, becoming the very thing she seeks to destroy.  Now she strides in the Masquerade\'s halls of power. To save the world, she must tear it asunder...and with it, all that remains of her soul.'),
(5, 'KOWT', 'Brandon Sanderson', 'TOR', 0, 'Fantasy', 2, 5, false, 1, 'https://images-na.ssl-images-amazon.com/images/I/91KzZWpgmyL.jpg', 'Upcoming fourth book of the Stormlight Archive'),
(6, 'Warbreaker', 'Brandon Sanderson', 'TOR', 592, 'Fantasy', 0, 1, true, 1, 'https://images-na.ssl-images-amazon.com/images/I/71MXd10O+VL.jpg', 'From #1 New York Times bestselling author Brandon Sanderson, Warbreaker is the story of two sisters, who happen to be princesses, the God King one of them has to marry, the lesser god who doesn\'t like his job, and the immortal who\'s still trying to undo the mistakes he made hundreds of years ago.'),
(7, 'Heir to the Empire', 'Timothy Zahn', 'Del Ray', 434, 'Science Fiction', 4, 1, true, 2, 'https://images-na.ssl-images-amazon.com/images/I/91hrDLoa8TL.jpg', "It is a time of renewal, five years after the destruction of the Death Star and the defeat of Darth Vader and the Empire. But with the war seemingly won, strains are beginning to show in the Rebel Alliance. New challenges to galactic peace have arisen. And Luke Skywalker hears a voice from his past. A voice with a warning. Beware the dark side…"),
(8, 'Dark Force Rising', 'Timothy Zahn', 'Del Ray', 439, 'Science Fiction', 4, 2, true, 2, 'https://images-na.ssl-images-amazon.com/images/I/91pZWSKV5sL.jpg', 'The dying Empire\'s most cunning and ruthless warlord—Grand Admiral Thrawn—has taken command of the remnants of the Imperial fleet and launched a massive campaign aimed at the New Republic\'s destruction. Meanwhile, Han and Lando Calrissian race against time to find proof of treason inside the highest Republic Council—only to discover instead a ghostly fleet of warships that could bring doom to their friends and victory to their enemies.'),
(9, 'The Last Command', 'Timothy Zahn', 'Del Ray', 467, 'Science Fiction', 4, 3, true, 2, 'https://images-na.ssl-images-amazon.com/images/I/917vMtyhCOL.jpg', 'The embattled Republic reels from the attacks of Grand Admiral Thrawn, who has marshaled the remnants of the Imperial forces and driven the Rebels back with an abominable technology recovered from the Emperor\'s secret fortress: clone soldiers. As Thrawn mounts his final siege, Han and Chewbacca struggle to form a coalition of smugglers for a last-ditch attack against the empire, while Leia holds the Alliance together and prepares for the birth of her Jedi twins. Overwhelmed by the ships and clones at Thrawn\'s command, the Republic has one last hope--sending a small force, led by Luke Skywalker, into the very stronghold that houses Thrawn\'s terrible cloning machines. There a final danger awaits, as the Dark Jedi C\'baoth directs the battle against the Rebels and builds his strength to finish what he had already started: the destruction of Luke Skywalker.'),
(10, 'Oathbringer', 'Brandon Sanderson', 'TOR', 1248, 'Fantasy', 2, 3, true, 1, 'https://images-na.ssl-images-amazon.com/images/I/91x4fchgt2L.jpg', 'In Oathbringer, the third volume of the New York Times bestselling Stormlight Archive, humanity faces a new Desolation with the return of the Voidbringers, a foe with numbers as great as their thirst for vengeance.'),
(11, 'Rhythm of War', 'Brandon Sanderson', 'TOR', 1230, 'Fantasy', 2, 4, true, 1, 'https://images-na.ssl-images-amazon.com/images/I/A1-3LkxN7bL.jpg', 'After forming a coalition of human resistance against the enemy invasion, Dalinar Kholin and his Knights Radiant have spent a year fighting a protracted, brutal war. Neither side has gained an advantage, and the threat of a betrayal by Dalinar\'s crafty ally Taravangian looms over every strategic move.'),
(12, 'The Tyrant Baru Cormorant', 'Seth Dickinson', 'TOR', 656, 'Fantasy', 1, 3, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/91-wEu6eyAL.jpg', 'The hunt is over. After fifteen years of lies and sacrifice, Baru Cormorant has the power to destroy the Imperial Republic of Falcrest that she pretends to serve. The secret society called the Cancrioth is real, and Baru is among them. But the Cancrioth\'s weapon cannot distinguish the guilty from the innocent. If it escapes quarantine, the ancient hemorrhagic plague called the Kettling will kill hundreds of millions...not just in Falcrest, but all across the world. History will end in a black bloodstain. Is that justice?'),
(13, 'The blank Baru Cormorant', 'Seth Dickinson', 'TOR', 0, 'Fantasy', 1, 4, false, NULL, 'https://images-na.ssl-images-amazon.com/images/I/91-wEu6eyAL.jpg', 'The unamed fourth book in the Baru Cormorant, this book promises to wrap up the series, continuing its extraodinary plot, and fascinating analysis of colonization, empires, and the meaning of power'),-- I wrote this, no summary yet exists
(14, 'The Lightning Thief', 'Rick Riordan', 'Puffin Books', 377, 'YA', 3, 1, true, 3, 'https://images-na.ssl-images-amazon.com/images/I/91RQ5d-eIqL.jpg', 'Percy Jackson is a good kid, but he can\'t seem to focus on his schoolwork or control his temper. And lately, being away at boarding school is only getting worse - Percy could have sworn his pre-algebra teacher turned into a monster and tried to kill him. When Percy\'s mom finds out, she knows it\'s time that he knew the truth about where he came from, and that he go to the one place he\'ll be safe. She sends Percy to Camp Half Blood, a summer camp for demigods (on Long Island), where he learns that the father he never knew is Poseidon, God of the Sea. Soon a mystery unfolds and together with his friends—one a satyr and the other the demigod daughter of Athena - Percy sets out on a quest across the United States to reach the gates of the Underworld (located in a recording studio in Hollywood) and prevent a catastrophic war between the gods.'),
(15, 'The Sea of Monsters', 'Rick Riordan', 'Puffin', 279, 'YA', 3, 2, true, 3, 'https://images-na.ssl-images-amazon.com/images/I/9117OFw0G4L.jpg', 'The heroic son of Poseidon makes an action-packed comeback in the second must-read installment of Rick Riordan\'s amazing young readers series. Starring Percy Jackson, a \"half blood\" whose mother is human and whose father is the God of the Sea, Riordan\'s series combines cliffhanger adventure and Greek mythology lessons that results in true page-turners that get better with each installment.'),
(16, 'The Titan\'s Curse', 'Rick Riordan', 'Puffin', 320, 'YA', 3, 3,true, 3, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/1413/9780141338262.jpg', 'But when you\'re the son of a Greek god, it happens. And now my friend Annabeth is missing, a goddess is in chains and only five half-blood heroes can join the quest to defeat the doomsday monster.'),
(17, 'The Battle Of The Labyrinth', 'Rick Riordan', 'Puffin', 361, 'YA', 3, 4, true, 3, 'https://images-na.ssl-images-amazon.com/images/I/81D3wCkNJDL.jpg', 'In this fourth installment of the blockbuster series, time is running out as war between the Olympians and the evil Titan lord Kronos draws near. Even the safe haven of Camp Half-Blood grows more vulnerable by the minute as Kronos\'s army prepares to invade its once impenetrable borders. To stop the invasion, Percy and his demigod friends must set out on a quest through the Labyrinth - a sprawling underground world with stunning surprises at every turn.'),
(18, 'The Last Olympian', 'Rick Riordan', 'Puffin', 381, 'YA', 3, 5, true, 3, 'https://images-na.ssl-images-amazon.com/images/I/918s2eM4pSL.jpg', 'All year the half-bloods have been preparing for battle against the Titans, knowing the odds of victory are grim. Kronos\'s army is stronger than ever, and with every god and half-blood he recruits, the evil Titan\'s power only grows.'),
(19, 'The Red Pyramid', 'Rick Riordan', 'Puffin', 528, 'YA', 5, 1, true, 3, 'https://m.media-amazon.com/images/I/51iXg+CcMcL.jpg', 'Soon Carter and Sadie discover that the gods of Ancient Egypt are waking, and the worst of them—Set—has a frightening scheme. To save their father, they must embark on a dangerous journey—a quest that brings them ever closer to the truth about their family and its links to the House of Life, a secret order that has existed since the time of the pharaohs. '),
(20, 'The Throne of Fire', 'Rick Riordan', 'Puffin', 452, 'YA', 5, 2, true, 3, 'https://m.media-amazon.com/images/I/51NLHqHaRfL.jpg', 'Ever since the gods of Ancient Egypt were unleashed in the modern world, Carter Kane and his sister Sadie have been in trouble. As descendants of the House of Life, the Kanes have some powers at their command, but the devious gods haven\'t given them much time to master their skills at Brooklyn House, which has become a training ground for young magicians. And now their most threatening enemy yet - the chaos snake Apophis - is rising. If they don\'t prevent him from breaking free in a few days\' time, the world will come to an end. In other words, it\'s a typical week for the Kane family.' ),
(21, 'The Serpent\'s Shadow', 'Rick Riordan', 'Puffin', 406, 'YA', 5, 3, true, 3, 'https://images-na.ssl-images-amazon.com/images/I/71KVy9z6WVL.jpg', 'He\'s b-a-a-ack! Despite their best efforts, Carter and Sadie Kane can\'t seem to keep Apophis, the chaos snake, down. Now Apophis is threatening to plunge the world into eternal darkness, and the Kanes are faced with the impossible task of having to destroy him once and for all. Unfortunately, the magicians of the House of Life are on the brink of civil war, the gods are divided, and the young initiates of Brooklyn House stand almost alone against the forces of chaos. The Kanes\' only hope is an ancient spell that might turn the serpent\'s own shadow into a weapon, but the magic has been lost for a millennia. To find the answer they need, the Kanes must rely on the murderous ghost of a powerful magician who might be able to lead them to the serpent\'s shadow . . . or might lead them to their deaths in the depths of the underworld. Nothing less than the mortal world is at stake when the Kane family fulfills its destiny in this thrilling conclusion to the Kane Chronicles.'),
(22, 'Gideon the Ninth', 'Tamsyn Muir', 'TOR', 448, 'Murder Mystery', 6, 1, true, NULL, 'https://m.media-amazon.com/images/I/412PSZfs4FL.jpg', 'Brought up by unfriendly, ossifying nuns, ancient retainers, and countless skeletons, Gideon is ready to abandon a life of servitude and an afterlife as a reanimated corpse. She packs up her sword, her shoes, and her dirty magazines, and prepares to launch her daring escape. But her childhood nemesis won\'t set her free without a service. Harrowhark Nonagesimus, Reverend Daughter of the Ninth House and bone witch extraordinaire, has been summoned into action. The Emperor has invited the heirs to each of his loyal Houses to a deadly trial of wits and skill. If Harrowhark succeeds she will become an immortal, all-powerful servant of the Resurrection, but no necromancer can ascend without their cavalier. Without Gideon\'s sword, Harrow will fail, and the Ninth House will die.'),
(23, 'Harrow the Ninth', 'Tamsyn Muir', 'TOR', 512, 'Gothic Horror', 6, 2, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/81sb8os2uxL.jpg', 'The sequel to Gideon the Ninth, Harrow the Ninth continues the story of "Lesbian Necromancers in Space!" from the point of view of the necromancer Harrowhark NonaGesimus. While a fascinating take on grief and loss, Harrow the Ninth is also a very weird book to read, with the first two thirds being a combination of office comedy, horror, lying to the reader about the events of the previous book, memes from early 2010\'s tumblr, and the effects of an empire'), -- I wrote this!
(24, 'Nona the Ninth', 'Tamsyn Muir', 'TOR', 0, 'Fantasy', 6, 3, false, NULL, 'https://cdn.vox-cdn.com/thumbor/Z3lrGDT8CBKU2ovG1WEclYWYoGY=/1400x0/filters:no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/23194284/Nona_type_comp_v3.jpg', 'Her city is under siege. The zombies are coming back. And all Nona wants is a birthday party. In many ways, Nona is like other people. She lives with her family, has a job at her local school, and loves walks on the beach and meeting new dogs. But Nona\'s not like other people. Six months ago she woke up in a stranger\'s body, and she\'s afraid she might have to give it back. The whole city is falling to pieces. A monstrous blue sphere hangs on the horizon, ready to tear the planet apart. Blood of Eden forces have surrounded the last Cohort facility and wait for the Emperor Undying to come calling. Their leaders want Nona to be the weapon that will save them from the Nine Houses. Nona would prefer to live an ordinary life with the people she loves, with Pyrrha and Camilla and Palamedes, but she also knows that nothing lasts forever.'), -- I have no idea d what genre to characterize this as? Fantasy? Sci-Fi? Horror? High School Comedy? All of the above?
(25, 'Alecto the Ninth', 'Tamsyn Muir', 'TOR', 0, 'Unknown', 6, 4, false, NULL, 'https://static.wikia.nocookie.net/the-ninth-house-trilogy/images/9/97/Placeholder_Cover.jpg/revision/latest?cb=20200903151407', 'Book not released. Description for Goodreads user Baba Yaga: \'Me: this book can\'t possibly be any crazier than Harrow the Ninth /n Tamsyn Muir, somewhere in the world: :) :) :)'),
(26, 'Edgedancer', 'Brandon Sanderson', 'TOR', 272, 'Fantasy', 2, 2.5, true, 1, 'https://m.media-amazon.com/images/I/51+mTNmSphL.jpg', 'Three years ago, Lift asked a goddess to stop her from growing older--a wish she believed was granted. Now, in Edgedancer, the barely teenage nascent Knight Radiant finds that time stands still for no one. Although the young Azish emperor granted her safe haven from an executioner she knows only as Darkness, court life is suffocating the free-spirited Lift, who can\'t help heading to Yeddaw when she hears the relentless Darkness is there hunting people like her with budding powers. The downtrodden in Yeddaw have no champion, and Lift knows she must seize this awesome responsibility. A side book in the Stormlight Archive, Edgedancer takes place between Words of Radiance and Oathbringer'),
(27, 'Dawnshard', 'Brandon Sanderson', 'TOR', 171, 'Fantasy', 2, 3.5, true, 1, 'https://m.media-amazon.com/images/I/518nkgOg1IL.jpg', 'Taking place between Oathbringer and Rhythm of War, this tale (like Edgedancer before it) gives often-overshadowed characters their own chance to shine. When a ghost ship is discovered, its crew presumed dead after trying to reach the storm-shrouded island Akinah, Navani Kholin must send an expedition to make sure the island hasn\'t fallen into enemy hands. Knights Radiant who fly too near find their Stormlight suddenly drained, so the voyage must be by sea'),
(28, 'Legion Versus Phalanx', 'Myke Cole', 'Osprey', 288, 'History', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/91FZPeyzn0L.jpg', 'From the time of ancient Sumeria, the heavy infantry Phalanx dominated the battlefield. Armed with spears or pikes, standing shoulder-to-shoulder and with overlapping shields, they presented an impenetrable wall of metal to the enemy until the Roman legion eclipsed the phalanx as the masters of infantry battle. Covering the period in which the legion and phalanx clashed (280-168 BC), this book looks at each formation in detail - delving into their tactics, arms and equipment, organization and deployment. It then examines six documented battles in which the legion fought the phalanx: Heraclea (280 BC), Asculum (279 BC), Beneventum (275 BC), Cynoscephalae (197 BC), Magnesia (190 BC), and Pydna (168 BC).'), -- Fun Read
(29, 'The Fall Of Carthage', 'Adrian Goldsworthy', 'Phoenix',  416, 'History', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/81PUv5VBl1L.jpg', 'The struggle between Rome and Carthage in the Punic Wars was arguably the greatest and most desperate conflict of antiquity. The forces involved and the casualties suffered by both sides were far greater than in any wars fought before the modern era, while the eventual outcome had far-reaching consequences for the history of the Western World, namely the ascendancy of Rome. An epic of war and battle, this is also the story of famous generals and leaders: Hannibal, Fabius Maximus, Scipio Africanus, and his grandson Scipio Aemilianus, who would finally bring down the walls of Carthage. '),
(30, 'The Final Empire', 'Brandon Sanderson', 'TOR', 541, 'Fantasy', 7, 1, true, 1, 'https://m.media-amazon.com/images/I/51c8b8Bn7wL.jpg', 'For a thousand years the ash fell and no flowers bloomed. For a thousand years the Skaa slaved in misery and lived in fear. For a thousand years the Lord Ruler, the "Sliver of Infinity," reigned with absolute power and ultimate terror, divinely invincible. Then, when hope was so long lost that not even its memory remained, a terribly scarred, heart-broken half-Skaa rediscovered it in the depths of the Lord Ruler\'s most hellish prison. Kelsier \"snapped\" and found in himself the powers of a Mistborn. A brilliant thief and natural leader, he turned his talents to the ultimate caper, with the Lord Ruler himself as the mark.'),
(31, 'The Well of Ascension', 'Brandon Sanderson', 'TOR', 590, 'Fantasy', 7, 2, true, 1, 'https://m.media-amazon.com/images/I/51c8b8Bn7wL.jpg', ' The impossible has been accomplished. The Lord Ruler—the man who claimed to be god incarnate and brutally ruled the world for a thousand years—has been vanquished. But Kelsier, the hero who masterminded that triumph, is dead too, and now the awesome task of building a new world has been left to his young protégé, Vin, the former street urchin who is now the most powerful Mistborn in the land, and to the idealistic young nobleman she loves. As Kelsier\'s protégé and slayer of the Lord Ruler she is now venerated by a budding new religion, a distinction that makes her intensely uncomfortable. Even more worrying, the mists have begun behaving strangely since the Lord Ruler died, and seem to harbor a strange vaporous entity that haunts her.'),
(32, 'The Hero of Ages', 'Brandon Sanderson', 'TOR', 572, 'Fantasy', 7, 3, true, 1, 'https://images-na.ssl-images-amazon.com/images/I/81RPa1UMMwL.jpg', 'To end the Final Empire and restore freedom, Vin killed the Lord Ruler. But as a result, the Deepness—the lethal form of the ubiquitous mists—is back, along with increasingly heavy ashfalls and ever more powerful earthquakes. Humanity appears to be doomed. Having escaped death at the climax of The Well of Ascension only by becoming a Mistborn himself, Emperor Elend Venture hopes to find clues left behind by the Lord Ruler that will allow him to save the world. Vin is consumed with guilt at having been tricked into releasing the mystic force known as Ruin from the Well. Ruin wants to end the world, and its near omniscience and ability to warp reality make stopping it seem impossible. Vin can\'t even discuss it with Elend lest Ruin learn their plans!'),
(33, 'The Paradise Snare', 'AC Crispin', 'Del Ray', 306, 'Science Fiction', 8,1,true,2, 'https://static.wikia.nocookie.net/starwars/images/6/62/The_Paradise_Snare_cover.jpg/revision/latest?cb=20100105134300', 'The first book in this exciting Han Solo series begins with a recounting of Han\'s late teen years and shows us how he escaped an unhappy adopted home situation to carve out an adventurous new life for himself as a pilot. Han Solo, the handsome rogue, is every girl\'s dream man, and every boy\'s hero. '),
(34, 'The Hutt Gambit', 'AC Crispin', 'Del Ray', 340, 'Science Fiction', 8,2,true,2, 'https://static.wikia.nocookie.net/starwars/images/8/83/The_Hutt_Gambit_cover.jpg/revision/latest?cb=20100105134206', 'Solo is now a fugitive from the Imperial Navy. But he has made a valuable friend in a former Wookiee slave named Chewbacca, who has sworn Han a life debt. Han will need all the help he can get. For the Ylesian Hutts have dispatched the dreaded bounty hunter Boba Fett to track down the man who already outsmarted them once. But Han and Chewie find themselves in even bigger trouble when they agree to lend their services to the crime lords Jiliac and Jabba the Hutt. Suddenly the two smugglers are thrust into the middle of a battle between the might of the Empire and the treachery of their outlaw allies...a battle where even victory means death!'),
(35, 'The Rebel Dawn', 'AC Cripsin', 'Del Ray', 389, 'Science Fiction', 8,3,true,2, 'https://images-na.ssl-images-amazon.com/images/I/51txBUZkqUL._SX300_BO1,204,203,200_.jpg', 'The Millennium Falcon is "the fastest hunk of junk in the galaxy."  So when Han Solo wins it in a game of sabacc, he and Chewbacca become kings of the smugglers—uncatchable, unstoppable.  But with the Empire clamping down, Han knows his luck can\'t last.  Still, when an old girlfriend who is now the leader of an insurgent Rebel group offers him a shot at an incredible fortune, Han can\'t resist. The plan seems a sure thing. The resistance will be light and the take enormous. Han and his friends will divide it equally with the Rebels.  '),
(36, 'Unlocking Android', 'W. Frank Ableson', 'GenericPublisher', 416, 'Technology', NULL,1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/51Z8+Z3n6IL._SX397_BO1,204,203,200_.jpg', 'Android is a free, open source, Java-based mobile platform developed by Google. Unlocking Android prepares the reader to embrace the Android mobile platform in easy-to-understand language and builds on this foundation with reusable Java code examples. It\'s ideal for corporate developers and hobbyists who have an interest, or a mandate, to deliver mobile software.The book first covers the big picture so the reader can get comfortable with the Android approach to the mobile applications. Then the reader learns to apply Android by following the many practical examples. The book concludes with two deep and hands-on examples'),
(37, 'Forests and Keyboards', 'GenName', 'genPublisher', 375,'Technology', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/41Tqpv3a4pL._SX397_BO1,204,203,200_.jpg', 'A combination of Fantasy fiction and an explanation of modern technology, Griffon in Action is a very odd book to read. On one page, you\ll read the story of Wyn Norgwyn, heir to the Kingdom of Vadora, its merchant network that streches from tundra to rainforest, and the harbour-city of Adorellan. On the other, you\'ll read about the push towards centralization and redundancy, and how Griffon, a cloud computing technology is driving this. Uncohesive, but somehow riveting'), -- Also not a real book. I made the description up because it seemed funny
(38, 'From Gutenberg to Zuckerberg: Disruptive Innovation in the Age of the Internet', 'John Naughton', 'Quercus', 352, 'Technology and History', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/41KufcM-vxL.jpg', 'John Naughton is the Observer\'s \'Networker\' columnist, a prominent blogger, and Vice-President of Wolfson College, Cambridge. The Times has said that his writings, “[it] draws on more than two decades of study to explain how the internet works and the challenges and opportunities it will offer to future generations,” and Cory Doctrow raved that \"this is the kind of primer you want to slide under your boss’s door.\" In From Gutenberg to Zuckerberg, Naughton explores the living history of one of the most radically transformational technologies of all time.'), -- Haven't read this in years, but I recall it being very interesting. Need to reread, definitely worth taking a look at
(39, 'the Wealth of Nations', 'Adam Smith', 'University of Chicago', 1076, 'Economics', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/71ANvymikVL.jpg', 'Adam Smith\'s masterpiece, first published in 1776, is the foundation of modern economic thought and remains the single most important account of the rise of, and the principles behind, modern capitalism. Written in clear and incisive prose, The Wealth of Nations articulates the concepts indispensable to an understanding of contemporary society; and Robert Reich\'s Introduction both clarifies Smith\'s analyses and illuminates his overall relevance to the world in which we live. As Reich writes, "Smith\'s mind ranged over issues as fresh and topical today as they were in the late eighteenth century--jobs, wages, politics, government, trade, education, business, and ethics.'),
(40, 'Guns, Germs, and Steel: The Fates of Human Societies', 'Jared Diamond', 'W.W Norton', 498, 'Anthropology', 0, 1, true, 0, 'https://m.media-amazon.com/images/I/51-KH9CL1SL.jpg', 'In this "artful, informative, and delightful" (William H. McNeill, New York Review of Books) book, Jared Diamond convincingly argues that geographical and environmental factors shaped the modern world. Societies that had a head start in food production advanced beyond the hunter-gatherer stage, and then developed writing, technology, government, and organized religion—as well as nasty germs and potent weapons of war—and adventured on sea and land to conquer and decimate preliterate cultures. A major advance in our understanding of human societies, Guns, Germs, and Steel chronicles the way that the modern world came to be and stunningly dismantles racially based theories of human history.'), -- Interesting book. An anthropological look at history is fascinating. Diamond doesn't use citations, which means the reliability of his work can not be measured with ease, and most of his arguments rely on the ability to domesticate animals and plants
(41, 'A Promised Land', 'Barack Obama', 'Crown', 768, 'Politics', 0, 1, true, 0, 'https://images-na.ssl-images-amazon.com/images/I/91QAA9JXFJL.jpg', 'In the stirring, highly anticipated first volume of his presidential memoirs, Barack Obama tells the story of his improbable odyssey from young man searching for his identity to leader of the free world, describing in strikingly personal detail both his political education and the landmark moments of the first term of his historic presidency—a time of dramatic transformation and turmoil.'),
(42, 'White Tears/Brown Scars', 'Ruby Hamad', 'Catapult', 304, 'Feminism and Race', NULL, 1, true, NULL, 'https://m.media-amazon.com/images/I/41mgMHSbIxL.jpg', ' For readers of White Fragility, White Tears/Brown Scars is an explosive book of history and cultural criticism that argues that white feminism, from Australia to Zimbabwe to the United States, has been a weapon of white supremacy and patriarchy deployed against black and indigenous women, and women of color.'), -- Good book
(43, 'The Jungle', 'Upton Sinclair', 'See Sharp Press', 335, 'Historical Fiction, Social Justice', NULL, 1, true, NULL, 'https://m.media-amazon.com/images/M/MV5BMTc3NTUzNTI4MV5BMl5BanBnXkFtZTgwNjU0NjU5NzE@._V1_FMjpg_UX1000_.jpg', 'The Jungle is a 1906 novel written by the American journalist and novelist Upton Sinclair (1878–1968). Sinclair wrote the novel to portray the lives of immigrants in the United States in Chicago and similar industrialized cities. Many readers were most concerned with his exposure of health violations and unsanitary practices in the American meatpacking industry during the early 20th century, based on an investigation he did for a socialist newspaper. The book depicts working class poverty, the lack of social supports, harsh and unpleasant living and working conditions, and a hopelessness among many workers. These elements are contrasted with the deeply rooted corruption of people in power. A review by the writer Jack London called it, "the Uncle Tom\'s Cabin of wage slavery.'),
(44, 'Abolition. Feminism. Now.', 'Angela Davis', 'Haymarket Books', 150, 'Activism', NULL, 1, true, NULL, 'https://m.media-amazon.com/images/M/MV5BMTc3NTUzNTI4MV5BMl5BanBnXkFtZTgwNjU0NjU5NzE@._V1_FMjpg_UX1000_.jpg', 'As a politic and a practice, abolition increasingly shapes our political moment—halting the construction of new jails and propelling movements to divest from policing. Yet erased from this landscape are the central histories of feminist organizing—usually queer, anti-capitalist, grassroots, and women of color—that continue to cultivate abolition. Also erased is a recognition of the stark reality: abolition is our best response to endemic forms of state and interpersonal gender and sexual violence.'), -- Haven't read, but is going on my to-read book. I believe abolition here is referring to the prison abolition movement
(45, 'Annihilation of Caste', 'B.R. Ambedkar', 'Verso', 415, 'Sociology', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/71N3gHxDlFL.jpg', 'B.R. Ambedkar’s Annihilation of Caste is one of the most important, yet neglected, works of political writing from India. Written in 1936, it is an audacious denunciation of Hinduism and its caste system. Ambedkar – a figure like W.E.B. Du Bois – offers a scholarly critique of Hindu scriptures, scriptures that sanction a rigidly hierarchical and iniquitous social system. The world’s best-known Hindu, Mahatma Gandhi, responded publicly to the provocation. The hatchet was never buried.'), -- Also going on to read
(46, 'The End of Everything (Astrophysically Speaking)', 'Katie Mack', 'Scribner', 240, 'Astronomy', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/81ALQQbp10L.jpg', ' From one of the most dynamic rising stars in astrophysics, an accessible and eye-opening look—in the bestselling tradition of Sean Carroll and Carlo Rovelli—at the five different ways the universe could end, and the mind-blowing lessons each scenario reveals about the most important concepts in physics.'), -- Theoretically fascinating, but astronomy and physics isn't something I have a background in
(47, 'What If? Serious Scientific Answers to Absurd Hypothetical Questions ', 'Randal Munroe', 'Mariner Books', 303, 'Science Humour', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/41CbMLtLgQL._SX258_BO1,204,203,200_.jpg', ' Randall Munroe left NASA in 2005 to start up his hugely popular site XKCD \'a web comic of romance, sarcasm, math and language\' which offers a witty take on the world of science and geeks. It now has 600,000 to a million page hits daily. Every now and then, Munroe would get emails asking him to arbitrate a science debate. \'My friend and I were arguing about what would happen if a bullet got struck by lightning, and we agreed that you should resolve it . . . \' He liked these questions so much that he started up What If. '), -- While I haven't read this particular work, XKCD was one of my first looks at certain technology, and definitely something I'd recommend any nerd take a look at. explainxkcd might be better, as its substantially more informative. XKCD comics cover a vast array of topics
(48, 'Roger II of Sicily: A Ruler between East and West', 'Hubert Houben', 'Cambridge University Press', 260, 'Biographies', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/51nupZ9KhwL._SX306_BO1,204,203,200_.jpg', ' This is a scholarly and up-to-date narrative of the epic reign of the Norman King Roger II, the founder of the kingdom of Sicily during the first half of the twelfth century. It is a thoughtful analysis of the kingdom\'s mixed east-west culture and the development of its royal government; the most advanced in twelfth-century Europe. Although many recent studies have addressed important aspects of medieval southern Italy, this is the first work in nearly ninety years to be devoted specifically to Roger\'s life and reign.'), -- The Normon Kingdom of Sicily is fascinating. If I recall correctly, Roger II sponsered the Tabula Rogeriana, a 12th century map depicting regions from Iberia to the Indian Ocean that was one of the most accurate maps for centuries at least
(49, 'Breakfast with Seneca: A Stoic Guide to the Art of Living', 'David Fideler', 'W.W. Norton Company', 288, 'Psychology', NULL, 1, true, NULL, 'https://images-na.ssl-images-amazon.com/images/I/61a7JwmbrKL.jpg', ' Stoicism, the most influential philosophy of the Roman Empire, offers refreshingly modern ways to strengthen our inner character in the face of an unpredictable world. Widely recognized as the most talented and humane writer of the Stoic tradition, Seneca teaches us to live with freedom and purpose. His most enduring work, over a hundred “Letters from a Stoic” written to a close friend, explains how to handle adversity; overcome grief, anxiety, and anger; transform setbacks into opportunities for growth; and recognize the true nature of friendship.'),
(50, 'Mindset: The New Psychology of Success', 'Carol S. Dweck', 'Ballantine', 320, 'Self-Improvement', NULL, 1, true, NULL, 'https://m.media-amazon.com/images/I/41vS70Qo3rL.jpg', 'After decades of research, world-renowned Stanford University psychologist Carol S. Dweck, Ph.D., discovered a simple but groundbreaking idea: the power of mindset. In this brilliant book, she shows how success in school, work, sports, the arts, and almost every area of human endeavor can be dramatically influenced by how we think about our talents and abilities. People with a fixed mindset — those who believe that abilities are fixed — are less likely to flourish than those with a growth mindset — those who believe that abilities can be developed. Mindset reveals how great parents, teachers, managers, and athletes can put this idea to use to foster outstanding accomplishment.'); -- One of the materials from the week 2 jump packet
COMMIT;

INSERT INTO users VALUES
(1, 'admin', 'password'),
(2, 'calvinHerrick', 'password'),
(3, 'siobhanKelsie', 'password'),
(4, 'rohanKurup', 'password'),
(5, 'ericSei', 'password'),
(6, 'pasangSherpa', 'password'),
(7, 'williamSidleyParker', 'password'),
(8, 'nathan', 'password'),
(9, 'jamesArlock', 'HorseBatteryStaple');
COMMIT;


INSERT INTO userBooks VALUES
(4, 3, 399,  'f', 10),
(4, 4, 464, 'f', 10),
(4, 12, 656,  'f', 10),
(4, 13, 0, 'p', -1),
(4, 29, 153,  's', -1),
(4, 24, 0, 'p', -1),
(4, 23, 0, 'f', 6),
(4, 7, 434, 'f', 6),
(4, 8, 439, 'f', 6),
(4, 9, 467, 'f', 6),
(4, 33, 306, 'f', 7),
(4, 34, 389, 'f', 7),
(7, 7, 434, 'f', 9),
(7, 8, 439, 'f', 9), 
(7, 9, 467, 'f', 10),
(9, 3, 399, 'f', 7),
(8, 3, 399, 'f', 6),
(7, 3, 399, 'f', 9),
(6, 3, 399, 'f', 8),
(5, 3, 399, 'f', 2),
(4, 3, 109, 's', -1),
(3, 3, 0, 'p', -1),
(2, 3, 0, 'p', -1);
COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION;
SET SQL_NOTES=@OLD_SQL_NOTES; 