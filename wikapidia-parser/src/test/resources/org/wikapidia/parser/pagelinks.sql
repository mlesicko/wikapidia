-- MySQL dump 10.13  Distrib 5.1.66, for debian-linux-gnu (x86_64)
--
-- Host: 10.0.6.49    Database: simplewiki
-- ------------------------------------------------------
-- Server version	5.5.30-MariaDB-mariadb1~precise-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pagelinks`
--

DROP TABLE IF EXISTS `pagelinks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagelinks` (
  `pl_from` int(8) unsigned NOT NULL DEFAULT '0',
  `pl_namespace` int(11) NOT NULL DEFAULT '0',
  `pl_title` varbinary(255) NOT NULL DEFAULT '',
  UNIQUE KEY `pl_from` (`pl_from`,`pl_namespace`,`pl_title`),
  KEY `pl_namespace` (`pl_namespace`,`pl_title`,`pl_from`)
) ENGINE=InnoDB DEFAULT CHARSET=binary;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagelinks`
--

/*!40000 ALTER TABLE `pagelinks` DISABLE KEYS */;
INSERT INTO `pagelinks` VALUES (1,0,'1320'),(1,0,'1502'),(1,0,'1509'),(1,0,'1519'),(1,0,'1533'),(1,0,'1721'),(1,0,'1722'),(1,0,'1789'),(1,0,'1803'),(1,0,'1814'),(1,0,'1815'),(1,0,'1830'),(1,0,'1841'),(1,0,'1861'),(1,0,'1865'),(1,0,'1897'),(1,0,'1902'),(1,0,'1906'),(1,0,'1906_San_Francisco_earthquake'),(1,0,'1908'),(1,0,'1909'),(1,0,'1912'),(1,0,'1915'),(1,0,'1916'),(1,0,'1918'),(1,0,'1930'),(1,0,'1937'),(1,0,'1940'),(1,0,'1943'),(1,0,'1945'),(1,0,'1946'),(1,0,'1947'),(1,0,'1952'),(1,0,'1954'),(1,0,'1960'),(1,0,'1961'),(1,0,'1964'),(1,0,'1968'),(1,0,'1970'),(1,0,'1971'),(1,0,'1973'),(1,0,'1974'),(1,0,'1975'),(1,0,'1976'),(1,0,'1979'),(1,0,'1980'),(1,0,'1982'),(1,0,'1986'),(1,0,'1989'),(1,0,'1990'),(1,0,'1991'),(1,0,'1993'),(1,0,'1994'),(1,0,'1995'),(1,0,'1999'),(1,0,'2001'),(1,0,'2005'),(1,0,'2010'),(1,0,'2011'),(1,0,'2013'),(1,0,'2014'),(1,0,'20th_century'),(1,0,'753_BC'),(1,0,'ANZAC_Day'),(1,0,'Abraham_Lincoln'),(1,0,'Adolf_Hitler'),(1,0,'Albert_Hofmann'),(1,0,'Alphabet'),(1,0,'American_Civil_War'),(1,0,'American_Samoa'),(1,0,'Angola'),(1,0,'Anne_Frank'),(1,0,'Aphrodite'),(1,0,'Apple_Inc.'),(1,0,'April_1'),(1,0,'April_10'),(1,0,'April_11'),(1,0,'April_12'),(1,0,'April_13'),(1,0,'April_14'),(1,0,'April_15'),(1,0,'April_16'),(1,0,'April_17'),(1,0,'April_18'),(1,0,'April_19'),(1,0,'April_2'),(1,0,'April_20'),(1,0,'April_21'),(1,0,'April_22'),(1,0,'April_23'),(1,0,'April_24'),(1,0,'April_25'),(1,0,'April_26'),(1,0,'April_27'),(1,0,'April_28'),(1,0,'April_29'),(1,0,'April_3'),(1,0,'April_30'),(1,0,'April_4'),(1,0,'April_5'),(1,0,'April_6'),(1,0,'April_7'),(1,0,'April_8'),(1,0,'April_9'),(1,0,'April_Fools\'_Day'),(1,0,'Arbroath'),(1,0,'Argentina'),(1,0,'Aries'),(1,0,'Armenia'),(1,0,'Army'),(1,0,'Assassination'),(1,0,'August'),(1,0,'Australia'),(1,0,'Autism'),(1,0,'Autumn'),(1,0,'Baha\'i'),(1,0,'Bangladesh'),(1,0,'Barbados'),(1,0,'Basque_Country'),(1,0,'Battle'),(1,0,'Beatrix_of_the_Netherlands'),(1,0,'Belgrade'),(1,0,'Benito_Mussolini'),(1,0,'Berlin'),(1,0,'Bicycle'),(1,0,'Birthday'),(1,0,'Birthstone'),(1,0,'Book'),(1,0,'Boston,_Massachusetts'),(1,0,'Boston_Marathon'),(1,0,'Brazil'),(1,0,'Brighton'),(1,0,'Buddha'),(1,0,'Buddhism'),(1,0,'Calendar'),(1,0,'California'),(1,0,'Cambodia'),(1,0,'Canada'),(1,0,'Cannabis'),(1,0,'Carl_XVI_Gustaf_of_Sweden'),(1,0,'Catalonia'),(1,0,'Catherine,_Duchess_of_Cambridge'),(1,0,'Catherine_of_Aragon'),(1,0,'Chernobyl'),(1,0,'Child'),(1,0,'Chile'),(1,0,'China'),(1,0,'Christianity'),(1,0,'Church_of_England'),(1,0,'Common_year'),(1,0,'Commonwealth_realm'),(1,0,'Confederate_States_of_America'),(1,0,'Conservation'),(1,0,'Copenhagen'),(1,0,'Cosmonaut'),(1,0,'Country'),(1,0,'Culture'),(1,0,'Cyclone'),(1,0,'DNA'),(1,0,'Daisy'),(1,0,'Dance'),(1,0,'Day'),(1,0,'Daylight_Saving_Time'),(1,0,'December'),(1,0,'Deepwater_Horizon_oil_spill'),(1,0,'Denmark'),(1,0,'Diamond'),(1,0,'Diary'),(1,0,'Dublin'),(1,0,'Earth_Day'),(1,0,'Earthquake'),(1,0,'Easter'),(1,0,'Easter_Island'),(1,0,'Easter_Rising'),(1,0,'Elba'),(1,0,'Elizabeth_II'),(1,0,'Emperor'),(1,0,'England'),(1,0,'English_language'),(1,0,'Enschede'),(1,0,'Ethiopia'),(1,0,'Europe'),(1,0,'Exile'),(1,0,'Eyjafjallajokull'),(1,0,'Falkland_Islands'),(1,0,'Falklands_War'),(1,0,'Faroe_Islands'),(1,0,'Father_Damien'),(1,0,'February'),(1,0,'Female'),(1,0,'Finnish_language'),(1,0,'Fire'),(1,0,'Flag'),(1,0,'Fletcher_Christian'),(1,0,'Florida'),(1,0,'Flower'),(1,0,'France'),(1,0,'Franklin_D._Roosevelt'),(1,0,'Frederick_Cook'),(1,0,'Gabon'),(1,0,'Gallipoli'),(1,0,'Gambia'),(1,0,'George_Washington'),(1,0,'Georgia_(country)'),(1,0,'Georgian_language'),(1,0,'Germany'),(1,0,'Grand_National'),(1,0,'Great_Britain'),(1,0,'Gulf_of_Mexico'),(1,0,'Haile_Selassie'),(1,0,'Harry_S._Truman'),(1,0,'Hawaii'),(1,0,'Health'),(1,0,'Hemisphere'),(1,0,'Hemophilia'),(1,0,'Henri,_Grand_Duke_of_Luxembourg'),(1,0,'Henry_VIII_of_England'),(1,0,'Hirohito'),(1,0,'Holy_Week'),(1,0,'Horse_racing'),(1,0,'Hubble_Space_Telescope'),(1,0,'Human'),(1,0,'Iceland'),(1,0,'Inauguration'),(1,0,'India'),(1,0,'Indonesia'),(1,0,'Iran'),(1,0,'Ireland'),(1,0,'Islamic_Republic_of_Iran'),(1,0,'Island'),(1,0,'Italy'),(1,0,'Jackie_Robinson'),(1,0,'Jacob_Roggeveen'),(1,0,'January'),(1,0,'January_1'),(1,0,'Japan'),(1,0,'Jazz'),(1,0,'John_Muir'),(1,0,'John_Wilkes_Booth'),(1,0,'Judaism'),(1,0,'Juliana_of_the_Netherlands'),(1,0,'July'),(1,0,'June'),(1,0,'June_3'),(1,0,'Kentucky_Derby'),(1,0,'Kenya'),(1,0,'Kim_Il-Sung'),(1,0,'King'),(1,0,'Kon-Tiki'),(1,0,'LSD'),(1,0,'Laos'),(1,0,'Latin'),(1,0,'Leap_year'),(1,0,'Lech_Kaczynski'),(1,0,'London'),(1,0,'Louisiana'),(1,0,'Love'),(1,0,'Madrid'),(1,0,'Maine'),(1,0,'Major_League_Baseball'),(1,0,'Malaria'),(1,0,'Marathon'),(1,0,'March'),(1,0,'March_1'),(1,0,'March_15'),(1,0,'March_19'),(1,0,'March_20'),(1,0,'March_21'),(1,0,'March_22'),(1,0,'March_23'),(1,0,'Margrethe_II_of_Denmark'),(1,0,'Marie_Curie'),(1,0,'Martin_Luther_King,_Jr.'),(1,0,'Massachusetts'),(1,0,'May'),(1,0,'May_21'),(1,0,'May_8'),(1,0,'Memphis,_Tennessee'),(1,0,'Mexico'),(1,0,'Mobile_phone'),(1,0,'Monarch'),(1,0,'Month'),(1,0,'Monument'),(1,0,'Mormonism'),(1,0,'Morocco'),(1,0,'Mother\'s_Day'),(1,0,'Mozambique'),(1,0,'Mswati_III'),(1,0,'Napoleon_Bonaparte'),(1,0,'Nazi'),(1,0,'Nepal'),(1,0,'Netherlands'),(1,0,'New_York_City'),(1,0,'New_Zealand'),(1,0,'New_year'),(1,0,'Newfoundland'),(1,0,'Newspaper'),(1,0,'North_Korea'),(1,0,'North_Pole'),(1,0,'North_Vietnam'),(1,0,'Northern_Hemisphere'),(1,0,'Norway'),(1,0,'November'),(1,0,'Nunavut'),(1,0,'October'),(1,0,'Oil'),(1,0,'Oklahoma_City_bombing'),(1,0,'Orthodox'),(1,0,'Outer_space'),(1,0,'Pablo_Picasso'),(1,0,'Pacific_Ocean'),(1,0,'Paris'),(1,0,'Passover'),(1,0,'Pedro_Alvares_Cabral'),(1,0,'Peru'),(1,0,'Pierre_Curie'),(1,0,'Plane'),(1,0,'Poetry'),(1,0,'Poland'),(1,0,'Polynesia'),(1,0,'Pope'),(1,0,'Pope_Benedict_XVI'),(1,0,'Pope_John_Paul_II'),(1,0,'Portugal'),(1,0,'President'),(1,0,'President_of_the_United_States'),(1,0,'Prime_Minister'),(1,0,'Prince_William,_Duke_of_Cambridge'),(1,0,'Qinghai'),(1,0,'RMS_Titanic'),(1,0,'Radiation'),(1,0,'Radium'),(1,0,'Ramadan'),(1,0,'Rastafari_movement'),(1,0,'Reichstag'),(1,0,'Republic'),(1,0,'Ridran'),(1,0,'Robert_E._Lee'),(1,0,'Robert_Peary'),(1,0,'Robert_Walpole'),(1,0,'Rome'),(1,0,'Rotterdam'),(1,0,'Royal_Air_Force'),(1,0,'Russia'),(1,0,'Rwanda'),(1,0,'Rwandan_Genocide'),(1,0,'Saigon'),(1,0,'Saint_George'),(1,0,'Same-sex_marriage'),(1,0,'San_Francisco'),(1,0,'Sardinia'),(1,0,'Scotland'),(1,0,'Season'),(1,0,'Senegal'),(1,0,'September'),(1,0,'Serbia'),(1,0,'Ship'),(1,0,'Sierra_Leone'),(1,0,'Snooker'),(1,0,'South_Africa'),(1,0,'South_America'),(1,0,'South_Carolina'),(1,0,'Southeast_Asia'),(1,0,'Southern_Hemisphere'),(1,0,'Soviet'),(1,0,'Space_Shuttle_Discovery'),(1,0,'Spain'),(1,0,'Spanish_Civil_War'),(1,0,'Spring'),(1,0,'Summer'),(1,0,'Sunday'),(1,0,'Swaziland'),(1,0,'Sweden'),(1,0,'Sweet_Pea'),(1,0,'Sweet_pea'),(1,0,'Switzerland'),(1,0,'Syria'),(1,0,'Tambora'),(1,0,'Tanganyika'),(1,0,'Tanzania'),(1,0,'Tapir'),(1,0,'Taurus'),(1,0,'Tax'),(1,0,'Tbilisi'),(1,0,'Territory'),(1,0,'Texas'),(1,0,'Thailand'),(1,0,'Thomas_Jefferson'),(1,0,'Thor_Heyerdahl'),(1,0,'Timothy_McVeigh'),(1,0,'Togo'),(1,0,'Trafalgar_Square'),(1,0,'Tree'),(1,0,'Tunisia'),(1,0,'Turkey'),(1,0,'UK'),(1,0,'Ukraine'),(1,0,'United_Kingdom'),(1,0,'United_States'),(1,0,'Utrecht_(city)'),(1,0,'Vietnam_War'),(1,0,'Voice'),(1,0,'Walpurgis_Night'),(1,0,'Washington,_DC'),(1,0,'Willem-Alexander_of_the_Netherlands'),(1,0,'William_Henry_Harrison'),(1,0,'Winter'),(1,0,'Wisconsin'),(1,0,'World_War_I'),(1,0,'World_War_II'),(1,0,'Year'),(1,0,'Yuri_Gagarin'),(1,0,'Zanzibar'),(1,0,'Zimbabwe'),(1,0,'Zurich'),(2,0,'1291'),(2,0,'14'),(2,0,'1492'),(2,0,'153_BC'),(2,0,'1792'),(2,0,'1809'),(2,0,'1825'),(2,0,'1883'),(2,0,'1914'),(2,0,'1919'),(2,0,'1940'),(2,0,'1944'),(2,0,'1945'),(2,0,'1947'),(2,0,'1957'),(2,0,'1959'),(2,0,'1960'),(2,0,'1961'),(2,0,'1962'),(2,0,'1963'),(2,0,'1965'),(2,0,'1968'),(2,0,'1971'),(2,0,'1974'),(2,0,'1977'),(2,0,'1984'),(2,0,'1990'),(2,0,'1991'),(2,0,'1997'),(2,0,'2005'),(2,0,'43_BC'),(2,0,'45_BC'),(2,0,'79'),(2,0,'Afghanistan'),(2,0,'Alabama'),(2,0,'Amsterdam'),(2,0,'Anne_Frank'),(2,0,'April'),(2,0,'Argentina'),(2,0,'Assumption_of_Mary'),(2,0,'August_1'),(2,0,'August_10'),(2,0,'August_11'),(2,0,'August_12'),(2,0,'August_13'),(2,0,'August_14'),(2,0,'August_15'),(2,0,'August_16'),(2,0,'August_17'),(2,0,'August_18'),(2,0,'August_19'),(2,0,'August_2'),(2,0,'August_20'),(2,0,'August_21'),(2,0,'August_22'),(2,0,'August_23'),(2,0,'August_24'),(2,0,'August_25'),(2,0,'August_26'),(2,0,'August_27'),(2,0,'August_28'),(2,0,'August_29'),(2,0,'August_3'),(2,0,'August_30'),(2,0,'August_31'),(2,0,'August_4'),(2,0,'August_5'),(2,0,'August_6'),(2,0,'August_7'),(2,0,'August_8'),(2,0,'August_9'),(2,0,'Augustus'),(2,0,'Augustus_Caesar'),(2,0,'Bahamas'),(2,0,'Bahrain'),(2,0,'Barbados'),(2,0,'Benin'),(2,0,'Berlin_Wall'),(2,0,'Bermuda'),(2,0,'Bolivia'),(2,0,'Brazil'),(2,0,'Burkina_Faso'),(2,0,'Calendar'),(2,0,'Canada'),(2,0,'Celts'),(2,0,'Central_African_Republic'),(2,0,'Chad'),(2,0,'Christianity'),(2,0,'Christopher_Columbus'),(2,0,'Colorado'),(2,0,'Common_year'),(2,0,'Croatia'),(2,0,'Dahomey'),(2,0,'Day'),(2,0,'December'),(2,0,'Decemvirs'),(2,0,'Diana,_Princess_of_Wales'),(2,0,'Ecuador'),(2,0,'Edinburgh'),(2,0,'Edinburgh_Castle'),(2,0,'Elvis_Presley'),(2,0,'England'),(2,0,'Estonia'),(2,0,'February'),(2,0,'Flag'),(2,0,'French_Revolution'),(2,0,'Gabon'),(2,0,'Gerald_Ford'),(2,0,'Gestapo'),(2,0,'Gladiolus'),(2,0,'Gregorian_calendar'),(2,0,'Guyana'),(2,0,'Hawaii'),(2,0,'Hemisphere'),(2,0,'Herculaneum'),(2,0,'Hiroshima'),(2,0,'Humanitarian'),(2,0,'Hurricane_Katrina'),(2,0,'India'),(2,0,'Indonesia'),(2,0,'Iraq'),(2,0,'Islam'),(2,0,'Ivory_Coast'),(2,0,'Jamaica'),(2,0,'January'),(2,0,'January_1'),(2,0,'Japan'),(2,0,'Java'),(2,0,'Jose_de_San_Martin'),(2,0,'Julian_calendar'),(2,0,'Julius_Caesar'),(2,0,'July'),(2,0,'July_24'),(2,0,'June'),(2,0,'Kazakhstan'),(2,0,'Krakatoa'),(2,0,'Kuwait'),(2,0,'Kyrgyzstan'),(2,0,'Lammas'),(2,0,'Latin'),(2,0,'Leap_year'),(2,0,'Leon_Trotsky'),(2,0,'Liechtenstein'),(2,0,'Louisiana'),(2,0,'Malaysia'),(2,0,'March'),(2,0,'March_on_Washington_for_Jobs_and_Freedom'),(2,0,'Marilyn_Monroe'),(2,0,'Martin_Luther_King,_Jr.'),(2,0,'May'),(2,0,'Meteor');
INSERT INTO `pagelinks` VALUES (2512,11,'Wikipedia_policies_and_guidelines'),(2512,12,'Redirect'),(2512,12,'Revert_a_page'),(2512,14,'Pages_needing_cleanup'),(2512,14,'Wikipedia_guidelines'),(2512,14,'Wikipedia_policies'),(2515,2,'Mariusz_Marcel_Ernst'),(2516,2,'Mariusz_Marcel_Ernst'),(2518,2,'Mariusz_Marcel_Ernst'),(2519,2,'Mariusz_Marcel_Ernst'),(2520,2,'Mariusz_Marcel_Ernst'),(2521,2,'Mariusz_Marcel_Ernst'),(2522,2,'Mariusz_Marcel_Ernst'),(2523,2,'Mariusz_Marcel_Ernst'),(2524,2,'Mariusz_Marcel_Ernst'),(2525,2,'Mariusz_Marcel_Ernst'),(2529,0,'Text_Of_The_GNU_Free_Documentation_License'),(2529,1,'Simple_English_Lessons'),(2529,2,'Angela'),(2529,2,'Cprompt'),(2529,2,'Mariusz_Marcel_Ernst'),(2529,2,'Ricky81682'),(2529,3,'Ricky81682'),(2529,4,'Block_log'),(2529,4,'Copyrights'),(2529,4,'Deletion_log'),(2529,4,'Protection_log'),(2529,4,'Protection_policy'),(2529,10,'Wikitopics'),(2529,14,'Protected_deleted_pages'),(2530,2,'Mariusz_Marcel_Ernst'),(2532,0,'Cascading_Style_Sheets'),(2532,0,'IP'),(2532,0,'JavaScript'),(2532,0,'Legal'),(2532,0,'Main_Page'),(2532,0,'MediaWiki'),(2532,4,'Administrators_and_Bureaucrats'),(2532,4,'Blocks_and_bans'),(2532,4,'Deletion_policy'),(2532,4,'Edit_war'),(2532,4,'P'),(2532,4,'PROTECT'),(2532,4,'Policies_and_guidelines'),(2532,4,'Protected_deleted_pages'),(2532,4,'Shortcut'),(2532,4,'Transclusion'),(2532,4,'Vandalism'),(2532,5,'Protection_policy'),(2532,8,'Titleblacklist'),(2532,10,'Unblock'),(2670,2,'Mariusz_Marcel_Ernst'),(2676,2,'Mariusz_Marcel_Ernst'),(2677,2,'Mariusz_Marcel_Ernst'),(2679,2,'Mariusz_Marcel_Ernst'),(2680,2,'Mariusz_Marcel_Ernst'),(2681,2,'Angela'),(2681,4,'Useful'),(2682,0,'Irregular_numbers'),(2682,0,'Names_for_large_numbers'),(2682,0,'Names_for_small_numbers'),(2682,0,'Number'),(2682,0,'Word'),(2684,0,'Abraham_Lincoln'),(2684,0,'Alexander_Hamilton'),(2684,0,'Americans'),(2684,0,'Andrew_Jackson'),(2684,0,'Bank'),(2684,0,'Benjamin_Franklin'),(2684,0,'Bill'),(2684,0,'Celebrity'),(2684,0,'Cent_(currency)'),(2684,0,'Coin'),(2684,0,'Colors'),(2684,0,'Currency'),(2684,0,'Cycle'),(2684,0,'Declaration_of_Independence'),(2684,0,'Department_of_the_Treasury'),(2684,0,'Design'),(2684,0,'Federal_Reserve'),(2684,0,'George_Washington'),(2684,0,'Great_Seal_of_the_United_States'),(2684,0,'Grover_Cleveland'),(2684,0,'Independence_Hall'),(2684,0,'James_Madison'),(2684,0,'Lincoln_Memorial'),(2684,0,'Multimedia'),(2684,0,'One'),(2684,0,'Paper'),(2684,0,'Payment'),(2684,0,'Salmon_P._Chase'),(2684,0,'Thomas_Jefferson'),(2684,0,'U.S._Capitol'),(2684,0,'Ulysses_S._Grant'),(2684,0,'United_States'),(2684,0,'United_States_Constitution'),(2684,0,'Value'),(2684,0,'Vending_machine'),(2684,0,'White_House'),(2684,0,'William_McKinley'),(2684,0,'Woodrow_Wilson'),(2685,2,'Angela'),(2685,4,'Username'),(2686,0,'United_States_dollar'),(2687,2,'Angela'),(2687,4,'Useful'),(2803,2,'Angela'),(2803,4,'Useful'),(2805,2,'Angela'),(2809,0,'Avoirdupois'),(2809,0,'Cubic_inch'),(2809,0,'Fluidram'),(2809,0,'Gallon'),(2809,0,'Imperial_unit'),(2809,0,'Ounce'),(2809,0,'U.S._customary_units'),(2809,0,'Volume'),(2809,0,'Water'),(2810,0,'Astrophysics'),(2810,0,'Curve'),(2810,0,'Einstein'),(2810,0,'Euclidean_geometry'),(2810,0,'International_Standard_Book_Number'),(2810,0,'Milky_Way'),(2810,0,'NASA'),(2810,0,'Observable_universe'),(2810,0,'Relativity'),(2810,0,'Special_theory_of_relativity'),(2810,0,'Supercluster'),(2810,0,'Topology'),(2810,0,'Virgo_Supercluster'),(2812,0,'Atmosphere'),(2812,0,'Earth'),(2812,0,'Milky_Way'),(2812,0,'Rock_(geology)'),(2812,4,'Stub'),(2812,14,'Science_stubs'),(2815,0,'Adaptation'),(2815,0,'African_Wildcat'),(2815,0,'Anatomy'),(2815,0,'Anchor'),(2815,0,'Animal'),(2815,0,'Biological_classification'),(2815,0,'Bird'),(2815,0,'Blind'),(2815,0,'Body_language'),(2815,0,'Breed'),(2815,0,'Calico_cat'),(2815,0,'Camel'),(2815,0,'Camouflage'),(2815,0,'Canine_teeth'),(2815,0,'Carnivora'),(2815,0,'Carnivore'),(2815,0,'Carolus_Linnaeus'),(2815,0,'Cathead'),(2815,0,'Cats_(musical)'),(2815,0,'Cheetah'),(2815,0,'Chirp'),(2815,0,'Chordate'),(2815,0,'Clavicle'),(2815,0,'Claw'),(2815,0,'Click'),(2815,0,'Communication'),(2815,0,'Conservation_status'),(2815,0,'Defecate'),(2815,0,'Diagonal'),(2815,0,'Digital_object_identifier'),(2815,0,'Digitigrade'),(2815,0,'Domestication'),(2815,0,'Ernest_Hemingway'),(2815,0,'Europe'),(2815,0,'Family_(biology)'),(2815,0,'Felidae'),(2815,0,'Felis'),(2815,0,'Flea'),(2815,0,'Flexibility'),(2815,0,'Fly'),(2815,0,'Fur'),(2815,0,'Gait'),(2815,0,'Genus'),(2815,0,'Gestation'),(2815,0,'Giraffe'),(2815,0,'Grass'),(2815,0,'Insect'),(2815,0,'Instinct'),(2815,0,'International_Standard_Book_Number'),(2815,0,'International_Standard_Serial_Number'),(2815,0,'Jaguar'),(2815,0,'Leopard'),(2815,0,'Lion'),(2815,0,'List_of_cat_breeds'),(2815,0,'Lynx'),(2815,0,'Mammal'),(2815,0,'Manufacture'),(2815,0,'Mate'),(2815,0,'Meow'),(2815,0,'Mice'),(2815,0,'Millimeter'),(2815,0,'Mummification'),(2815,0,'Mutation'),(2815,0,'Nickname'),(2815,0,'Nutrient'),(2815,0,'Ovulation'),(2815,0,'Penis'),(2815,0,'Pet'),(2815,0,'Polydactyly'),(2815,0,'Pregnant'),(2815,0,'Prey'),(2815,0,'PubMed_Identifier'),(2815,0,'Puma'),(2815,0,'Reflex'),(2815,0,'Releaser'),(2815,0,'Rodents'),(2815,0,'Ship'),(2815,0,'Skull'),(2815,0,'Spine'),(2815,0,'Sugar'),(2815,0,'Tabby'),(2815,0,'Taurine'),(2815,0,'Tiger'),(2815,0,'Trinomen'),(2815,0,'Trot'),(2815,0,'Urinate'),(2815,0,'Vaccine'),(2815,0,'Vagina'),(2815,0,'Verb'),(2815,0,'Vertebrae'),(2815,0,'Veterinarian'),(2815,0,'Vomit'),(2815,0,'Vulva'),(2815,0,'Weaning'),(2815,0,'Woodland'),(2815,0,'Wrist'),(2816,0,'Aerospace_engineering'),(2816,0,'Analysis'),(2816,0,'Biomedical_engineering'),(2816,0,'Building'),(2816,0,'Chemical_engineering'),(2816,0,'Civil_engineering'),(2816,0,'College'),(2816,0,'Computer_engineering'),(2816,0,'Computers'),(2816,0,'Design'),(2816,0,'Doctor_of_Philosophy'),(2816,0,'Electrical_engineering'),(2816,0,'Electricity'),(2816,0,'Electronic_engineering'),(2816,0,'Electronics'),(2816,0,'Engineer'),(2816,0,'Environment'),(2816,0,'Gravity'),(2816,0,'Manufacturing_engineering'),(2816,0,'Math'),(2816,0,'Mechanical_engineering'),(2816,0,'Mechatronics_engineering'),(2816,0,'Multimedia'),(2816,0,'Nanotechnology'),(2816,0,'Natural_disaster'),(2816,0,'Nuclear_engineering'),(2816,0,'Science'),(2816,0,'Software_engineering'),(2816,0,'Structural_engineering'),(2816,0,'Structure'),(2816,0,'Systems_engineering'),(2816,0,'United_Kingdom'),(2816,0,'United_States'),(2816,0,'University'),(2816,0,'Wind'),(2816,0,'Wind_turbines'),(2817,0,'Biology'),(2817,0,'Body_language'),(2817,0,'Border'),(2817,0,'Context'),(2817,0,'Dog'),(2817,0,'Finance'),(2817,0,'Gesture'),(2817,0,'Information'),(2817,0,'Investment'),(2817,0,'Language'),(2817,0,'Linguistics'),(2817,0,'Media'),(2817,0,'Microphone'),(2817,0,'Multimedia'),(2817,0,'Nonverbal_communication'),(2817,0,'Olfaction'),(2817,0,'Propaganda'),(2817,0,'Risk'),(2817,0,'Rudy_Giuliani'),(2817,0,'Shrub'),(2817,0,'Sign_language'),(2817,0,'Smile'),(2817,0,'Sound'),(2817,0,'Touch'),(2817,0,'Tree'),(2817,0,'Writing'),(2819,0,'Angel'),(2819,0,'Authority'),(2819,0,'Avesta'),(2819,0,'Benefit'),(2819,0,'Bible'),(2819,0,'Blind'),(2819,0,'Desert'),(2819,0,'Egypt'),(2819,0,'Elijah'),(2819,0,'Evil'),(2819,0,'Flood'),(2819,0,'Furnace'),(2819,0,'God'),(2819,0,'International_Standard_Book_Number'),(2819,0,'Isaiah'),(2819,0,'Israel'),(2819,0,'Jerusalem'),(2819,0,'Jesus'),(2819,0,'Judgment'),(2819,0,'Laws_of_nature'),(2819,0,'Lazarus'),(2819,0,'Mahabharata'),(2819,0,'Manna'),(2819,0,'Nebuchadnezzar_II'),(2819,0,'Noah'),(2819,0,'Paul_the_Apostle'),(2819,0,'Prophet'),(2819,0,'Quran'),(2819,0,'Red_Sea'),(2819,0,'Shadrach,_Meshach_and_Abednego'),(2819,0,'Throne'),(2819,0,'Vision'),(2820,0,'Coordinated_Universal_Time'),(2827,0,'Internet_slang'),(2832,0,'Ama-no-Uzume'),(2832,0,'Amaterasu'),(2832,0,'Creature'),(2832,0,'Definition'),(2832,0,'Deity'),(2832,0,'Divinity'),(2832,0,'Event'),(2832,0,'Folktale'),(2832,0,'Fox'),(2832,0,'God'),(2832,0,'Goddess'),(2832,0,'Inari_(mythology)'),(2832,0,'Infinity'),(2832,0,'Izanagi'),(2832,0,'Izanami'),(2832,0,'Izumo_Province'),(2832,0,'Japan'),(2832,0,'Japanese_dragon'),(2832,0,'Japanese_folklore'),(2832,0,'Japanese_mythology'),(2832,0,'Kami_(disambiguation)'),(2832,0,'Kappa_(folklore)'),(2832,0,'Kintarō'),(2832,0,'Kitsune'),(2832,0,'Kojiki'),(2832,0,'Kotoamatsukami'),(2832,0,'Legend'),(2832,0,'List_of_divinities_in_Japanese_mythology'),(2832,0,'Momotarō'),(2832,0,'Mount_Fuji'),(2832,0,'Mount_Hiei'),(2832,0,'Myth'),(2832,0,'Nature'),(2832,0,'Nihon_Shoki'),(2832,0,'Oni_(folklore)'),(2832,0,'Otogizōshi'),(2832,0,'Person'),(2832,0,'Phenomenon'),(2832,0,'Ryūgū-jō'),(2832,0,'Ryūjin'),(2832,0,'Sacred'),(2832,0,'Sarutahiko'),(2832,0,'Seven_Lucky_Gods'),(2832,0,'Shinto'),(2832,0,'Shinto_shrine'),(2832,0,'Simple_English_Wiktionary'),(2832,0,'Spirit'),(2832,0,'Storm'),(2832,0,'Sun'),(2832,0,'Susanoo'),(2832,0,'Takamagahara'),(2832,0,'Tamamo-no-Mae'),(2832,0,'Tanuki'),(2832,0,'Tengu'),(2832,0,'Trinity'),(2832,0,'Urashima_Tarō'),(2832,0,'Worship'),(2832,0,'Yomi'),(2832,0,'Yotsuya_Kaidan'),(2832,0,'Yōkai'),(2832,4,'Stub'),(2832,12,'Installing_Japanese_character_sets'),(2832,14,'Japan_stubs'),(2832,14,'Religion_stubs'),(2833,2,'Angela'),(2833,4,'Useful'),(2834,0,'Peer_review'),(2834,4,'Edit_summary'),(2834,4,'NPOV_dispute'),(2834,4,'Verifiability'),(2834,10,'Disputed'),(2835,2,'Angela'),(2835,2,'DavidCary'),(2835,2,'Eptalon'),(2835,2,'Sim'),(2835,2,'There-is-life-on-mars'),(2835,3,'DavidCary'),(2835,3,'Eptalon'),(2835,3,'There-is-life-on-mars'),(2837,0,'Albert_Jones'),(2837,0,'Brooklyn'),(2837,0,'China'),(2837,0,'Durability'),(2837,0,'England'),(2837,0,'Environmentalism'),(2837,0,'John_Harvey_Kellogg'),(2837,0,'New_York_City'),(2837,0,'Paper'),(2837,0,'Patent'),(2837,0,'Robert_Gair'),(2838,0,'Alaska'),(2841,0,'Vulcanicity'),(2842,0,'Vulcanicity'),(2843,0,'Achterhooks'),(2843,0,'Afrikaans'),(2843,0,'Alemannic_German'),(2843,0,'Alemán_Coloniero_dialect'),(2843,0,'Alsatian_language'),(2843,0,'America'),(2843,0,'American_English'),(2843,0,'Anglo-Frisian_languages'),(2843,0,'Anglo-Saxon'),(2843,0,'Arabic_language'),(2843,0,'Australia'),(2843,0,'Australian_English'),(2843,0,'Austro-Bavarian_language'),(2843,0,'Barossa_German'),(2843,0,'Bokmål');
