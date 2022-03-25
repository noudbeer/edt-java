# edt-java

## Table des matières
1. [Présentation du projet](#Présentation-du-projet)
2. [Vue d'accueil](#Vue-daccueil)
3. [Vue par mois](#Vue-par-mois)
4. [Les technologies utilisées](#Les-technologies-utilisées)
5. [Conclusion du projet](#Conclusion-du-projet)

## Présentation du projet
Le projet edt-java est un projet d'application mobile développé avec le langage de programmation Java.
L'objectif de ce projet est de consulter l'emploi du temps de l'USMB en un clic.

## Vue d'accueil
Lorsque l'on ouvre l'application nous nous rendons directement sur une vue avec un button en haut de l'écran. Celui-ci servira pour etre dirigé vers la seconde vue.  
En dessous de celui-ci, le mois et l'année de la semaine (affichée juste en dessous) est affichée, encadré par deux boutons "PREC." et "SUIV". Ces boutons servent à passer à la semaine précédente et suivante respectivement.  
Ensuite se trouve les jours d'une semaine affichée. Les jours de la semaine peuvent être sélectionnées (par défaut le jour de la date d'aujourd'hui).
Pour finir, l'emploi du temps du jour selectionné est affiché en dessous, par événement (enseignement). Nous retrouvons le détail de son titre, son lieu, son heure de début et son heure de fin.

## Vue par mois
Lorsque l'on appuie suir le bouton "CHOOSE A DATE" en tête de la première vue, la vue par mois s'affiche. Celle-ci est composé d'un bouton "RETOUR" en tête de la vue pour retourner à la vue d'accueil.  
Ensuite nous retrouvons le nom du mois et l'année du mois affiché, encadré par les boutons "PREC." et "SUIV" pour passer au mois précédent et au mois suivant, respectivement.  
Nous retrouvons en dessous un calendier d'un mois. Nous pouvons selectionner une date.
Et pour finir en bas de page, nous avons un bouton valider. Celui-ci permet de retourner sur la page d'accueuil en ouvrant l'emploi du temps du jour selectionné.

## Les technologies utilisées
Comme énoncé dans la [présentation du projet](#Présentation-du-projet), le langage utilisé a été Java. La librairie "[biweekly](https://github.com/mangstadt/biweekly)" a été utilisé pour parser le fichier .ics extrait du site de l'emploi du temps de l'usmb (ADE). 

## Difficultés rencontrées
Une des difficulté rencontrée était de savoir où, dans quel repertoire enregistrer le fichier .ics de l'emploi du temps.
Mais la plus grosse difficulté de ce projet à été de comprendre comment parser ce fichier. En effet même à l'aide de la libraie, il a été compliqué de savoir comment faire. Une fois le fichier parser il a été compliqué de comprendre quelles méthode utilisers pour récupérer les données. En effet, pour récupéré l'heure de début et l'heure de fin d'un événement (VEvent) il fallait utiliser la méthode `getDate()`, ce qui n'est pas intuitif. Une documentation bien fournit, bien structurée et bien expliquée aurait été la bienvenue.  
Ces blocages ont fait prendre du retard au projet.

## Etat d'avancement du projet
Le projet initial était d'avoir les emplois du temps de toute les filières de l'USMB. Malheureusement par manque de temps, pour le moment il n'est possible que de visualiser l'emploi du temps de la filière M1 IME. 
Le futur de se projet serait d'avoir un menu déployable pour pouvoir selectionner l'emplois du temps que l'on souhaiterai visionner, et de pouvoir en mettre un ou plusieurs en favoris, afin d'avoir un accès facile à celui-ci. 

Projet réalisé par Simon Bernoud
