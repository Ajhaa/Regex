# Testaus
Ohjelmassa on kattava yksikkötestaus, testikattavuus on lähes 100%.
Testit testaavat kaikkia implementoituja Regex-ominaisuuksia.

Koska ohjelma testaa vain yhtä merkkijonoa kerrallaan, suurin osa ajasta menee NFA:n parsimiseen,


Parsimisessa kuluu noin satakertaisesti aikaa pidempienkin (~1000 merkkiä) merkkijonojen läpikäyntiin.