# Regex
Toteutan projektina grep-tyylisen säännöllisten lauseiden tulkin, joka perustuu äärellisen automaatin rakentamiseen.  
Tavoitteena on luoda komentorivityökalu, jolle annetaan parametrinä merkkijono tai tiedosto, sekä säännöllinen lause. Ohjelma etsii
Annetusta tekstistä kaikki säännöllistä lauseketta vastaavat kohdat. Kehityksen alkuvaiheessa ohjelma kertoo vain, hyväksyykö
säännöllinen lause yksittäisen annetun merkkijonon.  
  
Toteutuskielenä toimii Java.

# Miksi?
Aihe on kiinnotava, sillä siinä pääsee soveltamaan laskennan mallit- kurssin oppeja. Regex-tulkki on myös hyödyllinen
tosielemän työkalu, jonka toimintaa
haluaisin ymmärtää paremmin



## Aika- ja tilavaativuus
Säännöllisestä lauseesta luodaan äärellinen automaatti, jonka seä aika-, että tilavaativuus on O(2<sup>m</sup>), jossa m on säännöllisen
lauseen pituus. Tämän jälkeen n-pituisen merkkijonon käsittely automaatilla vaatii O(n)-ajan.
Jos koko n-pituinen merkkijono tallennetaan muistiin käsittelyn ajaksi, myös tilavaativuus on O(n). Tutkin kurssin aikana, voiko tätä vähentää.




# Lähteet
[https://en.wikipedia.org/wiki/Regular_expression](https://en.wikipedia.org/wiki/Regular_expression)
