package org.wecancodeit.reviewstagscomments;


import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ReviewsPopulator implements CommandLineRunner {
	
	
		
		@Resource
		private ReviewRepository reviewRepo;
		
		@Resource
		private TagRepository tagRepo;

		@Override
		public void run(String... args) throws Exception {
			
			Tag mmo = new Tag("Massive Multiplayer Online");
			mmo = tagRepo.save(mmo);
			
			Tag fps = new Tag("First Person Shooter");
			fps = tagRepo.save(fps);
			
			Tag platformer = new Tag("Platformer");
			platformer = tagRepo.save(platformer);
			
			Tag farmingSim = new Tag("Farming Simulator");
			farmingSim = tagRepo.save(farmingSim);
			
			Tag rpg = new Tag("RPG");
			rpg = tagRepo.save(rpg);
			
			Review banjoKazooie = new Review("Banjo-Kazooie", 
					"Banjo-Kazooie is just one of those games that come around every so often and blow your mind. "
					+ "The story was excellent, the puzzles were challenging enough but rewarding, and the soundtrack remains to date my favorite of all time."
					+ " Banjo-Kazooie was my gateway into the world of hardcore video gaming and I've never looked back."
					, "BanjoKazooie", platformer);
			
			banjoKazooie = reviewRepo.save(banjoKazooie);		
			
			
			Review stardewValley = new Review("Stardew Valley",
					"Farming Simulation RPG Stardew Valley took my love of Harvest Moon and RPGs" 
					+ " and made a beautiful harmony of both. It is a wonderfully relaxing game with a great soundtrack and game atmosphere."
					+ " I am ashamed to admit the amount of hours I spent on my artisinal mayonnaise operation, but I have no regrets.", "SDV", farmingSim, rpg);
			
			stardewValley = reviewRepo.save(stardewValley);
			
			Review halo2 = new Review("Halo 2", 
					"Generally speaking, video game sequels can be pretty disappointing."
					+ " Halo 2 is one of the few instances where the sequel outshone the original in just about all regards."
					+ " The mutliplayer aspect was excellent, allowing for couch co-op through the campaign, which is something I appreciate because"
					+ " it allowed me to spend time with my brother without beating each other up or bribing the other sibling for game time."
					+ " Some of my best memories were playing zombies on a LAN at my friends house with mutliple TVs and room setups.", "halo2", fps);
			
			halo2 = reviewRepo.save(halo2);
			
			Review blackDesertOnline = new Review("Black Desert Online",
					"Black Desert Online is one of the most visually appealing MMOs that I have played to date."
					+ " The concept of the game is centered around RNG-weighted gear-progression, and most players have an end goal of high-tier PVP."
					+ " The biggest downfall of the game is the lack of end-game PVE content, but the PVP is satisfying enough to make up for it. "
					,"BlackDesertOnline", mmo, rpg);
			
			blackDesertOnline = reviewRepo.save(blackDesertOnline);
			
			Review worldOfWarcraft = new Review("World of Warcraft", 
					"World of Warcraft is the video game that comes to mind when anyone says MMO."
					+ " It has withstood the test of time and is still the most popular MMO around, with competitive dungeons, PVP, and raiding."
					+ " The graphics and character customization leave a little to be desired for a modern-day MMO, "
					+ " but the end-game content is plentiful for both the hardcore and casual player alike."
					+ "In its present iteration, class design is pretty lacking with a lot of downtime in DPS and tanking rotations but it's still a great game."
					, "Sarazzlin", mmo, rpg);
			
			worldOfWarcraft = reviewRepo.save(worldOfWarcraft);
			
			
			
		}
		
		}



