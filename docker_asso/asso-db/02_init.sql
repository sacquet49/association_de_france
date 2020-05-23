--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Debian 12.2-2.pgdg100+1)
-- Dumped by pg_dump version 12.2 (Ubuntu 12.2-2.pgdg18.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: fuzzystrmatch; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS fuzzystrmatch WITH SCHEMA public;


--
-- Name: EXTENSION fuzzystrmatch; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION fuzzystrmatch IS 'determine similarities and distance between strings';


--
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;


--
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE USER u_asso WITH PASSWORD 'u_asso';

--
-- Name: association; Type: TABLE; Schema: public; Owner: u_asso_admin
--

CREATE TABLE public.association (
    id character varying NOT NULL,
    id_ex character varying,
    siret character varying,
    gestion character varying,
    date_creat date,
    date_publi date,
    nature character varying,
    groupement character varying,
    titre character varying,
    objet character varying,
    objet_social1 character varying,
    objet_social2 character varying,
    adr1 character varying,
    adr2 character varying,
    adr3 character varying,
    adrs_codepostal character varying,
    libcom character varying,
    adrs_codeinsee character varying,
    dir_civilite character varying,
    telephone character varying,
    siteweb character varying,
    email character varying,
    observation character varying,
    "position" character varying,
    rup_mi character varying,
    maj_time date
);


ALTER TABLE public.association OWNER TO u_asso_admin;

--
-- Name: association_id_seq; Type: SEQUENCE; Schema: public; Owner: u_asso_admin
--

CREATE SEQUENCE public.association_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.association_id_seq OWNER TO u_asso_admin;

--
-- Name: catergorie; Type: TABLE; Schema: public; Owner: u_asso_admin
--

CREATE TABLE public.catergorie (
    id bigint NOT NULL,
    titre character varying
);


ALTER TABLE public.catergorie OWNER TO u_asso_admin;

--
-- Name: catergorie_id_seq; Type: SEQUENCE; Schema: public; Owner: u_asso_admin
--

CREATE SEQUENCE public.catergorie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.catergorie_id_seq OWNER TO u_asso_admin;

--
-- Name: nouvelle; Type: TABLE; Schema: public; Owner: u_asso_admin
--

CREATE TABLE public.nouvelle (
    id bigint NOT NULL,
    date_creation date,
    description character varying,
    titre character varying
);


ALTER TABLE public.nouvelle OWNER TO u_asso_admin;

--
-- Name: nouvelle_id_seq; Type: SEQUENCE; Schema: public; Owner: u_asso_admin
--

CREATE SEQUENCE public.nouvelle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nouvelle_id_seq OWNER TO u_asso_admin;

--
-- Name: seq_statistique; Type: SEQUENCE; Schema: public; Owner: u_asso_admin
--

CREATE SEQUENCE public.seq_statistique
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_statistique OWNER TO u_asso_admin;

--
-- Name: sous_catergorie; Type: TABLE; Schema: public; Owner: u_asso_admin
--

CREATE TABLE public.sous_catergorie (
    id bigint NOT NULL,
    titre character varying,
    id_cat bigint
);


ALTER TABLE public.sous_catergorie OWNER TO u_asso_admin;

--
-- Name: sous_catergorie_id_seq; Type: SEQUENCE; Schema: public; Owner: u_asso_admin
--

CREATE SEQUENCE public.sous_catergorie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sous_catergorie_id_seq OWNER TO u_asso_admin;

--
-- Name: statistique; Type: TABLE; Schema: public; Owner: u_asso_admin
--

CREATE TABLE public.statistique (
    id bigint NOT NULL,
    departement bigint,
    nombre_association bigint
);


ALTER TABLE public.statistique OWNER TO u_asso_admin;

--
-- Name: users; Type: TABLE; Schema: public; Owner: u_asso_admin
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(25) NOT NULL,
    password character varying(500) NOT NULL,
    is_active boolean NOT NULL,
    roles character varying
);


ALTER TABLE public.users OWNER TO u_asso_admin;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: u_asso_admin
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO u_asso_admin;

--
-- Name: waldec_association; Type: TABLE; Schema: public; Owner: u_asso_admin
--

