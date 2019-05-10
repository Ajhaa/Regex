# Toteutus
Tähän toteutukseen on implementoitu konkatenaatio, kleenin tähti, unioni ja ryhmittäminen.

Regex parsitaan ohjelmassa rekursiivisiksi NFA-olioiksi.
Jokainen NFA:olio sisältää alkutilan, sisäkkäisen NFA:n ja lopputilan.

Parsimisen alussa luodaan NFA, joka sisältää alkutilan ja lopputilan. Lopullisessa NFA:ssa on vain yksi lopputila, joka on tämä ensimmäisenä luodun NFA:n lopputila.

Tilat on totetutettu State-olioina. State-olio sisältää kaksi siirtymää, jotka voivat olla epsilon-siirtymiä, normaaleja siirtymiä tai tyhjiä (ei siirtymää). Jokaisella tilalla on siis 0-2 siirtymää, joka riittää Thompsonin algoritmissa.



Koska ohjelmassa ei luoda DFA:ta, parsimisaika on lineaarinen. Tämä kuitenkin aiheuttaa sen, että merkkijonon läpikäymisaika on O(mn), jossa m on regexin pituus, ja n merkkijonon pituus. Läpikäymisajan pituus johtuu siitä, että epsilonsiirtymiä joudutaan simuloimaan rekursiolla siten, että jokainen haara käydään läpi.