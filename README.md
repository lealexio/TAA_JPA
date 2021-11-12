# TAA TP1 JPA

## Template de projet pour le TP JPA 2021 UniR

Nous avons initialisé le projet depuis celui donné.

## Question 0 : Regardez rapidement le pom.xml, vous constaterez que c’est un projet simple avec deux dépendances (hibernate et hsqldb (driver jdbc pour hsqldb)).

 * **Hibernate** est un framework open source gérant la persistance des objets en base de données relationnelle. 
 * **Hsqldb** système de gestion de base de données relationnelle.
 * **Jdbc** est un interface pour Hsqldb

## Question 1 : Transformez  une première classe en entité. 

Nous avons creer quatres classes entités pour représenter le contexte, voici l'UML :
![UML](https://i.ibb.co/LZGQztY/Sans-titre-2021-09-16-1020.png) 

@Id et @GeneratedValue(strategy = GenerationType.IDENTITY) permet de definir l'Id utilisé pour la BDD.


## Question 2 : Même travail avec une première association entre deux entités.

Professional et individual étendent User.

User prend alors un statégie, ici une table sera creer pour tout les user ainsi que les classes que l'étendent:

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

## Question 3 : Finissez le modèle métier que vous aurez défini pour le projet. Intégrer une classe de DAO permettant de peupler la base mais aussi de faire des requêtes sur la base de données.

Le package dao contient les dao pour les différentes entités.

## Question 4 : Connexion à une base mysql.

Apres l'ajout de la dépendance maven, il a suffit de completer les parametres de la BDD ainsi que sa version dans le persistence.xml.

## Question 5 : Question 5. Portez votre application et gérer au minimum une relation d’héritage, les requêtes, une requête nommée. 

Professional et Individual heritent de User.

Des requetes et NamedQueries ont ete definies dans les Dao.

L'interface GenericDao defini les methodes principales, celles-ci sont implementées par AsbstractDao puis par la classe Dao cible.

## Question 6 : Mise en évidence du problème de n+1 select. 

Durée d'execution n+1 : 2674

Durée d'execution joinFetch : 304

Avec un joinFetch la durée d'execution est 10x plus faible.