CREATE TABLE public.waldec_association (
    id character varying NOT NULL,
    id_ex character varying,
    siret character varying,
    rup_mi character varying,
    gestion character varying,
    date_creat date,
    date_decla date,
    date_publi date,
    date_disso date,
    nature character varying,
    groupement character varying,
    titre character varying,
    titre_court character varying,
    objet character varying,
    objet_social1 character varying,
    objet_social2 character varying,
    adrs_complement character varying,
    adrs_numvoie character varying,
    adrs_repetition character varying,
    adrs_typevoie character varying,
    adrs_libvoie character varying,
    adrs_distrib character varying,
    adrs_codeinsee character varying,
    adrs_codepostal character varying,
    adrs_libcommune character varying,
    adrg_declarant character varying,
    adrg_complemid character varying,
    adrg_complemgeo character varying,
    adrg_libvoie character varying,
    adrg_distrib character varying,
    adrg_codepostal character varying,
    adrg_achemine character varying,
    adrg_pays character varying,
    dir_civilite character varying,
    telephone character varying,
    siteweb character varying,
    email character varying,
    publiweb character varying,
    observation character varying,
    "position" character varying,
    maj_time timestamp without time zone
);


ALTER TABLE public.waldec_association OWNER TO u_asso_admin;

--
-- Name: waldec_association_id_seq; Type: SEQUENCE; Schema: public; Owner: u_asso_admin
--

CREATE SEQUENCE public.waldec_association_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.waldec_association_id_seq OWNER TO u_asso_admin;

--
-- Data for Name: association; Type: TABLE DATA; Schema: public; Owner: u_asso_admin
--

COPY public.association (id, id_ex, siret, gestion, date_creat, date_publi, nature, groupement, titre, objet, objet_social1, objet_social2, adr1, adr2, adr3, adrs_codepostal, libcom, adrs_codeinsee, dir_civilite, telephone, siteweb, email, observation, "position", rup_mi, maj_time) FROM stdin;
\.


--
-- Data for Name: catergorie; Type: TABLE DATA; Schema: public; Owner: u_asso_admin
--

COPY public.catergorie (id, titre) FROM stdin;
1000	Activités politiques
2000	Clubs, cercles de réflexion
3000	Défense de droits fondamentaux, activités civiques
4000	Justice
5000	Information communication
6000	Culture, pratiques d'activités artistiques, culturelles
7000	Clubs de loisirs, relations
9000	Action socio-culturelle
10000	Préservation du patrimoine
11000	Sports, activités de plein air
13000	Chasse pêche
14000	Amicales, groupements affinitaires, groupements d'entraide (hors défense de droits fondamentaux
15000	Éducation formation
16000	recherche
17000	Santé
18000	Services et établissements médico-sociaux
19000	Interventions sociales
20000	Associations caritatives, humanitaires, aide au développement, développement du bénévolat
21000	Services familiaux, services aux personnes âgées
22000	Conduite d'activités économiques
23000	représentation, promotion et défense d'intérêts économiques
24000	Environnement, cadre de vie
30000	aide à l'emploi, développement local, promotion de solidarités économiques, vie locale
32000	logement
34000	Tourisme
36000	sécurité, protection civile
38000	armée (dont préparation militaire, médailles)
40000	activités religieuses, spirituelles ou philosophiques
50000	domaines divers, domaines de nomenclature SITADELE à reclasser
\.


--
-- Data for Name: nouvelle; Type: TABLE DATA; Schema: public; Owner: u_asso_admin
--

COPY public.nouvelle (id, date_creation, description, titre) FROM stdin;
1	2018-04-15	Bienvenue sur l''annuaire des associations de France, le site se divise en deux parties. Les associations créées et non modifier depuis 2009. Une seconde partie pour les associations modifiées et créer après 2009. Bonne visite sur le site.	Bienvenu à toi visiteur
2	2018-05-03	Mise à jour des données, import mensuel.	Mise à jour
\.


--
-- Data for Name: sous_catergorie; Type: TABLE DATA; Schema: public; Owner: u_asso_admin
--

COPY public.sous_catergorie (id, titre, id_cat) FROM stdin;
1005	associations à caractère politique général	1000
1010	soutien et financement de partis et de campagnes électorales	1000
1015	action politique locale	1000
1020	action politique globale	1000
1025	activités citoyennes européennes	1000
2005	associations philanthropiques	2000
2010	amicales laïques	2000
2015	clubs de réflexion	2000
2020	organisation de conférences	2000
3010	défense de la paix	3000
3012	défense des droits des enfants	3000
3015	défense des libertés publiques et des droits de l'Homme	3000
3020	défense des droits des femmes, condition féminine	3000
3025	défense des droits des personnes homosexuelles	3000
3030	défense des droits des personnes en situation de handicap	3000
3035	association pour la défense de droits de minorités	3000
3040	lutte contre les discriminations	3000
3045	défense des droits des personnes rapatriées	3000
3050	défense des droits des personnes étrangères ou immigrées, de personnes réfugiées	3000
3060	activités civiques, information civique	3000
4010	médiation, prévention	4000
4020	contrôle judiciaire, associations de personnels de justice	4000
4025	accès aux droits dans les tribunaux, assistance juridique	4000
4030	défense des droits des victimes	4000
4035	maisons du droit, accès au droit	4000
5005	presse, édition	5000
5010	radios privées	5000
5015	audiovisuel	5000
5020	réseaux internet	5000
5025	autres supports de communication	5000
5030	auditeurs, consommateurs d'outils d'information et de communication	5000
5035	professionnels de l'information et de communication	5000
6005	bibliothèques, ludothèques, discothèques, vidéothèques	6000
6010	expression écrite, littérature, poésie	6000
6020	arts graphiques, bande dessinée, peinture, sculpture, architecture	6000
6025	photographie, cinéma (dont ciné-clubs)	6000
6030	chant choral, musique	6000
6040	danse	6000
6045	folklore	6000
6070	théâtre, marionnettes, cirque, spectacles de variété	6000
6090	artisanat, travaux manuels, bricolage, expositions	6000
6100	promotion de l'art et des artistes	6000
6105	loisirs scientifiques et techniques	6000
6110	langues, dialectes, patois	6000
6115	arts de la rue	6000
7002	aéroclubs	7000
7003	modélisme	7000
7005	bridge, jeux de cartes, échecs, dames, jeux de société...	7000
7010	billard, quilles	7000
7025	clubs de collectionneurs (hors sauvegarde, entretien du patrimoine), philatélie, numismatique	7000
7030	collectionneurs de véhicules, clubs amateurs de voitures anciennes	7000
7035	cercles privés, fan-clubs	7000
7040	activités festives (soirées…)	7000
7045	élevage canin, clubs de chiens de défense	7000
7050	animaux familiers, colombophilie, aquariophilie	7000
7060	gastronomie, oenologie, confréries, gourmets	7000
7070	jardins ouvriers, floralies	7000
7075	échanges locaux, réseaux d'échanges	7000
7080	centres de loisirs, clubs de loisirs multiples	7000
7085	relaxation, sophrologie	7000
7095	radioamateurs	7000
9005	maisons de jeunes, foyers, clubs de jeunes	9000
9007	maisons de la culture, office municipal, centres culturels	9000
9010	loisirs pour personnes en situation de handicap	9000
9015	associations socio-éducatives, scoutisme	9000
9020	centres aérés, colonies de vacances	9000
9025	mouvements éducatifs de jeunesse et d'éducation populaire	9000
9030	comités des fêtes	9000
9035	foyers ruraux	9000
9040	clubs du troisième âge	9000
9045	majorettes, twirlings, carnavals, défilés	9000
9050	jumelages, échanges culturels, organisation d'échanges linguistiques, échanges culturels au plan international	9000
10005	collections d'objets, de documents, bibliothèques spécialisées pour la sauvegarde et l'entretien du patrimoine	10000
10010	musées, collections historiques	10000
10015	associations, sociétés savantes pour des études historiques, histoire du patrimoine	10000
10017	sociétés, clubs de généalogie	10000
10020	commémorations, entretien de monuments et sites historiques, souvenir militaire	10000
10022	comités de défense du patrimoine	10000
10030	construction de monuments (sauf lieux de culte)	10000
11004	arbitrage	11000
11005	associations multisports locales	11000
11010	associations multisports scolaires ou universitaires	11000
11015	associations multisports d'entreprise	11000
11018	handisport	11000
11020	Athlétisme (triathlon, pentathlon, footing, jogging)	11000
11025	Aviron, canoë kayak (aviron, rafting, canoë kayak, joutes)	11000
11030	Badminton (badminton, squash, pelote basque)	11000
11035	Boules (pétanque, boules)	11000
11040	Bowling	11000
11045	Danse sportive (danse sportive, hip hop, claquettes)	11000
11050	Equitation (équitation, hippisme, course camarguaise, landaise)	11000
11055	Escalade, montagne (escalade, spéléologie, via ferrata, canyonisme, alpinisme)	11000
11060	Escrime	11000
11065	Basket-ball	11000
11070	Handball	11000
11075	Football (football, futsal)	11000
11080	Rugby (rugby à 13, à 15)	11000
11085	Volley ball (volley, beach volley)	11000
11090	Autres sports collectifs (baseball, hockey sur glace, football américain)	11000
11092	hockey sur glace, sports de glace	11000
11095	nautisme, glisse sur eau (ski nautique, surf, char à voile)	11000
11100	Golf	11000
11105	Gymnastique (gymnastique, gymnastique d'entretien, éducation physique, yoga), aérobic	11000
11110	Haltérophilie	11000
11115	Marche sportive (randonnée pédestre, raid, trekking, course orientation)	11000
11120	Musculation (culturisme, musculation)	11000
11125	Natation - Baignade (natation, plongée)	11000
11130	Roller, skate	11000
11135	Sports aériens (avion, planeur, ULM, parachutisme)	11000
11140	Judo	11000
11145	Sports de combat (boxe, kick box, boxe thaï, lutte)	11000
11150	Autres arts martiaux (karaté, aïkido, taekwondo)	11000
11155	Sports de neige (ski alpin, ski de fond, snowboard) , montagne	11000
11160	Sports mécaniques (sport automobile, moto, trial)	11000
11165	Tennis (tennis, longue paume)	11000
11170	Tennis de table (tennis de table, ping-pong)	11000
11175	Tir (tir à l’arc, tir à balle, ball trap), javelot	11000
11180	Cyclisme (cyclisme, vélo, VTT, y c course d'orientation à vélo, cyclotourisme)	11000
11185	Voile (voile, dériveur, planche à voile)	11000
11190	gestion d'équipements sportifs, organisation de rencontres sportives, organisation de championnats, clubs de supporters	11000
11192	associations pour la promotion du sport, médailles, mérite sportif	11000
11400	activités de plein air (dont saut à l'élastique)	11000
13005	chasse	13000
13010	pêche	13000
14025	organisation de professions (hors caractère syndical)	14000
14030	association du personnel d'une entreprise (hors caractère syndical)	14000
14035	groupements d'entraide et de solidarité	14000
14040	amicale de personnes originaires d'un même pays (hors défense des droits des étrangers)	14000
14045	amicale de personnes originaires d'une même région	14000
14050	associations féminines pour l'entraide et la solidarité (hors défense de droits fondamentaux)	14000
14060	associations de personnes homosexuelles pour l'entraide et la solidarité (hors défense de droits fondamentaux)	14000
14070	associations de personnes en situation de handicap pour l'entraide et la solidarité (hors défense de droits fondamentaux)	14000
14080	associations de classe d'âge	14000
15005	parents d'élèves	15000
15010	organisation de professions enseignantes, amicales de personnel	15000
15025	associations périscolaires, coopération, aide à l'enseignement	15000
15030	œuvres sociales en faveur des élèves, œuvres en faveur pupilles de la nation	15000
15035	organisme de gestion d'établissement d'enseignement général et technique	15000
15040	organisme de gestion d'établissement d'enseignement supérieur	15000
15045	établissement de formation professionnelle, formation continue	15000
15050	centre d'enseignement et de formation	15000
15065	associations d'étudiants, d'élèves	15000
15070	amicales, associations d'anciens étudiants, d'anciens élèves	15000
15075	amicales, personnel d'établissements scolaires ou universitaires	15000
15085	organisation, financement de voyages d'études, d'échanges, pour scolaires ou universitaires	15000
15087	études et formations linguistiques	15000
15090	promotion de titres, de diplômes	15000
15100	apprentissage	15000
15105	maisons familiales rurales	15000
16005	recherche sur l’éducation et la formation	16000
16010	recherche sur la culture	16000
16015	recherche sur la vie sociale et politique	16000
16025	recherche sur l'environnement et le climat	16000
16030	association de recherches scientifiques, sciences physiques, sciences humaines…	16000
16050	autres associations de recherche	16000
16080	diffusion de savoirs, sociétés savantes ou académiques	16000
17005	cliniques, centres médicaux, hôpitaux, sanatoriums, établissements de rééducation, maisons de convalescence	17000
17015	hôpitaux psychiatriques, soins ambulatoires en santé mentale	17000
17020	dispensaires, soins infirmiers, services paramédicaux, de garde	17000
17025	services médicaux d'urgence	17000
17045	centres de réadaptation	17000
17055	accompagnement, aide aux malades	17000
17065	don de sang, d'organes	17000
17075	gestion de matériel médical	17000
17085	hygiène, diététique	17000
17095	accueil, information pour contraception et avortement	17000
17105	médecine du travail	17000
17115	prévention et dépistage du sida	17000
17120	éducation sanitaire, prévention générale	17000
17125	prévention et dépistage de maladies (autres que le sida)	17000
17130	associations de personnes malades, ou anciens malades	17000
17135	homéopathie, médecines douces	17000
17145	organisation de professions médicales ou paramédicales	17000
17155	organisation de congrès médicaux	17000
17200	recherche médicale	17000
17210	financement de la recherche médicale	17000
17300	médecine animale, vétérinaire	17000
18005	accueil et protection de la petite enfance	18000
18010	établissements et services pour adolescents en difficulté	18000
18015	établissements, services pour personnes handicapées (y c C.A.T)	18000
18025	établissements et services pour adultes en difficulté, CHRS  (centres d'hébergement et de réadaptation sociale)	18000
18030	prévention et lutte contre l'alcoolisme, la toxicomanie	18000
18040	aide aux accidentés du travail	18000
18045	aide aux victimes de maladies professionnelles	18000
18050	aide sociale aux personnes en situation de handicap	18000
19004	aide et conseils aux familles	19000
19005	associations familiales, services sociaux pour les familles	19000
19010	centres sociaux et socioculturels, foyers de jeunes travailleurs, centres d'études et d'action sociale	19000
19012	lutte contre le surendettement	19000
19014	lutte contre l’illettrisme	19000
19016	aide à l'insertion des jeunes	19000
19020	groupements de chômeurs, aide aux chômeurs	19000
19025	aide aux réfugiés et aux immigrés (hors droits fondamentaux)	19000
19030	aide aux victimes de calamités, de catastrophes naturelles	19000
19032	aide aux victimes de violences conjugales	19000
19035	aide aux victimes de violences faites aux enfants	19000
19040	aide aux personnes en danger, solitude, désespoir, soutien psychologique et moral	19000
19042	lutte contre la violence routière	19000
19045	lutte contre diverses formes de violence	19000
19047	foyers socio-éducatifs	19000
19050	réinsertion des délinquants	19000
19055	soutien, reclassement des détenus	19000
20005	secours financiers et autres services aux personnes en difficulté	20000
20010	secours en nature, distribution de nourriture et de vêtements	20000
20015	associations caritatives à buts multiples	20000
20020	associations caritatives intervenant au plan international	20000
20025	développement du bénévolat	20000
21005	crèches, garderies, haltes garderies	21000
21010	aide à domicile	21000
21015	services aux personnes âgées (téléalarme...)	21000
21020	foyers pour personnes âgées, maisons de retraite,  maisons de retraite médicalisées	21000
22510	cantines, restaurants d'entreprises	22000
22515	centres de gestion, centres juridiques, audits	22000
22520	gestion financière, gestion immobilière	22000
22525	études techniques	22000
22530	groupement d'achats, groupement d'entreprises	22000
22535	amicales de commerçants, organisation de foires	22000
22540	chambres de commerce, chambres économiques	22000
22542	association à but commercial, développement économique	22000
22543	transports	22000
22545	caisses de retraite, de prévoyance, de pensions	22000
22550	caisses de congés payés, caisses de secours	22000
23001	usagers de services publics	23000
23002	mouvements de consommateurs	23000
23003	défense des contribuables	23000
23004	actionnaires, épargnants	23000
23005	groupements de salariés à caractère syndical	23000
23007	groupements professionnels	23000
23010	associations de défense d'intérêts des retraités ou des personnes âgées	23000
23020	associations d'exploitants agricoles, élevage, horticulture, aviculture, apiculture, viticulture, viniculture	23000
23022	associations d'intérêts maritimes, marins	23000
23025	associations pour la représentation d'artisans, de commerçants	23000
23030	unions patronales	23000
23035	association de représentation de professions libérales	23000
23040	représentation d'intérêts économiques sectoriels	23000
23045	représentation d'intérêts régionaux et locaux	23000
24005	pollutions, assainissement	24000
24010	ressources naturelles	24000
24015	espaces naturels	24000
24020	protection de sites naturels	24000
24025	préservation de la faune sauvage	24000
24026	protection des animaux	24000
24030	préservation de la flore sauvage	24000
24035	comités de défense, de sauvegarde	24000
24040	mouvements écologiques	24000
24045	défense et amélioration du cadre de vie	24000
24050	actions de sensibilisation et d'éducation à l'environnement et au développement durable	24000
30005	Comité, défense d'un emploi	30000
30010	entreprises d'insertion, associations intermédiaires, régies de quartier	30000
30012	comités de défense et d'animation de quartier, association locale ou municipale	30000
30015	groupement d'employeurs	30000
30020	aide à la création d'activités économiques individuelles	30000
30050	promotion d'initiatives de développement durable	30000
32510	aide au logement	32000
32520	associations et comités de locataires, de propriétaires, comités de logement	32000
32525	réhabilitation et construction de logements	32000
34210	auberges de jeunesse, organisation de voyages	34000
34220	maisons et villages de vacances	34000
34230	gîtes ruraux, camping, caravaning, naturisme	34000
34240	syndicats d'initiative, offices de tourisme, salons du tourisme	34000
36510	amicale de sapeurs pompiers	36000
36520	sauvetage, secourisme, protection civile	36000
36530	prévention, formation, cours de secourisme	36000
36535	sécurité routière	36000
36540	sauvetage en mer	36000
36545	sécurité et sauvetage en montagne	36000
38105	anciens combattants	38000
38110	associations de militaires, amicales, associations de conscrits	38000
\.


--
-- Data for Name: statistique; Type: TABLE DATA; Schema: public; Owner: u_asso_admin
--

COPY public.statistique (id, departement, nombre_association) FROM stdin;
219	81	11378
284	89	9887
249	47	9316
218	67	18
254	75	69835
231	23	3849
206	68	3
283	66	13490
257	57	9
272	52	6489
261	38	30310
275	8	6151
209	84	14776
260	55	6566
271	98	15257
220	22	13410
251	74	15568
234	7	9734
224	82	6787
287	40	11590
243	69	49509
226	28	11546
263	58	7305
262	48	3173
264	1	16433
210	92	24003
286	2	10095
294	70	9447
211	44	29346
205	65	7091
280	86	13383
282	63	16965
250	80	24952
242	50	14645
197	26	14900
201	15	6467
223	88	9345
267	9	6250
288	13	51676
198	17	17343
237	72	15236
229	54	16192
291	42	18544
246	3	10651
293	16	9369
259	37	18804
244	59	60762
266	60	15980
216	5	5981
213	31	34811
232	87	13446
230	36	7673
208	62	33922
207	94	23908
281	43	9138
252	6	21255
248	25	10661
199	0	91
240	91	20631
258	64	18965
204	21	13792
277	76	26104
233	35	24910
215	83	26730
222	61	7575
212	20	10351
214	12	11292
273	85	16129
253	41	8425
202	77	29219
285	34	33735
236	18	7732
245	4	6485
221	45	20694
265	49	24692
228	79	9815
227	97	50104
276	30	21669
268	93	24626
256	11	11918
274	24	13401
200	71	16752
279	53	8071
269	33	38278
239	73	10291
225	32	7121
247	14	18975
255	27	10115
217	95	19385
278	29	27147
270	10	6218
292	90	2845
235	39	9609
290	51	11024
203	56	17987
289	78	23992
238	46	7869
241	19	6959
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: u_asso_admin
--

COPY public.users (id, username, password, is_active, roles) FROM stdin;
4	admin	$2y$13$vYmaCUe1DkuCqd1fa6RdFu29ILk2kleyjjEyMX7k6vqpkiy5FO8KG	t	ROLE_SUPER_ADMIN
5	consultant	$2y$13$x6g9GBreClWSmUNguJD5.u2Ydmb8MtCJVPW7lcEAh47vM1Q.CjlmS	t	ROLE_USER_CLASSIC
\.


--
-- Data for Name: waldec_association; Type: TABLE DATA; Schema: public; Owner: u_asso_admin
--

COPY public.waldec_association (id, id_ex, siret, rup_mi, gestion, date_creat, date_decla, date_publi, date_disso, nature, groupement, titre, titre_court, objet, objet_social1, objet_social2, adrs_complement, adrs_numvoie, adrs_repetition, adrs_typevoie, adrs_libvoie, adrs_distrib, adrs_codeinsee, adrs_codepostal, adrs_libcommune, adrg_declarant, adrg_complemid, adrg_complemgeo, adrg_libvoie, adrg_distrib, adrg_codepostal, adrg_achemine, adrg_pays, dir_civilite, telephone, siteweb, email, publiweb, observation, "position", maj_time) FROM stdin;
\.


--
-- Name: association_id_seq; Type: SEQUENCE SET; Schema: public; Owner: u_asso_admin
--

SELECT pg_catalog.setval('public.association_id_seq', 1, false);


--
-- Name: catergorie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: u_asso_admin
--

SELECT pg_catalog.setval('public.catergorie_id_seq', 1, false);


--
-- Name: nouvelle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: u_asso_admin
--

SELECT pg_catalog.setval('public.nouvelle_id_seq', 16, true);


--
-- Name: seq_statistique; Type: SEQUENCE SET; Schema: public; Owner: u_asso_admin
--

SELECT pg_catalog.setval('public.seq_statistique', 294, true);


--
-- Name: sous_catergorie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: u_asso_admin
--

SELECT pg_catalog.setval('public.sous_catergorie_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: u_asso_admin
--

SELECT pg_catalog.setval('public.users_id_seq', 5, true);


--
-- Name: waldec_association_id_seq; Type: SEQUENCE SET; Schema: public; Owner: u_asso_admin
--

SELECT pg_catalog.setval('public.waldec_association_id_seq', 1, false);


--
-- Name: association association_pkey; Type: CONSTRAINT; Schema: public; Owner: u_asso_admin
--

ALTER TABLE ONLY public.association
    ADD CONSTRAINT association_pkey PRIMARY KEY (id);


--
-- Name: catergorie catergorie_pkey; Type: CONSTRAINT; Schema: public; Owner: u_asso_admin
--

ALTER TABLE ONLY public.catergorie
    ADD CONSTRAINT catergorie_pkey PRIMARY KEY (id);


--
-- Name: nouvelle nouvelle_pkey; Type: CONSTRAINT; Schema: public; Owner: u_asso_admin
--

ALTER TABLE ONLY public.nouvelle
    ADD CONSTRAINT nouvelle_pkey PRIMARY KEY (id);


--
-- Name: sous_catergorie sous_catergorie_pkey; Type: CONSTRAINT; Schema: public; Owner: u_asso_admin
--

ALTER TABLE ONLY public.sous_catergorie
    ADD CONSTRAINT sous_catergorie_pkey PRIMARY KEY (id);


--
-- Name: statistique statistique_pkey; Type: CONSTRAINT; Schema: public; Owner: u_asso_admin
--

ALTER TABLE ONLY public.statistique
    ADD CONSTRAINT statistique_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: u_asso_admin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: waldec_association waldec_association_pkey; Type: CONSTRAINT; Schema: public; Owner: u_asso_admin
--

ALTER TABLE ONLY public.waldec_association
    ADD CONSTRAINT waldec_association_pkey PRIMARY KEY (id);


--
-- Name: idx2_waldec_asso; Type: INDEX; Schema: public; Owner: u_asso_admin
--

CREATE INDEX idx2_waldec_asso ON public.waldec_association USING btree (objet_social1, objet_social2);


--
-- Name: idx_cp; Type: INDEX; Schema: public; Owner: u_asso_admin
--

CREATE INDEX idx_cp ON public.association USING btree (adrs_codepostal);


--
-- Name: idx_waldec_asso; Type: INDEX; Schema: public; Owner: u_asso_admin
--

CREATE INDEX idx_waldec_asso ON public.waldec_association USING btree (adrs_codepostal);


--
-- Name: uniq_1483a5e9f85e0677; Type: INDEX; Schema: public; Owner: u_asso_admin
--

CREATE UNIQUE INDEX uniq_1483a5e9f85e0677 ON public.users USING btree (username);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: u_asso_admin
--

GRANT USAGE ON SCHEMA public TO u_asso;


--
-- Name: FUNCTION difference(text, text); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.difference(text, text) TO u_asso;


--
-- Name: FUNCTION dmetaphone(text); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.dmetaphone(text) TO u_asso;


--
-- Name: FUNCTION dmetaphone_alt(text); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.dmetaphone_alt(text) TO u_asso;


--
-- Name: FUNCTION levenshtein(text, text); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.levenshtein(text, text) TO u_asso;


--
-- Name: FUNCTION levenshtein(text, text, integer, integer, integer); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.levenshtein(text, text, integer, integer, integer) TO u_asso;


--
-- Name: FUNCTION levenshtein_less_equal(text, text, integer); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.levenshtein_less_equal(text, text, integer) TO u_asso;


--
-- Name: FUNCTION levenshtein_less_equal(text, text, integer, integer, integer, integer); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.levenshtein_less_equal(text, text, integer, integer, integer, integer) TO u_asso;


--
-- Name: FUNCTION metaphone(text, integer); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.metaphone(text, integer) TO u_asso;


--
-- Name: FUNCTION soundex(text); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.soundex(text) TO u_asso;


--
-- Name: FUNCTION text_soundex(text); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.text_soundex(text) TO u_asso;


--
-- Name: FUNCTION unaccent(text); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.unaccent(text) TO u_asso;


--
-- Name: FUNCTION unaccent(regdictionary, text); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.unaccent(regdictionary, text) TO u_asso;


--
-- Name: FUNCTION unaccent_init(internal); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.unaccent_init(internal) TO u_asso;


--
-- Name: FUNCTION unaccent_lexize(internal, internal, internal, internal); Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON FUNCTION public.unaccent_lexize(internal, internal, internal, internal) TO u_asso;


--
-- Name: TABLE association; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.association TO u_asso;


--
-- Name: SEQUENCE association_id_seq; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON SEQUENCE public.association_id_seq TO u_asso;


--
-- Name: TABLE catergorie; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.catergorie TO u_asso;


--
-- Name: SEQUENCE catergorie_id_seq; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON SEQUENCE public.catergorie_id_seq TO u_asso;


--
-- Name: TABLE nouvelle; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.nouvelle TO u_asso;


--
-- Name: SEQUENCE nouvelle_id_seq; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON SEQUENCE public.nouvelle_id_seq TO u_asso;


--
-- Name: SEQUENCE seq_statistique; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON SEQUENCE public.seq_statistique TO u_asso;


--
-- Name: TABLE sous_catergorie; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.sous_catergorie TO u_asso;


--
-- Name: SEQUENCE sous_catergorie_id_seq; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON SEQUENCE public.sous_catergorie_id_seq TO u_asso;


--
-- Name: TABLE statistique; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.statistique TO u_asso;


--
-- Name: TABLE users; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.users TO u_asso;


--
-- Name: SEQUENCE users_id_seq; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON SEQUENCE public.users_id_seq TO u_asso;


--
-- Name: TABLE waldec_association; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.waldec_association TO u_asso;


--
-- Name: SEQUENCE waldec_association_id_seq; Type: ACL; Schema: public; Owner: u_asso_admin
--

GRANT ALL ON SEQUENCE public.waldec_association_id_seq TO u_asso;


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: u_asso_admin
--

ALTER DEFAULT PRIVILEGES FOR ROLE u_asso_admin IN SCHEMA public REVOKE ALL ON SEQUENCES  FROM u_asso_admin;
ALTER DEFAULT PRIVILEGES FOR ROLE u_asso_admin IN SCHEMA public GRANT ALL ON SEQUENCES  TO u_asso;


--
-- Name: DEFAULT PRIVILEGES FOR FUNCTIONS; Type: DEFAULT ACL; Schema: public; Owner: u_asso_admin
--

ALTER DEFAULT PRIVILEGES FOR ROLE u_asso_admin IN SCHEMA public REVOKE ALL ON FUNCTIONS  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE u_asso_admin IN SCHEMA public REVOKE ALL ON FUNCTIONS  FROM u_asso_admin;
ALTER DEFAULT PRIVILEGES FOR ROLE u_asso_admin IN SCHEMA public GRANT ALL ON FUNCTIONS  TO u_asso;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: u_asso_admin
--

ALTER DEFAULT PRIVILEGES FOR ROLE u_asso_admin IN SCHEMA public REVOKE ALL ON TABLES  FROM u_asso_admin;
ALTER DEFAULT PRIVILEGES FOR ROLE u_asso_admin IN SCHEMA public GRANT SELECT,INSERT,DELETE,UPDATE ON TABLES  TO u_asso;


--
-- PostgreSQL database dump complete
--

